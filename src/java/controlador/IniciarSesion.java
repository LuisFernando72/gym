package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Clientes;
import modelo.Usuarios;

/**
 * @author Luis Fernando Paxel
 */
public class IniciarSesion extends HttpServlet {

    Usuarios usuario = new Usuarios();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();

            String accion = request.getParameter("accion");
            if (accion.equalsIgnoreCase("Ingresar")) {
                String correo = request.getParameter("txtCorreoUsuario");
                String password = request.getParameter("txtPassword");
                boolean autocompleteEmail = correo.matches("^(.*)@(gmail|googlemail|(.*\\.)google)\\.com");
                if (!autocompleteEmail) {
                    correo += "@gmail.com";
                }
                usuario = usuario.validar(correo, password);

                if (usuario.getId_usuario() != 0) {

                    response.getWriter().println("1");
                    Clientes cl = new Clientes();
                    int idCliente = cl.getidCl(usuario.getCorreo());

                    Cookie IdUser01 = new Cookie("IdUser01", String.valueOf(usuario.getId_usuario()));
                    Cookie idC001 = new Cookie("idC001", String.valueOf(usuario.getIdRol()));
                    Cookie correos = new Cookie("idCl", String.valueOf(idCliente));

                    IdUser01.setMaxAge(365 * 24 * 60 * 60);
                    idC001.setMaxAge(365 * 24 * 60 * 60);
                    correos.setMaxAge(365 * 24 * 60 * 60);
                    response.addCookie(IdUser01);
                    response.addCookie(idC001);
                    response.addCookie(correos);
                    //    response.sendRedirect("Modulos?modulo=dashboard");

                } else {

                    response.getWriter().println("0");
                }

            } else {

            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
