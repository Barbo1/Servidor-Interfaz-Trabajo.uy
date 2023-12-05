<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import=" publicador.DtUsuario, publicador.DtEmpresa,publicador.DtPostulante, java.util.Collection" %>
    
<script src="media/js/busqueda.js"></script>
<nav class="navbar navbar-expand-lg navbar-primary fondoAzulLetraBlanca">
    <div class="navbar-collapse d-flex justify-content-between" id="navbarSupportedContent">
        <a href="./home" class="px-3"><img src="media/imagenes/general/logo.png" width="55%" height="55%" id="logoPrincipal"></a>
        <form>
            <div class="input-group rounded-2">
                <div id="menuAutocompletado">
                    <input type="text" autocomplete="off" class="form-control dropdown-toggle dropdown-toggle-split" placeholder="Busqueda..." id="busqueda">
                    <ul id='opcionesAutoCompletado' class='dropdown-menu'>
                    </ul>
                </div>
                <div class="dropdown">
                    <button class="btn botonbusqueda dropdown-toggle" type="button" id="botonFiltros">
                        Filtros
                    </button>
                    <div class="dropdown-menu dropend" id="dropdownFiltros">
                        <div>
                            <button id="botonBusqueda" type="button" class="dropdown-item dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                Busqueda
                            </button>
                            <ul class="dropdown-menu" id="dropdownBusqueda">
                                <div class="dropdown-item d-flex justify-content-between">
                                    <a>por defecto</a>
                                    <input id="resID1" class="form-check-input" type="radio" value="0" name="buscaRadio" checked="checked"/>
                                </div>
                                <div><hr class="dropdown-divider"></div>
                                <div class="dropdown-item d-flex justify-content-between">
                                    <a>por oferta</a>
                                    <input id="resID2" class="form-check-input" type="radio" value="1" name="buscaRadio"/>
                                </div>
                            </ul>
                        </div>
                        <div id="ordenamiento">
                            <button id="botonOrdenamiento" type="button" class="dropdown-item dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                Ordenamiento 
                            </button>
                            <ul class="dropdown-menu" id="dropdownOrdenamiento">
                                <div class="dropdown-item d-flex justify-content-between">
                                    <a>normal</a>
                                    <input class="form-check-input" type="radio" value="0" name="ordenRadio" checked="checked"/>
                                </div>
                                <div><hr class="dropdown-divider"></div>
                                <div class="dropdown-item d-flex justify-content-between">
                                    <a>alfabetico</a>
                                    <input class="form-check-input" type="radio" value="1" name="ordenRadio"/>
                                </div>
                            </ul>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary botonbusqueda" type="button" onClick="redireccion()">Buscar</button>  
            </div>
        </form>


        <% 
        DtUsuario usr = null;
        if (session.getAttribute("estado_sesion") != "VISITANTE") {
            usr = (DtUsuario) session.getAttribute("usuario_logueado");
        }

        if (usr == null) { %>

        <div class="btn-group px-3" id="user_name">
            <ul class="navbar-nav">
                <li><a class="nav-item nav-link text-light" href="iniciar-sesion">Iniciar Sesion</a></li>
                <li class="mt-1 text-light"><h4>|</h4></li>
                <li><a class="nav-item nav-link text-light" href="alta-usuario">Crear Usuario</a></li>
            </ul>
        </div>

        <%}else if (usr != null) {%>
            <div class="btn-group px-4">
                <ul class="navbar-nav">
                    <a href="#"
                       type="button"
                       class="nav-link dropdown-toggle text-light mr-5" 
                       data-bs-toggle="dropdown"
                       data-bs-display="static" 
                       aria-expanded="false">
                        <img src="Imagenes?id=<%= usr.getImagen() %>&tipo=usuarios" alt="hugenerd" width="30" height="30" class="rounded-circle">
                        <span class="mx-1"> <%= usr.getNickname() %></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="consulta-usuario?user=<%= usr.getNickname()%>">Mi Perfil</a></li>
                        <li><a class="dropdown-item" href="cerrar-sesion">Cerrar Sesión</a></li>
                    </ul>
                </ul>
            </div>
            <% } %>
        </div>
    </nav>
