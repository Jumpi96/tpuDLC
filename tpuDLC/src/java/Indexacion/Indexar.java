/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juamp
 */
public class Indexar {

    /**
     * @param args the command line arguments
     */
    String origen="../../../DocumentosTP1";
    
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Indexar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //GestorProcesamiento g = new GestorProcesamiento();
        //g.procesar("16082-8.txt");
        
        String origen="../../DocumentosTP1";
        CargaArchivos carga= new CargaArchivos(origen);
        carga.procesar();
        
        //COMENTARIOS NUEVOS
        //InterfazGrafica i = new InterfazGrafica();
        //i.habilitarPantalla(g);
    }
    
    private static void baseDatos() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:sqlite:vocabulario");
        //seguir
    }
}
