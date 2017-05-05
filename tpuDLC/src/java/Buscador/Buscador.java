/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscador;

import Indexacion.AparicionPalabra;
import Indexacion.Documento;
import Indexacion.Palabra;
import Indexacion.StringSimbolizador;
import java.io.File;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juampilorenzo
 */
public class Buscador {
    private HashMap<String,Palabra> vocabulario;
    private HashMap<Integer,Documento> documentos;
    
    private int R=30;

    public Buscador() {
        vocabulario=new HashMap<String,Palabra>();
        documentos=new HashMap<Integer,Documento>();
        cargarVocabulario();
        listarArchivos();
        //probando
        buscar("hello intelligence superb");
    }
    
    public List buscar(String busqueda){
        listarArchivos();
        
        HashMap<String,Documento> respuestas=new HashMap<String,Documento>();
        List<Palabra> palabras = new ArrayList<Palabra>();
        List<Documento> docs= new ArrayList<Documento>();
        String cont;
        
        StringSimbolizador st=new StringSimbolizador(busqueda);
        while(st.hasMoreTokens()){
            cont=st.nextToken();
            if(cont!=null)
            {
                if(vocabulario.containsKey(cont))                                
                    palabras.add(vocabulario.get(cont));
            }
        }
        palabras.sort(Palabra.PalabraComparator);
        
        String origen;
        int idDocumento;
        for (Iterator<Palabra> i = palabras.iterator(); i.hasNext();) {
            List<AparicionPalabra> listaPosteo = getListaDePosteo(i.next());
            for (int j = 0; j < listaPosteo.size(); j++) {
                origen=listaPosteo.get(j).getDocumento().getArchivo().getPath();
                if(respuestas.containsKey(origen))
                    respuestas.get(origen).contar();
                else{
                    respuestas.put(origen,listaPosteo.get(j).getDocumento());
                    respuestas.get(origen).contar();
                }
            }
        }
        /*
        PASAR HASHMAP A UNA ESTRUCTURA QUE PERMITA ORDENAR Y LUEGO SELECCIONAR
        SOLO 30 PARA DEVOLVER
        */
        while(docs.size()<30){
            
        }
        
        return docs;
    }
    
    private void cargarVocabulario(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:vocabulario");
            Statement st = conn.createStatement();
            String consulta="SELECT * FROM palabras";
            ResultSet rs=st.executeQuery(consulta);
            
            while (rs.next()) {
                vocabulario.put(rs.getString(2), new Palabra(rs.getString(2),
                        rs.getInt(1),rs.getInt(3),rs.getInt(4)));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listarArchivos(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:vocabulario");
            Statement st = conn.createStatement();
            String consulta="SELECT ROWID,origen FROM Documentos";
            ResultSet rs=st.executeQuery(consulta);
            
            while (rs.next()) {
                documentos.put(rs.getInt(1), new Documento(new File(rs.getString(2)),
                        rs.getInt(1)));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<AparicionPalabra> getListaDePosteo(Palabra palabra){
        List listaPosteo = new ArrayList<AparicionPalabra>();
        
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:vocabulario");
            Statement st = conn.createStatement();
            String consulta="SELECT idDocumento,contador FROM palabrasXDocumento"
                    + " WHERE idPalabra=" + palabra.getIdPalabra() + " ORDER BY"
                    + " 2 DESC LIMIT 30";
            ResultSet rs=st.executeQuery(consulta);
            
            while (rs.next()) {
                listaPosteo.add(new AparicionPalabra(palabra,
                        documentos.get(rs.getInt(1)),rs.getInt(2)));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPosteo;
    }
    
}
