/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utn.frc.dlc.sac.web.ahah.calificaciones;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utn.frc.dlc.sac.Fecha;
import utn.frc.dlc.sac.SAC;
import utn.frc.dlc.sac.db.DBManager;

/**
 *
 * @author scarafia
 */
public class AhahCalificacionesNotas extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.close();

        List calificaciones = new ArrayList();
        DBManager db = null;

        try {
            int curso = Integer.parseInt(request.getParameter("curso"));
            int alumno = Integer.parseInt(request.getParameter("alumno"));
            //db = SAC.getPoolDB();

            String query = "SELECT * " +
                    "FROM v_calificacion c " +
                    "WHERE c.idcurso = ? " +
                    "AND c.idalumno = ? " +
                    "ORDER BY c.fecha";
            db.prepare(query);
            db.setInt(1, curso);
            db.setInt(2, alumno);
            ResultSet rs = db.executeQuery();
            while (rs.next()) {
                int idCalificacion = rs.getInt("idcalificacion");
                String cursoNombre = rs.getString("curso");
                Date fechaAux = rs.getDate("fecha");
                Fecha fecha = new Fecha(fechaAux);
                int nota = rs.getInt("nota");
                Hashtable calificacion = new Hashtable();
                calificacion.put("id", idCalificacion);
                calificacion.put("curso", cursoNombre);
                calificacion.put("fecha", fecha);
                calificacion.put("nota", nota);
                calificaciones.add(calificacion);
                request.setAttribute("tmp", calificacion);
            }
            rs.close();
            request.setAttribute("curso", curso);
            request.setAttribute("alumno", alumno);
            request.setAttribute("calificaciones", calificaciones);
        } catch (Exception e) {
            String msg = "Error al cargar las calificaciones.";
            request.setAttribute("msg", msg);
        } finally {
            if (db != null) db.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
