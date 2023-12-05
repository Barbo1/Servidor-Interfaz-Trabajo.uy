<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="publicador.DtTipoDeOferta" %>
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

                    <h1 class="tituloPrincipal">
                        Informacion del Tipo de Publicación Seleccionado
                    </h1>

                    <div class="col">
                        <div class="card card-body border-primary">
                            <div class="row d-flex justify-content-center">

                                <%
                                    DtTipoDeOferta tof = (DtTipoDeOferta) request.getSession().getAttribute("tipos_ofertas");
                                	String fecha = tof.getFechaAlta();
                                	String[] aux = fecha.split("-");
                                %>

                                <!-- muestro el nombre -->
                                <div class="row mb-3">
                                    <div class="col">
                                        <h5>Nombre</h5>
                                        <div class="card pr-2 pl-2 border-primary card-body" id="card-panel">
                                            <b> <%= tof.getNombre() %> </b>
                                        </div>
                                    </div>
                                </div>

                                <!-- muestro la descripcion -->
                                <div class="row mb-3">
                                    <div class="col">
                                        <h5>Descripción</h5>
                                        <div class="card pr-2 pl-2 border-primary card-body" id="card-panel">
                                            <b> <%= tof.getDescripcion() %> </b>
                                        </div>
                                    </div>
                                </div>

                                <!-- muestro todos los datos numbericos en la misma linea -->
                                <div class="row mb-3">
                                    <div class="col">
                                        <h5 class="pl-2">Exposición</h5>
                                        <div class="card border-primary card-body" id="card-panel">
                                            <b> <%= tof.getExposicion() %> </b>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <h5>Duración (Dias)</h5>
                                        <div class="card border-primary card-body" id="card-panel">
                                            <b> <%= tof.getDuracion() %> </b>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <h5>Fecha de Creación</h5>
                                        <div class="row">
                                            <div class="col">
                                                <div class="card border-primary card-body" id="card-panel">
                                                    <b> <%= aux[0] %> </b>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="card border-primary card-body" id="card-panel">
                                                    <b> <%= aux[1] %> </b>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="card border-primary card-body" id="card-panel">
                                                    <b> <%= aux[2] %> </b>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col">
                                        <h5 class="pr-2">Costo</h5>
                                        <div class="card border-primary card-body" id="card-panel">
                                            <b> <%= tof.getCosto() %> </b>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        &nbsp;
                    </div>

                </div>
                <!-- -->

            </div>
        </div>
        <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>    
        <script src="media/js/consulta_de_usuario.js"></script>
    </body>
</html>

