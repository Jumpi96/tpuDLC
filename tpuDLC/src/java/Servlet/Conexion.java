/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Buscador.Buscador;
import Indexacion.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juampilorenzo
 */
@WebServlet(name = "Conexion", urlPatterns = {"/Conexion"})
public class Conexion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Buscador b;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        
		//Toma la búsqueda, carga la raíz dónde están los archivos.
        String busqueda = request.getParameter("campoBusqueda");
        List<AparicionPalabra> respuestaBuscador;
        String origen=cargarOrigen();
        
		//Llama a clase Buscador y obtiene los resultados.
        b = new Buscador();       
 
        respuestaBuscador=b.buscar(busqueda);
        
        String[] titulos = new String[respuestaBuscador.size()];
        String[] origenes = new String[respuestaBuscador.size()];
        
        System.out.println("Longitud de t" + titulos.length);
        
		//Carga dos array de String para el formulario resultadoBusqueda.jsp
        for (int i = 0; i < respuestaBuscador.size(); i++) {
            titulos[i]=respuestaBuscador.get(i).getDocumento().getTitulo();
            origenes[i]=origen+respuestaBuscador.get(i).getDocumento()
                    .getArchivo().getPath();
        }
        
        request.setAttribute("titulos", titulos);
        request.setAttribute("origenes", origenes);
        request.setAttribute("consulta",busqueda);
        request.getRequestDispatcher("resultadoBusqueda.jsp").forward(request, response);
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        response.setContentType("text/html;charset=UTF-8");        
//
//        String busqueda = request.getParameter("campoBusqueda");
//        List<Documento> respuestaBuscador;
//        String origen=cargarOrigen();
//        
//        b = new Buscador();
//        respuestaBuscador=b.buscar(busqueda);
//        
//        String[] titulos = new String[respuestaBuscador.size()];
//        String[] origenes = new String[respuestaBuscador.size()];
//        
//        for (int i = 0; i < respuestaBuscador.size(); i++) {
//            titulos[i]=respuestaBuscador.get(i).getTitulo();
//            origenes[i]=origen+respuestaBuscador.get(i).getArchivo().getAbsolutePath();
//        }
//        
//        request.setAttribute("titulos", titulos);
//        request.setAttribute("origenes", origenes);
//        request.setAttribute("consulta",busqueda);
//        request.getRequestDispatcher("resultadoBusqueda.jsp").forward(request, response);
 }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);       
//
//		//Toma la búsqueda, carga la raíz dónde están los archivos.
//        String busqueda = request.getParameter("campoBusqueda");
//        List<AparicionPalabra> respuestaBuscador;
//        String origen=cargarOrigen();
//        
//		//Llama a clase Buscador y obtiene los resultados.
//        b = new Buscador();
//        respuestaBuscador=b.buscar(busqueda);
//        
//        String[] titulos = new String[respuestaBuscador.size()];
//        String[] origenes = new String[respuestaBuscador.size()];
//        
//		//Carga dos array de String para el formulario resultadoBusqueda.jsp
//        for (int i = 0; i < respuestaBuscador.size(); i++) {
//            titulos[i]=respuestaBuscador.get(i).getDocumento().getTitulo();
//            origenes[i]=origen+respuestaBuscador.get(i).getDocumento()
//                    .getArchivo().getPath();
//        }
//        
//        request.setAttribute("titulos", titulos);
//        request.setAttribute("origenes", origenes);
//        request.setAttribute("consulta",busqueda);
//        request.getRequestDispatcher("resultadoBusqueda.jsp").forward(request, response);
    }
    
    private String cargarOrigen() {
        Properties propiedades=new Properties();
        InputStream entrada=null;
        
            try {
                entrada = new FileInputStream("Configuracion.properties");

                propiedades.load(entrada);
                
                return propiedades.getProperty("archivos");

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (entrada != null) {
                    try {
                        entrada.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        return null;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
