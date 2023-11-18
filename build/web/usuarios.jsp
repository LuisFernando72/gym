<%-- 
    Document   : usuarios
    Created on : Dec 18, 2022, 11:01:47 AM
    Author     : Luis Fernando Paxel
--%>

<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Tablas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="icon"  href="img/LogoGymicono.ico"/>
        <title>usuarios</title>
        <script src="library/jQuery3.js"></script>
        <link rel="stylesheet" href="library/jquery.dataTables.min.css" />
        <script src="library/jquery.dataTables.min.js"></script>
        <link href="css/estilo_form_usuario.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/estilo_tabla_usuario.css" />
        <script src="library/sweetalert2.min.js"></script>
        <script src="library/fontawesome/js/all.min.js"></script>
    </head>
    
    <body>

        <%
            String IdUser01 = null;
            String idC001 = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("IdUser01")) {
                        IdUser01 = cookie.getValue();
                    }
                    if (cookie.getName().equals("idC001")) {
                        idC001 = cookie.getValue();
                    }

                }
            }
            if (IdUser01 == null) {
                response.sendRedirect("Modulos?modulo=login");
            }
            if (Integer.valueOf(idC001) != 3) {
                response.sendRedirect("Modulos?modulo=dashboard");
            }

        %>
        <div id="formContent" class="formContent">
            <div class="headingTable">
                <h2>Usuarios</h2>
            </div>
            <div class="ContainerDataTable">
                <table id="tblCustomer" class="display tableCon" style="width: 100%">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Image</th>

                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>Cui</th>
                            <th>Correo Electronico</th>
                            <th>Rol</th>
                            <th>Fecha Creacion</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%                            Tablas tabla = new Tablas();
                            DefaultTableModel tblUser = new DefaultTableModel();

                            tblUser = tabla.table_usuarios();
                            int NumRow = 0;

                            for (int i = 0; i < tblUser.getRowCount(); i++) {
                                NumRow++;
                                out.println("<tr data-id=" + tblUser.getValueAt(i, 1) + " data-idimg=" + tblUser.getValueAt(i, 0) + " data-idp=" + tblUser.getValueAt(i, 8) + " >");
                                out.println("<td>" + NumRow + "</td>");
                                out.println("<td> <div class ='imageU' >  <img  src=imgUser/" + tblUser.getValueAt(i, 0) + " class='imgUser'> </div> </td>");
//                                out.println("<td>" + tblUser.getValueAt(i, 1) + "</td>");
                                out.println("<td>" + tblUser.getValueAt(i, 2) + "</td>");
                                out.println("<td>" + tblUser.getValueAt(i, 3) + "</td>");
                                out.println("<td>" + tblUser.getValueAt(i, 4) + "</td>");
                                out.println("<td>" + tblUser.getValueAt(i, 5) + "</td>");
                                out.println("<td>" + tblUser.getValueAt(i, 6) + "</td>");
                                out.println("<td>" + tblUser.getValueAt(i, 7) + "</td>");
                                out.println("</tr>");
                            }
                        %>


                    </tbody>
                </table>
            </div>

            <div class="administracionRedes">
                <div class="headingTable">
                    <h2>Administración de redes</h2>
                </div>
                <form action="#">
                    <ul class="box-info">
                        <!-- FACEBOOK -->
                        <li>

                            <a href="#" id="Ifacebook" target="_blank" class="social-icon">
                                <i class="bx fab fa-facebook-square"></i>

                            </a>

                            <!-- <i class="bx fa-solid fa-person-dress"></i> -->
                            <span class="text">
                                <input type="text" id="txtFace" name="txtFace" />
                            </span>
                        </li>
                        <!-- INSTAGRAM -->
                        <li>
                            <a
                                href="#"
                                id="Iinstagram"
                                target="_blank"
                                >
                                <i class="bx fa-brands fa-square-instagram"></i>
                            </a>
                            <span class="text">
                                <input type="text" id="txtinsta" name="txtinsta" />
                            </span>
                        </li>

                        <!-- TWITTER -->
                        <li>
                            <a
                                href="#"
                                id="Itw"
                                target="_blank"
                                >
                                <i class="bx fa-brands fa-tiktok"></i>
                            </a>
                            <span class="text">
                                <input type="text" name="txtTw" id="txtTw" />
                            </span>
                        </li>
                    </ul>

                    <div class="btnAceptarR">
                        <button type="button" name="accion" id="btnAceptarR" value="ActualizarRedesS">
                            Actualizar
                        </button>
                    </div>
                </form>
            </div>


            <div class="administracionRedes">
                <div class="headingTable">
                    <h2>Frase inicio</h2>
                </div>
                <form action="#">
                    <ul class="box-info">
                        <!-- fRASE -->
                        <li>
                            <div class="AreaFrase">
                                <textarea name="txtFrase" id="txtFrase"></textarea>
                            </div>
                        </li>
                        <!-- AUTOR-->
                        <li>
                            <label for="">Autor</label>
                            <input type="text" id="txtAutor" name="txtAutor" />
                        </li>
                    </ul>

                    <div class="btnAceptarR">
                        <button
                            type="button"
                            name="accion"
                            id="btnActualizarFrase"
                            value="ActualizarFrase"
                            >
                            Actualizar
                        </button>
                    </div>
                </form>
            </div>

        </div>

        <!-- FORMULARIO USUARIOS -->
        <dialog id="modalUser">
            <div class="contenedorUsuario"> 
                <div class="container">
                    <div id="btn_cancelarUs" class="btn_cancelar">
                        <i class="fa-solid fa-circle-xmark ex" title="Salir"> </i>
                    </div>
                    <div class="form">
                        <div class="ImagenContainer">
                            <div class="ImgUserFrm">
                                <img class="imgA" id="txtImgU" alt="" />
                            </div>
                        </div>
                        <h2>Usuarios</h2>

                        <form id="frmAsignarP" method="post" action="Usuario">
                            <!-- CONTRASEÑA ACTUAL -->
                            <div class="gridPagina">
                                <input
                                    type="text"
                                    name="txt_idUser"
                                    id="txt_idUser"
                                    value=""
                                    class="hide"
                                    />
                                <!-- NOMBRES -->

                                <div class="inputBox">
                                    <label for="">Nombres</label>
                                    <input
                                        type="text"
                                        id="txtNombres"
                                        name="txtNombres"
                                        placeholder="Nombres"
                                        />
                                </div>
                                <!-- APELLIDOS -->
                                <div class="inputBox">
                                    <label for="">Apellidos</label>
                                    <input
                                        type="text"
                                        id="txtApellidos"
                                        name="txtApellidos"
                                        placeholder="Apellidos"
                                        />
                                </div>
                                <!--CUI -->
                                <div class="inputBox">
                                    <label for="">Cui</label>
                                    <input
                                        type="text"
                                        id="txtCui"
                                        name="txtCui"
                                        placeholder="Cui"
                                        />
                                </div>
                                <!-- CORREO ELECTRONICO -->
                                <div class="inputBox">
                                    <label for="">Correo Electronico</label>
                                    <input
                                        type="text"
                                        id="txtCorreo"
                                        name="txtCorreo"
                                        placeholder="Correo Electronico"
                                        />
                                </div>
                                <!-- ROL -->
                                <div class="inputBox">
                                    <label for="">Rol</label>
                                    <label for="">Fecha Inicio</label>
                                    <select name="txtRol" id="txtRol" style="width: 95%; height: 100%;">
                                        <option value="2">Entrenador</option>
                                        <option value="1">Usuario</option>
                                    </select>
                                </div>

                                <!-- FECHA INICIO -->
                                <div class="inputBox">
                                    <label for="">Fecha Inicio</label>
                                    <input
                                        type="text"
                                        id="txtFechai"
                                        name="txtFechai"
                                        placeholder="Fecha Inicio"
                                        />
                                </div>
                            </div>
                            <div class="btns">
                                <div class="inputBox">
                                    <button
                                        type="button"
                                        id="btnAsignarPuesto"
                                        name="accion"
                                        value="AsignarPuesto"
                                        >
                                        Asignar Puesto
                                    </button>
                                </div>

                                <div class="inputBox">
                                    <button
                                        type="button"
                                        id="btnEliminarU"
                                        name="accion"
                                        value="EliminarUsuario"
                                        >
                                        Eliminar Usuario
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </dialog>

        <script src="js/main_usuario.js"></script>
    </body>
</html>
