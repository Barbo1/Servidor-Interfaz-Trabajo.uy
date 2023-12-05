package model.controllers_managers;

import java.util.HashMap;
import java.util.Map;
import model.clases.TipoDeOferta;
import model.utils.Strings;

public class ManejadorTipoDeOferta {
	private static Strings utils = new Strings();
	private static Map<String, TipoDeOferta> tipoDeOfertas;
	
	private static ManejadorTipoDeOferta instance;
	
	// Initialize fields
	private ManejadorTipoDeOferta() {
		tipoDeOfertas = new HashMap<String, TipoDeOferta>();
	}
	
	public static ManejadorTipoDeOferta getInstance(){
		if (instance == null) {
			instance = new ManejadorTipoDeOferta();
		}
		return instance;
	}
	
	public Map<String, TipoDeOferta> getTipoDeOfertas(){
		return tipoDeOfertas;
	}
	
	public void addTipoDeOferta(TipoDeOferta tipoDeOferta) {
		tipoDeOfertas.put(utils.unico(tipoDeOferta.getNombre()), tipoDeOferta);
	}
	
	public TipoDeOferta getTipoDeOferta(String nombre) {
		return tipoDeOfertas.get(utils.unico(nombre));
	}
}
