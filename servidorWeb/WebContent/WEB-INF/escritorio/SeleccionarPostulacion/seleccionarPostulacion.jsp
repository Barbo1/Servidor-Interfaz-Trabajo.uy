<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import=" publicador.* ,java.util.Collection,java.util.List, java.time.format.DateTimeFormatter, java.time.LocalDate,java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
      <!-- Titulo -->
     <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
     <jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>		
</head>
	<body>
	<!-- hacer en el servlet! Hay que ver que solo pase cuando  la oferta esta finalizada-->
	<!-- Aca puedo hacer que si ya esta seleccionado puestre solo la lista -->
	
	<% 	
    		DtEmpresaCompleto dataEmpresaComp = (DtEmpresaCompleto) request.getAttribute("dataEmpresa");
    		DtEmpresa dataEmpresa = dataEmpresaComp.getEmpresa();
    		DtOfertaCompleto dataOfertaComp = (DtOfertaCompleto) request.getAttribute("dataOfertaComp");
    		DtOferta dataOferta = dataOfertaComp.getOferta();
    		List<DtPostulacionCompleto> dataPostulaciones = (List<DtPostulacionCompleto>) dataOfertaComp.getPostulaciones();
		%>
	
	<jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
    
    <div class="container-fluid">
    	<div class="row">
    		<jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
    		<!-- Contenido del caso -->
    		<div class="col-10 py-4 container-fluid">
                    <h1 class="tituloPrincipal">Seleccionar Postulaciones</h1> 
                   
                    <form id="formulario" action="seleccion-confirmar?nombreOferta=<%=dataOferta.getNombre() %>" method="POST">	      
	                	<div class="d-flex flex-wrap" id="lista">
	                    
	                    	<!-- HAER UN FOR QUE CARGUE POSTULACIONES -->
	                    	<% 
	                    	int cantPost = dataPostulaciones.size(); 
	                    	for(DtPostulacionCompleto dataPost: dataPostulaciones){
							%>
								<div class = 'card extendida'>
		                    		<img class='card-img-top imagenLista' src='Imagenes?id=<%= dataPost.getImagen()%>&tipo=usuarios' class='imagenConsOferta'></img>
		                        	<div class='card-body'>
		                            	<h4 class='card-title'><%= dataPost.getPostulante() %></h4>
		                            	
		                        		<a href='consulta-postulacion?postulante=<%=dataPost.getPostulante()%>&oferta=<%=dataOferta.getNombre()%>' class='btn btn-primary' id='links'>Ver postulación</a> 
			                            	<select class="form-select texto chico contenedorSeleccion"  id="orden_<%= dataPost.getPostulante() %>" name="<%=dataPost.getPostulante()%>">
			                                	<option selected value="0">- Elegir posición -</option>
			                                	<% 
			                						for (int i = 1; i <= cantPost; i++) {
			           						 	%>
			                					<option value="<%= i %>"><%= i %></option>
								            	<%
								                	}
								            	%>
			                            	</select>         
		                        	</div>
		                    	</div>
	                    	<%} %>

	                	</div>
	                	<br>			
	                	 					 
	            		<div class="d-flex justify-content-center">
	            			<button onclick="validarFormulario()" class = "row btn btn-primary sinHipervinculo boton-follow mx-auto" id="confirmar">Confirmar</button>				
	            		</div>		
	            									
              		</form>
              		<br>
              		<div class="d-flex justify-content-center">
						<p class="warnings" id="warningCero">Ingrese un orden para toda postulación.</p>			            		
					</div>	
              	
            	</div>
     <!-- Contenido del caso -->
 	  
		</div>
	</div>
	<jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>    
	<script src="media/js/consulta_de_usuario.js"></script>
	<script src="media/js/seleccionarPostulacion.js"></script>
	
	</body>
</html>
