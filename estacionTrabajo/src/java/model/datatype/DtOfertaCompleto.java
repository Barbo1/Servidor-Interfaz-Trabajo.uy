package model.datatype;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtOfertaCompleto {	
	private DtOferta oferta;
	private DtPostulacionCompleto[] postulaciones;
	
    public DtOfertaCompleto() {
		this.oferta = null;
		this.postulaciones = null;
	}
	
	public DtOfertaCompleto(DtOferta oferta, DtPostulacionCompleto[] postulaciones) {
		this.oferta = oferta;
		this.postulaciones = postulaciones;
	}

	public DtOferta getOferta() {
		return oferta;
	}

	public DtPostulacionCompleto[] getPostulaciones() {
		return postulaciones;
	}
	
    public void setOferta(DtOferta oferta) {
		this.oferta = oferta;
	}

	public void setPostulaciones(DtPostulacionCompleto[] postulaciones) {
		this.postulaciones = postulaciones;
	}
	

}
