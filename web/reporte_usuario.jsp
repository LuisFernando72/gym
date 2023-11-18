<%-- 
    Document   : reporte_usuario
    Created on : Dec 18, 2022, 11:24:52 AM
    Author     : Luis Fernando Paxel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="icon"  href="img/LogoGymicono.ico"/>
        <title></title>
        <link rel="stylesheet" href="css/estilo_reporte.css"/>
        <script src="library/jQuery3.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            String email001 = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("idCl")) {
                        email001 = cookie.getValue();
                    }
                }
            }
        %>
        <script>
            let idcl = <%=email001%>;
           
        </script>

        <div class="btnAceptar">
            <button
                id="btn_aceptar"
                name="btn_aceptar"
                class="btnAc"
                onclick="printHTML()"
                >
                Opciones
            </button>
        </div>
        <div class="contenedorReporte">



            <div>
                <div id="details">
                    <div id="logo" class="logo">
                        <img src="img/LogoGym.png" />
                        <div class="nombreEmpresa">
                            <span class="logo_name">LA FÁBRICA</span>
                            <p>&#9733;Gimnasio & Cross training&#9733;</p> 
                        </div>
                    </div>

                    <div id="company" class="dtEmpresa">
                        <h2 class="name">Gimnasio La Fábrica</h2>
                        <div>Avenida Norte, Antigua Guatemala, Guatemala</div>
                        <div>(+502) 7832-9840</div>
                        <div>
                            <a href="mailto:gymlafabrika@gmail.com" target="_blank"
                               >La fabric@</a
                            >
                        </div>
                    </div>
                    <div id="client">
                        <div id="project">
                            <!-- <div class="headingf"><h2>Datos Generales</h2></div> -->
                            <div id="txtNombres">
                                <span>Cliente:</span> <span id="r_Nombre"></span>
                            </div>
                            <div>
                                <span>Correo Eletronico: </span>
                                <a target="_blank" id="txtCorreo"
                                   ></a
                                >
                            </div>
                            <div id="txtTelefono">
                                <span>No Teléfono: </span> <span id="r_telefono"></span> 
                            </div>
                            <div id="txtEdad"><span>Edad: </span > <span id="r_edad"></span></div>
                            <div id="txtAltura"><span>Estatura: </span> <span id="r_estatura"></span></div>

                            <div id="txtNombresEm">
                                <span><b> Caso de emeregencia:</b> </span><span id="r_nombresE"></span> 
                            </div>
                            <div id="txtTelefonoEm">
                                <span>No Teléfono: </span> <span id="r_telefonoE"></span>
                            </div>
                        </div>
                    </div>
                    <div id="invoice">
                        <h1>Ficha técnica de clientes</h1>
                        <div class="date" > <span id="fechaI"> </span> </div>

                    </div>
                </div>
                <div class="ContainerHistorialClinico">
                    <div class="headingf"><h2>Historial Clinico</h2></div>
                    <div id="PreguntasCC" class="HistorialClinico">





                    </div>
                </div>

                <!-- OBSERVACIONES -->
                <div class="cObservaciones">
                    <div class="headingf"><h2>Observaciones</h2></div>
                    <div class="cObs">
                        <span id="txtobservaciones"
                              ></span
                        >
                    </div>
                </div>

                <!-- CONDICIONES DE ENERGIA -->
                <div class="condiciones-energia">
                    <div class="headingf"><h2>¿Cómo considera su nivel de?</h2></div>
                    <div class="containerCodiciones">
                        <div class="cPregunta">
                            <span> 1) ¿Cómo considera su salud?</span>
                            <span id="cond1"></span>
                        </div>

                        <div class="cPregunta">
                            <span> 2) ¿Cómo considera su condición física?</span>
                            <span id="cond2"></span>
                        </div>

                        <div class="cPregunta">
                            <span>3) ¿Cómo considera su condición nutricional?</span>
                            <span id="cond3"></span>
                        </div>
                        <div class="cPregunta">
                            <span>4) ¿Cómo considera su condición de estrés?</span>
                            <span id="cond4"></span>
                        </div>
                    </div>
                </div>
                <!-- Tabla CONTROL PESO -->
                <div class="ContainerTablePeso">
                    <div class="headingf"><h2>Control Peso</h2></div>
                    <div class="TablaCpeso">
                        <table id="tablePeso" class="tablePeso">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Peso</th>
                                    <th>Grasa corporal</th>
                                    <th>% agua</th>
                                    <th>M. muscular</th>
                                    <th>Valor fisico</th>
                                    <th>M. basal</th>
                                    <th>Edad metabolica</th>
                                    <th>Masa osea</th>
                                    <th>Grasa visceral</th>
                                    <th>Hora y fecha</th>
                                </tr>
                            </thead>
                            <tbody id="containerCPeso" >

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- Tabla CONTROL PESO -->
                <div class="ContainerTablePeso">
                    <div class="headingf"><h2>Medidas Corporales</h2></div>
                    <div class="TablaCpeso">
                        <table id="tablePeso" class="tablePeso">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Pectoral</th>
                                    <th>Dorsal</th>
                                    <th>Biceps</th>
                                    <th>Antebrazos</th>
                                    <th>Cintura</th>
                                    <th>Cadera</th>
                                    <th>Muslo</th>
                                    <th>Pantorrilla</th>
                                    <th>Hora y fecha</th>
                                </tr>
                            </thead>
                            <tbody id="containerMedidas">


                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="footer">
                <h5>Gimnasio la fábrica, una familia.</h5>
            </div>
        </div>
        <script src="js/main_reporte_usuario.js"></script>
    </body>
</html>
