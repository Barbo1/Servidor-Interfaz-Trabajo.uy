<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="publicador.* , java.util.Collection, java.util.List" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Titulo -->
        <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
        <jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>		

    </head>
    <body>
    	<!-- Obtengo la infromacion de la oferta y de la empresa que publico la oferta -->
    	<% 	
    		DtEmpresaCompleto dataEmpresaComp = (DtEmpresaCompleto) request.getAttribute("dataEmpresa");
    		DtEmpresa dataEmpresa = dataEmpresaComp.getEmpresa();
    		DtOfertaCompleto dataOfertaComp = (DtOfertaCompleto) request.getAttribute("dataOfertaComp");
    		DtOferta dataOferta = dataOfertaComp.getOferta();
    		int remune = Math.round(dataOferta.getRemuneracion()); // castea de float a int
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
	                        <div class="row centrar">
	                            <div class="col-1"></div>
	                            <div class="col-4 py-4 px-3" id="imagen"><img src='Imagenes?id=<%= dataOferta.getImagen() %>&tipo=ofertas' class='imagenOferta'></div>
	                            <div class="col-3"></div>
	                            <div class="col-5 py-4 px-3 margenTitulo">
	                                <div class="row titulo" id="nombreOferta"><%= dataOferta.getNombre()%></div>
	                                <div class="row subtitulo" id="publicadaPor"><%= dataOferta.getEmpresa() + " / " + dataEmpresa.getCorreoElectronico()%></div>
	                            </div>
	                        </div>
	                        <div class="contenedorInfoOferta">
	                            <h4>Descripcion: </h4>
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
	                        </div>
	                    </div>
	                    <!--Se le indica al visitante que cree una cunta o que inicie sesion -->
	                    <div class="col-4 py-4">
	                        <div class="contenedorPostulantesPaquete">
	                            <h4 class="titulo centrar">Postulate</h4>
	                            <div class="contenedorConsOf centrar" id="postulantes">
	                                <p class="texto">Crea una cuenta o inicia sesion</p>
	                            </div>
	                            <div class="row">
	                                <div class="col-1"></div>
	                                <a href='iniciar-sesion' class="col-4 btn btn-primary sinHipervinculo boton-follow mx-auto text-center" type="button">Iniciar Sesion</a>
	                                <div class="col-2"></div>
	                                <a href='alta-usuario' class="col-4 btn btn-primary sinHipervinculo boton-follow mx-auto text-center" type="button">Crear Usuario</a>
	                                <div class="col-1"></div>
	                            </div>
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