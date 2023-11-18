package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import modelo.Clientes;
import modelo.Control_estadistica;
import modelo.Registros;
import modelo.Usuarios;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/* @author Luis Fernando Paxel
 */
public class Estadistica extends HttpServlet {

    Clientes cliente = new Clientes();
    Registros registro = new Registros();
    ArrayList<Integer> ContarPersona;
    DefaultTableModel tabla = new DefaultTableModel();
    Usuarios usuario = new Usuarios();
    ArrayList<Integer> RegistroDia;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("accion");
            switch (operacion) {

                case "CantidadPersonas":
                    CantidadPersona(request, response);
                    break;

                case "TablaEntrenadores":
                    TablaEntrenadores(request, response);
                    break;

                case "RegistrosPorDia":
                    RegistrosPorDia(request, response);
                    break;

                case "IntervaloSemanaA":
                    IntervaloSemanaA(request, response);
                    break;

            }
        }
    }

    private void CantidadPersona(HttpServletRequest request, HttpServletResponse response) {

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            ContarPersona = cliente.TraerEstadistica();
            JSONObject json = new JSONObject();
            json.put("arra", ContarPersona);
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Estadistica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void TablaEntrenadores(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {

            tabla = usuario.EntrenadoresTable();
            JSONArray json2 = new JSONArray();
            json2.addAll(0, tabla.getDataVector());
            out.print(json2);

        } catch (IOException ex) {
            Logger.getLogger(Estadistica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void RegistrosPorDia(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            RegistroDia = registro.RegistrosPorDia();
            JSONObject json = new JSONObject();
            json.put("ArrayDia", RegistroDia);
            out.print(json);
        } catch (IOException ex) {
            Logger.getLogger(Estadistica.class.getName()).log(Level.SEVERE, null, ex);
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

    private void IntervaloSemanaA(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
          //  request.setCharacterEncoding("UTF-8");

            Control_estadistica ce = new Control_estadistica();
            ArrayList<Integer> semanaA = new ArrayList();
            ArrayList<Integer> semanaB = new ArrayList();
            semanaA = ce.RegistroPorSemanaEdad1(); //actual
            semanaB = ce.RegistroPorSemanaEdad2(); //pasada
            JSONObject json = new JSONObject();
            json.put("A1", semanaA);
            json.put("B1", semanaB);
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Estadistica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
