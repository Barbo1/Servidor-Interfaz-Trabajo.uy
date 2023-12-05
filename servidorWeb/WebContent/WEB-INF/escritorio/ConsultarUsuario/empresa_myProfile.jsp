<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.time.format.DateTimeFormatter,java.util.Collection, publicador.*,java.time.LocalDate,java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Consulta de usuario</title>
		<jsp:include page="/WEB-INF/escritorio/template/Head.jsp"></jsp:include>
	</head>
	<body>
	<!-- barra lateral y barra de arriba -->
		<% DtEmpresaCompleto data = (DtEmpresaCompleto) request.getAttribute("dataUserEmpresa");
			DtEmpresa dte = data.getEmpresa();
		%>
		
	    <jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
	    <div class="container-fluid">
	    	<div class="row">
	    	    <jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
	    		<div class = "col-1 py-4 px-3"></div>
                <div class="col-9 py-4 px-3">
                    <div class = "row">
                        <div class="col-2"></div>
                        <div class = "col-2 py-4 px-3" id = "imagen"><img src='Imagenes?id=<%= dte.getImagen() %>&tipo=usuarios' class = 'imagenOferta'></div>
                        <div class = "col-3"></div>
                        <div class = "col-4 py-4 px-3 margenTitulo">
                            <div class = "row titulo centrar" id = "nombreCompleto"><%= dte.getNombre() + " " + dte.getApellido()%></div>
                            <div class = "row subtitulo centrar" id = "otrosDatos"><%= dte.getNickname() +" / " + dte.getCorreoElectronico() %></div>
                        </div>
                        
                    </div>
                    <%
                    List<DtUsuario> seguidos = dte.getSeguidos();
                    List<DtUsuario> seguidores = dte.getSeguidores();
                    %>
                    <div class = "row">
                        <div class = "wrap">
                            <ul class= "tabs">
                                <li><a href="#perfil"><span class = "tab-text">Perfil</span></a></li>
                                <li><a href="#ofertas"><span class = "tab-text">Ofertas publicadas</span></a></li>
                                <li><a href="#paquetes"><span class = "tab-text">Paquetes adquiridos</span></a></li>
                                <li><a href="#seguidos"><span class = "tab-text">Seguidos <%= seguidos.size()%></span></a></li>
                               	<li><a href="#seguidores"><span class = "tab-text">Seguidores <%= seguidores.size() %></span></a></li>
                                <li class="editar"><a href="#editar"><span class ="tab-text"><img src="media/imagenes/general/editarCampo.png" class="imagenEditar"></a></li>
                            </ul>
                            <div class="secciones">
                                <article id = "perfil">
                                    <h4>Nickname: </h4><p id = "nickname" class="texto"><%= dte.getNickname() %></p>
                                    <h4>Nombre: </h4><p id = "nombre" class="texto"><%= dte.getNombre() %></p>
                                    <h4>Apellido: </h4><p id = "apellido" class="texto"><%= dte.getApellido() %></p>
                                    <h4>E-mail: </h4><p id = "mail" class="texto"><%= dte.getCorreoElectronico() %></p>
                                    <h4>Sitio web: </h4><p id = "link" class="texto"><%= dte.getLink() %></p>
                                    <h4>Descripción: </h4><p id = "descripcion" class="texto"><%= dte.getDescripcion() %></p>
                                </article>
                                <article id = "ofertas">
                                <% 
                                List<DtOferta> ofertas = (List<DtOferta>) data.getDtofertas();
                                LocalDate fechaActual = LocalDate.now();
		        				for(DtOferta oferta: ofertas){  
		        				%>
                                    <a href='consulta-oferta?name=<%=oferta.getNombre()%>' class="sinHipervinculo">
                                        <div class='row efectoZoom coloresOfertas centrar'>
                                            <div class='col-3 px-0'>
                                                <img src='Imagenes?id=<%=oferta.getImagen()%>&tipo=ofertas' class='imagenOfertaIndex'></img>
                                            </div>
                                            <div class='col-9'>
                                                <div>
                                                    <p class="tituloOferta" id="encabezadoOferta"><%= oferta.getNombre() %></p>
                                                </div>
                                                <div>
                                                    <p id="descpOferta"><%= oferta.getDescripcion() %></p>
                                                </div>
                                                <div class="row">
                                                
                                                <%if(oferta.getEstado().equals(EstadoOferta.CONFIRMADA)){ 
                                                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                		        			        LocalDate fecha = LocalDate.parse(oferta.getExpiracion(), formatter);
                                                	if(fechaActual.compareTo(fecha) <= 0){%> <!-- Confirmada y vigente -->
		                                        		<button type="button" class="tags activo col-2">Activa</button>      
                                                	<%}else{%> <!-- Confirmada y vencida -->
                                                		<button type="button" class="tags vencido col-2">Vencida</button>
                                                <%}}else if(oferta.getEstado().equals(EstadoOferta.INGRESADA)){ %>
                                                		<button type="button" class="tags proceso col-2">Ingresada</button>
                                               	<%}else if(oferta.getEstado().equals(EstadoOferta.RECHAZADA)){ %>
                                                		<button type="button" class="tags vencido col-2">Rechazada</button>
                                                <%}else{%> <!-- Finalizada -->
                                                	     <button type="button" class="tags vencido col-2">Finalizada</button>                                               	
                                                <%} %>
                                                </div>
                                            </div>
                                        </div>
                                    </a> 
                                    <% } %>
                                </article>
                                <article id = "paquetes">
                                <% 
                                
                                Collection<DtPaquete> paquetes = (Collection<DtPaquete>) data.getPaquetes();
		        				for(DtPaquete paquete: paquetes){ 
		        					int duracion = paquete.getValidez();
		        					LocalDate fechaAhora= LocalDate.now();
		        					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		        			        LocalDate fecha = LocalDate.parse(paquete.getFechaAlta(), formatter);
		        					LocalDate fechaVencimiento = fecha.plusDays(duracion);
		        				%>
                                    <a href='ConsultarPaquete?paquete=<%=paquete.getNombre() %>' class="sinHipervinculo">
                                        <div class='row efectoZoom coloresOfertas centrar'>
                                            <div class='col-3 px-0'>
                                                <img src='Imagenes?id=<%= paquete.getImagen() %>&tipo=paquetes' class='imagenOfertaIndex'></img>
                                            </div>
                                            <div class='col-9'>
                                                <div>
                                                    <p class="tituloOferta" id="encabezadoOferta"><%= paquete.getNombre() %></p>
                                                </div>
                                                <div>
                                                    <p id="descpOferta"><%= paquete.getDescripcion() + ". Fue adquirido el " + paquete.getFechaAlta() + " con un costo de " + paquete.getCosto()%></p>
                                                </div>
                                                <div class="row">
                                                	<% if(fechaActual.compareTo(fechaVencimiento) <= 0){%>
		                                        		<button type="button" class="tags activo col-2">Activo</button>      
                                                <%}else{%>
                                                		<button type="button" class="tags vencido col-2">Vencido</button>
                                                <%}%>
                                                </div>
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
                                	<form id="formulario1" method= "post" action="modificar-usuario" enctype="multipart/form-data" >
                                	
	                                    <h4>Nombre: </h4><input class="campo" type="text" value="<%= dte.getNombre() %>" id="nombreEmpresa" name="nombre">
	                                    <p class ="warnings" id="warningNombre">Ingrese un nombre</p><br>
	                                    
	                                    <h4>Apellido: </h4><input class="campo" type="text" value="<%= dte.getApellido() %>" id="apellidoEmpresa" name="apellido">
	                                    <p class ="warnings" id="warningApellido">Ingrese su apellido</p><br>
	                                    
					                    <h4>Ingrese su nueva contraseña: </h4><input class = "campo" type="password" id ="password" name="password">
					                    <p class ="warnings" id="warningPassword">Ingrese su contraseña</p><br>
					                    
					                    <h4>Repita su nueva contraseña: </h4><input class = "campo" type="password" id ="checkpassword" name="checkpassword">
					                    <p class ="warnings" id="warningCheckpassword">Las contraseñas no coinciden</p><br>
					                    
	                                    <h4>Link: </h4><input class="campo" type="text" value="<%= dte.getLink() %>" id="linkEmpresa" name="link">
	                                    <p class ="warnings" id="warningLink">Ingrese un link valido</p><br>	    
	                                                                   
	                                    <h4>Descripción: </h4><textarea class="campo" rows="6" cols="30" id="descripcionEmpresa" name="descripcion"><%= dte.getDescripcion() %></textarea>
	                                    <p class ="warnings" id="warningDescripcion">Ingrese una descripción</p><br>
	                                    
	                                    <label class = "labels">Ingresar imagen de perfil</label>
					                    <br>
										<img id="uploadedImage" src="Imagenes?id=<%= dte.getImagen() %>&tipo=usuarios" alt="Imagen cargada" class="imagenOferta">
										<br><br>
										<input class = "inputImagen" type="file" id ="fileInput" name="imagen" accept = "image/*">
										<br><br>
	                                    <button onclick="validarEmpresa()" class = "col-4 btn botonseleccionar btn-lg" id="btnEditarEmpresa">Aplicar cambios</button>
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