package model.controllers_managers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.clases.Empresa;
import model.clases.Postulante;
import model.clases.Usuario;
import model.utils.Strings;

// Singleton
public class ManejadorUsuario{
	private static Strings utils = new Strings(); 
	private static Map<String, Usuario> usuarios;
	private static Set<String> correos;
	
	private static ManejadorUsuario instance;
	
	// Initialize fields
	private ManejadorUsuario() {
		usuarios = new HashMap<String, Usuario>();
		correos = new HashSet<String>();
	}
	
	public static ManejadorUsuario getInstance(){
		if (instance == null) {
			instance = new ManejadorUsuario();
		}
		return instance;
	}
	
	// methods

	public void addEmpresa(Empresa empresa) {
		if (!usuarios.containsKey(empresa.getNickname())) {
			usuarios.put(utils.unico(empresa.getNickname()), empresa);
			correos.add(empresa.getCorreoElectronico());
		}
	}

	public void addPostulante(Postulante postulante) {
		if (!usuarios.containsKey(postulante.getNickname())) {	
			usuarios.put(utils.unico(postulante.getNickname()), postulante);
			correos.add(postulante.getCorreoElectronico());
		}
	}	
	
	public Map<String, Usuario> getUsuarios(){
		return usuarios;
	}
	
	public Set<String> getCorreos(){
		return correos;
	}
	
	public Postulante getPostulante(String nicknamePostulante) {
		return (Postulante) usuarios.get(utils.unico(nicknamePostulante));
	}
	
	public Empresa getEmpresa(String nicknameEmpresa) {
		return (Empresa) usuarios.get(utils.unico(nicknameEmpresa));
	}
	public Usuario getUsuario(String nickname) {
		return usuarios.get(utils.unico(nickname));
	}
	public boolean nicknameRegistrado(String nickname) {
		return usuarios.containsKey(utils.unico(nickname));
	}
	
	public boolean emailRegistrado(String email) {
		return correos.contains(email);
	}

}
