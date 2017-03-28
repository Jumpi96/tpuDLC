/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utn.frc.dlc.sac.web.ahah.calificaciones;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Hashtable;
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
public class AhahCalificacionesForm extends HttpServlet {
   
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

        DBManager db = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int curso = Integer.parseInt(request.getParameter("curso"));
            int alumno = Integer.parseInt(request.getParameter("alumno"));

            Fecha fecha = null;
            Integer nota = null;

            if (id > 0) {
                //db = SAC.getPoolDB();
                String query = "SELECT * " +
                        "FROM v_calificacion c " +
                        "WHERE c.idcalificacion = ?";
                db.prepare(query);
                db.setInt(1, id);
                ResultSet rs = db.executeQuery();
                if (rs.first()) {
                    Date fechaAux = rs.getDate("fecha");
                    fecha = new Fecha(fechaAux);
                    nota = rs.getInt("nota");
                }
                rs.close();
            }

            request.setAttribute("id", id);
            request.setAttribute("curso", curso);
            request.setAttribute("alumno", alumno);
            request.setAttribute("fecha", fecha);
            request.setAttribute("nota", nota);
            
        } catch (Exception e) {
            String msg = "Error al inicializar/cargar la calificación.";
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
