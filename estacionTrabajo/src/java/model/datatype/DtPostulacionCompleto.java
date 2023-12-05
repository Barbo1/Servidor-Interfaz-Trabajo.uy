package model.datatype;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPostulacionCompleto {
	private String postulante; //nickname
	private String oferta;
	private DtPostulacion postulacion;
	private String imagen;
	private int orden;
	
	public DtPostulacionCompleto() {
		this.postulante = "";
		this.oferta = "";
		this.postulacion = null;
		this.imagen = "";
	}
	
	public DtPostulacionCompleto(String postulante, String oferta, DtPostulacion postulacion, String imagen) {
		this.postulante = postulante;
		this.oferta = oferta;
		this.postulacion = postulacion;
		this.imagen = imagen;

	}

	public String getPostulante() {
		return postulante;
	}
	
	public String getOferta() {
		return oferta;
	}

	public DtPostulacion getPostulacion() {
		return postulacion;
	}

	public String getImagen() {
		return imagen;
	}
	

	public void setPostulante(String postulante) {
		this.postulante = postulante;
	}
	
	public void setOferta(String oferta) {
		this.oferta = oferta;
	}

	public void setPostulacion(DtPostulacion postulacion) {
		this.postulacion = postulacion;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	

	public String toString() {
		return this.postulante;
	}
	
	public int getOrden() {
		return orden;
	}
	

	public void setOrden(int orden) {
		if(orden!=0) {
			this.orden = orden;

		}
	}
	
}
