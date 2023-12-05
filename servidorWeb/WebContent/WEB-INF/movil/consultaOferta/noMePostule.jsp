<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List, publicador.*" %>
<!DOCTYPE html>
<html lang="es">
    <head>
    	<%
            DtEmpresaCompleto dataEmpresaComp = (DtEmpresaCompleto) request.getAttribute("dataEmpresa");
            DtEmpresa dataEmpresa = dataEmpresaComp.getEmpresa();
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
            <div class="row py-4">
            	<h1 class="tituloPrincipal">Información de la oferta</h1><br>
                <div class="titulo" id="nombreOferta"><%= dataOferta.getNombre() %></div>
                <div class="subtitulo" id="publicadaPor"><%= dataEmpresa.getNickname() %></div>
            </div>
            <div class="row py-4 mx-auto" id="imagen">
                <img src='Imagenes?id=<%= dataOferta.getImagen() %>&tipo=ofertas' class='imagenOferta'>
            </div>
            <div class="row py-4 contenedorInfoOferta">
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
        </div>
        <!-- FIN CONSULTA DE OFERTA -->

        <!-- postulado -->
        <h4 class="titulo centrar">Trabaja con nosotros</h4>
        <p class="texto centrar">¡Tu oportunidad está aquí!</p>
        <p class="texto centrar">Postulate y envíanos tu CV:</p>
        <div class="d-flex justify-content-center">
            <button class="btn botonbusqueda mx-auto" data-bs-toggle="modal" data-bs-target="#formularioPopup" id="botonPost">Postular</button>
        </div>
        <% 
            String vidLink = (String) request.getParameter("vid");
            String cvValue = (String) request.getParameter("cv") != null ? request.getParameter("cv") : "";
            String motValue = (String) request.getParameter("mot") != null ? request.getParameter("mot") : ""; 
        %>
        <!-- formulario popup -->
        <div class="modal fade" id="formularioPopup" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <h2 class="text-center">Ingrese la informacion de su postulación:</h2>
                        <form class="formulario" id="formulario" action="postulacion?name=<%=dataOferta.getNombre().replaceAll("\\s","_").replaceAll("[áäâà]", "a").replaceAll("[éêèë]", "e").replaceAll("[íïîì]", "i").replaceAll("[óôöò]", "o").replaceAll("[úüûù]", "u").replaceAll("[ÁÄÂÀ]", "A").replaceAll("[ÉÊÈË]", "E").replaceAll("[ÍÏÎÌ]", "I").replaceAll("[ÓÔÖÒ]", "O").replaceAll("[ÚÜÛÙ]", "U").replaceAll("/", "")%>" method="post">
                            <div class="row espacio">
                                <label for="cv_reducido" class="labels">CV Reducido</label>
                                <textarea class="campo" id="cv_reducido"  cols="25" rows="6" name="cv_reducido"><%=cvValue %></textarea><br>
                                <p class="warnings" id="warningCV">Ingrese un CV reducido </p>	
                            </div>
                            <div class="row espacio">
                                <label for="motivacion" class="labels">Motivación</label>
                                <textarea  class="campo" id="motivacion" cols="25" rows="6" name="motivacion"><%=motValue %></textarea><br>
                                <p class="warnings" id="warningMot">Ingrese una motivación </p>

                            </div>
                            <div class="row espacio">
	                            <label for="video" class="labels">Video (opcional)</label>
	                            <textarea  class="campo" id="video" cols="25" rows="2" name="video"><%=vidLink %></textarea><br>
	                            <p class="warnings" id="warningVid">Ingrese un video válido </p>
	                            
                            </div>
                            <br>
                            <div class="row ">
                                <div class="col d-flex justify-content-center">
                                    <button onclick="validarFormulario()" class = "btn botonbusqueda mx-auto" id="aceptar">Aceptar</button>
                                </div>
                                <div class="col d-flex justify-content-center">
                                    <input class="btn botonbusqueda mx-auto" type="button" id="cancelar" value="Cancelar">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/movil/template/footer.jsp"/>
        <script src="media/js/consulta_de_oferta.js"></script>
        
    </body>
</html>
