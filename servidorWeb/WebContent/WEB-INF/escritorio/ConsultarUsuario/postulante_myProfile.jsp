<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="publicador.DtUsuario,java.util.List,publicador.DtPostulanteCompleto,java.util.Collection, publicador.DtPostulante, publicador.DtOferta, java.text.SimpleDateFormat,java.time.LocalDate" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Consulta de usuario</title>
		<jsp:include page="/WEB-INF/escritorio/template/Head.jsp"></jsp:include>
	</head>
	<body>
	<!-- barra lateral y barra de arriba -->
		<% DtPostulanteCompleto data = (DtPostulanteCompleto) request.getAttribute("dataUserPostulante");
			DtPostulante dtp = data.getPostulante();
			String fecha = dtp.getNacimiento();
			String[] fecha2 = fecha.split("-");
			fecha = fecha2[2] + "-" + fecha2[1] + "-" + fecha2[0];
		%>
	    <jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
	    <div class="container-fluid">
	    	<div class="row">
	    	    <jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
	    		<div class = "col-1 py-4 px-3"></div>
	                <div class="col-9 py-4 px-3">
	                    <div class = "row">
	                        <div class="col-2"></div>
	                        <div class = "col-2 py-4 px-3" id = "imagen"><img src='Imagenes?id=<%= dtp.getImagen() %>&tipo=usuarios' class = 'imagenOferta'></div>
	                        <div class = "col-3"></div>
	                        <div class = "col-4 py-4 px-3 margenTitulo">
	                            <div class = "row titulo centrar" id = "nombreCompleto"><%= dtp.getNombre() + " " + dtp.getApellido()%></div>
	                            <div class = "row subtitulo centrar" id = "otrosDatos"><%= dtp.getNickname() +" / " + dtp.getCorreoElectronico() %></div>
	                        </div>
	                    </div>
	                     <%
		                    List<DtUsuario> seguidos = dtp.getSeguidos();
		                    List<DtUsuario> seguidores = dtp.getSeguidores();
		                    %>
	                    <div class = "row">
	                        <div class = "wrap">
	                            <ul class= "tabs">
	                                <li><a href="#perfil"><span class = "tab-text">Perfil</span></a></li>
	                                <li><a href="#postulaciones"><span class = "tab-text">Postulaciones</span></a></li>
	                                <li><a href="#seguidos"><span class = "tab-text">Seguidos <%= seguidos.size()%></span></a></li>
                               		<li><a href="#seguidores"><span class = "tab-text">Seguidores <%= seguidores.size() %></span></a></li>
                                	<li class="editar"><a href="#editar"><span class ="tab-text"><img src="media/imagenes/general/editarCampo.png" class="imagenEditar"></a></li>
	                            </ul>
	                            <div class="secciones">
	                                <article id = "perfil">
	                                    <h4>Nickname: </h4><p id = "nickname" class="texto"><%= dtp.getNickname() %></p>
	                                    <h4>Nombre: </h4><p id = "nombre" class="texto"><%= dtp.getNombre()%></p>
	                                    <h4>Apellido: </h4><p id = "apellido" class="texto"><%= dtp.getApellido() %></p>
	                                    <h4>E-mail: </h4><p id = "mail" class="texto"><%= dtp.getCorreoElectronico() %></p>
	                                    <h4>Nacionalidad: </h4><p id = "nacionalidad" class="texto"><%= dtp.getNacionalidad() %></p>
	                                    <h4>Fecha de nacimiento: </h4><p id = "fecha" class="texto"><%= dtp.getNacimiento() %></p>
	                                </article>
	                                <article id = "postulaciones">
	                                <% 
	                                List<DtOferta> ofertas = (List<DtOferta>) data.getDtofertas();
			        				for(DtOferta oferta: ofertas){  
			        				%>
	                                    <a href='consulta-postulacion?postulante=<%=dtp.getNickname()%>&oferta=<%=oferta.getNombre()%>' class="sinHipervinculo">
	                                        <div class='row efectoZoom coloresOfertas centrar'>
	                                            <div class='col-3 px-0'>
	                                                <img src='Imagenes?id=<%= oferta.getImagen() %>&tipo=ofertas' class='imagenOfertaIndex'></img>
	                                            </div>
	                                            <div class='col-9'>
	                                                <div>
	                                                    <p class="tituloOferta" id="encabezadoOferta"><%= oferta.getNombre()%></p>
	                                                </div>
	                                                <div>
	                                                    <p id="descpOferta"><%= oferta.getDescripcion() %></p>
	                                                </div>
				                   		 			<%if (!oferta.getFechaResultado().equals("01-01-0001")){ %>          
		                                                <div class="row">
		                                                	<button type="button" class="tags activo col-4">Resultado disponible</button>      
		                                                </div>
	                                                <%} %>
	                                            </div>
	                                        </div>
	                                    </a>
	                                    <%} %>
	                                </article>
	                                <article id = "seguidos">
	                                <% for(DtUsuario usuario: seguidos){%>
	                                    <a href='consulta-usuario?user=<%=usuario.getNickname()%>' class="sinHipervinculo">
	                                        <div class='row efectoZoom coloresOfertas centrar'>
	                                            <div class='col-3 px-0'>
	                                                <img src='Imagenes?id=<%= usuario.getImagen() %>&tipo=usuarios' class='imagenOfertaIndex'></img>
	                                            </div>
	                                            <div class='col-9'>
	                                                <div>
	                                                    <p class="tituloOferta" id="encabezadoOferta"><%= usuario.getNombre() + " " + usuario.getApellido() + " / " + usuario.getNickname() %></p>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </a> 
	                                <%}%>
	                                </article>
	                                <article id = "seguidores">
	                                <% for(DtUsuario usuario: seguidores){%>
	                                    <a href='consulta-usuario?user=<%=usuario.getNickname()%>' class="sinHipervinculo">
	                                        <div class='row efectoZoom coloresOfertas centrar'>
	                                            <div class='col-3 px-0'>
	                                                <img src='Imagenes?id=<%= usuario.getImagen() %>&tipo=usuarios' class='imagenOfertaIndex'></img>
	                                            </div>
	                                            <div class='col-9'>
	                                                <div>
	                                                    <p class="tituloOferta" id="encabezadoOferta"><%= usuario.getNombre() + " " + usuario.getApellido() + " / " + usuario.getNickname() %></p>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </a> 
	                                <%}%>
	                                </article>
	                                <article id="editar">
	                               
		                                <form id="formulario2" method="post" action="modificar-usuario" enctype="multipart/form-data" >
		                                
		                                    <h4>Nombre: </h4><input class="campo" type="text" value="<%= dtp.getNombre() %>" id="nombrePostulante" name="nombre">
		                                    <p class ="warnings" id="warningNombrePostulante">Ingrese su nombre</p><br>
		                                    
		                                    <h4>Apellido: </h4><input class="campo" type="text" value="<%= dtp.getApellido() %>" id="apellidoPostulante" name="apellido">
		                                    <p class ="warnings" id="warningApellidoPostulante">Ingrese su apellido</p><br>
		                                    
		                                    <h4>Ingrese su nueva contraseña: </h4><input class = "campo" type="password" id ="password" name="password">
						                    <p class ="warnings" id="warningPassword">Ingrese su contraseña</p><br>
						                    
						                    <h4>Repita su nueva contraseña: </h4><input class = "campo" type="password" id ="checkpassword" name="checkpassword">
						                    <p class ="warnings" id="warningCheckpassword">Las contraseñas no coinciden</p><br>
					                    
		                                    <h4>Nacionalidad: </h4><input class="campo" type="text" value="<%= dtp.getNacionalidad()%>" id="nacionalidadPostulante" name="nacionalidad">
		                                    <p class ="warnings" id="warningNacionalidadPostulante">Ingrese su nacionalidad</p><br>
		                                    
		                                    <h4>Fecha de nacimiento: </h4><input class="campo" type="date" value="<%= fecha %>" id="fechaPostulante" name="nacimiento">
		                                    <p class ="warnings" id="warningFechaPostulante">Ingrese una fecha valida</p><br>
		                                    <label class = "labels">Ingresar imagen de perfil</label>
					                    	<br>
											<img id="uploadedImage" src="Imagenes?id=<%= dtp.getImagen() %>&tipo=usuarios" alt="Imagen cargada" class="imagenOferta">
											<br><br>
											<input class = "inputImagen" type="file" id ="fileInput" name="imagen" accept = "image/*">
											<br><br>
	                                    <button onclick="validarPostulante()" class = "col-4 btn botonseleccionar btn-lg" id="btnEditarEmpresa">Aplicar cambios</button>
		                                </form>
	                                </article>
	                            </div>
	                        </div>
	                    </div>
	                </div>             
			    </div>
			</div>
			<jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>
			<script src="media/js/consulta_de_usuario.js"></script>
	</body>
</html>