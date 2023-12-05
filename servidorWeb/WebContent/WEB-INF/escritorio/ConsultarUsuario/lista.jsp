<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="publicador.DtUsuario, java.util.Collection" %>
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
		                
		                    <h1 class="tituloPrincipal">Lista de usuarios</h1>
				
		                        <div class="d-flex flex-wrap" id="lista">
		                                         		                        
		                        <% 
								Collection<DtUsuario> usuarios = (Collection<DtUsuario>) request.getAttribute("usuarios");
		                        int elementos = usuarios.size();
		                        int paginas = elementos / 10;
		                   		int pagActual = (int) request.getAttribute("pagina");
		                   		int i = 0;
		                   		if(elementos % 10 != 0)
		                   			paginas ++;
		        				for(DtUsuario usuario: usuarios){
		        					i++;
		                    		if(i > (pagActual-1)*10 && i <= pagActual * 10){
								%>	
							
		                            <div class = 'card extendida extendd'>
			                            <img class='card-img-top imagenLista' src="Imagenes?id=<%= usuario.getImagen() %>&tipo=usuarios">
			                            <div class='card-body'>
			                                <h4 class='card-title'><%= usuario.getNombre() + " " + usuario.getApellido() %> | <%= usuario.getNickname() %></h4>
			                                <a href='./consulta-usuario?user=<%= usuario.getNickname() %>' class='btn btn-primary' id='links'>Ver perfil</a>
			                            </div>
                        			</div>
		                            <%} } %>   
		                        </div>  
		                        <div class = "center">
				                  <ul class="pagination">
				                    <li><a href="consulta-usuario?pagina=1">«</a></li>
				                    <%
				                    	for(i = 0; i < paginas; i++){ %>
				                    		<li><a href="consulta-usuario?pagina=<%=i+1%>"><%= i + 1 %></a></li>
				                 	<%}%>
				                    <li><a href="consulta-usuario?pagina=<%=paginas%>">»</a></li>
				                  </ul>
                				</div> 
		                </div>
                <!-- -->
                </div>
          </div>
        <jsp:include page="/WEB-INF/escritorio/template/footer.jsp"/>    
        <script src="media/js/consulta_de_usuario.js"></script>
    </body>
</html>