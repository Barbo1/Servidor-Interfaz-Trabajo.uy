<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="publicador.DtTipoDeOferta"%>
<%@page import="publicador.DtPaquete"%>
<%@page import="publicador.DtPaqueteCompleto"%>
<%@page import="publicador.DtCantidad"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.DtEmpresa"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Titulo -->
        <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
        <jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>		
        
    </head>
    <body>	
    			  <!-- barra lateral y barra de arriba -->
    			
    		<!-- barra lateral y barra de arriba -->
    		<jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
    			<div class="container-fluid">
            		<div class="row">
    	    			<jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
    			
    			
                <!-- content -->
                
                 <% 
                 
                DtPaqueteCompleto dataPaqueteCompleto = (DtPaqueteCompleto) request.getAttribute("paquete"); 
                 
                DtPaquete dataPaquete = dataPaqueteCompleto.getPaquete();
                 
                List<DtCantidad> tiposOferta = dataPaqueteCompleto.getTipos();
                                  
             	String fechaAlta = dataPaquete.getFechaAlta();                          			 
           		
             	LocalDate fecha = LocalDate.now();
         		
				boolean comprado = false;
                 %>
                
                <div class = "col-1 py-4 px-3"></div>
                <div class="col-9 py-4 px-3">
                    <div class = "row">
                        <div class="col-2"></div>
                      
                        <div class = "col-2 py-4 px-3" id = "imagen"><img src="Imagenes?id=<%=dataPaquete.getImagen()%>&tipo=paquetes" class = 'imagenOferta'"></div>
                        <div class = "col-2"></div>  
                        <div class = "col-4 py-4 px-3 margenTitulo">
                       		<div class = "row titulo centrar"><%= dataPaquete.getNombre() %></div>
                                      
                        <% if( request.getSession().getAttribute("estado_sesion").equals("EMPRESA") ){ 
                        	comprado = (boolean) request.getAttribute("comprado");
                        	if (!comprado){
                        	%>	<div class="row centrar">	
		                   			<a href="CompraPaquete?nombrePaquete=<%= dataPaquete.getNombre()%>" type="button" class = "col-5 centrar btn botonseleccionar btn-lg">Comprar paquete</a>	
		                   		</div>	     	                    		
	     	                   <%	}else{
		     	  	                DtPaquete dataPaqueteAdquirido = (DtPaquete) request.getAttribute("paqueteAdquirido");
	     	                	  	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 		        			        fecha = LocalDate.parse(dataPaqueteAdquirido.getFechaAlta(), formatter);
 		        			        fecha = fecha.plusDays(dataPaqueteAdquirido.getValidez()); %>
	     	                   		       <div class="centrar"><button onclick="" style="margin-top:10%; background-color: #606264; color:white;" class = "col-6 btn botonseleccionar btn-lg comprado" id="btnCrear">Paquete adquirido</button></div>	     	                  
	     	                  
	     	                  <%  }}%> 
	     	            </div>
                    </div>
                    
                    <div class = "row">
                        <div class = "wrap">
                            <ul class= "tabs">
                                <li><a href="#perfil"><span class = "tab-text">Paquete</span></a></li>
                                <li><a href="#ofertas"><span class = "tab-text">Tipos de oferta</span></a></li>
                            </ul>

                            <div class="secciones">
                                <article id = "perfil">
                                 <%   
                                 if(comprado){
                                 	if(LocalDate.now().compareTo(fecha) > 0){ %>
	     	                        	<h4>Estado: </h4><button type="button" class="tags vencido col-2">Vencido</button>
	     	                        <%}else{ %>
	     	                            <h4>Estado: </h4><button type="button" class="tags activo col-2">Activo</button>
	     	                   <%}
	     	                   }%>
                                    <h4>Nombre: </h4><p id = "nombre" class="texto"> <%= dataPaquete.getNombre() %> </p>
                                    <h4>Descripcion: </h4><p id = "descripcion" class="texto"><%= dataPaquete.getDescripcion() %></p>
                                    <h4>Periodo: </h4><p id = "periodo" class="texto"><%= dataPaquete.getValidez()%> días</p>
                                    <h4>Descuento: </h4><p id = "descuento" class="texto"><%= dataPaquete.getDescuento() * 100 + "%"%> </p>
                                    <h4>Fecha de alta: </h4><p id = "fecha" class="texto"> <%= fechaAlta %> </p>
                                    <% if (comprado) { 
    		     	  	                DtPaquete dataPaqueteAdquirido = (DtPaquete) request.getAttribute("paqueteAdquirido");

                                    %>
                                   		<h4>Fecha de compra: </h4><p id = "fecha" class="texto"> <%= dataPaqueteAdquirido.getFechaAlta() %> </p>     
                                   		<h4>Fecha de vencimiento: </h4><p id = "fecha" class="texto"> <%= fecha.getDayOfMonth() + "-" + fecha.getMonthValue() + "-" + fecha.getYear() %> </p>     
                                   		                              		
                                   <%} %>
                                    <h4>Costo: </h4><p id = "costo" class="texto"><%= "$" + dataPaquete.getCosto() %></p>
                                       
				      
				                
                                </article>    

        	                        
                                <article id = "ofertas">
                                
                                <% for (DtCantidad cantidad : tiposOferta){ %>     
                                 
                                  <a href="ConsultarTipoDeOferta?tipo=<%= cantidad.getTipo() %>" class="sinHipervinculo">
                                        <div class='row efectoZoom coloresOfertas centrar'>
                                            <div class='col-9'>
                                                <div>
                                                    <p class="tituloOferta" id="encabezadoOferta"><%= cantidad.getTipo() %></p>
                                                </div>
                                                <div>
                                                    <p id="descpOferta">Cantidad: <%= cantidad.getCantidad() %></p>
                                                    
                                                    <% 
                                                    int cantidadRestante = 0;
                                                    if (comprado){
                                    	         		Map<String, Map<String, Integer>> dataPaqueteEmpresa = (Map<String, Map<String, Integer>>) request.getSession().getAttribute("dataPaqueteEmpresa");
	                                                    if (dataPaqueteEmpresa.get(cantidad.getTipo()) != null && dataPaqueteEmpresa.get(cantidad.getTipo()).get(dataPaquete.getNombre()) != null){
	                                                       cantidadRestante = dataPaqueteEmpresa.get(cantidad.getTipo()).get(dataPaquete.getNombre());
	                                                    }
	                                                    	
	                                                    %>
	                                                    <p id="descpOferta">Cantidad restante: <%= cantidadRestante %></p>
                                                    <%} %>
                                                    
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
                <!-- -->
          <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>
        <script src="media/js/consulta_de_usuario.js"></script>
    </body>
</html>