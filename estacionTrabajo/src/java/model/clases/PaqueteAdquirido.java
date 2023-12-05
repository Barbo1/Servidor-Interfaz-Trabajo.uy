package model.clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PaqueteAdquirido {
	private LocalDate fechaCompra;
	private LocalDate vencimiento;
	private Empresa empresa;
	private Paquete paquete;
	private Map<String, Set<Oferta>> ofertas = new HashMap<String, Set<Oferta>>(); //Ofertas que la empresa compro con el paquete 
	
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public LocalDate getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(LocalDate vencimiento) {
		this.vencimiento = vencimiento;
	}
	public Paquete getPaquete() {
		return paquete;
	}
	public void setPaquete(Paquete paq) {
		this.paquete = paq;
		for (Map.Entry<String , Cantidad> tipoO : paq.getCantidadTipoDeOferta().entrySet()) {
			Set <Oferta> aux = new HashSet<Oferta>();
			ofertas.put(tipoO.getKey(),aux);
		}
		paq.setComprado();
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Map<String, Set<Oferta>> getOfertas() {
		return ofertas;
	}
	
	public void addOferta(Oferta oferta, String tipoOferta) {
		Set<Oferta> aux;
		oferta.setCompraPaquete(this);
		if (ofertas.get(tipoOferta) == null)
			aux = new HashSet<Oferta>();
		else
			aux = ofertas.get(tipoOferta);
		aux.add(oferta);
		ofertas.put(tipoOferta, aux);
	}
	
	public Map<String, Integer> getSaldoRestante() {
		Map<String, Integer> res = new HashMap<String, Integer>();
		for (Map.Entry<String, Set<Oferta>> ofertass : ofertas.entrySet()) {
			int usado = ofertass.getValue().size();
			int cantidad = this.paquete.getCantidadTipoDeOferta().get(ofertass.getKey()).getCantidad();
			res.put(ofertass.getKey(), cantidad-usado);
		}
		return res;
	}
}
