package model.datatype;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPostulante extends DtUsuario{
	private String nacimiento;
	private String nacionalidad;
//	private Map<String, Oferta> ofertasFavoritas = new HashMap<String, Oferta>();

	public DtPostulante() {
		super();
		this.nacimiento = "";
		this.nacionalidad = "";
	}

	public DtPostulante(String nombre, String apellido, String nickname, String correo, String fecha, String nacionalidad, String pass, String imagen, List<DtUsuario> seguidos, List<DtUsuario> seguidores) {
		super(nombre, apellido, nickname, correo, pass, imagen, seguidos, seguidores);
		this.nacimiento = fecha;
		this.nacionalidad = nacionalidad;
	}

	public String getNacimiento() {
		return nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
	
    public void setNacimiento(String nacimiento) {
	    this.nacimiento = nacimiento;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
//	public Map<String, Oferta> getOfertasFavoritas(){
//		return this.ofertasFavoritas;
//	}
	
//	public void addOfertaFavorita(String nombreOferta) {
//		this.ofertasFavoritas.put(nombreOferta, oferta);
//	}
	
//	public void removeOfertaFavorita(String nombreOferta) {
//		this.ofertasFavoritas.remove(nombreOferta);
//	}
}
