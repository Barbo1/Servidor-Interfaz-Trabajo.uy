package model.controllers_managers;

import java.util.HashMap;
import java.util.Map;
import model.clases.Oferta;
import model.utils.Strings;

public class ManejadorOferta {

private static Map<String,Integer> visitasOfertasMap;
private static Map<String, Oferta> ofertas;
private static Strings utils = new Strings();

	private static ManejadorOferta instance;
	
	// Initialize fields
	private ManejadorOferta() {
		ofertas = new HashMap<String, Oferta>();
		visitasOfertasMap = new HashMap<String, Integer>();
	}
	
	public static ManejadorOferta getInstance(){
		if (instance == null) {
			instance = new ManejadorOferta();
		}
		return instance;
	}
	
	public Map<String, Oferta> getOfertas(){
		return ofertas;
	}
	
	public void addOferta(Oferta oferta) {
		ofertas.put(utils.unico(oferta.getNombre()), oferta);
		visitasOfertasMap.put(utils.unico(oferta.getNombre()), 0);
	}
	
	public boolean nombreOfertaRegistrado(String nombre) {
		return ofertas.containsKey(utils.unico(nombre));
	}
	
	public Oferta getOferta(String nombre) {
		return ofertas.get(utils.unico(nombre));
	}
	
	public Map<String, Integer> getVisitasOfertas(){
		return visitasOfertasMap;
	}
	
	public void setVisita(String nombreOferta, int cantidad) {
		visitasOfertasMap.replace(utils.unico(nombreOferta), cantidad);
	}
	
	public void addVisita(String nombre) {
		int aux = visitasOfertasMap.get(utils.unico(nombre));
		aux++;
		visitasOfertasMap.replace(utils.unico(nombre), aux);
	}
}
