/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utn.frc.dlc.sac.web.ctrl.cursos;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utn.frc.dlc.sac.Curso;
import utn.frc.dlc.sac.SAC;
import utn.frc.dlc.sac.web.ErrorMsg;

/**
 *
 * @author scarafia
 */
public class CtrlCursoInscribir extends HttpServlet {
   
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

        ErrorMsg errorMsg = null;
        String errorTitle = "No se pudo cargar el curso";
        String dest = "/error.jsp";
        //DBManager db = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            //----------------------------------------
            Curso curso = SAC.getCurso(id);
            //----------------------------------------
            //db = SAC.getSingleDB();
            //Curso curso = DBCurso.loadDB(db, id);
            //----------------------------------------
            //db = SAC.getPoolDB();
            //Curso curso = DBCurso.loadDB(db, id);
            //----------------------------------------

            if (curso != null) {
                //----------------------------------------
                List candidatos = SAC.getCanditatos(curso);
                //----------------------------------------
                //List candidatos = DBCurso.loadCandidatos(db, curso);
                //----------------------------------------
                request.setAttribute("curso", curso);
                request.setAttribute("candidatos", candidatos);
                dest = "/inscripcion.form.jsp";
            } else {
                throw new Exception("El curso especificado no existe.");
            }

        } catch (Exception e) {
            errorMsg = new ErrorMsg(errorTitle, e.getMessage());
            request.setAttribute("errorMsg", errorMsg);
        } finally {
            //if (db != null) db.close();
        }

        ServletContext app = this.getServletContext();
        RequestDispatcher disp = app.getRequestDispatcher(dest);
        disp.forward(request, response);
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
