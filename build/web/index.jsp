<%-- 
    Document   : index
    Created on : Dec 18, 2022, 9:36:46 AM
    Author     : Luis Fernando Paxel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="icon"  href="img/LogoGymicono.ico"/>

        <title>Iniciar Sesión</title>
        <link rel="stylesheet" href="css/estilo_index.css"/>
        <script src="library/fontawesome/js/all.min.js"></script> 
        <script src="library/jQuery3.js" type="text/javascript"></script>
        <script src="library/sweetalert2.min.js" type="text/javascript"></script>
    </head>
    <body>


        <div class="container">
            <div class="forms-container">
                <div class="signin-signup">
                    <form action="IniciarSesion" method="POST" id="frm_login"  class="sign-in-form">
                        <h2 class="title">Iniciar Sesion</h2>
                        <!-- correo -->
                        <div class="input-field">
                            <div class="input-text">
                                <i class="fa fa-user-alt"></i>
                                <input type="text" id="txtCorreoUsuario" name="txtCorreoUsuario" placeholder="Correo electronico" />
                            </div>
                            <span style="font-size: 14px">@gmail.com</span>
                        </div>
                        <!-- Contraseña -->
                        <div class="input-field">
                            <div class="input-text">
                                <i class="fas fa-lock"></i>
                                <input type="password" id="txtPassword" name="txtPassword"  autocomplete="on"  placeholder="password" />
                            </div>
                            <span class="hide" id="eye-hide">
                                <i class="fa solid fa-eye-slash"></i>
                            </span>
                            <span class="show" id="eye-show"
                                  ><i class="fa light fa-eye ico"></i>
                            </span>
                        </div>

                        <input type="submit" name="accion" id="accion" value="Ingresar" class="btn solid" />
                        <!-- ICONOS REDES SOCIALES -->
                        <p class="social-text">Nuestras Redes Sociales</p>
                        <div class="social-media">
                            <a href="#" id="Ifacebook" target="_blank" class="social-icon">
                                <i class="fab fa-facebook-square"></i>
                            </a>
                            <a href="#" id="Itwitter" target="_blank" class="social-icon">
                                <i class="fa-brands fa-tiktok"></i>
                            </a>
                            <a href="#" id="Iinstragram" target="_blank"  class="social-icon">
                                <i class="fab fa-instagram-square"></i>
                            </a>

                        </div>
                    </form>
                </div>
            </div>
            <!-- PANEL LADO IZQUIERDO   -->
            <div class="panels-container">
                <div class="panel left-panel">
                    <div class="contenedorLogo">
                        <div class="logoEmpresa">
                            <img src="img/LogoLogin.png" />
                            <div class="nombreEmpresa">
                                <span class="logo_name">LA FÁBRICA</span>
                                <p>&#9733;Gimnasio & Cross training&#9733;</p>
                            </div>
                        </div>
                    </div>
                    <div class="content">
                        <h3>Bienvenido</h3>
                        <p>No eres lo que logras, eres lo que superas.</p>
                    </div>
                    <img src="img/gym_login1.svg" class="image" alt="" />
                </div>
            </div>
        </div>
        <script src="js/main_index.js"></script>  

    </body>
</html>
