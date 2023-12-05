package model.controllers_managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import model.utils.Compare;
import model.utils.Strings;
import model.clases.Empresa;
import model.clases.Postulacion;
import model.clases.Postulante;
import model.clases.Usuario;
import model.datatype.DtEmpresaCompleto;
import model.interfacess.IcontroladorUsuario;
import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;
import model.utils.Strings;
import model.datatype.DtPostulanteCompleto;
import model.datatype.DtUsuario;
import model.clases.TipoDeOferta;

public class ControladorUsuario implements IcontroladorUsuario{
	private static Strings utils = new Strings();
	private static Compare compare = new Compare();
	ManejadorUsuario manager = ManejadorUsuario.getInstance();
	
	public ControladorUsuario() {}
	
	//Methods

	// Alta de Postulante y Empresa
	
	public void ingresarPostulante(LocalDate fechaNacimientoU, String nacionalidad, String nombre, String apellido, String nickname, String correo, String pass, String imagen) throws DuplicationException {
		
	    if (manager.nicknameRegistrado(nickname)) {
	        throw new DuplicationException("El nickname " + nickname + " ya esta registrado");            
	    }	    
        if (manager.emailRegistrado(correo)) {
         	throw new DuplicationException("El correo " + correo + " ya esta registrado");
        }
       
		Postulante nuevoPostulante = new Postulante(fechaNacimientoU, nacionalidad, nombre, apellido, nickname, correo, imagen, pass);
		manager.addPostulante(nuevoPostulante);
	}
	
	public void ingresarEmpresa(String link, String descripcion, String nombre, String apellido, String nickname, String correo, String pass, String imagen) throws DuplicationException {
	  if (manager.nicknameRegistrado(nickname)) {
	    throw new DuplicationException("El nickname " + nickname + " ya esta registrado");            
	  }	    
    if (manager.emailRegistrado(correo)) {
      throw new DuplicationException("El correo " + correo + " ya esta registrado");
    }

		Empresa nuevaEmpresa = new Empresa(link, descripcion, nombre, apellido, nickname, correo, imagen, pass);
		manager.addEmpresa(nuevaEmpresa);
	}
	
	public boolean esEmpresa(String nick) {
		Usuario user = manager.getUsuario(nick);
		if (user instanceof Empresa)
			return true;
		else
			return false;
	}
	
	public boolean esPostulante(String nick) {
		Usuario user = manager.getUsuario(nick);
		if (user instanceof Postulante)
			return true;
		else
			return false;
	}
	
	public String[] listarUsuarios(){
		Map<String, Usuario> usuarios = manager.getUsuarios();
		String[] listaUsuarios = new String[usuarios.size()];
		int aux = 0;
		for (Iterator<Map.Entry<String, Usuario>> it = usuarios.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String, Usuario> nick = it.next();
			listaUsuarios[aux] = nick.getValue().getNickname();
			aux++;
		}
		return listaUsuarios;
	}
	
	public String[] listarEmpresas() {
		// Guardo las empresas en una lista
		Map<String,Usuario> usuarios = manager.getUsuarios();
		List<String> listaEmpresas = new ArrayList<String>();
		for (Map.Entry<String, Usuario> usuario : usuarios.entrySet()) {
			if (usuario.getValue() instanceof Empresa) {
				String nick = usuario.getValue().getNickname();
				listaEmpresas.add(nick);
			}
		}
		// Paso las empresas a un arreglo y devuelvo eso
		String[] arregloEmpresas = new String[listaEmpresas.size()];
		int aux = 0;
		ListIterator<String> lItr = listaEmpresas.listIterator();
		while (lItr.hasNext()) {
			String nombre = lItr.next();
			arregloEmpresas[aux] = nombre;
			aux++;
		}
		return arregloEmpresas;
	}
	
	public String[] listarPostulantes() {
		// Guardo los postulantes en una lista
		Map<String, Usuario> usuarios = manager.getUsuarios();
		List<String> listaPostulantes = new ArrayList<String>();
		for (Map.Entry<String, Usuario> usuario : usuarios.entrySet()) {
			if (usuario.getValue() instanceof Postulante) {
				String nick = usuario.getKey();
				listaPostulantes.add(nick);
			}
		}
		// Paso los postulantes a un arreglo y devuelvo eso
		String[] arregloPostulantes = new String[listaPostulantes.size()];
		int aux = 0;
		ListIterator<String> lItr = listaPostulantes.listIterator();
		while (lItr.hasNext()) {
			String nombre = lItr.next();
			arregloPostulantes[aux] = nombre;
			aux++;
		}
		return arregloPostulantes;
	}
	
	public DtPostulanteCompleto mostrarPostulante(String postulanteID) {
        Postulante post = (Postulante) manager.getPostulante(postulanteID);
        return post.getDatosCompletos();
	}

	public DtEmpresaCompleto mostrarEmpresa(String empresaID) {
		Empresa emp = (Empresa) manager.getEmpresa(empresaID);
		return emp.getDatosCompletos();
	}
	
	public String[] listarOfertasEmpresa(String empresaNickname) throws NonExistentException {
		if (!manager.nicknameRegistrado(empresaNickname)) {
			throw new NonExistentException("No existe un usuario con ese nickname");
		}
		Empresa emp = (Empresa) manager.getEmpresa(empresaNickname);
		String[] ofertas = emp.getNombreOfertas();
		return ofertas;
	}
	
	public void modificarDatosPostulante(String nickname, String nombre, String apellido, LocalDate nacimiento, String nacionalidad, String imagen, String password) {
		Postulante postulante = manager.getPostulante(nickname);
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setNacimiento(nacimiento);
		postulante.setNacionalidad(nacionalidad);
		postulante.setImagen(imagen);
		postulante.setPassword(password);
	}

	public void modificarDatosEmpresa(String nickname, String nombre, String apellido, String link, String descripcion, String imagen, String password) {
		Empresa empresa = manager.getEmpresa(nickname);
		empresa.setNombre(nombre);
		empresa.setApellido(apellido);
		empresa.setLink(link);
		empresa.setDescripcion(descripcion);
		empresa.setImagen(imagen);
		empresa.setPassword(password);
	}
	
	public List<DtUsuario> getDtUsuarios(){
		Map<String, Usuario> usuarios = manager.getUsuarios();
		List<DtUsuario> res = new ArrayList<DtUsuario>();
		for (Map.Entry<String, Usuario> usuario : usuarios.entrySet()) {
				DtUsuario dtu = usuario.getValue().getDtUsuario();
				res.add(dtu);
		}
		return res;
	}

	public Empresa getEmpresa(String nombre) {
		return manager.getEmpresa(nombre);
	}
	
	public boolean estaPostulado(String nickname, String oferta) {
		boolean res = false;
		Strings utils = new Strings();
		Set<Postulacion> postulaciones = manager.getPostulante(nickname).getPostulaciones();
		oferta = utils.unico(oferta);
		for (Postulacion postulacion: postulaciones) {
			if (utils.unico(postulacion.getOferta().getNombre()).equals(oferta)) {
				res = true;
			}
		}
		return res;
	}

	public boolean ofertaDeEmpresa(String empresa, String oferta) {
		String[] ofertas = listarOfertasEmpresa(empresa);
		int aux = 0;
		while (aux < ofertas.length && !ofertas[aux].equals(oferta)) {	
			aux++;
		}
		return aux < ofertas.length;
	}
	
    // me devuelve para cada tipo de oferta (del sistema) un map con el nombre del paquete y el saldo disponible
	public Map<String, Map<String, Integer>> obtenerPaquetes(String nickname) { 
		Empresa emp = manager.getEmpresa(nickname);
		ManejadorTipoDeOferta managerTipoDeOferta = ManejadorTipoDeOferta.getInstance();
		Map<String, TipoDeOferta> map = managerTipoDeOferta.getTipoDeOfertas();
		String[] nombreTiposDeOferta = new String[map.size()];
		int aux = 0;
		for (Map.Entry<String, TipoDeOferta> mapAux : map.entrySet()) {
			nombreTiposDeOferta[aux] = mapAux.getValue().getNombre();
			aux++;
		}
		return emp.obtenerPaquetesE(nombreTiposDeOferta);
	}
	
	public void setFollow(String user, String seguido){
		Usuario userClase = manager.getUsuario(user);
		Usuario userSeguido = manager.getUsuario(seguido);
		userSeguido.addSeguidor(userClase);
		userClase.addSeguir(userSeguido);
	}

	public void setUnFollow(String user, String seguido){
		Usuario userClase = manager.getUsuario(user);
		Usuario userSeguido = manager.getUsuario(seguido);
		userSeguido.quitarSeguidor(userClase);
		userClase.quitarSeguir(userSeguido);
	}
	
    public String[] listarEmpresaAutoCompletar(String nombre_empresa) {
        String nombre_ref = utils.unico(nombre_empresa);
		Map<String,Usuario> usuarios = manager.getUsuarios();
		List<String> listaEmpresas = new ArrayList<String>();
		for (Map.Entry<String, Usuario> usuario : usuarios.entrySet()) {
			if (usuario.getValue() instanceof Empresa && compare.comparar(usuario.getKey(), nombre_ref)) {
				listaEmpresas.add(usuario.getValue().getNickname());
			}
		}
		return listaEmpresas.toArray(new String[0]);
	}
}
