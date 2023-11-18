package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 * @author Luis Fernando Paxel
 */
public class Configuraciones extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("accion");
            switch (operacion) {
                case "MostrarConfiguracion":
                    MostrarConfiguraciones(request, response);
                    break;

                case "ActualizarRedesS":
                    ActualizarRedesS(request, response);
                    break;

                case "ActualizarFrase":
                    ActualizarFrase(request, response);
                    break;

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

    private void MostrarConfiguraciones(HttpServletRequest request, HttpServletResponse response) {

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            modelo.Configuraciones cf = new modelo.Configuraciones();
            cf = cf.traerConf();
            JSONObject json = new JSONObject();
            json.put("facebook", cf.getFacebook());
            json.put("tw", cf.getTiktok());
            json.put("ins", cf.getInstagram());
            json.put("frase", cf.getFrase());
            json.put("autor", cf.getAutor());

            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ActualizarRedesS(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            modelo.Configuraciones cf = new modelo.Configuraciones();
            String face = request.getParameter("txtface");
            String twitter = request.getParameter("txttw");
            String insta = request.getParameter("txtins");
            int retorno = cf.ActualizarRedes(face, twitter, insta);
            if (retorno == 1) {
                response.getWriter().print("1");
            } else {
                response.getWriter().print("0");
            }

        } catch (IOException ex) {
            Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ActualizarFrase(HttpServletRequest request, HttpServletResponse response) {

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            modelo.Configuraciones cf = new modelo.Configuraciones();
            String frase = request.getParameter("txtfrase");
            String autor = request.getParameter("txtautor");
            int retorno = cf.ActualizarFrase(frase, autor);
            if (retorno == 1) {
                response.getWriter().print("1");
            } else {
                response.getWriter().print("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(Configuraciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
