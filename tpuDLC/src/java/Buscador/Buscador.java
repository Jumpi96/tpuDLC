/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscador;

import Indexacion.GestorProcesamiento;
import Indexacion.Palabra;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juampilorenzo
 */
public class Buscador {
    private HashMap<String,Palabra> vocabulario;

    public Buscador() {
        vocabulario=new HashMap<String,Palabra>();
        cargarVocabulario();
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
            Logger.getLogger(GestorProcesamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private  getListaDePosteo(Palabra palabra){
        
    }
}
