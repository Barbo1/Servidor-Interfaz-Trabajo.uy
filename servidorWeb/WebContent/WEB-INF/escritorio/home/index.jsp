<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection, publicador.DtOferta" %>
<%@ page import="java.util.Collection, publicador.DtEmpresa" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Titulo -->
        <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
        <jsp:include page="/WEB-INF/escritorio/template/Head.jsp"/>
    </head>
    <body>
        <link rel="stylesheet" href="media/css/consulta_de_oferta.css" text="text/css">
        <jsp:include page="/WEB-INF/escritorio/template/Navbar.jsp"/>
    	<div class="container-fluid">
        	<div class="row">
    			<jsp:include page="/WEB-INF/escritorio/template/LeftNavbar.jsp"/>
    			
                <!-- content -->
                <div class="col-10 py-4 px-3">
                    <!-- Carrusel -->
                    <div id="carouselPresentacion" class="carousel slide" data-bs-ride="carousel"> <!-- carousel-dark Para hacerlo oscuro -->
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselPresentacion" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselPresentacion" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselPresentacion" data-bs-slide-to="2" aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="media/imagenes/carrusel/personafeliz.jpeg" class="d-block w-100 " alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="titulo">¡Tu trabajo soñado en Uruguay!</h5>
                                    <p class="descCarrusel">En Trabajo.UY encontraras ofertas de todo indole distribuidas por todo el territorio.</p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="media/imagenes/carrusel/equipo-trabajo.jpeg" class="d-block w-100" alt="...">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="titulo">¡Arma tu equipo ideal!</h5>
                                    <p class="descCarrusel">En Trabajo.UY encontraras diversos candidatos a los puesto de trabajo que ofrezcas, pudiendo ver informacion detallada de ellos.</p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img src="media/imagenes/carrusel/edificios.jpg" class="d-block w-100" alt="...">

                                <div class="carousel-caption d-none d-md-block">
                                    <h5 class="titulo">¡Encuentra un empleo mejor!</h5>
                                    <p class="descCarrusel">En Trabajo.UY puedes hacer que tu currículum sea visible para miles de empresas en nuestra bolsa de trabajo.</p>
                                </div>
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselPresentacion" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Anterior</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselPresentacion" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Siguiente</span>
                        </button>
                    </div>
					<div>
                    <!-- Ofertas -->
               		<% 
               			List<DtOferta> ofertas = (List<DtOferta>) request.getAttribute("ofertas");
                        
                        /* Posiciones representa la cantidad de paginas que se van a mostrar en la paginacion.
                        *  Puede ser camibada, pero debe tener un minimo de 3 para una correcta navegacion. */
                        int posiciones = 3;

                        /* Cantidad maxima de caracteres que aparecen en la descripcion de las ofertas y empresas. */
                        int max_desc_char = 100;
                        
                        if (ofertas != null){
                            String str;

                            int cantOfertas = ofertas.size();
                            int paginas = (cantOfertas + 3) / 4;
                            int paginaOfertas = (int) request.getAttribute("paginaOfertas");
                            int tope_ofertas = paginaOfertas * 4;
                            int i = tope_ofertas - 4;
                            tope_ofertas = (cantOfertas > tope_ofertas ? tope_ofertas : cantOfertas);

                            List<DtOferta> ofertasFavoritas = (List<DtOferta>) request.getAttribute("ofertasFavoritas");
                            List<DtEmpresa> empresas = (List<DtEmpresa>) request.getAttribute("empresas");
                            DtOferta ofer;

                            /* Si hay empresas entonces hay que mostrar ofertas y empresas. */
                            if (empresas != null) {
                               	
                                int cantEmpresas = empresas.size();
                                int paginasE = (cantEmpresas + 3) / 4;
                                int paginaEmpresas = (int) request.getAttribute("paginaEmpresas");
                                int tope_empresas = (paginaEmpresas) * 4;
                                int j = tope_empresas - 4;
                                tope_empresas = cantEmpresas > tope_empresas ? tope_empresas : cantEmpresas;
                                DtEmpresa empr;
                                %>
                                    <div class="row pt-3">
                                        <div class="col pt-4 d-flex justify-content-center">
                                            <h2>Ofertas</h2>
                                        </div>
                                        <div class="col pt-4 d-flex justify-content-center">
                                            <h2>Empresas</h2>
                                        </div>
                                    </div>
                                <%
                                while (i < tope_ofertas || j < tope_empresas) {
                                    %>
                                    <div class="row">
                                    <%
                                    if (i < tope_ofertas) {
                                        ofer = ofertas.get(i);
                                        %>
                                        <div class="col-6 d-grid px-4 py-3">
                                            <div class='row'>
                                                <a href='consulta-oferta?name=<%=ofer.getNombre().replace("\\s","_").replaceAll("[áäâà]", "a").replaceAll("[éêèë]", "e").replaceAll("[íïîì]", "i").replaceAll("[óôöò]", "o").replaceAll("[úüûù]", "u").replaceAll("[ÁÄÂÀ]", "A").replaceAll("[ÉÊÈË]", "E").replaceAll("[ÍÏÎÌ]", "I").replaceAll("[ÓÔÖÒ]", "O").replaceAll("[ÚÜÛÙ]", "U").replace("/", "")%>' class="extendd2 card-oferta-empresa fondoBlancoLetraAzul texto sinHipervinculo">
                                                    <img src='Imagenes?id=<%=ofer.getImagen()%>&tipo=ofertas' class='imagenOfertaIndex pt-3'></img>
                                                    <hr style="height: 2px; background-color: #0A66C2; opacity: 100%; font-size: 90%"/>
                                                    <p class="tituloOferta" style="font-size: 90%" id="encabezadoOferta"><%= ofer.getNombre() %> | <%= ofer.getEmpresa() %></p>
                                                    <%
                                                    str = ofer.getDescripcion();
                                                    if (str.length() > max_desc_char) {
                                                        %>
                                                        <p id="descpOferta" style="font-size: 80%">
                                                            <%= 
                                                                str.substring(0, max_desc_char) + "..." 
                                                            %>
                                                        </p>
                                                        <% 
                                                    } else {
                                                        %>
                                                        <p id="descpOferta" style="font-size: 80%">
                                                            <%= 
                                                                str.substring(0, str.length()) + 
                                                                new String(new char[max_desc_char - str.length()]).replace("\0", " ")
                                                            %>
                                                        </p>
                                                        <% 
                                                    }
                                                    if (request.getSession().getAttribute("estado_sesion").equals("POSTULANTE") && 
                                                            ofertasFavoritas != null && 
                                                            ofertasFavoritas.contains(ofer)) { 
                                                        %>
                                                        <div>		   
                                                            <img class="Favorito" id="botonFavorito" src="media/imagenes/general/favorito.svg" height="50" width="50"/>
                                                        </div>	
                                                        <% 
                                                    } 
                                                    %>
                                                </a>
                                            </div>
                                        </div>
                                        <%
                                    } else {
                                        %>
                                        <div class="col-6 d-grid px-4 py-3">
                                            <div class='row' hidden>
                                                <a class="extendd2 card-oferta-empresa fondoBlancoLetraAzul texto sinHipervinculo">
                                                    <div class='row efectoZoom coloresOfertas my-auto'>
                                                        <img src='Imagenes?id=default.png&tipo=usuarios' class='imagenOfertaIndex pt-3'></img>
                                                        <hr style="height: 2px; background-color: #0A66C2; opacity: 100%; font-size: 90%"/>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                        <%
                                    }
                                    if (j < tope_empresas) {
                                        empr = empresas.get(j);
                                        %>
                                        <div class="col-6 d-grid px-4 py-3">
                                            <div class='row'>
                                                <a href="consulta-usuario?user=<%= empr.getNickname() %>" class="extendd2 card-oferta-empresa fondoBlancoLetraAzul texto sinHipervinculo">
                                                    <img src='Imagenes?id=<%=empr.getImagen()%>&tipo=usuarios' class='imagenOfertaIndex pt-3'></img>
                                                    <hr style="height: 2px; background-color: #0A66C2; opacity: 100%; font-size: 90%"/>
                                                    <p class="tituloOferta" style="font-size: 90%" id="encabezadoOferta"><%= empr.getNombre() %> | <%= empr.getNickname() %></p>
                                                    <%
                                                    str = empr.getDescripcion();
                                                    if (str.length() > max_desc_char) {
                                                        %>
                                                        <p id="descpOferta" style="font-size: 80%">
                                                            <%= str.substring(0, max_desc_char) 
                                                            + "..." %>
                                                        </p>
                                                        <% 
                                                    } else {
                                                        %>
                                                        <p id="descpOferta" style="font-size: 80%">
                                                            <%= str.substring(0, str.length()) + 
                                                                new String(new char[max_desc_char - str.length()]).replace("\0", " ") + 
                                                                "..." %>
                                                        </p>
                                                        <% 
                                                    }
                                                    %>
                                                </a>
                                            </div>
                                        </div>
                                        <% 
                                        } else {
                                            %>
                                            <div class="col-6 d-grid px-4 py-3">
                                                <div class='row' hidden>
                                                    <a class="extendd2 card-oferta-empresa fondoBlancoLetraAzul texto sinHipervinculo">
                                                        <div class='row efectoZoom coloresOfertas my-auto'>
                                                            <img src='Imagenes?id=default.png&tipo=ofertas' class='imagenOfertaIndex pt-3'></img>
                                                            <hr style="height: 2px; background-color: #0A66C2; opacity: 100%; font-size: 90%"/>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                            <%
                                        }
                                    i++;
                                    j++;
                                    %></div><%
                                }
                                %>
                                <div class="row">

                                    <!-- pagination para las ofertas -->

                                    <div class = "col center">
                                        <ul class="pagination">
                                            <%
                                            String res;
                                            String param;
                                            param = "home?buscar=0&orden=" + request.getAttribute("orden") + 
                                                    "&paginaEmpresas=" + paginaEmpresas +
                                                    "&busqueda=" + request.getAttribute("busqueda") + 
                                                    "&paginaOfertas=";
                                            %>
                                                <li><a href="<%= param %>1">«</a></li>
                                            <%
                                            int pag = paginaOfertas;

                                            /* Se calculan las posiciones que se van a mostrar, definidas mediante la variable posiciones. 
                                            *  Si se quieren cambiar, es necesario que sea mayor o igual a 3. */
                                            
                                            j = paginas - posiciones;
                                            if (pag > j && 0 < j) { pag = j; } 
                                            else if (pag <= (posiciones + 1) / 2) { pag = 1; }
                                            else { pag -= (posiciones + 2) / 2; }
                                            
                                            tope_ofertas = pag + (paginas < posiciones ? paginas : posiciones);

                                            for(i = pag; i < tope_ofertas; i++){ 
                                                %>
                                                    <li><a href="<%= param %><%= i %>"><%= i %></a></li>
                                                <%	
                                            }
                                            %>
                                                <li><a href="<%= param %><%= paginas %>">»</a></li>
                                        </ul>
                                    </div>

                                    <!-- pagination para las empresas -->

                                    <div class = "col center">
                                        <ul class="pagination">
                                            <%
                                            param = "home?buscar=0&orden=" + request.getAttribute("orden") + 
                                                    "&paginaOfertas=" + paginaOfertas + 
                                                    "&busqueda=" + request.getAttribute("busqueda") + 
                                                    "&paginaEmpresas=";
                                            %>
                                                <li><a href="<%= param %>1">«</a></li>
                                            <%
                                            pag = paginaEmpresas;
                                            paginas = (empresas.size() + 3) / 4;

                                            /* Se calculan las posiciones que se van a mostrar, definidas mediante la variable posiciones. 
                                            *  Si se quieren cambiar, es necesario que sea mayor o igual a 3. */

                                            if (pag > j && 0 < j) { pag = j; } 
                                            else if (pag <= (posiciones + 1) / 2) { pag = 1; }
                                            else { pag -= (posiciones + 2) / 2; }
                                            
                                            tope_ofertas = pag + (paginas < posiciones ? paginas : posiciones);

                                            for(i = pag; i < tope_ofertas; i++){ 
                                                %>
                                                    <li><a href="<%= param %><%= i %>"><%= i %></a></li>
                                                <%	
                                            }
                                            %>
                                                <li><a href="<%= param %><%= paginas %>">»</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <%
                            } else {
                                while (i < tope_ofertas) {
                                    ofer = ofertas.get(i);
                                    %> 
                                    <a href='consulta-oferta?name=<%=ofer.getNombre().replace("\\s","_").replaceAll("[áäâà]", "a").replaceAll("[éêèë]", "e").replaceAll("[íïîì]", "i").replaceAll("[óôöò]", "o").replaceAll("[úüûù]", "u").replaceAll("[ÁÄÂÀ]", "A").replaceAll("[ÉÊÈË]", "E").replaceAll("[ÍÏÎÌ]", "I").replaceAll("[ÓÔÖÒ]", "O").replaceAll("[ÚÜÛÙ]", "U").replace("/", "")%>' class="sinHipervinculo">
                                        <div class='row extendd coloresOfertas centrar'>
                                            <div class='col-3 px-0'>
                                                <img src='Imagenes?id=<%=ofer.getImagen()%>&tipo=ofertas' class='imagenOfertaIndex'></img>
                                            </div>
                                            <div class='col-9'>
                                                <div>
                                                    <p class="tituloOferta" id="encabezadoOferta"><%=ofer.getNombre() + " | " + ofer.getEmpresa()%></p>
                                                </div>
                                                <div>
                                                    <p id="descpOferta"><%= ofer.getDescripcion()%></p>
                                                </div>
                                                
                                                <% 
                                                if (request.getSession().getAttribute("estado_sesion").equals("POSTULANTE") && 
                                                        ofertasFavoritas != null && 
                                                        ofertasFavoritas.contains(ofer)) { 
                                                    %>
                                                    <div>		   
                                                        <img class="Favorito" id="botonFavorito" src="media/imagenes/general/favorito.svg" height="50" width="50"/>
                                                    </div>	
                                                    <% 
                                                } 
                                                %>
                                            </div>
                                        </div>
                                    </a>
                                    <%
                                    i++;
                                } 
                                %>
                                <div class = "center">
                                    <ul class="pagination">
                                        <%
                                        String res;
                                        String param;
                                        if ((res = (String)request.getAttribute("key")) != null) {
                                            param = "home?buscar=1&key=" + res + "&paginaOfertas=";
                                        } else if ((res = (String)request.getAttribute("empresa")) != null) {
                                            param = "home?buscar=1&empresa=" + res + "&paginaOfertas=";
                                        } else {
                                            param = "home?buscar=1&paginaOfertas=";
                                        }
                                        %>
                                            <li><a href="<%= param %>1">«</a></li>
                                        <%
                                        int pag = paginaOfertas;

										i = paginas - posiciones;
                                        if (pag > i && 0 < i) { pag = i; } 
                                        else if (pag <= (i = (posiciones + 1) / 2)) { pag = 1; }
                                        else { pag -= i - 1; }
                                        
                                        tope_ofertas = pag + (paginas < posiciones ? paginas : posiciones);

                                        for(i = pag; i < tope_ofertas; i++){ 
                                            %>
                                                <li><a href="<%= param %><%= i %>"><%= i %></a></li>
                                            <%	
                                        }
                                        %>
                                            <li><a href="<%= param %><%= paginas %>">»</a></li>
                                    </ul>
                                </div>
                                <%
                            }
                        } %>
              	</div> 
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>
    </body>
</html>
