package model.datatype;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.HashSet;
@XmlAccessorType(XmlAccessType.FIELD)
public class DtEmpresaCompleto {
	private DtEmpresa empresa;
	private String[] ofertas;
	private Set<DtPaquete> paquetes;
	private Set<DtOferta> dtofertas;
	
    public DtEmpresaCompleto() {
		this.empresa = null;
		this.ofertas = null;
		this.paquetes = new HashSet<DtPaquete>();
		this.dtofertas = new HashSet<DtOferta>();
	}
	
	public DtEmpresaCompleto(DtEmpresa empresa, String[] ofertas, Set<DtPaquete> paquetes, Set<DtOferta> dtofertas) {
		this.empresa = empresa;
		this.ofertas = ofertas;
		this.paquetes = paquetes;
		this.dtofertas = dtofertas;
	}

	public DtEmpresa getEmpresa() {
		return empresa;
	}

	public String[] getOfertas() {
		return ofertas;
	}
	
	public Set<DtPaquete> getPaquetes() {
		return paquetes;
	}

	public Set<DtOferta> getDtOfertas() {
		return dtofertas;
	}

	public void setEmpresa(DtEmpresa empresa) {
		this.empresa = empresa;
	}

	public void setOfertas(String[] ofertas) {
		this.ofertas = ofertas;
	}
	
	public void setPaquetes(Set<DtPaquete> paquetes) {
		this.paquetes = paquetes;
	}

	public void setDtOfertas(Set<DtOferta> ofertas) {
		this.dtofertas = ofertas;
	}
}
