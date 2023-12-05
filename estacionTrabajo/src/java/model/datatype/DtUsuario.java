package model.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario {
	private String nombre;
    private String apellido;
    private String nickname;
    private String correoElectronico;
    private String password;
	private String imagen;
	private List<DtUsuario> seguidos;
	private List<DtUsuario> seguidores;

	public DtUsuario() {
		this.nombre = "";
		this.apellido = "";
		this.nickname = "";
		this.correoElectronico = "";
		this.password = "";
		this.imagen = "";
		this.seguidores = new ArrayList<DtUsuario>();
		this.seguidos = new ArrayList<DtUsuario>();
	}

	public DtUsuario(String nombre, String apellido, String nickname, String mail, String pass, String image) {
		this.apellido = apellido;
		this.nickname = nickname;
		this.correoElectronico = mail;
		this.nombre = nombre;
		this.imagen = image;
		this.password = pass;
		this.seguidores = new ArrayList<DtUsuario>();
		this.seguidos = new ArrayList<DtUsuario>();
	}
	
	public DtUsuario(String nombre, String apellido, String nickname, String mail, String pass, String image, List<DtUsuario> seguidos, List<DtUsuario> seguidores) {
		this.apellido = apellido;
		this.nickname = nickname;
		this.correoElectronico = mail;
		this.nombre = nombre;
		this.imagen = image;
		this.password = pass;
		this.seguidores = seguidores;
		this.seguidos = seguidos;
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public String getImagen() {
		return imagen;
	}

	public String getPassword() {
		return password;
	}
	
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSeguidores(List<DtUsuario> seguidores) {
		this.seguidores = seguidores;
	}
	
	public void setSeguidos(List<DtUsuario> seguidos) {
		this.seguidos = seguidos;
	}
	
	public List<DtUsuario> getSeguidores() {
		return seguidores;
	}
	
	public List<DtUsuario> getSeguidos() {
		return seguidos;
	}
}
