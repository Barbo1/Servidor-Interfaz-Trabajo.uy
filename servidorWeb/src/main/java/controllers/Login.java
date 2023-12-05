package controllers;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import publicador.DtUsuario;
import publicador.WebServices;
import publicador.WebServicesService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = {"/login","/iniciar-sesion","/cerrar-sesion"})
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    WebServicesService service = new WebServicesService();
    WebServices port = service.getWebServicesPort();
    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("estado_sesion") == null) 
            response.sendRedirect("home");
        else{
            HttpSession objSesion = request.getSession();
            switch (request.getServletPath()) {
                case "/iniciar-sesion":
                    request.getRequestDispatcher("/WEB-INF/escritorio/InicioSesion/inicio_sesion.jsp").forward(request, response);
                    break;
                case "/login":
                    String nickname = request.getParameter("nickname");
                    String password = request.getParameter("password");
                    String userAgent = request.getHeader("User-Agent");
                    DtUsuario dtu;

                    boolean movil = userAgent != null && userAgent.toLowerCase().contains("mobile");
                    boolean empresa = port.esEmpresa(nickname);
                    String inicio_sesion = "/WEB-INF/" + (movil ? "movil" : "escritorio") + "/InicioSesion/inicio_sesion.jsp";
                    movil = empresa && movil;

                    if(
                            nickname == null || 
                            password == null ||
                            !port.existeNickname(nickname) ||
                            (dtu = port.getDtUsuario(nickname)) == null || 
                            !dtu.getPassword().equals(password) || 
                            movil) {
                        request.setAttribute("error_message", movil ? "El usuario no es un postulante" : "Usuario o contraseña incorrecto.");
                        request.getRequestDispatcher(inicio_sesion).forward(request, response);
                    } else {
                        objSesion.setAttribute("estado_sesion", empresa ? "EMPRESA" : "POSTULANTE");
                        objSesion.setAttribute("usuario_logueado", dtu);
                        response.sendRedirect("home"); 
                    }
                    break;
                case "/cerrar-sesion":
                    objSesion.setAttribute("estado_sesion","VISITANTE");
                    response.sendRedirect("home");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");

        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");

        processRequest(request, response);
    }

}
