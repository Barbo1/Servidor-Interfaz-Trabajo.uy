package model.controllers_managers;

import java.util.HashMap;
import java.util.Map;
import model.clases.Paquete;
import model.utils.Strings;

public class ManejadorPaquete {

private static Map<String, Paquete> paquetes;
private static Strings utils = new Strings();
	private static ManejadorPaquete instance;
	
	// Initialize fields
	private ManejadorPaquete() {
		paquetes = new HashMap<String, Paquete>();
	}
	
	public static ManejadorPaquete getInstance(){
		if (instance == null) {
			instance = new ManejadorPaquete();
		}
		return instance;
	}
	
	public Map<String, Paquete> getPaquetes(){
		return paquetes;
	}
	
	public void addPaquete(Paquete paquete) {
		paquetes.put(utils.unico(paquete.getNombre()), paquete);
	}
		
	public Paquete getPaquete(String nombre) {
		return  paquetes.get(utils.unico(nombre));
	}
		
}
