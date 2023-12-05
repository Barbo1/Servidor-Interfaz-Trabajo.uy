package model.clases;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import model.datatype.DtUsuario;
import model.utils.Strings;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class Usuario {
	
	
	@Column
    private String nombre;
	
	@Column
    private String apellido;
	
	@Id
	@Column
    private String nickname; // CLAVE
	
	@Column(
    		nullable = false,
    		unique = true)
    private String correoElectronico;
	
	@Column
	private String imagen;
	
	@Transient
	private String password;
	
	@Transient
	private Map<String,Usuario> seguidos = new HashMap<String,Usuario>();
	
	@Transient
	private Map<String,Usuario> seguidores = new HashMap<String,Usuario>();
	
	public Usuario() {
		
	}
	
    public Usuario(String name, String lastName, String nick, String mail, String image, String pass) {
        this.nombre = name;
        this.apellido = lastName;
        this.nickname = nick;
        this.correoElectronico = mail;
        this.imagen = image;
        this.password = pass;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNickname() {
        return nickname;
    }
    
    public String getCorreoElectronico(){
    	return correoElectronico;
    }
    
    public String getImagen(){
    	return this.imagen;
    }

    public void setNombre(String name) {
        nombre = name;
    }

    public void setApellido(String lastName) {
        apellido = lastName;
    }

	
	public void setImagen(String image) {
		this.imagen = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public DtUsuario getDtUsuarioCorto() {
		return new DtUsuario(nombre, apellido, nickname, correoElectronico, password, imagen);
	}
	
	public DtUsuario getDtUsuario() {
		List<DtUsuario> seguidoresNuevo = new ArrayList<DtUsuario>();
		List<DtUsuario> seguidosNuevo = new ArrayList<DtUsuario>();
		for(Map.Entry<String, Usuario> user : seguidores.entrySet())
			seguidoresNuevo.add(user.getValue().getDtUsuarioCorto());
		for(Map.Entry<String, Usuario> user : seguidos.entrySet())
			seguidosNuevo.add(user.getValue().getDtUsuarioCorto());
		return new DtUsuario(nombre, apellido, nickname, correoElectronico, password, imagen, seguidosNuevo, seguidoresNuevo);
	}
	
	public void addSeguidor(Usuario seguidor) {
		Strings aux = new Strings();
		if (seguidores.get(aux.unico(seguidor.getNickname())) == null)
			seguidores.put(aux.unico(seguidor.getNickname()), seguidor);
	}
	
	public void addSeguir(Usuario seguido) {
		Strings aux = new Strings();
		if (seguidos.get(aux.unico(seguido.getNickname())) == null)
			seguidos.put(aux.unico(seguido.getNickname()), seguido);
	}
	
	public void quitarSeguidor(Usuario seguidor) {
		Strings aux = new Strings();
		if (seguidores.get(aux.unico(seguidor.getNickname())) != null)
			seguidores.remove(aux.unico(seguidor.getNickname()));
	}
	
	public void quitarSeguir(Usuario seguido) {
		Strings aux = new Strings();
		if (seguidos.get(aux.unico(seguido.getNickname())) != null)
			seguidos.remove(aux.unico(seguido.getNickname()));
	}

	public Map<String,Usuario> getSeguidores(){
		return this.seguidores;
	}

	public Map<String,Usuario> getSeguidos(){
		return this.seguidos;
	}
}