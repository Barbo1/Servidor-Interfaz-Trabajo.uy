<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="publicador.DtPostulacionCompleto,publicador.DtPostulacion,java.util.Collection, publicador.DtPostulante,publicador.DtOferta, java.text.SimpleDateFormat,java.time.LocalDate" %>

<!DOCTYPE html>
<html>
	<head>
	<title>Consulta de postulacion</title>
		<jsp:include page="/WEB-INF/escritorio/template/Head.jsp"></jsp:include>
	</head>
	<body>
	<% DtPostulacionCompleto data = (DtPostulacionCompleto) request.getAttribute("dataPostulacion");
			String postulanteNombre = data.getPostulante();
			DtPostulacion dtpost = data.getPostulacion();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = dtpost.getFecha();
			DtOferta ofData = (DtOferta) request.getAttribute("dataOferta");
			String ofertaNombre = ofData.getNombre();
	%>
	<jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
    <div class="container-fluid">
    	<div class="row">
    		<jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
    		<div class="col-10 py-4 px-3">
				<h1 class="tituloPrincipal">Informacion de la postulacion</h1>
                <div class="col contenedorInfoPost">
                	<h4>CV Reducido: </h4><p id = "cvRed" class="texto"><%= dtpost.getCurriculum() %></p>
                	<h4>Motivacion: </h4><p id = "motivacion" class="texto"><%= dtpost.getMotivacion() %></p>
                	<h4>Fecha de postulacion: </h4><p id = "fecha" class="texto"><%= fecha %></p>                   
                </div><br>
                <%
                    if (data.getPostulacion().getOrden() != 0 && request.getSession().getAttribute("estado_sesion").equals("POSTULANTE")) {
                    %>
                        <div class="col contenedorInfoPost">
                            <h4 class="centrar">Se ha realizado la selección. Para comprobarla, descargue el siguiente archivo:</h4><br>
                            <div class="centrar">
                                <a class="btn botonbusqueda sinHipervinculo" href="/servidorWeb/descargar-pdf?postulante=<%= postulanteNombre %>&oferta=<%= ofertaNombre %>&empresa=<%= ofData.getEmpresa() %>">
                                    <h5>Descargar PDF</h5>
                                </a>
                            </div>
                        </div><br>
                    <%
                    }
                %>
				<div class="contenedorPostulanteOferta">
					<div class="row">
						<div class="col">
							<h4 class="titulo centrar row">Postulante</h4>
							<div class="contenedorConsOf centrar row" id="postulantes">
								<!-- LINK A OFERTA -->
								<a href='consulta-usuario?user=<%=postulanteNombre%>' class="sinHipervinculo">
                            	<div class='row efectoZoom coloresOfertas centrar'>
                                        <div class='col-5 px-0'>
                                        	<img src='Imagenes?id=<%= data.getImagen() %>&tipo=usuarios' class='imagenConsOferta'></img>
                                        		</div>
                                        <div class='col-7'>
                                            <p class="tituloPostulante" id="encabezadoPostulante"><%= postulanteNombre%></p>
                                        </div>
                                    </div>
                                </a>
                            </div>
						</div>
						<div class="col">						
							<h4 class="titulo centrar row">Oferta</h4>
							<div class="contenedorConsOf centrar row">
							<!-- LINK A OFERTA -->
								<a href='consulta-oferta?name=<%=ofertaNombre%>' class="sinHipervinculo">
                                <div class='row efectoZoom coloresOfertas centrar'>
                                        <div class='col-5 px-0'>
                                        	<img src='Imagenes?id=<%= ofData.getImagen() %>&tipo=ofertas' class='imagenConsOferta'></img>
										</div>
                                        <div class='col-7'>
                                            <p class="tituloPostulante" id="encabezadoOferta"><%=ofertaNombre%></p>

                                        </div>
                                    </div>
                                </a>
                            </div>
						</div>
					</div>
                </div>
                <!-- Si el postulante agrego un video lo muestro. el link tiene que tener /embed despues del .com !-->
                <%
                String linkVid = dtpost.getVideo().replace(".com/watch?v=", ".com/embed/");
                if (!linkVid.equals("")) { 
                    %>
                    
                    <br>
                    <div class="text-center">
                        <h4 class="titulo centrar row">Video de la postulación</h4>
                        <iframe width="560" height="315" src=<%=linkVid%> title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                    </div>
                </div>  
    		<% } %>
		</div>
	</div>
    <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>
	</body>
</html>


            
