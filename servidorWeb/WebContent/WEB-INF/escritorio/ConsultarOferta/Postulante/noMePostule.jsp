<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="publicador.* , java.util.Collection, java.util.List" %>
<!DOCTYPE html>
<html lang="es">
    <head>
         <link rel="stylesheet" href="media/css/consulta_de_oferta.css" text="text/css">
    
        <!-- Titulo -->
        <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
        <jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>		

    </head>
    <body>
    	<!-- Obtengo la infromacion de la oferta y de la empresa que publico la oferta -->
    	<% 	
    		DtEmpresaCompleto dataEmpresaComp = (DtEmpresaCompleto) request.getAttribute("dataEmpresa");
    		DtEmpresa dataEmpresa = dataEmpresaComp.getEmpresa();
    		DtOfertaCompleto dataOfertaComp = (DtOfertaCompleto) request.getAttribute("dataOfertaComp");
    		DtOferta dataOferta = dataOfertaComp.getOferta();
    		int remune = Math.round(dataOferta.getRemuneracion()); // castea de float a int
    		boolean tieneFavorito = (boolean) request.getAttribute("tieneFavorito");
    		
		%>
        <!-- barra lateral y barra de arriba -->
        <jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
				<!-- AQUI VA EL CONTENIDO DEL CU CONSULTA DE OFERTA -->
	            <div class="col py-4 px-3">
	                <div class="row">
	                    <!-- Aqui va la info de la oferta -->
	                    <div class="col-8 py-4">
	                        <div class="row ">
	                            <div class="col-1"></div>
	                            <div class="col-4 py-4 px-3" id="imagen"><img src='Imagenes?id=<%= dataOferta.getImagen() %>&tipo=ofertas' class='imagenOferta'></div>
	                            <div class="col-1"></div>
	                            <div class="col-6">
	                                <div class="row-3 pt-4 titulo" id="nombreOferta"><%= dataOferta.getNombre()%></div>
	                                <div class="row-3 subtitulo" id="publicadaPor"><%= dataOferta.getEmpresa() + " / " + dataEmpresa.getCorreoElectronico()%></div>
	                                <div class="row-6 d-flex">	                                
	                                	<% if (!tieneFavorito) { %>
	                                		<a class="px-3 pt-3">
	                                			<img class="NoFavorito" id="botonFavorito" src="media/imagenes/general/favorito.svg" height="50" width="50"/>
	                                		</a>	                  
	     	                        	<% } else { %>
	     	                        		<a class="px-3 pt-3">
	                                			<img class="Favorito" id="botonFavorito" src="media/imagenes/general/favorito.svg" height="50" width="50"/>
	                                		</a>
		                            	<% } %> 
		                            		<div class="pt-4">Favoritos: <span id="reputacion"><%= dataOferta.getReputacion() %></span> </div>	
	                                </div>
	                                <script src="media/js/mo.js"></script>
		                            <script src="media/js/ofertas_favoritas.js"></script>
	                            </div>
	                            
	                        </div>
	                        <div class="contenedorInfoOferta">
	                            <h4>Descripcion: </h4>
	                            <p id="desc" class="texto"><%= dataOferta.getDescripcion()%></p>
	                            <h4>Remuneracion (UYU): </h4>
	                            <p id="remun" class="texto"><%= remune%></p>
	                            <h4>Horario: </h4>
	                            <p id="horario" class="texto"><%= dataOferta.getHorarioTrabajo()%></p>
	                            <h4>Departamento: </h4>
	                            <p id="dept" class="texto"><%= dataOferta.getDepartamento()%></p>
	                            <h4>Ciudad: </h4>
	                            <p id="ciudad" class="texto"><%= dataOferta.getCiudad()%></p>
	                            <h4>Fecha de alta: </h4>
	                            <p id="fechaAlta" class="texto"><%= dataOferta.getFechaAlta()%></p>
	                            <h4>Keywords asociadas: </h4>
	                            <p id="keywords" class="texto"><% List<String> keywords = dataOferta.getKeywords();
                          		for (String key : keywords) {%>
									<a class = "sinHipervinculo blanco" href="home?key=<%= key %>"><button type="button" class="tags keywords col-3"><%= key%></button></a>
								<%}%>						
	                            </p>
	                        </div>
	                    </div>
	                    <!-- Se da la opcion de postularse al usuario -->
	                    
	                    <% 
                        String vidLink = (String) request.getParameter("vid");
						String cvValue = (String) request.getParameter("cv") != null ? request.getParameter("cv") : "";
						String motValue = (String) request.getParameter("mot") != null ? request.getParameter("mot") : ""; 
				
						%>
						
	                    <div class="col-4 py-4  px-0">
	                    <div class="contenedorPostulantesPaquete">
                    	<h4 class="titulo centrar">Trabaja con nosotros</h4>
                        <p class="texto centrar">¡Tu oportunidad está aquí!</p>
                        <p class="texto centrar">Postulate y envíanos tu CV:</p>
						<div class="row">
							<div class="boton-container">							
                            	<input class="btn botonbusqueda mx-auto boton-largo" type="button" id="mostrarPopup" value="Postular">
             					<div id="formularioPopup" data-role="popup" class="ui-content" >
									<div class="row">
										<h2>Ingrese la informacion de su postulación:</h2>
						        		<form class="formulario" id="formulario" action="postulacion?name=<%=dataOferta.getNombre().replaceAll("\\s","_").replaceAll("[áäâà]", "a").replaceAll("[éêèë]", "e").replaceAll("[íïîì]", "i").replaceAll("[óôöò]", "o").replaceAll("[úüûù]", "u").replaceAll("[ÁÄÂÀ]", "A").replaceAll("[ÉÊÈË]", "E").replaceAll("[ÍÏÎÌ]", "I").replaceAll("[ÓÔÖÒ]", "O").replaceAll("[ÚÜÛÙ]", "U").replaceAll("/", "")%>" method="post">						        		
											<div class="row espacio">										
												<label for="cv_reducido" class="labels">CV Reducido</label>
							        			<textarea class="campo" id="cv_reducido"  cols="25" rows="5" name="cv_reducido"><%=cvValue %></textarea><br>
												<p class="warnings" id="warningCV">Ingrese un CV reducido </p>					
											</div>
											<div class="row espacio">
											    <label for="motivacion" class="labels">Motivación</label>
										        <textarea  class="campo" id="motivacion" cols="25" rows="5" name="motivacion"><%=motValue %></textarea><br>
                                                <p class="warnings" id="warningMot">Ingrese una motivación </p>
                                            </div>
				
                                            <div class="row espacio">
                                                <label for="video" class="labels">Video (opcional)</label>
                                                <textarea  class="campo" id="video" cols="25" rows="2" name="video"><%=vidLink %></textarea><br>
                                                <p class="warnings" id="warningVid">Ingrese un video válido </p>
                                                
                                            </div>
                                            <br>
                                            <div class="row ">										
                                                <div class="col">												
                                                    <button onclick="validarFormulario()" class = "btn botonbusqueda mx-auto" id="aceptar">Aceptar</button>															
                                                </div>
                                                <div class="col">
                                                    <input class="btn botonbusqueda mx-auto" type="button" id="cancelar" value="Cancelar">
                                                </div>
											</div>
						   		 		</form>
						        	</div>
						 		</div>						
							</div> 
						</div>   
					</div>                        
                    	</div>
	                </div>
	            </div>
	            <!-- FIN CONSULTA DE OFERTA -->
            </div>
        </div>
        <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>    
        <script src="media/js/consulta_de_oferta.js"></script>
    </body>
</html>
