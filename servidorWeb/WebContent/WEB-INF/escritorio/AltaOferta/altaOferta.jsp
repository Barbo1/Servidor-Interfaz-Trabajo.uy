<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map,java.util.List" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Titulo -->
        <title>Alta de oferta laboral </title>
        <jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>		

    </head>
    <body>
    	<% 	
    		String errorMessage = (String) request.getAttribute("error_message");
    		String errorMessageNombreO = (String) request.getAttribute("error_message_nombreO");
    	    // Pongo los ultimos valor que ingreso el usuario en cada campo si es que algun campo es invalido (todos loc campos empiezan siendo null) 
    		String tipodeOValue = (request.getParameter("tiposdeO") != null) ? request.getParameter("tiposdeO") : "";
    		String nombreOValue = (request.getParameter("nombreO") != null) ? request.getParameter("nombreO") : "";
    		String descripcionValue = (request.getParameter("descripcion") != null) ? request.getParameter("descripcion") : "";
    		String horaComValue = (request.getParameter("horaCom") != null) ? request.getParameter("horaCom") : "";
    		String horaFinValue = (request.getParameter("horaFin") != null) ? request.getParameter("horaFin") : "";
    		String remuneracionValue = (request.getParameter("remuneracion") != null) ? request.getParameter("remuneracion") : "";
    		String departamentoValue = (request.getParameter("departamento") != null) ? request.getParameter("departamento") : "";
    		String ciudadValue = (request.getParameter("ciudad") != null) ? request.getParameter("ciudad") : "";
    		
    		// Paquetes de la empresa con su saldo
    		Map<String, Map<String, Integer>> dataPaqueteEmpresa = (Map<String, Map<String, Integer>>) request.getSession().getAttribute("dataPaqueteEmpresa");
  
		%>
        <!-- barra lateral y barra de arriba -->
        <jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
				
				
				<!-- AQUI VA EL CONTENIDO DEL CU ALTA DE OFERTA -->
                <div class="col-10 py-4 px-3">
                    <div class="row contenedor" id="casodeuso">
                        <h1 class="tituloPrincipal">Alta de oferta laboral</h1>
                        <form id="formulario" class = "formu" method="post" enctype="multipart/form-data" action="crear-oferta">
                            <!-- Tipo de Oferta -->
                            <label for="tiposdeO" class="labels">Seleccione un tipo de oferta:</label>
                            <select class="campo" id="tiposdeO" name="tiposdeO" value="<%=tipodeOValue%>">
                                <option selected value="0">- Seleccione una -</option>
                                <% List<String> tiposO = (List<String>) request.getSession().getAttribute("tipos_ofertas");
		                           for (String aux : tiposO) {%>
		                           		<option value="<%=aux%>"><%=aux%></option>
		                        <%} %>
                            </select><br>
                            <p class="warnings" id="warningTipoDeOferta">Selecione un tipo de oferta</p><br>
                            <!-- Nombre -->
                            <label for="nombreO" class="labels">Nombre de la oferta:</label>
                            <input class="campo" type="text" id="nombreO" name="nombreO" value="<%=nombreOValue%>">
                            <p class="warnings" id="warningNombre">Ingrese un nombre</p><br>
                            <% if (errorMessageNombreO != null && !errorMessageNombreO.isEmpty()) { %>
					    		<p class="warnings"><%= errorMessageNombreO %></p>
							<% } %> 
                            <!-- Descripcion -->
                            <label for="descripcion" class="labels">Descripción:</label>
                            <textarea  class="campo" id="descripcion" name="descripcion" cols="25" rows="6" value="<%=descripcionValue%>"></textarea><br>
                            <p class="warnings" id="warningDescripcion">Ingrese una descripcion</p><br><br>
                            <!-- Horario -->
                            <div>
                                <label class="labels">Horario:</label>
                                <label for="horaCom" class="texto chico espacios">Desde</label>
                                <input type="time" id="horaCom" name="horaCom" value="<%=horaComValue%>">
                                <label for="horaFin" class="texto chico espacios">hasta</label>
                                <input type="time" id="horaFin" name="horaFin" value="<%=horaFinValue%>">
                            </div>
                            <p class="warnings" id="warningHorario">Ingrese un horario</p><br>
                            <!-- Remuneracion -->
                            <label for="remuneracion" class="labels">Remuneración (UYU):</label>
                            <input class="campo" type="number" id="remuneracion" name="remuneracion" value="<%=remuneracionValue%>">
                            <p class="warnings" id="warningRemuneracion">Ingrese una remuneracion valida</p><br>
                            <!-- Departamento -->
                            <label for="departamento" class="labels">Departamento:</label>
                            <input list="departamentos" class="campo" id="departamento" name="departamento" value="<%=departamentoValue%>">
                            <datalist id="departamentos">
                                <option value="Artigas">
                                <option value="Canelones">
                                <option value="Cerro Largo">
                                <option value="Colonia">
                                <option value="Durazno">
                                <option value="Flores">
                                <option value="Florida">
                                <option value="Lavalleja">
                                <option value="Maldonado">
                                <option value="Montevideo">
                                <option value="Paysandu">
                                <option value="Rio Negro">
                                <option value="Rivera">
                                <option value="Rocha">
                                <option value="Salto">
                                <option value="San Jose">
                                <option value="Soriano">
                                <option value="Tacuarembo">
                                <option value="Treinta y Tres">
                            </datalist><br>
                            <p class="warnings" id="warningDepartamento">Ingrese un departamento</p><br>
                            
                            <!-- Ciudad -->
                            <label for="ciudad" class="labels">Ciudad:</label>
                            <input class="campo" type="text" id="ciudad" name="ciudad" value="<%=ciudadValue%>">
                            <p class="warnings" id="warningCiudad">Ingrese una ciudad</p><br>
                            
                            <!-- Imagen (opcional) -->
                            <label class = "labels">Ingrese una imagen (opcional):</label>
                    		<br>
							<img id="uploadedImage" src="Imagenes?id=default.png&tipo=ofertas" alt="Imagen cargada" class="imagenOferta">
							<br><br>
							<input class = "inputImagen" type="file" id ="fileInput" name="imagen" accept = "image/*">
							<br><br>

                            
                            <!-- Keywords -->
                            <label for="keywords" class="labels">Seleccione keywords asociadas a la oferta:</label>
                            <div class="campo keys">
                            	<% List<String> keywords = (List<String>) request.getSession().getAttribute("keywords");
		                           for (String key : keywords) {%>
		                           		<div class="form-check form-switch mx-2">
		  									<input class="form-check-input" type="checkbox" role="switch" id="<%=key%>" name="<%=key%>" value="<%=key%>">
		  									<label class="form-check-label texto chico" for="<%=key%>"><%=key%></label>
										</div>
		                            <%}%>
	                        </div> 
                            <!-- Botones metodo de pago -->
                            <h2 class="labels centrar">Seleccione un metodo de pago</h2>
                            <div class="row py-3">
                                <div class="col-2"></div>
                                <input class="col-3 btn botonseleccionar btn-lg" type="button" id="btnPagoGeneral" value="Pago general">
                                <div class="col-2"></div>
                                <input class="col-3 btn botonseleccionar btn-lg" type="button" id="btnPagoPaquete" value="Pago con paquete">
                                <div class="col-2"></div>
                            </div>
                            <!-- Pago general -->
                            <div class="row" id="camposPagoGeneral">
                                <!-- En Tarea 3 hara algo -->
                            </div>
                            <!-- Pago con paquete -->
                            <div class="row" id="camposPagoPaquete">
                                <h2 class="labels centrar">Estos son los paquetes en los que tiene saldo disponible</h2>
                                <div class="row">
	                            	<%if (dataPaqueteEmpresa != null){%>        
	                                    <select class="campo" id="seleccionPaquete" name="seleccionPaquete">
	                                         <!-- Codigo js abajo -->
			                           	</select><br>
			                         <%} else{ %>
			                        	 <p class="labels centrar">No posee paquetes con los que pueda pagar</p>
			                         <%}%>
                                </div>
                                <p class="warnings" id="warningSeleccionPaquete">Selecione un paquete</p><br>
                            </div>
                            <!-- Boton confirmar -->
                            <div class="row"> 
                                <div class="col-4"></div>
                                <div class="col-4 d-flex justify-content-center">
                                    <input class="btn botonseleccionar btn-lg px-5" type="submit" id="confirmar" value="Confirmar">
                                </div>
                                <div class="col-4"></div>
                            </div>
                        </form>
                    </div>
                    <div class="row" id="comproConGeneral">
                        <h1 class="titulo negro centrar py-3">Pago completado con exito. Gracias por su compra</h1>
                        <p class="labels centrar">Se ha generado un pdf con los detalles de su compra. Dirigase a un Abitab o Redpagos para
                        abonar el total dentro de las proximas 48 horas.</p>
                    </div>
                    <div class="row" id="comproConPaquete">
                        <h1 class="titulo negro centrar py-3">Pago completado con exito. Gracias por su compra</h1>
                        <p class="labels centrar">Nuestro equipo esta validando la oferta, recibira un correo cuando sea visible para el publico</p>
                    </div>
                </div>
                <!-- FIN ALTA DE OFERTA -->
                
                
            </div>
        </div>
        <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>    
        <script src="media/js/alta_de_oferta.js"></script>
        <script>
	        $("#tiposdeO").change(function(){
	            tdo = $("#tiposdeO").val();
	            <%if (dataPaqueteEmpresa != null){
		            for(Map.Entry<String, Map<String, Integer>> tipoOferta : dataPaqueteEmpresa.entrySet()){%>
		            	if (tdo == '<%=tipoOferta.getKey()%>'){
		            		 $("#seleccionPaquete").html(`
		            		 <option selected value="0">- Seleccione una -</option>	 
					         <% for(Map.Entry<String, Integer> paqOfe : tipoOferta.getValue().entrySet()){%>  
							      <%if(paqOfe.getValue() != 0){%>
					           			<option value="<%=paqOfe.getKey()%>"> Paquete <%=paqOfe.getKey()%> | Saldo restante = <%=paqOfe.getValue()%> </option>
					            	<%}%>
		            			<%}%>	
		            	`);}
		            <%}
		          }%>
	        })
        </script>
    </body>
</html>