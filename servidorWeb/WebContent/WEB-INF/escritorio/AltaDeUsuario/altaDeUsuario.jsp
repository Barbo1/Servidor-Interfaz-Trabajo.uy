<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/escritorio/template/Head.jsp"></jsp:include>

<title>Alta de usuario</title>

</head>
<body>
<% 
String errorMessage = (String) request.getAttribute("error_message");
String errorMessageNick = (String) request.getAttribute("error_message_nick");
String errorMessageEmail = (String) request.getAttribute("error_message_email");
String nicknameValue = (request.getParameter("nickname") != null) ? request.getParameter("nickname") : "";
String correoValue = (request.getParameter("correo") != null) ? request.getParameter("correo") : "";
String nombreValue = (request.getParameter("nombre") != null) ? request.getParameter("nombre") : "";
String apellidoValue = (request.getParameter("apellido") != null) ? request.getParameter("apellido") : "";
String descripcionValue = (request.getParameter("descripcion") != null) ? request.getParameter("descripcion") : "";
String nacionalidadValue = (request.getParameter("nacionalidad") != null) ? request.getParameter("nacionalidad") : "";
String nacimientoValue = (request.getParameter("nacimiento") != null) ? request.getParameter("nacimiento") : "";
String linkValue = (request.getParameter("link") != null) ? request.getParameter("link") : "";
String errorNickExiste = "Nickname ya registrado";
String errorEmailExiste = "Correo ya registrado";

%>
<div class = "row">
            <div class="col-3"></div>
            <div class="col-6"> 
                <form id="formulario" class = "formu" method="post" enctype="multipart/form-data" action="crear-usuario">
                    <div class = "row">
                        <div class = "col-3"></div>
                        <a class="col-6" href="./home"><img class="imagen"src="media/imagenes/general/logoSinFondo.png" /></a><br><br>
                        <div class = "col-3"></div>
                    </div><br>
                    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
					    <p class="warnings"><%= errorMessage %></p>
					<% } %> 
                    <p class ="warnings" id="warningNickExistente"><%= errorNickExiste %></p>
                    <label class = "labels">Nickname</label><br>
                    
                    <input class = "campo" type="text" id ="nickname" name="nickname" value="<%=nicknameValue %>">
                    <p class ="warnings" id="warningNickname">Ingrese su nickname</p><br>
                    <% if (errorMessageNick != null && !errorMessageNick.isEmpty()) { %>
					    <p class="warnings"><%= errorMessageNick %></p>
					<% } %>  
                    <label class = "labels">Nombre</label><br>
                    <input class = "campo" type="text" id ="nombre" name="nombre" value="<%=nombreValue %>">
                    <p class ="warnings" id="warningNombre">Ingrese su nombre</p><br>
                    <label class = "labels">Apellido</label><br>
                    <input class = "campo" type="text" id ="apellido" name="apellido" value="<%=apellidoValue %>">
                    <p class ="warnings" id="warningApellido">Ingrese su apellido</p><br>
                    <p class ="warnings" id="warningEmailExistente"><%= errorEmailExiste %></p>
                    <label class = "labels">Correo</label><br>
                    
                    <input class = "campo" type="email" id ="email" novalidate = "true" name="email" value="<%=correoValue %>">
                    <p class ="warnings" id="warningEmail">Ingrese un email valido</p><br>
                    <% if (errorMessageEmail != null && !errorMessageEmail.isEmpty()) { %>
					    <p class="warnings"><%= errorMessageEmail %></p>
					<% } %>
                    <div id="error_message_email" class="warnings"></div>  
                    <label class = "labels">Contraseña</label><br>
                    <input class = "campo" type="password" id ="password" name="password">
                    <p class ="warnings" id="warningPassword">Ingrese su contraseña</p><br>
                    <label class = "labels">Confirmar contraseña</label><br>
                    <input class = "campo" type="password" id ="checkpassword" name="checkpassword">
                    <p class ="warnings" id="warningCheckpassword">Las contraseñas no coinciden</p><br>

                    <!-- Imagen -->
                    <label class = "labels">Ingresar imagen de perfil</label>
                    <br>
					<img id="uploadedImage" src="Imagenes?id=default.png&tipo=usuarios" alt="Imagen cargada" class="imagenOferta">
					<br><br>
					<input class = "inputImagen" type="file" id ="fileInput" name="imagen" accept = "image/*">
					        
            
                    <div class = "row">
                        <div class = "col-2"></div>
                        <input class = "col-3 btn botonseleccionar btn-lg" type= "button" id = "btnEmpresa" value="Empresa">
                        <div class = "col-2"></div>
                        <input class = "col-3 btn botonseleccionar btn-lg" type= "button" id = "btnPostulante" value="Postulante"><br><br>
                        <div class = "col-2"></div>
                    </div>
                    
                    <!-- Campos de empresa -->
                    <div id = "camposEmpresa">
                        <label class = "labels">Link</label><br>
                        <input class = "campo" type="text" id ="link" name="link" value="<%=linkValue %>"><br>
                        <p class ="warnings" id="warningLink">Ingrese un link valido</p><br>
                        <label class = "labels">Descripción</label><br>
                        <textarea class = "campo" id ="descripcion" rows="6" cols="30" name="descripcion" ><%=descripcionValue %></textarea><br>
                        <p class ="warnings" id="warningDescripcion">Ingrese una descripción</p><br>
                    </div>

                    <!-- Campos de postulante -->
                    <div id = "camposPostulante">
                        <label class = "labels">Nacionalidad</label><br>
                        <input class = "campo" list ="nacionalidades" id ="nacionalidad" name="nacionalidad" value="<%=nacionalidadValue %>"><br>
                        <datalist id="nacionalidades">
                            <option value= "Uruguayo/a"></option>
                            <option value= "Argentino/a"></option>
                            <option value= "Chileno/a"></option>
                            <option value= "Brasilero/a"></option>
                            <option value= "Colombiano/a"></option>
                            <option value= "Peruano/a"></option>
                            <option value= "Venezolano/a"></option>
                            <option value= "Cubano/a"></option>
                            <option value= "Sirio/a"></option>
                            <option value= "Paraguayo/a"></option>
                            <option value= "Español/a"></option>
                        </datalist>
                        <p class ="warnings" id="warningNacionalidad">Ingrese su nacionalidad</p><br>
                        <label class = "labels">Nacimiento</label><br>
                        <input class = "campo" type="date" id ="nacimiento" name="nacimiento" value="<%=nacimientoValue %>"><br>
                        <p class ="warnings" id="warningNacimiento">Ingrese su fecha de nacimiento</p><br>
                    </div>

                    <div class="row">
                        <div class="col-4"></div>
                        <button onclick="validarFormulario()" class = "col-4 btn botonseleccionar btn-lg" id="btnCrear">Crear cuenta</button>
                    </div><br><br>
                    <p class="labels">¿Ya tenes cuenta?  <A class= "hipervinculo" href="./iniciar-sesion">Iniciar sesión</A></p>
                </form>
            </div>
            <div class = "col-3"></div>
    </div>
    <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>
    
    <script src="media/js/alta_de_usuario.js"></script>
</body>
</html>