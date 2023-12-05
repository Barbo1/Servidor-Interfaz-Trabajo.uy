package controllers;

import java.util.List;
import publicador.DtOferta;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

///////////////////////////Servlet implementation class ConsultaPostulacion///////////////////////////

@WebServlet(urlPatterns = {"/consulta-postulacion", "/postulaciones"})
public class ConsultaPostulacion extends HttpServlet {

    private static final long serialVersionUID = 1L;
    publicador.WebServicesService service = new publicador.WebServicesService();
    publicador.WebServices port = service.getWebServicesPort();

    //Contructor por defecto
    public ConsultaPostulacion() {}

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("estado_sesion").equals("VISITANTE"))
            response.sendRedirect("home");
        else {
            String userAgent = request.getHeader("User-Agent");
            String postulante = request.getParameter("postulante");
            String nickLog = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname();
            switch (request.getServletPath()) {
                case "/postulaciones":
                    if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
                        List<DtOferta> ofertas = (List<DtOferta>) port.mostrarPostulante(nickLog).getDtofertas();
                        request.setAttribute("postulaciones", ofertas);
                        String pagina = request.getParameter("paginaOfertas");
                        try {
                            request.setAttribute("paginaOfertas", pagina != null ? Integer.valueOf(pagina) : 1);
                        } catch (Exception e) {
                            request.setAttribute("paginaOfertas", 1);
                        }
                        request.getRequestDispatcher("/WEB-INF/movil/consultarPostulacion/consultarPostulaciones.jsp").forward(request, response);
                    }
                    response.sendRedirect("home");
                    break;
                case "/consulta-postulacion":
                    String oferta = request.getParameter("oferta");

                    if(postulante == null || oferta == null || !port.estaPostulado(postulante, oferta) || // la postulacion no existe 
                            (port.esEmpresa(nickLog) && !port.ofertaDeEmpresa(nickLog, oferta)) || //soy empresa y no es mi oferta
                            (port.esPostulante(nickLog)&& !postulante.equals(nickLog))) { //soy postulante y no es mi postulacion
                        response.sendRedirect("home");
                    } else {
                        publicador.DtPostulacionCompleto post=  port.getPostulanteDeOferta(postulante, oferta);
                        publicador.DtOferta of = port.mostrarOferta(oferta);

                        request.setAttribute("dataOferta",of);
                        request.setAttribute("dataPostulacion",post);
                        if (userAgent != null && userAgent.toLowerCase().contains("mobile")) 
                            request.getRequestDispatcher("/WEB-INF/movil/consultarPostulacion/consultarPostulacion.jsp").forward(request, response);
                        else
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarPostulacion/consultarPostulacion.jsp").forward(request, response);
                    }

                    break;
                default:
                    break;
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}




