package publicador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import model.clases.Fabrica;
import model.controllers_managers.ManejadorOferta;
import model.controllers_managers.ManejadorUsuario;
import model.datatype.DtEmpresaCompleto;
import model.datatype.DtOferta;
import model.datatype.DtOfertaCompleto;
import model.datatype.DtPaquete;
import model.datatype.DtPaqueteCompleto;
import model.datatype.DtPostulacionCompleto;
import model.datatype.DtPostulanteCompleto;
import model.datatype.DtTipoDeOferta;
import model.datatype.DtUsuario;
import model.datatype.EstadoOferta;
import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;
import model.exceptions.OutOfMarginException;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;
import model.utils.Strings;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {
	
	private Fabrica fabrica = Fabrica.getInstance();
	private IcontroladorLaboral controladorLaboral = fabrica.getIcontroladorLaboral();
	private IcontroladorUsuario controladorUsuario = fabrica.getIControladorUsuario();

    private Endpoint endpoint = null;
    //Constructor
    public WebServices(){}

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(){
        Properties properties = new Properties();
    	String directorioActual = System.getProperty("user.home");
    	try {
            properties.load(new FileInputStream(directorioActual + "/TrabajoUY/server.properties"));
            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            endpoint = Endpoint.publish("http://"+host+":"+port+"/webservices", this);
        }
    	catch (Exception e) {
            e.printStackTrace();
    	}
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    /*METODOS EN CONSULTA PAQUETE*/
    
    @WebMethod
    public DtPaquete[] getDtPaquetes(){ 
        List<DtPaquete> aux = controladorLaboral.getDtPaquetes();
        DtPaquete[] res = new DtPaquete[aux.size()];
        int contador = 0;
        for(DtPaquete paquete : aux) {
        	res[contador] = paquete;
        	contador ++;
        }
        return res;
    }
    
    @WebMethod
    public DtPaqueteCompleto getDtPaqueteCompleto(String paquete) {
    	return controladorLaboral.getDtPaqueteCompleto(paquete);
    }
    
    @WebMethod
    public boolean tienePaquete(String nicknameEmpresa, String nombrePaquete) {
    	return controladorLaboral.tienePaquete(nicknameEmpresa, nombrePaquete);
    }
    
    @WebMethod
    public void comprarPaquete(String nicknameEmpresa, String nombrePaquete, String fechaCompra) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaCompra, formatter);
        controladorLaboral.comprarPaquete(nicknameEmpresa, nombrePaquete, fecha);
    }
    
    /*METODOS CONSULTA POSTULACION*/
    
    @WebMethod
    public boolean estaPostulado(String postulante, String oferta) {
    	return controladorUsuario.estaPostulado(postulante, oferta);
    }
    
    @WebMethod
    public boolean esEmpresa(String nick) {
    	return controladorUsuario.esEmpresa(nick);
    }
    
    @WebMethod
	public boolean esPostulante(String nick) {
    	return controladorUsuario.esPostulante(nick);
    }
    
    @WebMethod
	public boolean ofertaDeEmpresa(String empresa, String oferta) {
		return controladorUsuario.ofertaDeEmpresa(empresa, oferta);
	}

    @WebMethod
	public DtPostulacionCompleto getPostulanteDeOferta(String post, String nombreOferta) {
		return controladorLaboral.getPostulanteDeOferta(post, nombreOferta);
	}

    @WebMethod
	public DtOferta mostrarOferta(String nombreOferta) {
		return controladorLaboral.mostrarOferta(nombreOferta);
	}
	
    /*METODOS CONSULTA USUARIO*/
    
    @WebMethod
	public DtUsuario[] getDtUsuarios(){
    	List<DtUsuario> aux = controladorUsuario.getDtUsuarios();
        DtUsuario[] res = new DtUsuario[aux.size()];
        int contador = 0;
        for(DtUsuario usuario : aux) {
        	res[contador] = usuario;
        	contador ++;
        }
        return res;
    }
    
    @WebMethod
    public DtPostulanteCompleto mostrarPostulante(String postulanteID) {
    	return controladorUsuario.mostrarPostulante(postulanteID);
    }
    
    @WebMethod
	public DtEmpresaCompleto mostrarEmpresa(String empresaID) {
    	return controladorUsuario.mostrarEmpresa(empresaID);	
    }

    
    /*METODOS HOME*/

    @WebMethod
    public DtOferta[] listarOfertasSinOrdenar(){
        return controladorLaboral.listarOfertasSinOrdenar().toArray(new DtOferta[0]);
    }
    
    @WebMethod
    public void cargarOrdenAPost(int orden,String nombreOferta, String nickPostulante) {
    	controladorLaboral.cargarOrdenAPost(orden, nombreOferta, nickPostulante);
    }
    
    @WebMethod
    public DtOferta[] listarOfertas(){
    	List<DtOferta> aux = controladorLaboral.listarOfertas();
        DtOferta[] res = new DtOferta[aux.size()];
        int contador = 0;
        for(DtOferta oferta : aux) {
        	res[contador] = oferta;
        	contador ++;
        }
        return res;
    }
    
    @WebMethod
	public DtOferta[] listarOfertasPorKeyword(String key){
    	List<DtOferta> aux = controladorLaboral.listarOfertasPorKeyword(key);
        DtOferta[] res = new DtOferta[aux.size()];
        int contador = 0;
        for(DtOferta oferta : aux) {
        	res[contador] = oferta;
        	contador ++;
        }
        return res;   
    }
    
    @WebMethod
	public DtOferta[] listarOfertasPorEmpresa(String nombre_empresa, EstadoOferta state){
    	List<DtOferta> aux = controladorLaboral.listarOfertasPorEmpresa(nombre_empresa,state);
        DtOferta[] res = new DtOferta[aux.size()];
        int contador = 0;
        for(DtOferta oferta : aux) {
        	res[contador] = oferta;
        	contador ++;
        }
        return res;
    }
    
    @WebMethod
	public String[] listarKeywords() {
    	return controladorLaboral.listarKeywords();
    }
    
    
    /*METODOS OFERTAS*/
    @WebMethod
	public String[] listarTipoDeOferta() {
    	return controladorLaboral.listarTipoDeOferta();
    }
    
    @WebMethod
    public void ingresarOfertaLaboralConPaquete(String empresaNickname, String tipoOferta, String nombreOferta,
			String descripcion, String horario, Float remuneracion, String ciudad, String departamento, String fechaAlta,
			String[] keywords, String imagen, String paquete, EstadoOferta est)
			throws OutOfMarginException, DuplicationException, NonExistentException{
    	Set<String> keys = new HashSet<String>();
    	for(int aux = 0; aux < keywords.length; aux++)
    		keys.add(keywords[aux]);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaAlta, formatter);
    	controladorLaboral.ingresarOfertaLaboralConPaquete(empresaNickname, tipoOferta, nombreOferta, descripcion, horario, remuneracion, ciudad, departamento, fecha, keys, imagen, paquete, est);
    }
    
    @WebMethod
    public void ingresarOfertaLaboral(
			String empresaNickname, 
			String tipoOferta, 
			String nombreOferta, 
			String descripcion, 
			String horario, 
			Float remuneracion, 
			String ciudad, 
			String departamento, 
			String fechaAlta, 
			String[] keywords,
			String imagen,
			EstadoOferta est
	) {
    	Set<String> keys = new HashSet<String>();
    	for(int aux = 0; aux < keywords.length; aux++)
    		keys.add(keywords[aux]);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaAlta, formatter);
    	controladorLaboral.ingresarOfertaLaboral(empresaNickname, tipoOferta, nombreOferta, descripcion, horario, remuneracion, ciudad, departamento, fecha, keys, imagen, est);
    }
    
    @WebMethod
	public DtOfertaCompleto mostrarOfertaCompleto(String nombreOferta) {
    	return controladorLaboral.mostrarOfertaCompleto(nombreOferta);
    }
	
    @WebMethod
	public void postular(
			String postulante, 
			String oferta, 
			String curriculum, 
			String motivo, 
			String fecha,
			String video
	) throws DuplicationException, NonExistentException, OutOfMarginException{
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(fecha, formatter);
		controladorLaboral.postular(postulante, oferta, curriculum, motivo, date, video);
	}
	
    @WebMethod
	public DtTipoDeOferta mostrarTipoOferta(String tipoOf) {
		return controladorLaboral.mostrarTipoOferta(tipoOf);
	}

    
    @WebMethod
	public String[] obtenerPaquetes(String nickname){
    	Map<String, Map<String, Integer>> cantidadMap = controladorUsuario.obtenerPaquetes(nickname);
    	List<String> lista = new ArrayList<String>();
    	for (Map.Entry<String, Map<String, Integer>> primerMap: cantidadMap.entrySet()) {
    		for (Map.Entry<String, Integer> segundoMap: primerMap.getValue().entrySet()) {
    			String aux = primerMap.getKey() + "-" + segundoMap.getKey() + "-" + segundoMap.getValue().toString();
    			lista.add(aux);
    		}
    	}    	
    	String[] res = new String[lista.size()];
    	int auxInt = 0;
    	for (String auxString : lista) {
    		res[auxInt] = auxString;
    		auxInt++;
    	}
    	return res;
    }

    /*METODOS DE USUARIO*/
    
    @WebMethod
    public void modificarDatosPostulante(String nickname, String nombre, String apellido, String nacimiento, String nacionalidad, String imagen, String password) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(nacimiento, formatter);
    	controladorUsuario.modificarDatosPostulante(nickname, nombre, apellido, fecha, nacionalidad, imagen, password);
    }
    
    @WebMethod
	public void modificarDatosEmpresa(String nickname, String nombre, String apellido, String link, String descripcion, String imagen, String password) {
		controladorUsuario.modificarDatosEmpresa(nickname, nombre, apellido, link, descripcion, imagen, password);
	}
    
    @WebMethod
	public void ingresarPostulante(String fechaNacimientoU, String nacionalidad, String nombre, String apellido, String nickname, String correo, String pass, String imagen) throws DuplicationException{
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaNacimientoU, formatter);
    	controladorUsuario.ingresarPostulante(fecha, nacionalidad, nombre, apellido, nickname, correo, pass, imagen);
    }
    
    @WebMethod
	public void ingresarEmpresa(String link, String descripcion, String nombre, String apellido, String nickname, String correo, String pass, String imagen) throws DuplicationException{
    	controladorUsuario.ingresarEmpresa(link, descripcion, nombre, apellido, nickname, correo, pass, imagen);
    }
    
    @WebMethod
    public boolean existeNickname(String nick) {
    	ManejadorUsuario manager = ManejadorUsuario.getInstance();
    	return manager.nicknameRegistrado(nick);
    }
    
    @WebMethod
    public boolean existeEmail(String email) {
    	ManejadorUsuario manager = ManejadorUsuario.getInstance();
    	return manager.getCorreos().contains(email);
    }
    
    @WebMethod
    public String unico(String text) {
    	Strings util = new Strings();
    	return util.unico(text);
    }
    @WebMethod
    public DtUsuario getDtUsuario(String nick) {
    	ManejadorUsuario manager = ManejadorUsuario.getInstance();
    	return manager.getUsuario(nick).getDtUsuario();
    }
    
    @WebMethod
    public byte[] getFile(@WebParam(name = "fileName") String name, String tipo)
                    throws  IOException {
        	String directorioActual = System.getProperty("user.home");
        	 byte[] byteArray = null;
             try {
                     File f = new File(directorioActual + "/TrabajoUY/media/"+tipo+"/" + name);
                     FileInputStream streamer = new FileInputStream(f);
                     byteArray = new byte[streamer.available()];
                     streamer.read(byteArray);
             } catch (IOException e) {
                     throw e;
             }
             return byteArray;
    }

    @WebMethod
    public void setFollow(String user, String seguido) {
        controladorUsuario.setFollow(user,seguido);
    }

    @WebMethod
    public void setUnFollow(String user, String seguido) {
        controladorUsuario.setUnFollow(user,seguido);
    }
    
    @WebMethod
    public void addVisita(String nombreOferta) {
    	controladorLaboral.addVisita(nombreOferta);
    }
    
    @WebMethod
    public void cambiarEstadoOferta(String nombreOferta, EstadoOferta estado) {
    	controladorLaboral.cambiarEstadoOferta(nombreOferta, estado);
    }
    
    @WebMethod
	public void addOfertaFavorito(String nombreOferta, String nickPostulante) {
    	controladorLaboral.addOfertaFavorito(nombreOferta, nickPostulante);
    }
    
    @WebMethod
	public void removeOfertaFavorito(String nombreOferta, String nickPostulante) {
    	controladorLaboral.removeOfertaFavorito(nombreOferta, nickPostulante);
    }
    
    @WebMethod
	public int getReputacionOferta(String nombreOferta) {
    	return controladorLaboral.getReputacionOferta(nombreOferta);
    }
    
    @WebMethod
	public boolean tienePostulanteFavorito(String nombreOferta, String nicknamePostulante) {
    	return controladorLaboral.tienePostulanteFavorito(nombreOferta, nicknamePostulante);
    }


    /* METODOS GENERALES */

    @WebMethod
    public String[] listarEmpresaAutoCompletar(String empresa) {
    	return controladorUsuario.listarEmpresaAutoCompletar(empresa);
    }
    
    @WebMethod 
    public void guardarImagen(byte[] imagen, String nombreArchivo, String lugar) throws IOException {
    	String directorioActual = System.getProperty("user.home");
        String fullPath = Paths.get(directorioActual, "TrabajoUY/media/" + lugar + "/",nombreArchivo).toString();
        FileOutputStream fileOutputStream = new FileOutputStream(fullPath);
        fileOutputStream.write(imagen);
        fileOutputStream.close();
    }
    
    @WebMethod
    public boolean seleccionFinalizada(String nombreOferta) {
    	return controladorLaboral.seleccionFinalizada(nombreOferta);
    }
    @WebMethod
    public void finalizarSeleccion(String nombreOferta) {
    	controladorLaboral.finalizarSeleccion(nombreOferta); 	
    }

    
    @WebMethod
    public boolean existeOferta(String nombreOferta) {
    	ManejadorOferta manager = ManejadorOferta.getInstance();
        return manager.getOferta(nombreOferta) != null;
    }

    @WebMethod
    public void limpiarOrden(String nombreOferta) {
		controladorLaboral.limpiarOrden(nombreOferta);
	}

}
