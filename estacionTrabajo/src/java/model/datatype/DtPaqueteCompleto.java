package model.datatype;

import java.util.HashSet;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPaqueteCompleto {
	private DtPaquete paquete;
	private Set<DtCantidad> tipos;
	
	public DtPaqueteCompleto() {
		this.paquete = null;
		this.tipos = new HashSet<DtCantidad>();
	}

	public DtPaqueteCompleto(DtPaquete paquete, Set<DtCantidad> tipos) {
		this.paquete = paquete;
		this.tipos = tipos;
	}
    
	public DtPaquete getPaquete() {
		return paquete;
	}

	public Set<DtCantidad> getTipos() {
		return tipos;
	}
	
    public void setPaquete(DtPaquete paquete) {
		this.paquete = paquete;
	}

	public void setTipos(Set<DtCantidad> tipos) {
		this.tipos = tipos;
	}	
}
