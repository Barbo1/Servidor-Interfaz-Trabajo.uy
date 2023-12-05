<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List, publicador.*" %>
<!DOCTYPE html>
<html lang="es">
    <head>
    
    	<%
    	DtEmpresaCompleto dataEmpresaComp = (DtEmpresaCompleto) request.getAttribute("dataEmpresa");
		DtEmpresa dataEmpresa = dataEmpresaComp.getEmpresa();
		DtPostulanteCompleto dataPostulanteComp = (DtPostulanteCompleto) request.getAttribute("dataPostulante");
		DtPostulante dataPostulante = dataPostulanteComp.getPostulante();
		DtOfertaCompleto dataOfertaComp = (DtOfertaCompleto) request.getAttribute("dataOfertaComp");
		DtOferta dataOferta = dataOfertaComp.getOferta();
		int remune = Math.round(dataOferta.getRemuneracion()); // castea de float a int
    	%>
        <!-- Titulo -->
        <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
        <jsp:include page="/WEB-INF/movil/template/Head.jsp"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/movil/template/Navbar.jsp"/>
    	<!-- AQUI VA EL CONTENIDO DEL CU CONSULTA DE OFERTA -->
        <div class="col py-4 px-3 d-grid">
            <div class="row py-4 mx-auto">
                <h1 class="tituloPrincipal">Información de la oferta</h1>    <br>       
                <div class="titulo mx-auto" id="nombreOferta"><%= dataOferta.getNombre() %></div>
                <div class="subtitulo mx-auto" id="publicadaPor"><%= dataEmpresa.getNickname() %></div>
            </div>
            <div class="row py-4 mx-auto" id="imagen">
                <img src='Imagenes?id=<%= dataOferta.getImagen() %>&tipo=ofertas' class='imagenOferta'>
            </div>
            <div class="row py-4 contenedorInfoOferta mx-auto">
                <h4>Descripcion: </h4>
                <p id="desc" class="texto"><%= dataOferta.getDescripcion() %></p>
                <h4>Remuneracion (UYU): </h4>
                <p id="remun" class="texto"><%= remune %></p>
                <h4>Horario: </h4>
                <p id="horario" class="texto"><%= dataOferta.getHorarioTrabajo() %></p>
                <h4>Departamento: </h4>
                <p id="dept" class="texto"><%= dataOferta.getDepartamento() %></p>
                <h4>Ciudad: </h4>
                <p id="ciudad" class="texto"><%= dataOferta.getCiudad() %></p>
                <h4>Fecha de alta: </h4>
                <p id="fechaAlta" class="texto"><%= dataOferta.getFechaAlta() %></p>
                <h4>Keywords asociadas: </h4>
                <p id="keywords" class="texto">
                <% List<String> keywords = dataOferta.getKeywords();
                for (String key : keywords) {%>
					<a class = "sinHipervinculo blanco" href="home?key=<%= key %>"><button type="button" class="tags keywords col-3"><%= key%></button></a>
				<%}%>						
				</p>
            </div>
        
        
            <div class="row py-4 mx-auto">
                <h4 class="titulo centrar">Mi Postulación</h4>
                <a href='consulta-postulacion?postulante=<%=dataPostulante.getNickname()%>&oferta=<%=dataOferta.getNombre()%>' class="sinHipervinculo">
                        <div class='row efectoZoom coloresPost centrar'>
                            <img src='Imagenes?id=<%= dataPostulante.getImagen() %>&tipo=usuarios' class='imagenPost' alt="User Image">
                            <br><br>
                            <p id="nomPostulante" class="text-center"><%= dataPostulante.getNickname()%></p>   
                        </div>
                 </a>
            </div>
        </div>
        <!-- FIN CONSULTA DE OFERTA -->
        <!-- Si ya me postule VER MEJOR-->
        
        <jsp:include page="/WEB-INF/movil/template/footer.jsp"/>
        <script src="media/js/consulta_de_oferta.js"></script>
    </body>
</html>
