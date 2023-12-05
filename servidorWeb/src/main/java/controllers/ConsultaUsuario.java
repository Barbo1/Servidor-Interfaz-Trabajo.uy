package controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publicador.DtUsuario;

/**
 * Servlet implementation class ConsultaUsuario
 */
@WebServlet("/consulta-usuario")
public class ConsultaUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultaUsuario() { }
    publicador.WebServicesService service = new publicador.WebServicesService();
    publicador.WebServices port = service.getWebServicesPort();

    private void processRequest(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
            respone.sendRedirect("home");
        } else {
            if (session.getAttribute("estado_sesion") == null)
                respone.sendRedirect("home");
            else {
                String nickname = request.getParameter("user");
                if(nickname == null) { // no existe 
                	String pagina = request.getParameter("pagina");
                    request.setAttribute("pagina", pagina != null ? Integer.valueOf(pagina) : 1);
                    request.setAttribute("usuarios", port.getDtUsuarios().getItem());
                    request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarUsuario/lista.jsp").forward(request, respone);

                } else { // muestro la data
                    if (port.esEmpresa(nickname)) {
                        request.setAttribute("dataUserEmpresa", port.mostrarEmpresa(nickname));
                        if(request.getSession().getAttribute("estado_sesion").equals("EMPRESA") && nickname.equals(((DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname())) {
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarUsuario/empresa_myProfile.jsp").forward(request, respone);
                        } else {
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarUsuario/empresa_guest.jsp").forward(request, respone);
                        }

                    }else if(port.esPostulante(nickname)){
                        request.setAttribute("dataUserPostulante",port.mostrarPostulante(nickname));
                        if(request.getSession().getAttribute("estado_sesion").equals("POSTULANTE") && nickname.equals(((DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname())) {
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarUsuario/postulante_myProfile.jsp").forward(request, respone);
                        } else {
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarUsuario/postulante_guest.jsp").forward(request, respone);
                        }
                    }else {
                        request.getRequestDispatcher("/WEB-INF/escritorio/errorPages/404.jsp").forward(request, respone);
                    }
                }
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
