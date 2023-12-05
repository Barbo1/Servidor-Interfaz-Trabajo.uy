package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import publicador.DtUsuario;
import publicador.DtOferta;
import publicador.DtPostulacionCompleto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet  (urlPatterns = {"/existeNickname", "/existeEmail", "/empresasBusqueda", "/Favorito", "/noFavorito", "/Follow", "/Unfollow", "/descargar-pdf"})
public class OperacionesExternas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	publicador.WebServicesService service = new publicador.WebServicesService();
    publicador.WebServices port = service.getWebServicesPort();
       
    public OperacionesExternas() {
        super();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/descargar-pdf")) {
            String postulante = (String) request.getParameter("postulante");
            String empresa = (String) request.getParameter("empresa");
            String oferta = (String) request.getParameter("oferta");
            DtOferta ofer;

            if (request.getSession().getAttribute("estado_sesion").equals("POSTULANTE") && 
                ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname().equals(postulante) &&
                empresa != null && port.esEmpresa(empresa) && 
                oferta != null && null != (ofer = port.mostrarOferta(oferta)) && port.ofertaDeEmpresa(empresa, oferta)) {

                DtPostulacionCompleto postulacion = port.getPostulanteDeOferta(postulante, oferta);
                
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=generated.pdf");
                
                Document document = new Document();

                try {
                    PdfWriter.getInstance(document, response.getOutputStream());
                    document.open();

                    document.add(new Paragraph("Nombre: " + postulante));
                    document.add(new Paragraph("Oferta '" + oferta + "' de la empresa '" + empresa + "'"));
                    document.add(new Paragraph("Tu eres el " + postulacion.getPostulacion().getOrden() + " en el orden de selección."));
                    document.add(new Paragraph("Fecha en la que te postulaste: " + postulacion.getPostulacion().getFecha()));
                    document.add(new Paragraph("Fecha de los resultados: " + ofer.getFechaResultado()));

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } finally {
                    document.close();
                }
            }
        } else {
            response.setContentType("application/xml");
            PrintWriter escritor = response.getWriter();
            escritor.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>");
            String nombre;
            String nick;
            String userAgent;

            switch(request.getServletPath()) {
                case "/empresasBusqueda" : 
                    List<String> empresas = (List<String>) port.listarEmpresaAutoCompletar((String) request.getParameter("arg0")).getItem();
                    for (String nom: empresas) {
                        response.getWriter().append("<element>" + nom + "</element>");
                    }
                    break;
                case "/existeNickname" :
                    nombre = (String)request.getParameter("arg0");
                    response.getWriter().append((nombre != null && port.existeNickname(nombre) ? "1" : "0"));
                    break;
                case "/existeEmail" : 
                    nombre = (String)request.getParameter("arg0");
                    response.getWriter().append((nombre != null && port.existeEmail(nombre) ? "1" : "0"));
                    break;
                case "/Favorito":
                    userAgent = request.getHeader("User-Agent");
                    if (request.getSession().getAttribute("estado_sesion").equals("POSTULANTE") && (userAgent == null || !userAgent.toLowerCase().contains("mobile"))) {
                        String nombreOferta = request.getParameter("nombreOferta");
                        String nickPostulante = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname();
                        port.addOfertaFavorito(nombreOferta, nickPostulante);
                    };	            	
                    break;
                case "/noFavorito":
                    userAgent = request.getHeader("User-Agent");
                    if (request.getSession().getAttribute("estado_sesion").equals("POSTULANTE") && (userAgent == null || !userAgent.toLowerCase().contains("mobile"))) {
                        String nombreOferta = request.getParameter("nombreOferta");
                        String nickPostulante = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname();
                        port.removeOfertaFavorito(nombreOferta, nickPostulante);
                    };     	
                    break;
                case "/Follow":
                    nick = request.getParameter("nick");
                    if(nick != null) {
                        DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
                        List<DtUsuario> seguidos = user.getSeguidos();

                        /* -cambiar esta porcion por una funcion de la logica- */
                        boolean sigue = false;
                        for(DtUsuario aux : seguidos) {
                            if(aux.getNickname().equals(nick)) {
                                sigue = true;
                            }
                        }
                        /* --------------------------------------------------- */

                        if (!sigue) {
                            String user_nick = user.getNickname();
                            port.setFollow(user_nick, nick);
                            request.getSession().setAttribute("usuario_logueado", port.getDtUsuario(user.getNickname()));
                            escritor.append(
                                    "<nick>" + user_nick + "</nick>" + 
                                    "<nombre>" + user.getNombre() + "</nombre>" + 
                                    "<apellido>" + user.getApellido() + "</apellido>" + 
                                    "<imagen>" + user.getImagen() + "</imagen>"
                                    );
                        } else {
                            throw new ServletException("El usuario ya esta siguido.");
                        }
                    } else {
                        throw new ServletException("No especificado el usuario a seguir.");
                    }
                    break;
                case "/Unfollow":
                    nick = request.getParameter("nick");
                    if(nick != null) {
                        DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
                        List<DtUsuario> seguidos = user.getSeguidos();

                        /* -cambiar esta porcion por una funcion de la logica- */
                        boolean sigue = false;
                        for(DtUsuario aux : seguidos) {
                            if(aux.getNickname().equals(nick)) {
                                sigue = true;
                            }
                        }
                        /* --------------------------------------------------- */

                        escritor.append(sigue ? "1" : "0");
                        if (sigue) {
                            port.setUnFollow(user.getNickname(), nick);
                            request.getSession().setAttribute("usuario_logueado", port.getDtUsuario(user.getNickname()));
                        } else {
                            throw new ServletException("El usuario no esta siguido.");
                        }
                    } else {
                        throw new ServletException("No especificado el usuario a seguir.");
                    }
                    break;
                default:
            }
            escritor.append("</data>");
        }
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
