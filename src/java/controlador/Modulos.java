package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*  @author Luis Fernando Paxel
 */
public class Modulos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
           request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String modulo = request.getParameter("modulo");

            if (modulo.equals("dashboard")) {
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            }
            if (modulo.equals("registros")) {
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }
            if (modulo.equals("inicio")) {
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            }
            if (modulo.equals("estadistica")) {
                request.getRequestDispatcher("estadistica.jsp").forward(request, response);
            }

            if (modulo.equals("usuarios")) {
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
            }

            if (modulo.equals("login")) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (modulo.equals("reporte")) {
                request.getRequestDispatcher("reporte.jsp").forward(request, response);
            }

            if (modulo.equals("reporteUs")) {
                request.getRequestDispatcher("reporte_usuario.jsp").forward(request, response);

            }

        }
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
