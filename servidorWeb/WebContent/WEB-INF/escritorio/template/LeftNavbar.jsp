<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<!-- left navbar -->
<div class="col-2 py-4">
    <ul class="nav nav-pills flex-column">
        <div class="accordion" id="accordionPanelsStayOpenExample">

            <!-- Consultas -->
           <div class="accordion-item">
               <h2 class="accordion-header">
                   <button class="seleccionable accordion-button d-block text-center" type="button" data-bs-toggle="collapse" data-bs-target="#consultas-panel" aria-expanded="false" aria-controls="consultas-panel">
                       Consultas
                   </button>
               </h2>
               <div id="consultas-panel" class="accordion-collapse collapse show">
                   <div class="accordion-body">
                       <div class="col">
                           <div class="row">
                               <a href="consulta-usuario" class="d-block text-decoration-none seleccionable-row py-2 text-center">
                                   <b>
                                       Usuarios
                                   </b>
                               </a>
                           </div>
                           <div class="row">
                               <a href="ConsultarPaquete" class="d-block text-decoration-none seleccionable-row py-2 text-center">
                                   <b>
                                       Paquetes
                                   </b>
                               </a>
                           </div>
                           <div class="row">
                               <a href="ConsultarTipoDeOferta" class="d-block text-decoration-none seleccionable-row py-2 text-center">
                                   <b>
                                       Tipos de Ofertas
                                   </b>
                               </a>
                           </div>
                       </div>
                   </div>
               </div>
               <!-- .. -->
				<% 
					if (request.getSession().getAttribute("estado_sesion").equals("EMPRESA")){%>
					<div class="accordion-item">
                                    <h2 class="accordion-header">
                                        <button class="seleccionable accordion-button d-block text-center" type="button" data-bs-toggle="collapse" data-bs-target="#alta-panel" aria-expanded="false" aria-controls="alta-panel">
                                            Dar de Alta
                                        </button>
                                    </h2>
                                    <div id="alta-panel" class="accordion-collapse collapse show">
                                        <div class="accordion-body d-flex justify-content-center">
                                            <div class="col">
                                                <div class="row">
                                                    <a href="alta-oferta" class="d-block text-decoration-none seleccionable-row py-2 text-center">
                                                        <b>
                                                            Oferta Laboral
                                                        </b>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
				<%} %>	
               <!-- Keywords -->
               <div class="accordion-item">
                   <h2 class="accordion-header">
                       <button class="seleccionable accordion-button d-block text-center" type="button" data-bs-toggle="collapse" data-bs-target="#keywords-panel" aria-expanded="false" aria-controls="keywords-panel">
                           Keywords
                       </button>
                   </h2>
                   <div id="keywords-panel" class="accordion-collapse collapse show">
                       <div class="accordion-body">
                           <div class="col">
                           <% 
                           List<String> keywords = (List<String>)request.getSession().getAttribute("keywords");
	                           for (String keys : keywords) {%>
	                               <div class="row">
	                                   <a href="home?key=<%= keys %>" class="d-block text-decoration-none seleccionable-row py-2 text-center">
	                                       <b>
	                                       	<%= keys %>
	                                       </b>
	                                   </a>
	                               </div>
	                            <%}
	                            %>
                           </div>
                       </div>

                   </div>
               </div>
           </div>
           <!-- .. -->

        </div>
    </ul>
</div>
