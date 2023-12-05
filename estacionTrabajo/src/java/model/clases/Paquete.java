package model.clases;

import java.util.HashMap;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import model.exceptions.ExistentException;
import model.datatype.DtCantidad;
import model.datatype.DtPaquete;
import model.datatype.DtPaqueteCompleto;

public class Paquete {
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	private float costo;
	private LocalDate fechaAlta;
	private Map<String, Cantidad> cantidadTipoDeOferta = new HashMap<String, Cantidad>();
	private String imagen;
	private boolean comprado;

    public Paquete(String nombre, String descripcion, int validez, float descuento, LocalDate fecha, String imagen){  
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validez = validez;
		this.descuento = descuento;
        this.fechaAlta = fecha;
		this.costo = 0;
		this.imagen = imagen;
		this.comprado = false;
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
	public int getValidez() {
		return validez;
	}
	public void setValidez(int validez) {
		this.validez = validez;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fecha) {
		this.fechaAlta = fecha;
	}
	public Map<String, Cantidad> getCantidadTipoDeOferta() {
		return cantidadTipoDeOferta;
	}
	public void agregarTipoDePublicacion(TipoDeOferta tdo, int cantidad) throws ExistentException {
		Cantidad cant;
		if (!cantidadTipoDeOferta.containsKey(tdo.getNombre())) {
			cant = new Cantidad(cantidad, tdo);
			this.cantidadTipoDeOferta.put(tdo.getNombre(), cant);
			this.costo = this.costo  + tdo.getCosto() * (1 - this.descuento) * cantidad;
		} else
			throw new ExistentException("El paquete ya tiene este tipo de oferta."); 
	}
	
	public DtPaquete getDtPaquete(){
		LocalDate fecha = this.fechaAlta;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fecha.format(formatter);
		return new DtPaquete(nombre, descripcion, validez, descuento, fechaFormateada, costo, imagen);
	}

	public DtPaquete getDtPaquete(LocalDate fecha){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fecha.format(formatter);
		return new DtPaquete(nombre, descripcion, validez, descuento, fechaFormateada, costo, imagen); //esta fecha es el alta de un paquete adquirido
	}
	
	public Set<DtCantidad> getSetDtCantidad() {	
		Map<String, Cantidad> cantidades =this.getCantidadTipoDeOferta();
		
		Set<DtCantidad> dtcant = new HashSet<DtCantidad>();
		for (Map.Entry<String, Cantidad> tipo : cantidades.entrySet()) {
			DtCantidad toc = tipo.getValue().getDtCantidad();
			dtcant.add(toc);
		}
		
		return dtcant;
		
	}
	
	
	public DtPaqueteCompleto getDtPaqueteCompleto() {
		DtPaquete dtpaq = this.getDtPaquete();
		Set<DtCantidad> dtcant = this.getSetDtCantidad();
	
		
		return new DtPaqueteCompleto(dtpaq, dtcant);
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getImagen(){
		return this.imagen;
	}

	public boolean isComprado() {
		return comprado;
	}

	public void setComprado() {
		this.comprado = true;
	}
	
}
