<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
    <jsp:include page="/WEB-INF/movil/template/Head.jsp"/>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("error_message");
    %>
        <div class="row">
            <div class="col-1"></div>
            <div class="col-10 main-wrapper" style="height: 100vh">
                <div class="section bg-white col-xs-9" style="justify-content: center;flex-direction: column;display: flex;height: 100%">
                    <div class="row my-auto" id="content-img">
                        <div class="col-1"></div>
                        <div class="col-10">
                            <a href="">
                                <img class="imagen" src="media/imagenes/general/logoSinFondo.png" />
                            </a>
                            <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
							    <p class="warnings"><%= errorMessage %></p>
							<% } %> 
                        </div>
                        <div class="col-1">
						</div>
                    </div>

                    <form class="my-auto" id="content" method="post" action = "login">
                    </form>
                </div>
            </div>
            <div class="col-1"></div>
        </div>
        
        <!-- funcionalidades -->
        <script src="media/js/iniciar_sesion_movil.js"></script>
    </body>
</html>