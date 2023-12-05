package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publicador.DtPaquete;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet  (urlPatterns = {"/ConsultarPaquete", "/CompraPaquete"})
public class ConsultaPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    publicador.WebServicesService service = new publicador.WebServicesService();
    publicador.WebServices port = service.getWebServicesPort();

    public ConsultaPaquete() {
        super();
    }
    @SuppressWarnings("unlikely-arg-type")
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 String userAgent = request.getHeader("User-Agent");
         if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
             response.sendRedirect("home");
         } else {
	    	HttpSession session = request.getSession();
			if (session.getAttribute("estado_sesion") == null) {
				response.sendRedirect("home");
			} else {
				switch (request.getServletPath()) {
                    case "/CompraPaquete":
                        String nicknameEmpresa = ((publicador.DtUsuario) session.getAttribute("usuario_logueado")).getNickname();
                       
                        LocalDate fecha = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String fechaCompra = fecha.format(formatter);
                        
                        String nombrePaquete = request.getParameter("nombrePaquete");
                        boolean fueAdquirido;
                        if (nombrePaquete != null) {
                            if(!port.tienePaquete(nicknameEmpresa, nombrePaquete)) {
                                port.comprarPaquete(nicknameEmpresa, nombrePaquete, fechaCompra);
                            }
                        }
                        
                        response.sendRedirect("ConsultarPaquete?paquete=" + URLEncoder.encode(nombrePaquete, "UTF-8"));
                        break;
                    case "/ConsultarPaquete":
                        String paquete = request.getParameter("paquete");
                        
                        if (paquete == null) { // no existe
                            request.setAttribute("paquetes", port.getDtPaquetes().getItem());
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarPaquete/Lista.jsp").forward(request, response);
                        } else { // muestro la data 
                        	if (session.getAttribute("estado_sesion").equals("EMPRESA")){
                                String nickname = ((publicador.DtUsuario) session.getAttribute("usuario_logueado")).getNickname();
                            	request.setAttribute("comprado", port.tienePaquete(nickname, paquete));

                            	Map<String, Map<String, Integer>> auxiliarMap = new HashMap<String,Map<String,Integer>>();
        	            		List<String> auxiliarList = port.obtenerPaquetes(nickname).getItem(); 
        	            		for (String tupla: auxiliarList) {
        	            			String[] partes = tupla.split("-");
                                    if (!auxiliarMap.containsKey(partes[0]))
                                        auxiliarMap.put(partes[0], new HashMap<String, Integer>());
                                    auxiliarMap.get(partes[0]).put(partes[1], Integer.valueOf(partes[2]));
        	            		}

        	            		session.setAttribute("dataPaqueteEmpresa", auxiliarMap);

                                for (DtPaquete paq : port.mostrarEmpresa(nickname).getPaquetes()) {
                                    if (paq.getNombre().equals(paquete)) {
                                        request.setAttribute("paqueteAdquirido", paq);
                                        break;
                                    }
                                }
                            }
                            publicador.DtPaqueteCompleto dataPaquete = port.getDtPaqueteCompleto(paquete); 												
                            request.setAttribute("paquete", dataPaquete);
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarPaquete/Datos.jsp").forward(request, response);
                        }
                        break;
                    default:
                        break;
                }
            }
         }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            processRequest(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

