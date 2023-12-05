<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="java.util.List" %>
    
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Titulo -->
        <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
        <jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>		

    </head>
    <body>

        <!-- barra lateral y barra de arriba -->
        <jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>

                <!-- content -->
                <div class="col-10 py-4 container-fluid">
                    <h1 class="tituloPrincipal">
                        Tipos de Publicación
                    </h1>

                    <div class="py-4" id="tabla_tipos_publicacion">
                        <div class="row">
                        <%
                            List<String> nombres = (List<String>) request.getSession().getAttribute("tipos_ofertas");
                            int i = 0;
                            for (String tdo : nombres) {
                                %>
                                    <div class="col">
                                        <div class="card border-primary coloresOfertas efectoZoom" id="btn-collapse">
                                            <div class="card-body d-flex justify-content-center">
                                                <b style="text-align:center" ><%= tdo %></b>
                                                <a class="stretched-link" href="./ConsultarTipoDeOferta?tipo=<%= tdo %>"></a>
                                            </div>
                                        </div>
                                    </div>
                                <%      
                                if (i % 3 == 2) { 
                                    %>
                                        </div>
                                        <div class="row">
                                    <%
                                }
                                i++;
                            } 
                            while (i % 3 != 0) {
                                %>
                                    <div class="col" style="visibility: hidden">
                                        <div class="card border-primary">
                                            <div class="card-body d-flex justify-content-center">
                                            </div>
                                        </div>
                                    </div>
                                <%
                                i++;
                            }
                        %>
                        </div>
                    </div>
                </div>
                <!-- -->
            </div>
        </div>
        <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>    
        <script src="media/js/consulta_de_usuario.js"></script>
    </body>
</html>

