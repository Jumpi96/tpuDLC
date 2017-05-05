/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexacion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juampilorenzo
 */
public class CargaArchivos {
    
    private HashMap<String,Palabra> vocabulario;
    private String origen;

    public CargaArchivos(String origen) {
        this.origen = origen;
        vocabulario=new HashMap<String,Palabra>();
    }
    
    
    public void procesarArchivos(){
        Documento[] documentos=listarArchivos();
        for (int i = 0; i < documentos.length; i++) {
            procesarDocumento(documentos[i]);
        }
    }
    
    private Documento[] listarArchivos(){
        File f = new File(origen);
        File[] archivos;
        Documento[] documentos=null;
                
        if (f.exists()){
            archivos = f.listFiles();
            documentos=new Documento[archivos.length];
            for (int i = 0; i < archivos.length; i++) {
                documentos[i]=nuevoDocumento(archivos[i].getPath());
            }
        }
        return documentos;        
    }
    
    public boolean procesarDocumento(Documento documento){
        if (!documento.isProcesado()) {
            HashMap <String,AparicionPalabra> nuevoHash=contarPalabras(documento);
            actualizarVocabulario(documento,nuevoHash);
            return true;
        }
        else
            return false;
    }
    
    public void procesar(){
        Documento[] documentos=listarArchivos();
        for (int i = 0; i < documentos.length; i++) {
            procesarDocumento(documentos[i]);
        }
 
    }
    
    private HashMap<String,AparicionPalabra> contarPalabras(Documento documento){
        BufferedReader br;
        StringSimbolizador st;
        String linea,cont;
        Palabra p;
        String origen=documento.getArchivo().getPath();
        HashMap <String,AparicionPalabra> hash=new HashMap();
        Charset inputCharset = Charset.forName("ISO-8859-1");
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(origen), inputCharset));
            linea=br.readLine();
            while(true){
                if(linea.equals("")==false){
                    st=new StringSimbolizador(linea);
                    while(st.hasMoreTokens()){
                        cont=st.nextToken();
                        if(cont!=null)
                        {
                            if(hash.containsKey(cont))                                
                                hash.get(cont).contar();
                            else{
                                if(vocabulario.containsKey(cont)){
                                    p=vocabulario.get(cont);
                                    hash.put(cont,new AparicionPalabra(p,documento));
                                }
                                else{
                                    p=nuevaPalabra(cont);
                                    vocabulario.put(cont,p);
                                    hash.put(cont,new AparicionPalabra(p,documento));
                                }
                            }
                        }
                    }
                }   
                linea=br.readLine();
            }
        } 
        catch (NullPointerException ex) {} 
        catch (IOException ex) {
            Logger.getLogger(GestorProcesamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {}
        return hash;
    }
    
    /*
    Graba apariciones en el archivo procesado en la BD y actualiza el Heap
    que contiene el vocabulario en memoria.
    Hace ambas cosas en la misma iteración para evitar duplicar el tiempo de
    ejecución. Realiza INSERT múltiples a través del procesamiento de lotes.
    Utiliza PreparedStatements para mejorar funcionamiento y evitar errores.
    Trabaja con transacciones dado optimización de SQLite en esos casos.
    Trabaja con lotes de hasta 1000 palabras para evitar problemas de memoria.
    */
    private void actualizarVocabulario(Documento documento,HashMap<String,AparicionPalabra> hash){       
        Set<String> s=hash.keySet();
        String origen=documento.getArchivo().getPath();
        Iterator it = s.iterator();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:vocabulario");
            Statement trans=conn.createStatement();
            //String consulta="INSERT INTO palabras (contenido,repeticiones,origen) VALUES (?,?,'"+origen+"')";
            String consulta="INSERT INTO PalabrasXDocumento (idPalabra,idDocumento,contador) VALUES ("
                    + "?,"+documento.getIdDocumento()+",?)";
            PreparedStatement st=conn.prepareStatement(consulta);
            String palabra;
            int[] temp;
            int tope;
            trans.execute("BEGIN");
            for(int contador=0;contador*1000<s.size();contador++){    
                if ((contador+1)*1000<s.size())
                    tope=1000;
                else
                    tope=s.size()-contador*1000;
                for (int i = 0; i < tope; i++) {
                    palabra=(String)it.next();
//                    if (!vocabulario.containsKey(palabra))
//                        vocabulario.put()
//                        hashCompleto.put(palabra,new int[] {hash.get(palabra),1});
//                    else{   
//                        temp=hashCompleto.get(palabra);
//                        hashCompleto.put(palabra,new int[]{temp[0]+hash.get(palabra),temp[1]+1});
//                    }
                    st.setInt(1, hash.get(palabra).getPalabra().getIdPalabra()); //mal
                    st.setInt(2, hash.get(palabra).getContador());
                    st.addBatch();
                }
                st.executeBatch();
            }
            trans.execute("END");
            trans.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorProcesamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private Palabra nuevaPalabra(String palabra){
        int id=0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:vocabulario");
            String consulta="INSERT INTO Palabras (palabra) VALUES ('"+palabra+"')";
            PreparedStatement st=conn.prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS);
            
            st.executeUpdate();
            
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id=generatedKeys.getInt(1);
                }
            }
            st.close();
            
            //probar si devuelve id, si no agregar codigo
            /*consulta="SELECT idPalabra FROM Palabras WHERE palabra LIKE "
            PreparedStatement q=con.prepareStatement(consulta);*/
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorProcesamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Palabra(palabra,id);
    }
    
    private Documento nuevoDocumento(String origen){
        int id=0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:vocabulario");
            String consulta="INSERT INTO Documentos (origen) VALUES ('"+origen+"')";
            PreparedStatement st=conn.prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS);
            
            st.executeUpdate();
            
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id=generatedKeys.getInt(1);
                }
            }
            
            st.close();
            
            //probar si devuelve id, si no agregar codigo
            /*consulta="SELECT idPalabra FROM Palabras WHERE palabra LIKE "
            PreparedStatement q=con.prepareStatement(consulta);*/
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorProcesamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Documento(new File(origen),id);
    }
}
