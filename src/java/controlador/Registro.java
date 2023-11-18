package controlador;

import conexion.ConexionCorreo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente_pregunta;
import modelo.Clientes;
import modelo.Condicion_cliente;
import modelo.Control_cliente;
import modelo.Funciones;
import modelo.Registros;
import modelo.Usuarios;
import org.json.simple.JSONObject;

/*  @author Luis Fernando Paxel
 */
public class Registro extends HttpServlet {

    ArrayList<Integer> estrellas = new ArrayList<>();
    Clientes clientes = new Clientes();
    Usuarios usuario = new Usuarios();
    ConexionCorreo cc = new ConexionCorreo();
    Registros registros;
    Cliente_pregunta cPregunta;
    Funciones funciones;
    Condicion_cliente condicion_cliente;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("accion");

            switch (operacion) {

                case "insertar_cliente":
                    insertar_cliente(request, response);
                    break;

                case "traer_registro":
                    traer_registro(request, response);
                    break;
                case "datos_cliente":
                    datos_cliente(request, response);
                    break;

                case "modificar_cliente":
                    ModificarCliente(request, response);
                    break;

                case "eliminar_registro":
                    EliminarRegistro(request, response);
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

    private void insertar_cliente(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Integer> id_pregunta = new ArrayList<>();
        ArrayList<String> respuesta_pregunta = new ArrayList<>();

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            response.setContentType("text/html;charset=UTF-8");
            int cont_pregunta = -1, index_Id_pregunta = -1, index_ResPregunta = -1;
            String star;
//            DATOS PRIMERA PAGINA
            String nameImg = "ImgUser.png";
            String emisorGmail = "lafabricagym1996@gmail.com";
            String asunto = "Confirmacion de cuenta";

            String NombresC = request.getParameter("txtnombresCliente");
            String ApellidosC = request.getParameter("txtApellidosCliente");
            String CorreoCl = request.getParameter("txtcorreoCliente");
            String FechaN = request.getParameter("txtfechaNacimiento");
            String Cui = request.getParameter("txtxCUIC");
            String Sexo = request.getParameter("txtsexo");
            String TelC = request.getParameter("telC");
            String Edad = request.getParameter("txtEdad");
            String estatura = request.getParameter("txtEstatura");
            String SelectEstatura = request.getParameter("estaturaSelect");
            String NomEmergencia = request.getParameter("txtnombresEmergencia");
            String ApeEmergencia = request.getParameter("txtapellidosEmergencia");
            String TelEmergencia = request.getParameter("telE");
            String obs_datosg = request.getParameter("observacion1");

            String Estaturaf = estatura + " " + SelectEstatura;

            respuesta_pregunta.clear();
            id_pregunta.clear();

            while (cont_pregunta != 23) {
                cont_pregunta++;
                respuesta_pregunta.add("");
                id_pregunta.add(cont_pregunta);
            }
            for (int i = 1; i <= 24; i++) {
                index_Id_pregunta++;
                String pregunta_radio = request.getParameter("radio" + i);
                String IdPregunta = request.getParameter("id_Pregunta" + i);

                id_pregunta.set(index_Id_pregunta, Integer.valueOf(IdPregunta));
                if ("si".equalsIgnoreCase(pregunta_radio)) {
                    index_ResPregunta++;
                    respuesta_pregunta.set(index_ResPregunta, "1");
                } else if ((!"no".equals(pregunta_radio)) & (!"si".equals(pregunta_radio))) {
                    index_ResPregunta++;
                    respuesta_pregunta.set(index_ResPregunta, pregunta_radio);
                } else {
                    index_ResPregunta++;
                    respuesta_pregunta.set(index_ResPregunta, "0");
                }

            }

            //ESTRELLAS
            estrellas.clear();

            for (int i = 1; i <= 4; i++) {

                star = request.getParameter("star" + i);

                switch (star) {
                    case "Muy mala":
                        estrellas.add(1);
                        break;
                    case "Mala":
                        estrellas.add(2);
                        break;
                    case "Regular":
                        estrellas.add(3);
                        break;
                    case "Buena":
                        estrellas.add(4);
                        break;
                    case "Excelente":
                        estrellas.add(5);
                        break;

                    default:
                        estrellas.add(0);
                    //    out.println("<h6>Error condicion</h6>");

                }
            }

            int duplicado = clientes.buscar_duplicadoC(Cui, CorreoCl);

            if (duplicado == 0) {

                clientes = new Clientes(NombresC, ApellidosC, Cui, CorreoCl, TelC, Integer.valueOf(Sexo), Estaturaf, FechaN, Integer.valueOf(Edad), NomEmergencia, ApeEmergencia, TelEmergencia, 0);
                int retorno_cliente = clientes.insertarCliente();

                if (retorno_cliente == 1) {
                    int id_Cliente = clientes.buscar_idCliente(Cui, CorreoCl);

                    registros = new Registros(id_Cliente, "");
                    int retorno_registro = registros.InsertarRegistro();

                    if (retorno_registro == 1) {
                        int idRegistro = registros.buscar_idRegistro(id_Cliente);

                        cPregunta = new Cliente_pregunta();
                        int retorno_pregunta = cPregunta.insertar_clienteHPre(id_pregunta, respuesta_pregunta, idRegistro);
                        if (!obs_datosg.isEmpty()) {
                            funciones = new Funciones();
                            int retornoObsClinica = funciones.Insertar_obsClinica(obs_datosg, idRegistro);
                        }

                        condicion_cliente = new Condicion_cliente();
                        int retorno_condicion = condicion_cliente.insertarCondiciones_E(estrellas, idRegistro);

                        if (retorno_condicion == 1) {
                            String pass = usuario.generateRandomPassword(10);
                            usuario = new Usuarios(0, NombresC, ApellidosC, Cui, CorreoCl, pass, 0, nameImg, "", 1);
                            usuario.RegistrarUsuario();
                            String txt = "Bienvenido(a), " + NombresC + " " + ApellidosC + " gracias por elegir al gimnasio La Fábrica, su contraseña de autenticación de cuenta es:\n" + pass + "\n\nDudas y consultas comunicarse al +(502) 7832-9840\n\n\n1a Avenida Norte, Antigua Guatemala.";
                            cc = new ConexionCorreo(emisorGmail, CorreoCl, asunto, txt, "");
                            cc.envioDeCorreos();
                            response.getWriter().println(id_Cliente);

                        } else {
                            response.getWriter().println("0");
                        }
                    }

                }
            } else {

            }

        } catch (IOException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void traer_registro(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("UTF-8");

            out = response.getWriter();
            String idCliente = request.getParameter("id_cliente");
            Clientes clientes = new Clientes();
            clientes = clientes.traerRegistro(Integer.valueOf(idCliente));
            JSONObject json = new JSONObject();
            json.put("cod_registro", clientes.getIdRegistro());
            json.put("nomC", clientes.getNombres());
            json.put("apeC", clientes.getApellidos());
            json.put("email", clientes.getCorreo());
            json.put("edad", clientes.getEdad());
            json.put("altura", clientes.getEstatura());
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Estadistica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    private void datos_cliente(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            String idCliente = request.getParameter("idCliente");
            Clientes clientes = new Clientes();
            clientes = clientes.DatosCliente(Integer.valueOf(idCliente));
            JSONObject json = new JSONObject();
            json.put("id_cliente", clientes.getIdRegistro());
            json.put("nombres_c", clientes.getNombres());
            json.put("apellidos_c", clientes.getApellidos());
            json.put("correo_c", clientes.getCorreo());
            json.put("fn_cliente", clientes.getFecha_nacimiento());
            json.put("cui_c", clientes.getDpi());
            json.put("genero_c", clientes.getGenero());
            json.put("telefonon_c", clientes.getTelefono());
            json.put("edad_c", clientes.getEdad());
            json.put("estatura_c", clientes.getEstatura());
            json.put("nombres_emergencia", clientes.getNombreE());
            json.put("apellidos_emergencia", clientes.getApellidoE());
            json.put("telefono_emergencia", clientes.getTelefonoE());
            out.print(json);

        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ModificarCliente(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            String NombresC = request.getParameter("txtnombresClienteM");
            String ApellidosC = request.getParameter("txtApellidosClienteM");
            String CorreoCl = request.getParameter("txtcorreoClienteM");
            String FechaN = request.getParameter("txtfechaNacimientoM");
            String Cui = request.getParameter("txtxCUICM");
            String Sexo = request.getParameter("txtsexoM");
            String TelC = request.getParameter("telC_mod");
            String Edad = request.getParameter("txtEdadM");
            String estatura = request.getParameter("txtEstaturaM");
            String NomEmergencia = request.getParameter("txtnombresEmergenciaM");
            String ApeEmergencia = request.getParameter("txtapellidosEmergenciaM");
            String TelEmergencia = request.getParameter("telEm_mod");
            String IdCliente = request.getParameter("txtidCliente");
            String Tel_clienteExtension = request.getParameter("txtTelefonoM");
            String TelEmergenciaExtension = request.getParameter("txttelefonoEM");
            String TelefonoClienteM = "";
            String TelefonoClienteEM = "";

            if (TelC.length() == 0) {
                TelefonoClienteM = Tel_clienteExtension;
            } else {
                TelefonoClienteM = TelC;
            }

            if (TelEmergencia.length() == 0) {
                TelefonoClienteEM = TelEmergenciaExtension;
            } else {
                TelefonoClienteEM = TelEmergencia;
            }

            clientes = new Clientes(NombresC, ApellidosC, Cui, CorreoCl, TelefonoClienteM, Integer.valueOf(Sexo), estatura, FechaN, Integer.valueOf(Edad), NomEmergencia, ApeEmergencia, TelefonoClienteEM, Integer.valueOf(IdCliente));
            int retorno = clientes.ModificarCliente();
            if (retorno == 1) {
                response.getWriter().println("1");

            } else {
                response.getWriter().println("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void EliminarRegistro(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            String idCliente = request.getParameter("txtidCliente");
            Control_cliente control_cliente = new Control_cliente();

            int idRegistro = control_cliente.getIdregistroCliente(Integer.valueOf(idCliente));

            int idControlCliente = control_cliente.getIdcontrolCliente(idRegistro);

            //SE ELIMINA DE PRIMERO EL DETALLE
            int retorno_medida = control_cliente.eliminarControlMedida(idControlCliente);
            int retorno_peso = control_cliente.eliminarControlPeso(idControlCliente);
            int retorno_control = control_cliente.eliminarControl(idControlCliente);

            //SE ELIMINA LA CABECERA
            int retorno_energia = control_cliente.eliminarCondicionesEnergia(idRegistro);
            int retorno_historial = control_cliente.eliminarHistorialClienteC(idRegistro);
            int retorno_registro = control_cliente.eliminarRegistroCliente(Integer.valueOf(idCliente));
            int retorno_cliente = control_cliente.eliminarCliente(Integer.valueOf(idCliente));
            response.getWriter().println(retorno_medida + " " + retorno_peso + " " + retorno_control + " " + retorno_energia + " " + retorno_historial + " " + retorno_registro + " " + retorno_cliente);

            if (retorno_cliente == 1) {
                response.getWriter().println("1");
            } else {
                response.getWriter().println("0");
            }

        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
