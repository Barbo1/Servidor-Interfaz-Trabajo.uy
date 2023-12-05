package model.datatype;

import java.util.HashSet;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPostulanteCompleto {
	private DtPostulante postulante;
	private String[] ofertas;
	private Set<DtOferta> dtofertas;
	
    public DtPostulanteCompleto() {
		this.ofertas = null;
		this.postulante = null;
		this.dtofertas = new HashSet<DtOferta>();
	}

    public DtPostulanteCompleto(DtPostulante dtp, String[] ofertas, Set<DtOferta> dtofs) {
		this.ofertas = ofertas;
		this.postulante = dtp;
		this.dtofertas = dtofs;
	}
	
	public DtPostulante getPostulante() {
		return postulante;
	}
	
	public String[] getOfertas() {
		return ofertas;
	}
	
	public Set<DtOferta> getDtOfertas() {
		return this.dtofertas;
	}

	public void setPostulante(DtPostulante postulante) {
		this.postulante = postulante;
	}
	
	public void setOfertas(String[] ofertas) {
		this.ofertas = ofertas;
	}
	
	public void setDtOfertas(Set<DtOferta> dtofertas) {
		this.dtofertas = dtofertas;
	}
}
