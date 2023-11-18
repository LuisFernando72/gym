<%-- 
    Document   : menu
    Created on : Dec 18, 2022, 12:09:29 PM
    Author     : Luis Fernando Paxel
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="conexion.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

        <link rel="icon"  href="img/LogoGymicono.ico"/>
        <title>Menu</title>
        <link rel="stylesheet" href="css/estilo_menu.css"/>
        <link rel="stylesheet" href="css/estilo_form_menu.css"/>
        <script src="library/fontawesome/js/all.min.js"></script>
        <script src="library/jQuery3.js" type="text/javascript"></script>
        <script src="library/sweetalert2.min.js"></script>
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

        %>
        <script>
            let miVar = <%=IdUser01%>;
            let miVar2 = <%=idC001%>;
            // alert("El valor del contador es " + miVar2);
        </script>

        <nav>
            <div class="logo-name">
                <div class="logo-image">
                    <img src="img/LogoGym.png" alt="" />
                </div>
                <div class="nombreEmpresa">
                    <span class="logo_name">LA FÁBRICA</span>
                    <p>&#9733;Gimnasio & Cross training&#9733;</p> 
                </div>
            </div>

            <div class="menu-items">
                <ul class="nav-links">

                    <%

                        Conexion cn;

                        try {
                            cn = new Conexion();
                            cn.openConexion();
                            String query = "select * from modulos where idRol = ?;";
                            PreparedStatement parametro;
                            parametro = (PreparedStatement) cn.conexiondb.prepareStatement(query);
                            parametro.setInt(1, Integer.valueOf(idC001));
                            ResultSet rs = parametro.executeQuery();
                            while (rs.next()) {

                    %>
                    <li>
                        <a href="<%=rs.getString("link")%>" target="myFrame">
                            <div class= "<%=rs.getString("classDiv")%>" >
                                <i class= "<%=rs.getString("Icono")%>"></i>
                            </div>
                            <span class="link-name"><%=rs.getString("nombre")%></span>
                        </a>
                    </li>
                    <%

                            }
                            cn.closedConexion();
                        } catch (SQLException ex) {
                            System.out.println("error func(Modulo) " + ex);

                        }
                    %>

                </ul>

                <ul class="logout-mode">
                    <li>
                        <a href="Modulos?modulo=inicio" target="myFrame">
                            <div class="icon-background conf-ico">
                                <i class="fa-solid fa-house-user"></i>
                            </div>
                            <span class="link-name">Inicio</span>
                        </a>
                    </li>


                    <li class="mode">
                        <a href="#">
                            <div class="icon-background conf-ico">
                                <i class="fa-sharp fa-solid fa-moon"></i>
                            </div>
                            <span class="link-name" style="font-size: 13px">Modo Oscuro</span>
                        </a>
                        <div class="mode-toggle">
                            <span class="switch"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <section class="dashboard">
            <div class="top">
                <div class="sidebar-toggle">
                    <i class="fa-sharp fa-solid fa-bars"></i>
                </div>
                <div class="search-box">
                    <div class="bienvenid">
                        <i>
                            <h4 id="txtUsuario" ></h4>
                        </i>
                    </div>

                </div>

                <div class="profile">
                    <img id="txtImagenPerfil"  alt="" />
                    <ul class="profile-link show">
                        <li>
                            <a href="#" id="btnCambiarFoto"><i class="fa-solid fa-user"></i>Cambiar foto </a>
                        </li>
                        <li>
                            <a href="#" id="btnCambiarPass"><i class="fa-solid fa-gear"></i>Nueva contraseña</a>
                        </li>

                        <div class="buttonCerrarSesion">
                            <form action="CerrarSesion" method="POST">
                                <button name="btnCerrarSesion" id="btnCerrarSesion" >
                                    <i class="fa-solid fa-right-from-bracket"></i> Cerrar Sesión
                                </button>
                            </form>
                        </div>

                    </ul>
                </div>
            </div>

            <!--            IFRAME CARGANDO MODULOS-->
            <div class="dash-content">
                <iframe name="myFrame" id="myFrame" frameborder="0"></iframe>
            </div>
        </section>

        <dialog id="modalCambiarFoto">
            <div class="container">
                <div class="form">
                    <h2>Foto de perfil</h2>
                    <form action="Usuario" method="post" id="formCambiarFotoPerfil"  enctype="multipart/form-data" >
                        <div class="imageContainer">
                            <input type="text" name="idusu" id="idusu" value="<%=IdUser01%>" class="hide">
                            <input type="text" name="txtNameimg" id="txtNameimg" class="hide">
                            <input
                                class="inputFile"
                                type="file"
                                id="txtFoto"
                                name="txtFoto"
                                onchange="previsualizar(event);"
                                accept=".png, .jpg"
                                />
                            <div class="ver-imagen">
                                <img id="txtImagenUsuario" />
                            </div>
                        </div>
                        <div class="btns">
                            <div class="inputBox">
                                <button type="button" id="btnCancelarimg">Cancelar</button>
                            </div>
                            <div class="inputBox">
                                <button type="submit" id="btnAceptarimg" name="accion" value="CambiarFotoPerfil"  >Aceptar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </dialog>
        <!-- form cambiarContraseña -->
        
        
        <dialog id="modalCambiarPasswd">
            <div class="container">
                <div class="form">
                    <h2>Cambiar Contraseña</h2>
                    <form id="frmModificarPass" method="post" action="Usuario" >
                        <!-- CONTRASEÑA ACTUAL -->
                        <input type="text" name="txt_idUser"  value="<%=IdUser01%>" class="hide">
                        <div class="inputBox">
                            <label for="">Contraseña Actual</label>
                            <input
                                type="password"
                                id="paswd_actual"
                                name="paswd_actual"
                                placeholder="Contraseña actual"
                                autocomplete="on"
                                />

                            <span class="hide" id="eye-hide1">
                                <i class="fa solid fa-eye-slash"></i>
                            </span>
                            <span class="show" id="eye-show1"
                                  ><i class="fa light fa-eye ico"></i>
                            </span>
                        </div>
                        <!-- NUEVA CONTRASEÑA -->
                        <div class="inputBox">
                            <label for="">Contraseña nueva</label>
                            <input
                                type="password"
                                id="passwd1"
                                name="passwd1"
                                placeholder="Contraseña nueva"
                                autocomplete="on"
                                />
                            <p id="textError1" class="textError"></p>
                            <span class="hide" id="eye-hide2">
                                <i class="fa solid fa-eye-slash"></i>
                            </span>
                            <span class="show" id="eye-show2"
                                  ><i class="fa light fa-eye ico"></i>
                            </span>
                        </div>
                        <!-- REPETIR CONTRASEÑA -->
                        <div class="inputBox">
                            <label for="">Repetir Contraseña</label>
                            <input
                                type="password"
                                id="passwd2"
                                name="passwd2"
                                placeholder="confirmar contraseña"
                                autocomplete="on"
                                />
                            <p id="textError2" class="textError"></p>
                            <span class="hide" id="eye-hide3">
                                <i class="fa solid fa-eye-slash"></i>
                            </span>
                            <span class="show" id="eye-show3"
                                  ><i class="fa light fa-eye ico"></i>
                            </span>
                        </div>
                        <div class="btns">
                            <div class="inputBox ">
                                <button type="button" id="btnCancelar" >Cancelar</button>
                            </div>

                            <div class="inputBox ">
                                <button type="submit" id="btnAceptar" name="accion" value="ModificarPass" disabled>Aceptar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </dialog>
        <script src="js/main_menu.js"></script>
    </body>
</html>
