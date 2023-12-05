package model.datatype;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtTipoDeOferta {
	private String nombre;
	private String descripcion;
	private int exposicion;
	private int duracion;
	private String fechaAlta;
	private float costo;
	
	public DtTipoDeOferta() {
		this.nombre = "";
		this.costo = (float)0;
		this.descripcion = "";
		this.duracion = 0;
		this.exposicion = -1;
		this.fechaAlta = "";
	}

	public DtTipoDeOferta(String nombre, String desc, int exp, int dur, String fec, float cos) {
		this.nombre = nombre;
		this.costo = cos;
		this.descripcion = desc;
		this.duracion = dur;
		this.exposicion = exp;
		this.fechaAlta = fec;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getExposicion() {
		return exposicion;
	}

	public int getDuracion() {
		return duracion;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public float getCosto() {
		return costo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setExposicion(int exposicion) {
		this.exposicion = exposicion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public String toArray() {
		return this.nombre + ", " + this.duracion + ", " + this.costo;
	}
}
