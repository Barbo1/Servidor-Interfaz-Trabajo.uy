<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="publicador.DtPostulacionCompleto,publicador.DtPostulacion,java.util.Collection, publicador.DtPostulante,publicador.DtOferta, java.text.SimpleDateFormat,java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Consulta de postulacion</title>
    <jsp:include page="/WEB-INF/movil/template/Head.jsp"></jsp:include>
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
    <jsp:include page="/WEB-INF/movil/template/Navbar.jsp" />

    <div class="container-fluid">
        <div class="row">
            <div class="col-12 py-4 px-3">
                <h1 class="tituloPrincipal">Informacion de la postulacion</h1>

                <div class="contenedorInfoPost">
                	<h4>CV Reducido: </h4><p id = "cvRed" class="texto"><%= dtpost.getCurriculum() %></p>
                	<h4>Motivacion: </h4><p id = "motivacion" class="texto"><%= dtpost.getMotivacion() %></p>
                	<h4>Fecha de postulacion: </h4><p id = "fecha" class="texto"><%= fecha %></p>                                
                </div><br>
                
               
                        
                        	<div class="row py-4 mx-auto">
						        <h4 class="titulo centrar">Oferta</h4>
								<a href='consulta-oferta?name=<%=ofertaNombre%>' class="sinHipervinculo">
					                    <div class='row efectoZoom coloresPost centrar'>
					                    	<img src='Imagenes?id=<%= ofData.getImagen() %>&tipo=ofertas' class='imagenPost'></img>
					                    	<br><br>
	                                        <p class="text-center" id="encabezadoOferta"><%=ofertaNombre%></p>
					                    </div>
					             </a>
				        	</div>
   
       

                <%
                    String linkVid = dtpost.getVideo().replace(".com/watch?v=", ".com/embed/");
                    if (!linkVid.equals("")) {
                %>
                <br>
                <div class="text-center">
                    <h4 class="titulo centrar row">Video de la postulación</h4>
                    <iframe width="100%" height="200" src=<%=linkVid%> title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" style="max-width: 100%;" allowfullscreen></iframe>
                </div>
                <%}
                %>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/movil/template/footer.jsp"/>

</body>
</html>


            