<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
    	<jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>	
    	
        <!-- CSS -->
        
        <link rel="stylesheet" href="media/css/alta_de_oferta.css">


        <title>Iniciar Sesión</title>
    </head>
    <body>
    <%
    String errorMessage = (String) request.getAttribute("error_message");
    String nicknameValue = (request.getParameter("nickname") != null) ? request.getParameter("nickname") : "";
    %>
        <div class = "row">
            <div class="col-3"></div>
            <div class="col-6 bg-white">
                <form class="formulario" class="formu" method="post" action = "login">
                    <div class="row pt-5">
                        <div class="col-3"></div>
                        <a class="col-6" href="./home"><img class="imagen" src="media/imagenes/general/logoSinFondo.png" /><br><br></a>
                        <div class="col-3"></div>
                    </div>

                    <label class = "labels">Nickname</label><br>
                    <input class = "campo" type="text" id ="nickname" name="nickname" value="<%=nicknameValue %>">
                    <label class="labels">Contraseña</label>
                    <input class="campo" type="password" id="password" name="password">
					<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
					    <p class="warnings"><%= errorMessage %></p>
					<% } %> 
                    <div class="row pb-3">
                        <div class="row pt-3">
                            <div class="col d-flex justify-content-center">
                                <button class="btn botonseleccionar btn-lg" onclick= "submit()">Iniciar sesión</button><br><br>
                            </div>
                        </div>
                        <p class="labels">¿No tenes cuenta? <a class="hipervinculo" href="alta-usuario">Crear Usuario</a></p>
                    </div>
                </form>
            </div>
            <div class="col-3"></div>
        </div>
        
    </body>
</html>