package controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Control_cliente;
import modelo.Usuarios;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;

/* * @author Luis Fernando Paxel
 */
public class Usuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String operacion = request.getParameter("accion");

            switch (operacion) {

                case "obtenerUsuario":
                    ObtenerUsuario(request, response);
                    break;

                case "CambiarFotoPerfil":
                    CambiarFotoPerfil(request, response);
                    break;

                case "ModificarPass":
                    ModificarPass(request, response);
                    break;

                case "AsignarUsuario":
                    AsignarUsuario(request, response);
                    break;

                case "EliminarUsuario":
                    EliminarUsuario(request, response);
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

    private void ObtenerUsuario(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            String idUser = request.getParameter("id_user");
            Usuarios usuario = new Usuarios();
            usuario = usuario.DatosUsuario(Integer.valueOf(idUser));
            JSONObject json = new JSONObject();
            json.put("idUser", usuario.getId_usuario());
            json.put("nombres", usuario.getNombres());
            json.put("apellidos", usuario.getApellidos());
            json.put("cui", usuario.getCui());
            json.put("correo", usuario.getCorreo());
            json.put("imagen", usuario.getImagen());
            json.put("fecha_creacion", usuario.getFecha_creacion());
            json.put("id_rol", usuario.getIdRol());
            out.print(json);
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void CambiarFotoPerfil(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            FileItemFactory file_factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(file_factory);

            ArrayList<String> campos = new ArrayList<>();
            ArrayList<String> imgs = new ArrayList<>();
            Usuarios usuario = new Usuarios();

            String pathCarpeta = "appservers/apache-tomcat-8x/webapps/ROOT/imgUser/";

            try {
                String frase = RandomStringUtils.randomAlphanumeric(6);
                List items = sfu.parseRequest(request);
                for (int i = 0; i < items.size(); i++) {
                    FileItem item = (FileItem) items.get(i);

                    if (!item.isFormField()) {
                        File archivo = new File(pathCarpeta + frase + item.getName());
                        item.write(archivo);
                        imgs.add(frase + item.getName());

                        if (campos.get(0).equals(campos.get(1))) {

                        } else {
                            if (campos.get(0).equals("ImgUser.png")) {

                            } else {
                                usuario.EliminarFotoServidor(pathCarpeta, campos.get(1));
                                int retorno = usuario.CambiarFotoPerfil(imgs.get(0), Integer.valueOf(campos.get(0)));
                                if (retorno == 1) {
                                    response.getWriter().print("1");
                                } else {
                                    response.getWriter().print("0");
                                }
                            }

                        }
                    } else {
                        campos.add(item.getString());
                    }

                }
            } catch (Exception ex) {

            }

        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ModificarPass(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            Usuarios usuario = new Usuarios();

            String idUser = request.getParameter("txt_idUser");
            String passActual = request.getParameter("paswd_actual");
            String pass1 = request.getParameter("passwd1");
            String pass2 = request.getParameter("passwd2");

            String retorno = usuario.VerificarPass(Integer.valueOf(idUser));

            if (retorno.equals(passActual)) {
                if (pass1.equals(pass2)) {

                    int retornoUs = usuario.ModificarPass(pass2, Integer.valueOf(idUser));
                    if (retornoUs == 1) {
                        response.getWriter().print("1");
                    } else {
                        response.getWriter().print("0");
                    }

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AsignarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            Usuarios usuario = new Usuarios();
            String id = request.getParameter("txtid");
            String rol = request.getParameter("txtrol");

            int retorno = usuario.AsignarPuesto(Integer.valueOf(rol), Integer.valueOf(id));
            if (retorno == 1) {
                response.getWriter().print("1");
            } else {
                response.getWriter().print("0");
            }

        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void EliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            ArrayList parametros = new ArrayList<>();
            Usuarios usuario = new Usuarios();
            Control_cliente cc = new Control_cliente();
            String idUser = request.getParameter("txtid");
            String Correo = request.getParameter("txtCorreo");
            String dpi = request.getParameter("txtCui");
            parametros = usuario.getUsuario(Correo, dpi);
            int idCliente = (int) parametros.get(0);
            int IDregistro = (int) parametros.get(1);

            int idControlCliente = cc.getIdcontrolCliente(IDregistro);

            //SE ELIMINA DE PRIMERO EL DETALLE
            int retorno_medida = cc.eliminarControlMedida(idControlCliente);
            int retorno_peso = cc.eliminarControlPeso(idControlCliente);
            int retorno_control = cc.eliminarControl(idControlCliente);

            //SE ELIMINA LA CABECERA
            int retorno_energia = cc.eliminarCondicionesEnergia(IDregistro);
            int retorno_historial = cc.eliminarHistorialClienteC(IDregistro);
            int retorno_registro = cc.eliminarRegistroCliente(idCliente);
            int retorno_cliente = cc.eliminarCliente(idCliente);
            int eliminar = usuario.eliminarUsuario(Integer.valueOf(idUser));

            if (eliminar == 1) {
                response.getWriter().print("1");
            } else {
                response.getWriter().print("0");
            }

        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
