<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="publicador.DtPostulanteCompleto,java.util.Collection, publicador.DtPostulante, java.time.*,publicador.DtUsuario,java.util.List,java.util.ArrayList" %>
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
			boolean recargar = (request.getAttribute("recargar") != null) ? true : false; 

		%>
		<script type="text/javascript"> if (<%= recargar%>){
										location.reload(true);
										recargar = false;
										}	</script>
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
	                            <%if (!request.getSession().getAttribute("estado_sesion").equals("VISITANTE")){ %>    
	                        	<div class = "row d-flex justify-content-center">
	                            <%
	                            	DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
	                				List<DtUsuario> seguidosUser = user.getSeguidos();
	                			
	                            	boolean sigue = false;
	                            	for(DtUsuario aux: seguidosUser) {
	                            		if(aux.getNickname().equals(dtp.getNickname())) {
	                            			sigue = true;
		                            	}
		                            }
	                          	  
	    	                     	if (sigue) { %>
                	            		<img class="imagen-follow pt-3" id="botonSeguir" src="media/imagenes/general/seguir.svg" height="60" width="60"/>
                       				<%} else { %>
                            			<img class="imagen-unfollow pt-3" id="botonSeguir" src="media/imagenes/general/seguir.svg" height="60" width="60"/>	
	                        		<%} %>
                        			
                        			<script> const nickSeguir = "<%=dtp.getNickname()%>"; </script>
			                        <script src="media/js/mo.js"></script>
			                        <script src="media/js/seguir.js"></script>
	        	                </div>                       	
                            <%} %>
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
	                                <li><a href="#seguidos"><span class = "tab-text">Seguidos <%= seguidos.size()%></span></a></li>
                               		<li><a href="#seguidores"><span class = "tab-text">Seguidores <span id="contadorSeguidores"><%= seguidores.size() %></span></span></a></li>
	
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
	                                <% 
                                	DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario_logueado"); 
                               	 	List<DtUsuario> seguidores_otros = new ArrayList<DtUsuario>();
                                	boolean yo_en_seguidores = false;
                                	for(DtUsuario usuario: seguidores) {
                                    	if (user != null && usuario.getNombre().equals(user.getNombre())) {
                                    		yo_en_seguidores = true;
                                    	} else {
                                        	seguidores_otros.add(usuario);
                                    	}
                                	}
                                	if (yo_en_seguidores) {
                                    	%>
                                    	<a href="consulta-usuario?user=<%= user.getNickname() %>" class="sinHipervinculo">
                                        	<div class='row efectoZoom coloresOfertas centrar'>
                                            	<div class='col-3 px-0'>
                                                	<img src='Imagenes?id=<%= user.getImagen() %>&tipo=usuarios' class='imagenOfertaIndex'></img>
                                            	</div>
                                            	<div class='col-9'>
                                                	<div>
                                                    	<p class="tituloOferta" id="encabezadoOferta"><%= user.getNombre() + " " + user.getApellido() + " / " + user.getNickname() %></p>
                                                	</div>
                                            	</div>
                                        	</div>
                                    	</a> 
                                    	<%
                                	}
                                	for(DtUsuario usuario: seguidores_otros) {
                                    	%>
                                    	<a href="consulta-usuario?user=<%= usuario.getNickname() %>" class="sinHipervinculo">
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
                                    <% } %>
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