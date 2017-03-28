/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utn.frc.dlc.sac.web.ahah.calificaciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utn.frc.dlc.sac.SAC;
import utn.frc.dlc.sac.db.DBManager;

/**
 *
 * @author scarafia
 */
public class AhahCalificacionesFormPost extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String result = null, msg = null;
        DBManager db = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int curso = Integer.parseInt(request.getParameter("curso"));
            int alumno = Integer.parseInt(request.getParameter("alumno"));
            String fecha = request.getParameter("fecha");
            int nota = Integer.parseInt(request.getParameter("nota"));

            //db = SAC.getPoolDB();

            String query = "SELECT fn_savecalificacion(?, ?, ?, ?, ?)";
            db.prepare(query);

            Integer idCalificacion = (id==0 ? null : new Integer(id));
            db.setInt(1, idCalificacion);
            db.setInt(2, curso);
            db.setInt(3, alumno);
            db.setString(4, fecha);
            db.setInt(5, nota);
            ResultSet rs = db.executeQuery();
            rs.close();

            request.setAttribute("id", id);
            request.setAttribute("curso", curso);
            request.setAttribute("alumno", alumno);
            request.setAttribute("fecha", fecha);
            request.setAttribute("nota", nota);

            result = "true";
            msg = "Calificación guardada!";

        } catch (Exception e) {
            result = "false";
            msg = "Error al guardar la calificación.";

        } finally {
            if (db != null) db.close();
            String json = "{result: " + result + ", msg: \"" + msg + "\"}";
            out.print(json);
            out.close();
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
