package model.clases;

import java.util.HashSet;
import java.util.Set;

public class Keyword{
	private String clave;
	private Set<Oferta> ofertas = new HashSet<>();

	public Keyword(String key) {
		clave = key;
	}

	public String getClave() {
		return clave;
	}
	
	public void setClave(String key) {
		clave = key;
	}
	
	public void addOferta(Oferta oferta) {
		ofertas.add(oferta);
	}
	
	public Set<Oferta> getOfertas(){
		return ofertas;
	}
}
