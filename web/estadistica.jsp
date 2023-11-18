<%-- 
    Document   : estadistica
    Created on : Dec 18, 2022, 9:52:14 AM
    Author     : Luis Fernando Paxel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
       <link rel="icon"  href="img/LogoGymicono.ico"/>
        <title>Estadistica</title>
        <link rel="stylesheet" href="css/estilo_estadistica.css"/>
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
                response.sendRedirect("index.jsp");
            }

            if (Integer.valueOf(idC001) == 1) {
                response.sendRedirect("Modulos?modulo=dashboard");
            }


        %>
        <!-- CONTENT -->
        <section id="content">
            <main>
                <ul class="box-info">
                    <li>
                        <i class="bx fa-solid fa-person-dress"></i>
                        <span class="text">
                            <h3 id="txtMujeres"></h3>
                            <p>Mujeres</p>
                        </span>
                    </li>
                    <li>
                        <i class="bx fa-solid fa-person"></i>
                        <span class="text">
                            <h3 id="txtHombres"></h3>
                            <p>Hombres</p>
                        </span>
                    </li>
                    
                    <li>
                        <i class="bx fa-solid fa-people-arrows"></i>
                        <span class="text">
                            <h3 id="txtTotalTodo"></h3>
                            <p>Total Clientes</p>
                        </span>
                    </li>
                </ul>

                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>Entrenadores</h3>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="cuerpoTabla">

                            </tbody>
                        </table>
                    </div>
                    <div class="todo">
                        <div class="content-data">
                            <div class="head">
                                <h3>Registros por mes</h3>
                            </div>
                            <div class="chart">
                                <div id="chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <script src="js/main_estadistica.js"></script>
    </body>
</html>
