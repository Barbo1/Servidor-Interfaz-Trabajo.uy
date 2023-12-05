package model.datatype;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtEmpresa extends DtUsuario {

	private String link;
	private String descripcion;
	
    public DtEmpresa() {
		super();
		this.descripcion = "";
		this.link = "";
	}
	
	public DtEmpresa(String nombre, String apellido, String nickname, String correo, String link, String descripcion, String password, String imagen, List<DtUsuario> seguidos, List<DtUsuario> seguidores) {
		super(nombre, apellido, nickname, correo, password, imagen, seguidos, seguidores);
		this.descripcion = descripcion;
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public void setDescripcion(String desc) {
		this.descripcion = desc;
	}
}
