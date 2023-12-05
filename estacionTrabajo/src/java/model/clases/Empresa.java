package model.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.datatype.DtEmpresa;
import model.datatype.DtEmpresaCompleto;
import model.datatype.DtOferta;
import model.datatype.DtPaquete;
import model.datatype.EstadoOferta;
import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;
import model.datatype.DtUsuario;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Empresa")
public class Empresa extends Usuario{
	
	@Column
	private String link;
	
	@Column(length = 2000)
	private String descripcion;
	
	@Transient
	private Map<String, PaqueteAdquirido> paquetes = new HashMap<String, PaqueteAdquirido>(); // Paquetes que la empresa compro
	
	@Transient
	private Set<Oferta> ofertas = new HashSet<Oferta>();
	
	
	public Empresa() {
		
	}
	
	public Empresa(String link, String descripcion, String nombre, String apellido, String nickname, String correo, String imagen, String password) {
		super(nombre, apellido, nickname, correo, imagen, password);
		this.link = link;
		this.descripcion = descripcion;
	}
	
	public String getLink() {
        return link;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desc) {
        descripcion = desc;
    }

    public void setLink(String link_) {
        link = link_;
    }

	public Set<Oferta> getOfertas() {
		return ofertas;
	}

	public Oferta crearOfertaPorEmpresa(TipoDeOferta tipoOferta, String nombreOferta, String descripcion, String horario, Float remuneracion, String ciudad, String departamento, LocalDate fechaAlta, Set<Keyword> keywords, String imagen, EstadoOferta estado) {
		Oferta nuevaOferta = new Oferta(this, tipoOferta, nombreOferta, descripcion, horario, remuneracion, ciudad, departamento, fechaAlta, keywords, imagen, estado);
		ofertas.add(nuevaOferta);
		return nuevaOferta;
	}
	
	public String[] getNombreOfertas() {
		List<String> nombresOfertas = new ArrayList<String>();
		for (Oferta ofertaAux : this.ofertas)
			nombresOfertas.add(ofertaAux.getNombre());
		String[] arrOfertas = new String[nombresOfertas.size()];
		int aux = 0;
		for(String nombreOferta : nombresOfertas) {
			arrOfertas[aux] = nombreOferta;
			aux++;
		}
		return arrOfertas;
	}
	
	public DtEmpresaCompleto getDatosCompletos() {
		DtEmpresa dte = this.getDtEmpresa();
		String[] ofertasArreglo = new String[this.ofertas.size()];
		Set<DtPaquete> paq = new HashSet<DtPaquete>();
		Set<DtOferta> dtos = new HashSet<DtOferta>();
		int aux = 0;
		for (Oferta ofertaAux : this.ofertas) {
			dtos.add(ofertaAux.getDtOferta());
			ofertasArreglo[aux] = ofertaAux.getNombre();
			aux++;
		}
		for (Map.Entry<String, PaqueteAdquirido> paquete: paquetes.entrySet()) {
			LocalDate compraPaquete = paquete.getValue().getFechaCompra();
			paq.add(paquete.getValue().getPaquete().getDtPaquete(compraPaquete));
		}
		return new DtEmpresaCompleto(dte, ofertasArreglo, paq, dtos);
	}

	public DtEmpresa getDtEmpresa() {
		List<DtUsuario> seguidoresNuevo = new ArrayList<DtUsuario>();
		List<DtUsuario> seguidosNuevo = new ArrayList<DtUsuario>();
		for(Map.Entry<String, Usuario> user : this.getSeguidores().entrySet())
			seguidoresNuevo.add(user.getValue().getDtUsuarioCorto());
		for(Map.Entry<String, Usuario> user : this.getSeguidos().entrySet())
			seguidosNuevo.add(user.getValue().getDtUsuarioCorto());
		return new DtEmpresa(this.getNombre(), this.getApellido(), this.getNickname(), this.getCorreoElectronico(), this.getLink(), this.getDescripcion(), this.getPassword(), this.getImagen(), seguidosNuevo, seguidoresNuevo);
	}
	
	public void addPaquete(Paquete paquete, LocalDate fechaAltaPaquete) throws DuplicationException {
		
		if (paquetes.get(paquete.getNombre()) == null) {
			PaqueteAdquirido paqueteAdquirido = new PaqueteAdquirido();
	        int validez = paquete.getValidez();
	        LocalDate vencimiento = fechaAltaPaquete.plusDays(validez);
			paqueteAdquirido.setEmpresa(this);
			paqueteAdquirido.setPaquete(paquete);
			paqueteAdquirido.setVencimiento(vencimiento); 
			paqueteAdquirido.setFechaCompra(fechaAltaPaquete);
			paquetes.put(paquete.getNombre(), paqueteAdquirido);
		}else 
			throw new DuplicationException("Paquete ya adquirido.");
	}
	
	public boolean adquirioPaquete(String nombrePaquete) {
		return paquetes.get(nombrePaquete) != null;
	}

	public Oferta crearOfertaConPaquete(TipoDeOferta tipoOferta, String nombreOferta, String descripcion2, String horario, Float remuneracion, String ciudad, String departamento, LocalDate fechaAlta, Set<Keyword> keywordsV, String imagen, Paquete paq, EstadoOferta estado) throws NonExistentException {
		if (paquetes.get(paq.getNombre()) == null)
			throw new NonExistentException("La empresa "+this.getNickname()+ " no adquirio el paquete " + paq.getNombre());
		
		if (paq.getCantidadTipoDeOferta().get(tipoOferta.getNombre()) == null)
			throw new NonExistentException("El paquete " + paq.getNombre()+" no tiene tipo de oferta de nombre " + tipoOferta.getNombre());
		
		int cantidad = paq.getCantidadTipoDeOferta().get(tipoOferta.getNombre()).getCantidad();
		
		PaqueteAdquirido paqAdq = paquetes.get(paq.getNombre());
		
		int cantidadActual = 0;
		
		if (paqAdq.getOfertas().get(tipoOferta.getNombre()) != null)
			cantidadActual = paqAdq.getOfertas().get(tipoOferta.getNombre()).size();
		if (cantidadActual == cantidad)
			throw new NonExistentException("En el paquete "+ paq.getNombre() + " no le quedan más tipos de oferta del tipo "+ tipoOferta.getNombre());
		Oferta nuevaOferta = new Oferta(this, tipoOferta, nombreOferta, descripcion2, horario, remuneracion, ciudad, departamento, fechaAlta, keywordsV, imagen, estado);
		ofertas.add(nuevaOferta);
		paqAdq.addOferta(nuevaOferta, tipoOferta.getNombre());
		return nuevaOferta;
	}
	
	public Map<String, Map<String, Integer>> obtenerPaquetesE(String[] tiposDeOferta){
		Map<String, Map<String, Integer>> res = new HashMap<String, Map<String, Integer>>();
		LocalDate fechaActual = LocalDate.now();
		for (int i = 0; i < tiposDeOferta.length; i++) {
			Map<String, Integer> aux = new HashMap<String, Integer>();
			for (Map.Entry<String, PaqueteAdquirido> paquete: paquetes.entrySet()) {
				int validez = paquete.getValue().getPaquete().getValidez();
				LocalDate fechaVencimiento = paquete.getValue().getFechaCompra().plusDays(validez);
				if (paquete.getValue().getOfertas().get(tiposDeOferta[i]) != null && fechaActual.compareTo(fechaVencimiento) <= 0) // si la empresa compro una oferta del tipoDeOferta[i] con ese paquete
					aux.put(paquete.getKey(),paquete.getValue().getSaldoRestante().get(tiposDeOferta[i])); // en aux se guarada para cada paquete el saldo restante del tipoDeOferta[i]
			}
			res.put(tiposDeOferta[i], aux);
		}
		return res;
	}
	

	
}