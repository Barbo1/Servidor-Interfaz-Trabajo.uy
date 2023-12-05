<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="publicador.DtUsuario" %>
    	<% DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario_logueado");%>
        <nav class="navbar navbar-expand-lg fondoAzulLetraBlanca navbar-custom d-flex justify-content-between">
            <div class="d-flex justify-content-center ms-3">
                <img src="Imagenes?id=<%=user.getImagen()%>&tipo=usuarios" alt="hugenerd" width="30" height="30" class="rounded-circle"> 
                <p class="my-auto ml-2"><%= user.getNickname() %></p>
            </div>
            <a class="navbar-brand" href="home">
                <img class="mx-auto" src="media/imagenes/general/uy.png" width=58px height=54px>
            </a>
            <button class="navbar-toggler fondoAzulLetraBlanca me-3" type="button" data-bs-toggle="collapse" data-bs-target="#menuOpciones" aria-controls="menuOpciones" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </nav>

        <div class="collapse" id="menuOpciones">
            <div class="d-grid gap-4 bg-dark">
                <a class="btn bg-dark botonMenuDesplegable rounded-0" href="cerrar-sesion">
                    Cerrar Sesion
                </a>
            </div>
            <div class="d-grid gap-4 bg-dark">
                <a class="btn bg-dark botonMenuDesplegable rounded-0" href="postulaciones">
                    Ver Postulacion
                </a>
            </div>
        </div>
