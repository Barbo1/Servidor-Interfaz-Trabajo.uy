<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, publicador.DtOferta" %>
<%@ page import="publicador.DtEmpresa" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Titulo -->
        <title>Trabajo.uy | Busca tu trabajo ideal en Uruguay </title>
        <jsp:include page="/WEB-INF/movil/template/Head.jsp"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/movil/template/Navbar.jsp"/>
        
        <script src="media/js/busqueda_movil.js"></script>
        
    	<!-- barra de busqueda -->
        <div class="row px-3 pt-4">
            <div class="col">
                <h2> Empresas </h2>
                <select class="form-select" aria-label="Default select example" id="PorQue">
                    <option selected value="E" style="font-size: 20px">-</option>
                    <%
                        List<DtEmpresa> empresas = (List<DtEmpresa>)request.getSession().getAttribute("empr");
                        if (empresas != null) {
                            for (DtEmpresa empr : empresas) {
                            %>
                                <option style="font-size: 20px" value="<%=empr.getNickname()%>" onClick="busquedae(this)">
                                    <%=empr.getNickname()%>
                                </option>
                            <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="col">
                <h2> Keywords </h2>
                <select class="form-select" aria-label="Default select example" id="PorQue">
                    <option selected value="E" style="font-size: 20px">-</option>
                    <%
                        List<String> keys = (List<String>)request.getSession().getAttribute("keywords");
                        if (keys != null) {
                            for (String key: keys) {
                            %>
                                <option style="font-size: 20px" value="<%= key %>" onClick="busquedak(this)">
                                    <%= key %>
                                </option>
                            <%
                            }
                        }
                    %>
                </select>
            </div>
        </div>
		<div class="container" id="ofertas">
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
                DtOferta ofer;

                while (i < tope_ofertas) {
                    ofer = ofertas.get(i);
                    %>
                        <a href="consulta-oferta?name=<%= ofer.getNombre() %>" class="sinHipervinculo">
                            <div class='row efectoZoom coloresOfertas centrar'>
                                <img src='Imagenes?id=<%= ofer.getImagen() %>&tipo=ofertas' class='imagenOfertaIndex'></img>
                                <hr style="height: 2px; background-color: #0A66C2; opacity: 100%"/>
                                <p class="tituloOferta" id="encabezadoOferta"><%= ofer.getNombre() %> | <%= ofer.getEmpresa() %></p>
                                <p id="descpOferta"><%= ofer.getDescripcion() %></p>
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
        %>
        </div>
        <!-- Fin ofertas -->  	
        <jsp:include page="/WEB-INF/movil/template/footer.jsp"/>
    </body>
</html>
