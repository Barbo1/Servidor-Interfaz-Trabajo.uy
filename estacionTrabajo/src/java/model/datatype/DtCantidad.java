package model.datatype;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCantidad {
	private String tipo;
	private int cantidad;
	
	public DtCantidad() {
		this.cantidad = 0;
		this.tipo = "";
	}

	public DtCantidad(String tipo, int cantidad) {
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public int getCantidad() {
		return cantidad;
	}
	
    public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String toString() {
		return tipo + " / " + String.valueOf(cantidad);
	}
	
	
	
}
