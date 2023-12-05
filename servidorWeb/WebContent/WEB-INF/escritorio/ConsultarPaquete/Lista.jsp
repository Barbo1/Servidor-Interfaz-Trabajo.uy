<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="publicador.DtPaquete, java.util.List" %>
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
		                
		                    <h1 class="tituloPrincipal">Lista de paquetes</h1>
				
		                        <div class="d-flex flex-wrap" id="lista">
		                                         		                        
		                        <% 
								List<DtPaquete> paquetes = (List<DtPaquete>) request.getAttribute("paquetes");
		        				for(DtPaquete paquete: paquetes){
								%>	
							
		                            <div class = 'card extendida extendd'> 
		                     			<img class='card-img-top imagenLista' src="Imagenes?id=<%=paquete.getImagen()%>&tipo=paquetes">                        	                                                   
		                                <div class='card-body'>
		                                    <h4 class='card-title'><%= paquete.getNombre() %></h4> 
		                                    <a href="ConsultarPaquete?paquete=<%= paquete.getNombre() %>" class='btn btn-primary' id='links'>Ver paquete</a>
		                                    
		                                </div>   
                 
		                            </div>
		                            <% } %>   
		                        </div>   
		                </div>
                <!-- -->
                </div>
          </div>
        <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>
            
        <script src="media/js/consulta_de_usuario.js"></script>
    </body>
</html>