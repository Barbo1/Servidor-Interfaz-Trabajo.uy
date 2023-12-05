package model.clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.datatype.DtTipoDeOferta;

public class TipoDeOferta {
	private String nombre;
	private String descripcion;
	private int exposicion;
	private int duracion;
	private LocalDate fechaAlta;
	private float costo;
	
    public TipoDeOferta(String _nombre, String _descripcion, int _duracion, int _exposicion, float _costo, LocalDate _fecha) {
        this.nombre = _nombre;
        this.descripcion = _descripcion;
        this.duracion = _duracion;
        this.exposicion = _exposicion;
        this.costo = _costo;
        this.fechaAlta = _fecha;
    }

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getExposicion() {
		return exposicion;
	}
	public void setExposicion(int exposicion) {
		this.exposicion = exposicion;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public DtTipoDeOferta getDtTipoDeOferta() {
		LocalDate fecha = this.fechaAlta;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fecha.format(formatter);
		return new DtTipoDeOferta(nombre, descripcion, exposicion, duracion, fechaFormateada, costo);
	}
	
	
}
