package controllers;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.sound.sampled.Port;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import publicador.DtUsuario;
import publicador.IOException_Exception;


/**
 * Servlet implementation class Usuario
 */
@MultipartConfig
@WebServlet(urlPatterns = {"/alta-usuario","/crear-usuario","/modificar-usuario"})
public class Usuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    publicador.WebServicesService service = new publicador.WebServicesService();
    publicador.WebServices port = service.getWebServicesPort();
    /**
     * Default constructor. 
     */
    public Usuario() {
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, ParseException {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
            response.sendRedirect("home");
        } else {
            if(request.getSession().getAttribute("estado_sesion") == null) {
                response.sendRedirect("home");
            }
            switch (request.getServletPath()) {
                case "/alta-usuario":
                    request.setAttribute("error_message_nick","");
                    request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);
                    break;
                case "/crear-usuario":
                    /* Verificamos si los datos unicos ya estan registrados. */
                    String nickname = request.getParameter("nickname");
                    String email = request.getParameter("email");
                    if(!port.existeNickname(nickname)) {
                        if(!port.existeEmail(email)) {
                            try {
                                String errorMessage = "Error inesperado";
                                if(nickname == null || nickname.replaceAll("\\s","").equals("")) {
                                    request.setAttribute("error_message_nick", errorMessage);
                                    request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);
                                }

                                String password = request.getParameter("password");
                                String checkpassword = request.getParameter("checkpassword");
                                String nombre = request.getParameter("nombre");
                                String apellido = request.getParameter("apellido");
                                String descripcion = request.getParameter("descripcion");
                                String nacionalidad = request.getParameter("nacionalidad");
                                String link = request.getParameter("link");
                                String nacimiento = request.getParameter("nacimiento");

                                if (!password.equals(checkpassword) || password.replaceAll("\\s","").equals("") || 
                                        email == null || email.replaceAll("\\s","").equals("") || 
                                        nombre == null || nombre.replaceAll("\\s","").equals("") || 
                                        apellido == null || apellido.replaceAll("\\s","").equals("")) {
                                    request.setAttribute("error_message", errorMessage);
                                    request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);
                                        }

                                /* Parte IMAGEN */

                                Part archivoFoto = request.getPart("imagen");
                                String imagen = "default.png";
                                if (archivoFoto.getSize() != 0) {

                                    String nombreOriginal = archivoFoto.getSubmittedFileName(); //Nombre del archivo subido por el cliente
                                    imagen = port.unico(nickname) + getFileExtension(nombreOriginal); //Nombre que llevara la imagen en el fyleSystem

                                    //Chequeo tamaño imagen
                                    long tamañoMaximo = 5 * 1024 * 1024;
                                    if (archivoFoto.getSize() > tamañoMaximo) {
                                        errorMessage = "La imagen es mayor a 5MB";
                                        request.setAttribute("error_message", errorMessage);
                                        request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);  
                                    }

                                    byte[] imagenByte = readImageBytes(archivoFoto);
                                    /*Guarda la imagen*/
                                    port.guardarImagen(imagenByte, imagen, "usuarios");
                                }

                                /* Se crea el usuario. */
                                if(!nacimiento.equals("")) {
                                    if(nacimiento == null || nacimiento.replaceAll("\\s","").equals("") || nacionalidad == null || nacionalidad.replaceAll("\\s","").equals("")) {
                                        errorMessage = "Error inesperado";
                                        request.setAttribute("error_message", errorMessage);
                                        request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);
                                    }
                                    String[] nacimiento2 = nacimiento.split("-");
                                    nacimiento = nacimiento2[2] + "-" + nacimiento2[1] + "-" + nacimiento2[0];
                                    port.ingresarPostulante(nacimiento, nacionalidad, nombre, apellido, nickname, email, password, imagen);
                                } else {
                                    if(descripcion == null || descripcion.replaceAll("\\s","").equals("")) {
                                        errorMessage = "Error inesperado";
                                        request.setAttribute("error_message", errorMessage);
                                        request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);
                                    }
                                    port.ingresarEmpresa(link != null ? link : "" , descripcion, nombre, apellido, nickname, email, password, imagen);
                                }

                                response.sendRedirect("iniciar-sesion");
                            } catch (Exception e) {
                                String errorMessage = "Error inesperado";
                                request.setAttribute("error_message", errorMessage);
                                request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);
                            }
                        } else {
                            String errorMessage = "El correo electrónico ya están en uso. Por favor, elige otro.";
                            request.setAttribute("error_message_email", errorMessage);
                            request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);
                        } 
                    } else {
                        String errorMessage = "El nickname ya está en uso. Por favor, elige otro.";
                        request.setAttribute("error_message_nick", errorMessage);
                        request.getRequestDispatcher("/WEB-INF/escritorio/AltaDeUsuario/altaDeUsuario.jsp").forward(request, response);
                    }
                    break;
                case "/modificar-usuario":
                    HttpSession objSesion = request.getSession();
                    publicador.DtUsuario dtu = (publicador.DtUsuario) objSesion.getAttribute("usuario_logueado");
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String password = request.getParameter("password");
                    nickname = dtu.getNickname();

                    if (password == null || port.unico(password).equals(""))
                        password = dtu.getPassword();

                    /*PARTE IMAGEN*/
                    Part archivoFoto = request.getPart("imagen");
                    String imagen = dtu.getImagen();
                    if (archivoFoto.getSize() != 0) {

                        String nombreOriginal = archivoFoto.getSubmittedFileName(); //Nombre del archivo subido por el cliente
                        imagen = port.unico(nickname) + getFileExtension(nombreOriginal); //Nombre que llevara la imagen en el fyleSystem     
                        byte[] imagenByte = readImageBytes(archivoFoto);
                        
                        /*Guarda la imagen*/
                        try {
                            port.guardarImagen(imagenByte, imagen, "usuarios");
                        } catch (IOException_Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if(objSesion.getAttribute("estado_sesion").equals("POSTULANTE")) {
                        String nacionalidad = request.getParameter("nacionalidad");
                        String nacimiento = request.getParameter("nacimiento");
                        String[] nacimiento2 = nacimiento.split("-");
                        
                        nacimiento = nacimiento2[2] + "-" + nacimiento2[1] + "-" + nacimiento2[0];
                        port.modificarDatosPostulante(dtu.getNickname(), nombre, apellido, nacimiento, nacionalidad, imagen, password);

                        dtu = port.getDtUsuario(nickname);
                        objSesion.setAttribute("usuario_logueado", dtu);  
                        response.sendRedirect("consulta-usuario?user=" + dtu.getNickname());
                    } else {
                        String link = request.getParameter("link");
                        String descripcion = request.getParameter("descripcion");

                        port.modificarDatosEmpresa(dtu.getNickname(), nombre, apellido, link != null ? link : "", descripcion, imagen, password);

                        dtu = port.getDtUsuario(nickname);
                        objSesion.setAttribute("usuario_logueado", dtu);
                        response.sendRedirect("consulta-usuario?user=" + dtu.getNickname());
                    }
                    break;
                default: 
                    break;
            }
        }
    }

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            processRequest(request,response);
        } catch (ServletException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            processRequest(request,response);
        } catch (ServletException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
