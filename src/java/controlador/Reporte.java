package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente_pregunta;
import modelo.Clientes;
import modelo.Condicion_cliente;
import modelo.ControlMedidasCorporales;
import modelo.Control_cliente;
import modelo.Control_peso;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * * @author Luis Fernando Paxel
 */
public class Reporte extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String operacion = request.getParameter("accion");

            switch (operacion) {

                case "Reporte":
                    ReporteC(request, response);
                    break;

                case "PreguntasCliente":
                    PreguntasCliente(request, response);
                    break;

                case "CondicionesE":
                    CondicionesE(request, response);
                    break;

                case "ControlPesos":
                    ControlPesos3(request, response);
                    break;

                case "ControlMedidas":
                    ControlMedidas(request, response);
                    break;

                case "Clienteid":
                    Clienteid(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void ReporteC(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            String idCliente = request.getParameter("id_cliente");
            int Id_cliente = Integer.valueOf(idCliente);
            Clientes c = new Clientes();
            c = c.DatosCliente(Id_cliente);
            JSONObject json = new JSONObject();

            json.put("nombres", c.getNombres());
            json.put("apellidos", c.getApellidos());
            json.put("Nodpi", c.getDpi());
            json.put("correo", c.getCorreo());
            json.put("Notelefonoc", c.getTelefono());
            json.put("estatura", c.getEstatura());
            json.put("edad", c.getEdad());
            json.put("nombreE", c.getNombreE());
            json.put("apellidosE", c.getApellidoE());
            json.put("telefonoE", c.getApellidoE());
            json.put("fechaI", c.getFechaR());
            json.put("observaciones", c.getObservaciones());
            json.put("genero", c.getGenero());
            json.put("idreg", c.getIdreg2());
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void PreguntasCliente(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            DefaultTableModel tabla = new DefaultTableModel();
            Cliente_pregunta ccp = new Cliente_pregunta();
            String idRegistro = request.getParameter("id_registro");
            String simbolo = request.getParameter("simbolo");
            tabla = ccp.PreguntasTable(Integer.valueOf(idRegistro), simbolo);
            JSONArray json = new JSONArray();
            json.addAll(0, tabla.getDataVector());
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void CondicionesE(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            Condicion_cliente cc = new Condicion_cliente();
            String idRegistro = request.getParameter("id_registro");
            cc = cc.CondicionesE(Integer.valueOf(idRegistro));
            JSONObject json = new JSONObject();
            json.put("salud", cc.getSaludGeneral());
            json.put("cfisica", cc.getCondicion_fisica());
            json.put("nivelN", cc.getNivel_nutricional());
            json.put("nivelE", cc.getNivel_estres());

            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ControlPesos3(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            DefaultTableModel tabla2 = new DefaultTableModel();
            Control_cliente control = new Control_cliente();
            Control_peso c = new Control_peso();

            String idreg = request.getParameter("id_reg");

            int idControl = control.getIdcontrolCliente(Integer.valueOf(idreg));
            tabla2 = c.Control_Peso(idControl);

            JSONArray json2 = new JSONArray();
            json2.addAll(0, tabla2.getDataVector());

            out.print(json2);

        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void ControlMedidas(HttpServletRequest request, HttpServletResponse response) {

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            DefaultTableModel tabla2 = new DefaultTableModel();
            Control_cliente control = new Control_cliente();
            ControlMedidasCorporales cm = new ControlMedidasCorporales();

            String idreg = request.getParameter("id_reg");

            int idControl = control.getIdcontrolCliente(Integer.valueOf(idreg));
            tabla2 = cm.Control_Corporal(idControl);

            JSONArray json2 = new JSONArray();
            json2.addAll(0, tabla2.getDataVector());
            out.print(json2);

        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Clienteid(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            String correo = request.getParameter("email001");
            Clientes c = new Clientes();
            int id = c.getidCl(correo);

            JSONObject json = new JSONObject();
            json.put("id", id);
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
