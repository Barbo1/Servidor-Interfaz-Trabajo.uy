package controllers;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Character.UnicodeBlock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import net.java.dev.jaxb.array.StringArray;
import publicador.DtOferta;
import publicador.DtPostulacionCompleto;
import publicador.DtUsuario;
import publicador.EstadoOferta;

///////////////////////////Servlet implementation class Ofertas///////////////////////////
@MultipartConfig
@WebServlet(urlPatterns = {"/alta-oferta","/crear-oferta","/consulta-oferta","/ConsultarTipoDeOferta","/postulacion", "/FinalizaOferta", "/SeleccionOferta", "/seleccion-confirmar"}) 
public class Ofertas extends HttpServlet {

    private static final long serialVersionUID = 1L;
    publicador.WebServicesService service = new publicador.WebServicesService();
    publicador.WebServices port = service.getWebServicesPort();

    //Contructor por defecto
    public Ofertas() {}

    // FUNCION PARA REDERIGIR 
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAgent = request.getHeader("User-Agent");
        HttpSession session = request.getSession(); // obtengo la sesion
        if (session.getAttribute("estado_sesion") == null) {
            response.sendRedirect("home");
        } else {
            switch (request.getServletPath()) {
                // ALTA DE OFERTA (cuando entra al caso de uso)
                case "/alta-oferta": 
                    if (!(userAgent != null && userAgent.toLowerCase().contains("mobile"))) { 
                        if(session.getAttribute("estado_sesion").equals("EMPRESA")) { 
                            request.setAttribute("error_message_nombreO",""); // es para el chequeo del fomrulario
                            session.setAttribute("tipos_ofertas", port.listarTipoDeOferta().getItem()); // cargo los tipos de oferta del sistema
                            String nickLog = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname(); // obtengo el nickname del usuario logueado
                            
                            Map<String, Map<String, Integer>> auxiliarMap = new HashMap<String,Map<String,Integer>>();
                            List<String> auxiliarList = port.obtenerPaquetes(nickLog).getItem(); 

                            for (String tupla: auxiliarList) {
                                String[] partes = tupla.split("-");
                                if (!auxiliarMap.containsKey(partes[0])) {
                                    auxiliarMap.put(partes[0], new HashMap<String, Integer>());
                                }
                                auxiliarMap.get(partes[0]).put(partes[1], Integer.valueOf(partes[2]));
                            }

                            request.getSession().setAttribute("dataPaqueteEmpresa", auxiliarMap);
                            request.getRequestDispatcher("/WEB-INF/escritorio/AltaOferta/altaOferta.jsp").forward(request, response);
                        } else response.sendRedirect("home");
                    } else response.sendRedirect("home");
                    break;

                    // CREAR OFERTA (cuando le da click al boton confirmar)
                case "/crear-oferta": 
                    /* se debe controlar que el usuario no sea empresa. */
                    if (userAgent == null || !userAgent.toLowerCase().contains("mobile")) {

                        // Verificacion de los datos del formulario
                        String nickLogueado = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname();
                        String nombreOferta = request.getParameter("nombreO");
                        if(!port.existeOferta(nombreOferta)) { 
                            try {
                                if(nombreOferta == null || nombreOferta.replaceAll("\\s","").equals("")) {
                                    String errorMessage = "Error inesperado";
                                    request.setAttribute("error_message_nombreO", errorMessage);
                                    request.getRequestDispatcher("/WEB-INF/escritorio/AltaOferta/altaOferta.jsp").forward(request, response);
                                }

                                // Pido el resto de los datos
                                String tipodeO = request.getParameter("tiposdeO");
                                String descripcion = request.getParameter("descripcion");
                                String horaCom = request.getParameter("horaCom");
                                String horaFin = request.getParameter("horaFin");
                                String remuneracion = request.getParameter("remuneracion");
                                String departamento = request.getParameter("departamento");
                                String ciudad = request.getParameter("ciudad");

                                /* Parte IMAGEN */
                                Part archivoFoto = request.getPart("imagen");
                                String imagen = "default.png";
                                if (archivoFoto.getSize() != 0) {
                                    String nombreOriginal = archivoFoto.getSubmittedFileName(); //Nombre del archivo subido por el cliente
                                    imagen = port.unico(nombreOferta) + getFileExtension(nombreOriginal); //Nombre que llevara la imagen en el fyleSystem
                                    byte[] imagenByte = readImageBytes(archivoFoto);
                                    
                                    /*Guarda la imagen*/
                                    port.guardarImagen(imagenByte, imagen, "ofertas");
                                }

                                /* Parte KEYWORDS */
                                List<String> keywords = (List<String>) request.getSession().getAttribute("keywords"); // keywords del sistema
                                StringArray keywordArray = new StringArray();
                                for(String key : keywords) {
                                    String key2 = request.getParameter(key);
                                    if (key2 != null) {
                                        keywordArray.getItem().add(key2);
                                    }
                                }

                                /* Parte PAQUETE Y CREAR OFERTA*/
                                String paquete = request.getParameter("seleccionPaquete");
                                String horario = horaCom + " - " + horaFin;
                                LocalDate fecha = LocalDate.now();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                String fechaString = fecha.format(formatter);

                                if (paquete == null || paquete.equals("0")) { // es por pago general
                                    port.ingresarOfertaLaboral(nickLogueado, tipodeO, nombreOferta, descripcion, horario, Float.valueOf(remuneracion), ciudad, departamento, fechaString, keywordArray ,imagen, publicador.EstadoOferta.INGRESADA);
                                } else { // es por pago con paquete
                                    port.ingresarOfertaLaboralConPaquete(nickLogueado, tipodeO, nombreOferta, descripcion, horario, Float.valueOf(remuneracion), ciudad, departamento, fechaString, keywordArray,imagen, paquete,EstadoOferta.INGRESADA);
                                }

                                response.sendRedirect("consulta-oferta?name="+nombreOferta);	

                            } catch (Exception e) {
                                System.out.println(e);
                                String errorMessage = "Error inesperado";
                                request.setAttribute("error_message", errorMessage);
                                request.getRequestDispatcher("/WEB-INF/escritorio/AltaOferta/altaOferta.jsp").forward(request, response);
                            }
                        } else {
                            String errorMessage = "El nombre de la oferta ya está en uso. Por favor, elige otro.";
                            request.setAttribute("error_message_nombreO", errorMessage);
                            request.getRequestDispatcher("/WEB-INF/escritorio/AltaOferta/altaOferta.jsp").forward(request, response);
                        }
                    } else
                        response.sendRedirect("home");
                    break;

                    // CONSULTA DE OFERTA
                case "/consulta-oferta":
                    String nickLog;
                    String nombreO = request.getParameter("name"); // obtengo la oferta que selecciono el ususario

                    if(port.mostrarOferta(nombreO) != null) {
                        publicador.DtOfertaCompleto ofertaComp = port.mostrarOfertaCompleto(nombreO);
                        DtOferta oferta = ofertaComp.getOferta();
                        
                        // le paso al JSP correspondinte la data de la oferta.
                        // esta data queda para cada usuario mientras no se apague el servidor.
                        request.setAttribute("dataOfertaComp", ofertaComp); 

                        // si el usuario es visitante
                        if (session.getAttribute("estado_sesion").equals("VISITANTE") && oferta.getEstado().equals(publicador.EstadoOferta.CONFIRMADA) && !oferta.isIsVencido()) {

                            // le paso al JSP visitante la data de la empresa (DtEmpresa) que publico la oferta
                            request.setAttribute("dataEmpresa", port.mostrarEmpresa(oferta.getEmpresa())); 
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarOferta/visitante.jsp").forward(request, response);

                            // si el usuario es empresa
                        } else if(session.getAttribute("estado_sesion").equals("EMPRESA")) {
                            nickLog = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname();

                            // la empresa publico la oferta
                            if(oferta.getEmpresa().equals(nickLog)) { 
                                String pagina = request.getParameter("pagina");
                                request.setAttribute("pagina", pagina != null ? Integer.valueOf(pagina) : 1); 

                                // le paso al JSP la data de la empresa (DtEmpresa) que publico la oferta EN ESTE CASO ELLA MISMA
                                request.setAttribute("dataEmpresa", port.mostrarEmpresa(nickLog)); 
                                request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarOferta/Empresa/mia.jsp").forward(request, response);
                                
                                // la publico otra empresa
                            } else if(oferta.getEstado().equals(publicador.EstadoOferta.CONFIRMADA) && !oferta.isIsVencido()){ 
                                
                                // le paso al JSP la data de la empresa (DtEmpresa) que publico la oferta
                                request.setAttribute("dataEmpresa", port.mostrarEmpresa(oferta.getEmpresa())); 
                                request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarOferta/Empresa/otra.jsp").forward(request, response);
                            } else {
                                response.sendRedirect("home");
                            }

                            // si el usuario es postulante
                        } else if(session.getAttribute("estado_sesion").equals("POSTULANTE") && ofertaComp.getOferta().getEstado().equals(publicador.EstadoOferta.CONFIRMADA) && !ofertaComp.getOferta().isIsVencido()) { 

                            nickLog = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname(); 
                            request.setAttribute("tieneFavorito", port.tienePostulanteFavorito(nombreO, nickLog));

                            // le paso al JSP la data de la empresa (DtEmpresa) que publico la oferta
                            request.setAttribute("dataEmpresa", port.mostrarEmpresa(ofertaComp.getOferta().getEmpresa())); 

                            // le paso al JSP la data del postulante logueado
                            boolean postulado = port.estaPostulado(nickLog,nombreO);
                            if (postulado) 
                                request.setAttribute("dataPostulante", port.mostrarPostulante(nickLog)); 
                            
                            if (userAgent != null && userAgent.toLowerCase().contains("mobile"))
                            	if (postulado)
                            		request.getRequestDispatcher("/WEB-INF/movil/consultaOferta/mePostule.jsp").forward(request, response);
                            	else 
                            		request.getRequestDispatcher("/WEB-INF/movil/consultaOferta/noMePostule.jsp").forward(request, response);
                            else if (postulado)
                                request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarOferta/Postulante/mePostule.jsp").forward(request, response);
                            else
                                request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarOferta/Postulante/noMePostule.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("home");
                        }
                    } else {  
                        response.sendRedirect("home");
                    }
                    break;

                    // POSTULARSE
                case "/postulacion": //Hay que crear la postulacion
                	nickLog = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname();
                	String oferta = request.getParameter("name");
                    if(session.getAttribute("estado_sesion").equals("POSTULANTE") && oferta != null && !oferta.replaceAll("\\s","").equals("") && !port.estaPostulado(nickLog, oferta)) { 
                 
                        String cv = request.getParameter("cv_reducido");
                        String mot = request.getParameter("motivacion");
                        LocalDate fecha = LocalDate.now();
                        String vid = request.getParameter("video");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String fechaString = fecha.format(formatter);

                        if (cv != null && !cv.replaceAll("\\s","").equals("") && mot != null && !mot.replaceAll("\\s","").equals("")) {
                            port.postular(nickLog, oferta, mot, cv, fechaString, vid);
                            response.sendRedirect("consulta-oferta?name=" + oferta);
                        }
                    }else {
                        response.sendRedirect("home");
                    }
                    break;

                    // CONSULTA TIPO DE OFERTA
                case "/ConsultarTipoDeOferta":
                    if (userAgent == null || !userAgent.toLowerCase().contains("mobile")) {
                        try {
                            String tipo = request.getParameter("tipo");
                            String redirect;
                            if (tipo == null) {
                                session.setAttribute("tipos_ofertas", port.listarTipoDeOferta().getItem());
                                redirect = "listar";
                            } else {
                                session.setAttribute("tipos_ofertas", port.mostrarTipoOferta(tipo));
                                redirect = "info";
                            }
                            request.getRequestDispatcher("/WEB-INF/escritorio/ConsultarTipoOferta/"+ redirect +".jsp").forward(request, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else { 
                        response.sendRedirect("home"); 
                    }
                    break;

                case "/FinalizaOferta":
                    if (!(userAgent != null && userAgent.toLowerCase().contains("mobile"))) {
                        String nombre_Oferta = request.getParameter("nombreOferta");
                        publicador.DtOfertaCompleto data_Oferta_Completo = port.mostrarOfertaCompleto(nombre_Oferta);
                        port.cambiarEstadoOferta(nombre_Oferta, EstadoOferta.FINALIZADA);
                        response.sendRedirect("consulta-oferta?name=" + nombre_Oferta);
                    } else response.sendRedirect("home");
                    break;
                    
                case "/SeleccionOferta":
                	if (userAgent == null || !userAgent.toLowerCase().contains("mobile")) { // IF MOVIL
                		
                		nickLog = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname(); 
                        nombreO = request.getParameter("nombreOferta"); // obtengo la oferta 
                        
                        if(port.mostrarOferta(nombreO) != null) { //IF OFERTA NULL
                        	
                            publicador.DtOfertaCompleto ofertaComp = port.mostrarOfertaCompleto(nombreO);
                            DtOferta ofertaInf = ofertaComp.getOferta();
			                //mirar si esta bien la fecha es necesario?
                            
                            
                            if(session.getAttribute("estado_sesion").equals("EMPRESA") &  ofertaInf.getEmpresa().equals(nickLog)) {   //IF empresa correcta  
                            	
                            	if (ofertaInf.getEstado().equals(publicador.EstadoOferta.CONFIRMADA)) { //IF estado
                            		
                            		request.setAttribute("dataOfertaComp", ofertaComp); 
                                    request.setAttribute("dataEmpresa", port.mostrarEmpresa(ofertaInf.getEmpresa())); 
                                    
                                    if (!port.seleccionFinalizada(ofertaInf.getNombre())) { //Si no se termino la seleccion
                                    	request.getRequestDispatcher("/WEB-INF/escritorio/SeleccionarPostulacion/seleccionarPostulacion.jsp").forward(request, response);
                                    }else { //Si se termino la seleccion
                                    	request.getRequestDispatcher("/WEB-INF/escritorio/SeleccionarPostulacion/postulacionTerminada.jsp").forward(request, response);
                                    }
                            		
                            		
                            	} else if (ofertaInf.getEstado().equals(publicador.EstadoOferta.FINALIZADA)){ //ELSE estado
                            		
                            		 if (port.seleccionFinalizada(ofertaInf.getNombre())) { //si se termino la seleccion
                            			request.setAttribute("dataOfertaComp", ofertaComp); 
                                        request.setAttribute("dataEmpresa", port.mostrarEmpresa(ofertaInf.getEmpresa())); 
                                      	request.getRequestDispatcher("/WEB-INF/escritorio/SeleccionarPostulacion/postulacionTerminada.jsp").forward(request, response);
                                     }else {
                                         response.sendRedirect("home");                             

                                 	}
                            		
                            	} else { //Else estado
                                    response.sendRedirect("home");                             

                            	}
                            	
                               
                            } else {  //ELSE empresa correcto
                                response.sendRedirect("home");                             

                            }
                        } else { //ELSE OFERTA NULL
                            response.sendRedirect("home");      

                        }
                	
                	} else {   // ELSE MOVIL
                        response.sendRedirect("home");
                	}
                break;
                
                case "/seleccion-confirmar":
                	
                	if (userAgent == null || !userAgent.toLowerCase().contains("mobile")) {
                		nickLog = ((publicador.DtUsuario) request.getSession().getAttribute("usuario_logueado")).getNickname();
                        nombreO = request.getParameter("nombreOferta"); // obtengo la oferta 
                        
                        if(port.mostrarOferta(nombreO) != null) {
                            publicador.DtOfertaCompleto ofertaComp = port.mostrarOfertaCompleto(nombreO);
                            DtOferta ofertaInf = ofertaComp.getOferta();
			                //mirar si esta bien la fecha es necesario?
                            if(session.getAttribute("estado_sesion").equals("EMPRESA") & ofertaInf.getEstado().equals(publicador.EstadoOferta.CONFIRMADA) & ofertaInf.getEmpresa().equals(nickLog)) {   
                            	List<DtPostulacionCompleto> postulaciones = ofertaComp.getPostulaciones();
                            	Map<String,Integer> mapPostulantes = new HashMap<String,Integer>();
                                for (DtPostulacionCompleto postulacion : postulaciones) {
                                	String orden = request.getParameter(postulacion.getPostulante());
                                	mapPostulantes.put(postulacion.getPostulante(),Integer.valueOf(orden));
                                }
                            	boolean[] check = new boolean[postulaciones.size()];
                            	for (int aux = 0; aux < postulaciones.size(); aux++) {
                            		check[aux] = false;
                            	}
                            	for (Map.Entry<String, Integer> par : mapPostulantes.entrySet()) {
                            		if (check[par.getValue()-1] == false) {
                            			check[par.getValue()-1] = true;
                            		} else {
                                    	response.sendRedirect("home");
                            		}
                            	}
                            	for (Map.Entry<String, Integer> par : mapPostulantes.entrySet()) {
                            		//COMO???
                            		try {
                                		port.cargarOrdenAPost(par.getValue(), nombreO, par.getKey());
                                    } catch (Exception e) {
                                    	System.out.println(e);
                                    	port.limpiarOrden(ofertaInf.getNombre());
                                    	request.getRequestDispatcher("/WEB-INF/escritorio/SeleccionarPostulacion/seleccionarPostulacion.jsp").forward(request, response);
                                    	
                                    }
                            	}
                            	
                            	port.finalizarSeleccion(nombreO);
                            	response.sendRedirect("SeleccionOferta?nombreOferta=" + ofertaInf.getNombre());
                            
                            
                            } else {
                                response.sendRedirect("home");                             

                            }
                        } else {
                            response.sendRedirect("home");      

                        }
                	} else {   
                        response.sendRedirect("home");
                	}
                	
                	
                	
                break;
                
               
            } // switch
        } // else
    } //processRequest

    // Funcion para la imagen
    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1 && lastIndex < fileName.length() - 1) {
            return fileName.substring(lastIndex);
        }
        return "";
    }

    private byte[] readImageBytes(Part imagePart) throws IOException {
        InputStream inputStream = imagePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }

    // Funciones del Servelet
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
