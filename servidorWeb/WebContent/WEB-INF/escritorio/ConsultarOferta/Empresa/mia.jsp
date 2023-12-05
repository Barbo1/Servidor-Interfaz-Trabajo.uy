<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import=" publicador.* ,java.util.Collection,java.util.List, java.time.format.DateTimeFormatter, java.time.LocalDate,java.util.List" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Titulo -->
        <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
        <jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>		

    </head>
    <body>
    	<!-- Obtengo la infromacion de la oferta y de la empresa que publico la oferta EN ESTE CASO ELLA MISMA -->
    	<% 	
    		DtEmpresaCompleto dataEmpresaComp = (DtEmpresaCompleto) request.getAttribute("dataEmpresa");
    		DtEmpresa dataEmpresa = dataEmpresaComp.getEmpresa();
    		DtOfertaCompleto dataOfertaComp = (DtOfertaCompleto) request.getAttribute("dataOfertaComp");
    		DtOferta dataOferta = dataOfertaComp.getOferta();
    		DtPaquete dataPaquete = dataOferta.getPaquete();
    		List<DtPostulacionCompleto> dataPostulaciones = (List<DtPostulacionCompleto>) dataOfertaComp.getPostulaciones();
    		int remune = Math.round(dataOferta.getRemuneracion()); // castea de float a int
    		int costo = Math.round(dataOferta.getCosto());
    		// Me fijo como se compro la oferta (tp = 1 pago paquete, tp = 2 pago general)
    		String tipoPago;
    		int tp;
    		if(dataOferta.getPaquete() != null){
    			tipoPago = "Pago con paquete";
    			tp = 1;
    		}
    		else{
    			tipoPago = "Pago general";
    			tp = 2;
    		}
		%>
        <!-- barra lateral y barra de arriba -->
        <jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
				<!-- AQUI VA EL CONTENIDO DEL CU CONSULTA DE OFERTA -->
	            <div class="col-10 py-4 px-3">
	                <div class="row">
	                    <!-- Aqui va la info de la oferta -->
	                    <div class="col-8 py-4">
	                        <div class="row">
	                            <div class="col-1"></div>
	                            <div class="col-4 py-4 px-3" id="imagen"><img src='Imagenes?id=<%= dataOferta.getImagen() %>&tipo=ofertas' class='imagenOferta'></div>
	                            <div class="col-3"></div>
	                            <div class="col-5 py-4 px-3 margenTitulo">
	                                <div class="row titulo mx-auto" id="nombreOferta"><%= dataOferta.getNombre()%></div>
	                                <div class="row subtitulo mx-auto" id="publicadaPor"><%= dataOferta.getEmpresa() + " / " + dataEmpresa.getCorreoElectronico()%></div>
				                  
						                  <%               
						                  
						                  LocalDate fechaActual = LocalDate.now();
		
						                  %>
						                  
						                 <%if(dataOferta.getEstado().equals(EstadoOferta.CONFIRMADA)){ 
			                                   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			   		        			        LocalDate fecha = LocalDate.parse(dataOferta.getExpiracion(), formatter);
			                                   	if(fechaActual.compareTo(fecha) > 0){%>
				                   		 			<br><a href="FinalizaOferta?nombreOferta=<%= dataOferta.getNombre() %>" class = "row btn btn-primary sinHipervinculo boton-follow mx-auto" id="btnCrear" type="button">Finalizar oferta</a>	   
				                   		 			<br>     <br>
				                   		 			<%if (dataOferta.getFechaResultado().equals("01-01-0001") && dataPostulaciones.size() > 0){ %>          
				     	                   				<a href="SeleccionOferta?nombreOferta=<%= dataOferta.getNombre() %>" class = "row btn btn-primary sinHipervinculo boton-follow mx-auto" id="btnCrear" type="button">Seleccionar postulantes</a>	               
				     	                   			<%}else{ %>
				     	                   				<a href="SeleccionOferta?nombreOferta=<%= dataOferta.getNombre() %>" class = "row btn btn-primary sinHipervinculo boton-follow mx-auto" id="btnCrear" type="button">Ver selección</a>	               
				     	                   	<%		}
				                   		 		} 				     	                   		
				     	               		} else {
				     	               			if (dataOferta.getEstado().equals(EstadoOferta.FINALIZADA) && !dataOferta.getFechaResultado().equals("01-01-0001")){ %>          
	     	                   						<br><a href="SeleccionOferta?nombreOferta=<%= dataOferta.getNombre() %>" class = "row btn btn-primary sinHipervinculo boton-follow mx-auto" id="btnCrear" type="button">Ver selección</a>	               
	     	                   					<%}
	     	                  
				     	               		} %>	
				     	              	 
	                            </div>
	                        </div>
	                        <div class="contenedorInfoOferta">
	                        	<h4>Estado: </h4>
	                             <%if(dataOferta.getEstado().equals(EstadoOferta.CONFIRMADA)){ 
                                   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
   		        			        LocalDate fecha = LocalDate.parse(dataOferta.getExpiracion(), formatter);
                                   	if(fechaActual.compareTo(fecha) <= 0){%> <!-- Confirmada y vigente -->
                             		<button type="button" class="tags activo col-2">Activa</button>      
                                   	<%}else{%> <!-- Confirmada y vencida -->
                                   		<button type="button" class="tags vencido col-2">Vencida</button>
                                   <%}}else if(dataOferta.getEstado().equals(EstadoOferta.INGRESADA)){ %>
                                   		<button type="button" class="tags proceso col-2">Ingresada</button>
                                  	<%}else if(dataOferta.getEstado().equals(EstadoOferta.RECHAZADA)){ %>
                                   		<button type="button" class="tags vencido col-2">Rechazada</button>
                                   <%}else{%> <!-- Finalizada -->
                                   	     <button type="button" class="tags vencido col-2">Finalizada</button>                                               	
                                   <%} %>
	                            <h4>Descripción: </h4>
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
																	<%}
																%>						
	                            </p>
	                            <!-- Esto solo lo ve la empresa que compro la oferta -->    
	                            <h4>Tipo de oferta: </h4>
	                            <p id="tipoOferta" class="texto"><%= dataOferta.getTipoDeOferta()%></p> 
	                            <h4>Costo (UYU): </h4>
	                            <p id="costo" class="texto"><%= costo %></p>
	                            <h4>Metodo de pago: </h4>
	                            <p id="metPago" class="texto"><%= tipoPago %></p> 
		                   </div>             	  	
		                   
	                    </div>
		                <!-- Aqui van las postulaciones a la oferta y los paquetes si la empresa de la sesion compro la ofertacon paquete-->
	                    <div class="col-4 py-4">
	                        <div class="contenedorPostulantesPaquete">
	                            <!-- Paquete (SI LA OFERTA FUE COMPRADA CON PAQUETE) -->
		                        <%if(tp == 1){ %> 
		                            <h4 class="titulo centrar">Paquete de la oferta</h4>
		                            <div class="contenedorConsOf centrar row" id="siComproConPaquete">
		                                <a href='ConsultarPaquete?paquete=<%=dataPaquete.getNombre()%>' class="sinHipervinculo">
		                                    <div class='row efectoZoom coloresOfertas centrar'>
		                                        <div class='col-5 px-0'>
		                                            <img src='Imagenes?id=<%= dataPaquete.getImagen() %>&tipo=paquetes' class='imagenConsOferta'></img>
		                                        </div>
		                                        <div class='col-7 px-4'>
		                                            <p id="nomPaquete"><%= dataPaquete.getNombre()%></p>
		                                        </div>
		                                    </div>
		                                </a>
		                            </div>
	                            <%}else{ %>
	                            	<h4 class="titulo centrar text-center">Paquete de la oferta</h4>
                            		<div class="contenedorConsOf centrar" id="siComproConPaquete">
                                		<p class="texto text-center">La oferta no fue comprada con paquete</p>
                            		</div>
	                            <%}%>
	                            
	                            <br><br><br>
	                            
	                            
	                            
	                            <!-- Postulaciones -->
		                        <%if(dataPostulaciones.size() > 0){ 
		                        	
		                        	
		                        	
		                        	
		                        	
		                        	
		                        	
		                       		%>
		                        
		                        
		                         <!-- La oferta tiene postulaciones-->   
	                            	<h4 class="titulo centrar">Postulaciones</h4>
	                            	
	                            <% 	int elementos = dataPostulaciones.size();
		                       		int paginas = elementos / 4;
		                       		int pagActual = (int) request.getAttribute("pagina");
		                       		int i = 0;
		                       		if(elementos % 4 != 0)
		                       			paginas ++;
		                            if (dataPostulaciones != null){
		                            	for(DtPostulacionCompleto dataPost : dataPostulaciones){
		                            		i++;
		                            		if(i > (pagActual-1)*4 && i <= pagActual * 4){
		                         %>



		                            	<div class="contenedorConsOf centrar row" id="postulantes">
			                                <a href='consulta-postulacion?postulante=<%=dataPost.getPostulante()%>&oferta=<%=dataOferta.getNombre()%>' class="sinHipervinculo"> 
			                                    <div class='row efectoZoom coloresOfertas centrar'>
			                                        <div class='col-5 px-0'>
			                                            <img src='Imagenes?id=<%= dataPost.getImagen()%>&tipo=usuarios' class='imagenConsOferta'></img>
			                                        </div>
			                                        <div class='col-7 px-4'>
			                                            <p id="nomPostulante"><%= dataPost.getPostulante()%></p>
			                                        </div>
			                                    </div>
			                                </a>
		                            	</div>
		                            
		                            
											                            	<%			} 
														                        	}	
														                   		} 
												                        	%>   
		                        
		                            <div class = "center">
		                            <ul class="pagination">
		                              <li><a href="consulta-oferta?name=<%=dataOferta.getNombre()%>&pagina=1">«</a></li>
		                              <% 
		                              	for(i = 0; i < paginas; i++){ %>
		                              		<li><a href="consulta-oferta?name=<%=dataOferta.getNombre()%>&pagina=<%=i+1%>"><%= i + 1 %></a></li>
		                              <% }%>	
		                             
		                              <li><a href="consulta-oferta?name=<%=dataOferta.getNombre()%>&pagina=<%=paginas%>">»</a></li>
		                            </ul>
		                          </div>
		                        
		                        
		                       <%  }else { %> <!-- La oferta NO tiene postulaciones-->
	                            	<h4 class="titulo centrar text-center">Postulaciones</h4>
                            		<div class="contenedorConsOf centrar" id="postulantes">
                                		<p class="texto text-center">No hay postulantes actualmente</p> 
                            		</div>
                            		<br><br><br>
	                            <%}%>
	                                  
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

