package model.datatype;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPaquete {
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	private String fechaAlta;
	private float costo;
	private String imagen;
	
    public DtPaquete() {
		this.nombre = "";
		this.descripcion = "";
		this.validez = 0;
		this.descuento = (float)0;
		this.fechaAlta = "";
		this.costo = (float)0;
		this.imagen = "";
	}
	
	public DtPaquete(String nombre, String descripcion, int validez, float descuento, String fecha, float costo, String imagen) {
		this.costo = costo;
		this.descripcion = descripcion;
		this.descuento = descuento;
		this.nombre = nombre;
		this.validez = validez;
		this.fechaAlta = fecha;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getImagen(){
		return this.imagen;
	}
	
	public int getValidez() {
		return validez;
	}

	public float getDescuento() {
		return descuento;
	}

	public float getCosto() {
		return costo;
	}
	
	public String getFechaAlta() {
		return this.fechaAlta;
	}
	
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setImagen(String imagen){
		this.imagen = imagen;
	}
	
	public void setValidez(int validez) {
		this.validez = validez;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
