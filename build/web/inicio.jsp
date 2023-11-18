<%-- 
    Document   : inicio
    Created on : Dec 18, 2022, 11:12:37 AM
    Author     : Luis Fernando Paxel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="icon"  href="img/LogoGymicono.ico"/>
        <title>Inicio</title>
        <link rel="stylesheet" href="css/estilo_inicio.css"/>
        <script src="library/fontawesome/js/all.min.js"></script>
        <script src="library/jQuery3.js" type="text/javascript"></script>

    </head>
    <body>

        <%
            String userName = null;
            String idC001 = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("IdUser01")) {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("idC001")) {
                        idC001 = cookie.getValue();
                    }

                }
            }
            if (userName == null) {
                response.sendRedirect("Modulos?modulo=inicio");
            }

        %>
        <div class="container">
            <div class="forms-container">
                <div class="container-right">
                    <form action="#" class="sign-in-form">

                        <img class="image-right" src="img/ImgInicio.svg" alt="" />

                        <!-- ICONOS REDES SOCIALES -->
                        <div class="social-media">
                            <a href="#" id="Ifacebook" target="_blank" class="social-icon">
                                <i class="fab fa-facebook-square"></i>
                            </a>
                            <a href="#" id="Itwitter" target="_blank" class="social-icon">
                                <i class="fa-brands fa-tiktok"></i>
                            </a>
                            <a href="#" id="Iinstragram" target="_blank" class="social-icon">
                                <i class="fab fa-instagram-square"></i>
                            </a>

                        </div>
                    </form>
                </div>
            </div>
            <!-- PANEL LADO IZQUIERDO   -->
            <div class="panels-container">
                <div class="panel left-panel">
                    <div class="content">
                        <p id="txtFraseI" >

                        </p>
                        <i class="Autorcs" id="txtAutorI" > <small> </small></i>
                    </div>
                    <img src="img/Arnold.png" class="image" alt="" />
                </div>
            </div>
        </div>
        <script src="js/main_inicio.js"></script>
    </body>
</html>
