package model.controllers_managers;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.utils.Compare;
import model.utils.Strings;

import java.util.stream.Collectors;
import model.clases.Cantidad;
import model.clases.Empresa;
import model.clases.Keyword;
import model.clases.Oferta;
import model.clases.Paquete;
import model.clases.Postulacion;
import model.clases.Postulante;
import model.clases.TipoDeOferta;
import model.datatype.DtCantidad;
import model.datatype.DtOferta;
import model.datatype.DtOfertaCompleto;
import model.datatype.DtPaquete;
import model.datatype.DtPaqueteCompleto;
import model.datatype.DtPostulacionCompleto;
import model.datatype.DtTipoDeOferta;
import model.datatype.EstadoOferta;
import model.interfacess.IcontroladorLaboral;
import model.exceptions.DuplicationException;
import model.exceptions.ExistentException;
import model.exceptions.NonExistentException;
import model.exceptions.OutOfMarginException;
import jakarta.persistence.*;

public class ControladorLaboral implements IcontroladorLaboral {
	private static Strings utils = new Strings();
	private static Compare compare = new Compare();
	ManejadorOferta manejadorOferta = ManejadorOferta.getInstance();
	ManejadorTipoDeOferta manejadorTdO = ManejadorTipoDeOferta.getInstance();
	ManejadorPaquete manejadorPaquete = ManejadorPaquete.getInstance();
	ManejadorKeyword manejadorKeyword = ManejadorKeyword.getInstance();
	ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();

	public ControladorLaboral() {};
	
	public void cambiarEstadoOferta(String nombreOferta, EstadoOferta estado) throws NonExistentException {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		if (oferta == null) {
			throw new NonExistentException("no existe una oferta con nombre " + nombreOferta);
		}
	    oferta.setEst(estado);
	    if (estado.equals(EstadoOferta.Confirmada) || estado.equals(EstadoOferta.Rechazada))
	    	oferta.setFechaAlta(LocalDate.now());
		if (estado.equals(EstadoOferta.Finalizada)) {
			oferta.setFechaFinalizada(LocalDate.now());
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrabajoJPA");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			tx.begin();
			
			for (Postulacion postulacion : oferta.getPostulaciones()) {
				if (em.find(Postulante.class, postulacion.getPostulante().getNickname()) == null)
					em.persist(postulacion.getPostulante());
				em.persist(postulacion);
			}
			if (em.find(Empresa.class, oferta.getEmpresa().getNickname()) == null)
				em.persist(oferta.getEmpresa());
			em.persist(oferta);
			
			
			tx.commit();
			
			
			em.close();
			emf.close();
			

		}
	}
	
	@Override
	public void ingresarOfertaLaboralConPaquete(String empresaNickname, String tipoOferta, String nombreOferta, String descripcion, String horario, Float remuneracion, String ciudad, String departamento, LocalDate fechaAlta, Set<String> keywords,String imagen, String paquete, EstadoOferta est) throws OutOfMarginException, DuplicationException, NonExistentException {
		if (remuneracion < 0) { 
			throw new OutOfMarginException("remuneracion fuera de los valores permitidos");
		}
		if (manejadorOferta.nombreOfertaRegistrado(nombreOferta)) {
			throw new DuplicationException("Ya hay una oferta con el nombre " + nombreOferta + " en el sistema");            
	  }	
		Empresa emp = (Empresa) manejadorUsuario.getEmpresa(empresaNickname);
		if (emp == null) {
			throw new NonExistentException("No existe una empresa llamada " + empresaNickname);
		}
		TipoDeOferta tipoDeOferta = manejadorTdO.getTipoDeOferta(tipoOferta);
		if (tipoDeOferta == null) {
			throw new NonExistentException("No existe un tipo de oferta con nombre " + tipoOferta);
		}
		Paquete paq = manejadorPaquete.getPaquete(paquete);
		if (paq == null) {
			throw new NonExistentException("No existe un paquete con nombre " + paquete);
		}
		Set<Keyword> keywordsV = new HashSet<Keyword>();
		for (String key : keywords) {
			Keyword keyword = manejadorKeyword.getKeyword(key);
			if (keyword != null) 
				keywordsV.add(keyword);
		}
		
		Oferta oferta = emp.crearOfertaConPaquete(tipoDeOferta, nombreOferta, descripcion, horario, remuneracion, ciudad, departamento, fechaAlta, keywordsV, imagen, paq, est);
		manejadorOferta.addOferta(oferta);
		
		/* NUEVO : linkear las keywords con la oferta creada... para los filtros*/
		for (Keyword key: keywordsV) {
			key.addOferta(oferta);
		}
	}
	
	public void ingresarOfertaLaboral(String empresaNickname, String tipoOferta, String nombreOferta, String descripcion, String horario, Float remuneracion, String ciudad, String departamento, LocalDate fechaAlta, Set<String> keywords, String imagen, EstadoOferta state ) throws DuplicationException, NonExistentException, OutOfMarginException, ExistentException {
		if (remuneracion < 0) { 
			throw new OutOfMarginException("remuneracion fuera de los valores permitidos");
		}
		if (manejadorOferta.nombreOfertaRegistrado(nombreOferta)) {
	        throw new DuplicationException("Ya hay una oferta con el nombre " + nombreOferta + " en el sistema");            
	    }	    
		Empresa emp = (Empresa) manejadorUsuario.getEmpresa(empresaNickname);
		if (emp == null) {
			throw new NonExistentException("no existe una empresa llamada " + empresaNickname);
		}
		TipoDeOferta tipoDeOferta = manejadorTdO.getTipoDeOferta(tipoOferta);
		if (tipoDeOferta == null) {
			throw new NonExistentException("no existe un tipo de oferta con nombre " + tipoOferta);
		}
		Set<Keyword> keywordsV = new HashSet<Keyword>();
		for (String key : keywords) {
			Keyword keyword = manejadorKeyword.getKeyword(key);
			if (keyword != null) 
				keywordsV.add(keyword);
		}
		Oferta nuevaOferta = emp.crearOfertaPorEmpresa(tipoDeOferta, nombreOferta, descripcion, horario, remuneracion, ciudad, departamento, fechaAlta, keywordsV, imagen, state);
		manejadorOferta.addOferta(nuevaOferta);
		/* NUEVO : linkear las keywords con la oferta creada... para los filtros*/
		for (Keyword key: keywordsV) {
			key.addOferta(nuevaOferta);
		}
	}

	@Override
	public DtTipoDeOferta mostrarTipoOferta(String tipoOf) throws NonExistentException {
		TipoDeOferta tipoOferta = manejadorTdO.getTipoDeOferta(tipoOf);
		if (tipoOferta == null) {
			throw new NonExistentException("no existe un tipo de oferta con nombre " + tipoOf);
		}
		return tipoOferta.getDtTipoDeOferta();
	}
	
	@Override
	public DtOfertaCompleto mostrarOfertaCompleto(String nombreOferta) throws NonExistentException {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		if (oferta == null) {
			throw new NonExistentException("no existe una oferta con nombre " + nombreOferta);
		}
		DtPostulacionCompleto[] dtpc = oferta.getDtPostulacionesCompleto();
		return new DtOfertaCompleto(oferta.getDtOferta(), dtpc);
	}

	@Override
	public DtOferta mostrarOferta(String nombreOferta) throws NonExistentException {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		if (oferta == null) {
			throw new NonExistentException("no existe una oferta con nombre " + nombreOferta);
		}
		return oferta.getDtOferta();
	}
	
	public DtOfertaCompleto mostrarOfertaCompletoPostulante(String nombreOferta, String nick) throws NonExistentException {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		if (oferta == null) {
			throw new NonExistentException("no existe una oferta con nombre " + nombreOferta);
		}
		DtPostulacionCompleto[] dtpc = oferta.getDtPostulacion(nick);
		return new DtOfertaCompleto(oferta.getDtOferta(), dtpc);
	}

	@Override
	public void altaTipoOferta(
            String nombre, 
            String descripcion, 
            int duracion, 
            int exposicion, 
            float costo,
            LocalDate fecha
    ) throws DuplicationException, OutOfMarginException {
		if (costo < 0) {
			throw new OutOfMarginException("costo fuera de los valores permitidos");
		} else if (duracion < 0) {
			throw new OutOfMarginException("duracion fuera de los valores permitidos");
		} else if (exposicion < 0 || exposicion > 5) {
			throw new OutOfMarginException("exposicion fuera de los valores permitidos");
		}
        Map<String, TipoDeOferta> mapTdO = manejadorTdO.getTipoDeOfertas();
        if (mapTdO.containsKey(nombre)) {
            throw new DuplicationException("Ya existe un tipo de oferta con este el nombre. Por favor, Ingrese otro.");
        }
        manejadorTdO.addTipoDeOferta(new TipoDeOferta(nombre, descripcion, duracion, exposicion, costo, fecha));
    }

	@Override
	public void altaPaquete(
			String nombre,
			String descripcion, 
			int periodoValido,
			float descuento, 
			LocalDate fecha,
			String imagen
	) throws DuplicationException, OutOfMarginException {
		if (periodoValido <= 0) {
			throw new OutOfMarginException("periodoValido fuera de los valores permitidos");
		}
		if (descuento < 0 || descuento > 1) {
			throw new OutOfMarginException("descuento fuera de los valores permitidos");
		}
		Paquete paquete = manejadorPaquete.getPaquete(nombre);
	    if (paquete != null) {
	    	throw new DuplicationException("Ya existe un paquete con este nombre. Por favor, Ingrese otro.");
	    }
    manejadorPaquete.addPaquete(new Paquete(nombre, descripcion, periodoValido, descuento, fecha, imagen));
	}
	
	public String[] listarPaquetes() {
		Map<String, Paquete> paquetes = manejadorPaquete.getPaquetes();
		int aux = 0;
		String[] listaPaquete = new String[paquetes.size()];
		for (Map.Entry<String, Paquete> paquete : paquetes.entrySet()) { 
			listaPaquete[aux] = paquete.getValue().getNombre();
			aux++;
		}
		return listaPaquete;
	}
	
	public String[] listarPaquetesNoComprados() {
		Map<String, Paquete> paquetes = manejadorPaquete.getPaquetes();
		int aux = 0;
		for (Map.Entry<String, Paquete> paquete : paquetes.entrySet())
			if (!paquete.getValue().isComprado())
				aux++;
		String[] listaPaquete = new String[aux];
		aux = 0;
		for (Map.Entry<String, Paquete> paquete : paquetes.entrySet())
			if (!paquete.getValue().isComprado()) {		
				listaPaquete[aux] = paquete.getValue().getNombre();
				aux++;
			}
		return listaPaquete;
	}
	
	public String[] listarTipoDeOferta() {
		Map<String, TipoDeOferta> tdos = manejadorTdO.getTipoDeOfertas();
		String[] listaTipoDeOferta = new String[tdos.size()];
		int aux = 0;
		for (Map.Entry<String, TipoDeOferta> tdo : tdos.entrySet()) {
			listaTipoDeOferta[aux] = tdo.getValue().getNombre();
			aux++;
		}
		return listaTipoDeOferta;
	}
	
	public void agregarTipoDePublicacionAPaquete(
			String paquete, 
			String tipoDeOferta, 
			int cantidad
		) throws NonExistentException, OutOfMarginException{
		if (cantidad < 0) {
			throw new OutOfMarginException("costo fuera de los valores permitidos");
		}
		TipoDeOferta tdo = manejadorTdO.getTipoDeOferta(tipoDeOferta);
		if (tdo == null) {
			throw new NonExistentException("no existe una oferta con nombre " + tipoDeOferta);
		}
		Paquete paq = manejadorPaquete.getPaquete(paquete);
		if (paq == null) {
			throw new NonExistentException("no existe un paquete con nombre " + paquete);
		}
		paq.agregarTipoDePublicacion(tdo, cantidad);
	}

	public Set<String> listarTiposDeOfertaSTR() {
		Map<String, TipoDeOferta> tiposDeOfertas = manejadorTdO.getTipoDeOfertas();
		Set<String> listaTiposDeOfertas = new HashSet<String>();
		for (Map.Entry<String, TipoDeOferta> tdo : tiposDeOfertas.entrySet()) {
			String nombreTO = tdo.getKey();
			listaTiposDeOfertas.add(nombreTO);
		}
		return listaTiposDeOfertas;
	}

	public void ingresarKeyword(String keyword) throws DuplicationException {
		if (manejadorKeyword.getKeyword(keyword) == null){
			Keyword key = new Keyword(keyword);
			manejadorKeyword.addKeyword(key);
		} else {
			throw new ExistentException("Ya existe tal keyword.");
		}
	}

	public void postular(
			String pos, 
			String nombreOferta, 
			String curriculum, 
			String motivacion, 
			LocalDate fecha,
			String video
	) throws DuplicationException, NonExistentException, OutOfMarginException {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		if (oferta == null) {
			throw new NonExistentException("No existe una oferta con nombre " + nombreOferta);
		}
		LocalDate fechaVencimiento = oferta.getVencimiento();
		LocalDate fechaActual = LocalDate.now();
		if (fechaActual.compareTo(fechaVencimiento) > 0) {
			throw new OutOfMarginException("La oferta seleccionada esta vencida");
		}
		DtPostulacionCompleto[] postOf = oferta.getDtPostulacionesCompleto();
		int aux = 0;
		while (aux < postOf.length) {
			if (postOf[aux].getPostulante().equals(pos)) {
				throw new DuplicationException("Ya existe la postulación. Por favor, si desea continuar ingrese nuevo postulante, oferta o empresa.");
			}
			aux++;
		}
		
	    Postulante post = manejadorUsuario.getPostulante(pos);
	    if (post == null) {
	    	throw new NonExistentException("No existe un postulante con nombre " + pos);
	    }
	    Postulacion postulacion = oferta.agregarPostulacion(curriculum, motivacion, fecha, video);
	    post.agregarPostulacion(postulacion);            
    }
	
	
	public void postularCargaDatos(
			String pos, 
			String nombreOferta, 
			String curriculum, 
			String motivacion, 
			LocalDate fecha,
			String video
	) throws DuplicationException, NonExistentException, OutOfMarginException {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		if (oferta == null) {
			throw new NonExistentException("No existe una oferta con nombre " + nombreOferta);
		}
		DtPostulacionCompleto[] postOf = oferta.getDtPostulacionesCompleto();
		int aux = 0;
		while (aux < postOf.length) {
			if (postOf[aux].getPostulante().equals(pos)) {
				throw new DuplicationException("Ya existe la postulación. Por favor, si desea continuar ingrese nuevo postulante, oferta o empresa.");
			}
			aux++;
		}
		
	    Postulante post = manejadorUsuario.getPostulante(pos);
	    if (post == null) {
	    	throw new NonExistentException("No existe un postulante con nombre " + pos);
	    }
	    Postulacion postulacion = oferta.agregarPostulacion(curriculum, motivacion, fecha, video);
	    post.agregarPostulacion(postulacion);            
    }
	
	public String[] listarKeywords() {
		Map<String, Keyword> keys = manejadorKeyword.getKeywords();
		int aux = 0;
		String[] listaKeywords = new String[keys.size()];
		for (Map.Entry<String, Keyword> keywords : keys.entrySet()) { 
			listaKeywords[aux] = keywords.getValue().getClave();
			aux++;
		}
		return listaKeywords;
	}
	
	public DtPaqueteCompleto mostrarInfoPaquete(String nombrePaquete) {
		Paquete paq = manejadorPaquete.getPaquete(nombrePaquete);
		if (paq == null) {
			throw new NonExistentException("No existe un paquete con nombre " + nombrePaquete);
		}
		return paq.getDtPaqueteCompleto();
	}
	
	public List<DtPaquete> getDtPaquetes(){
		Map<String, Paquete> paquetes = manejadorPaquete.getPaquetes();
		List<DtPaquete> res = new ArrayList<DtPaquete>();
		for (Map.Entry<String, Paquete> paquete : paquetes.entrySet()) {
			DtPaquete dtp = paquete.getValue().getDtPaquete();
            res.add(dtp);
		}
		return res;
	}
	
	public DtPaquete getDtPaquete(String nombre) {
		Paquete paquete = manejadorPaquete.getPaquete(nombre);
        String fechaFormateada = paquete.getFechaAlta().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return new DtPaquete(paquete.getNombre(), paquete.getDescripcion(), paquete.getValidez(), paquete.getDescuento(), fechaFormateada, paquete.getCosto(), paquete.getImagen());
	}
	
	public DtPaqueteCompleto getDtPaqueteCompleto(String nombre) {
		Paquete paquete = manejadorPaquete.getPaquete(nombre);
		DtPaquete dataPaquete = getDtPaquete(nombre);
		Set<DtCantidad> tipos = paquete.getSetDtCantidad();
		return new DtPaqueteCompleto(dataPaquete, tipos);
	}

	public List<DtOferta> listarOfertasSinOrdenar(){
        List<DtOferta> ret = new ArrayList<DtOferta>();
        LocalDate fechaActual = LocalDate.now();
        Oferta of;
		for (Map.Entry<String,Oferta> oferta: manejadorOferta.getOfertas().entrySet()) {
            of = oferta.getValue();
			if (of.getEst().equals(EstadoOferta.Confirmada) && fechaActual.compareTo(of.getVencimiento()) <= 0) {
                ret.add(of.getDtOferta());
            }
        }
        return ret;
    }

	public List<DtOferta> listarOfertas(){
		List<DtOferta> lis1 = new ArrayList<>();
		List<DtOferta> lis2 = new ArrayList<>();
		List<DtOferta> lis3 = new ArrayList<>();
		List<DtOferta> lis4 = new ArrayList<>();
		List<DtOferta> lis5 = new ArrayList<>();
		LocalDate fechaActual = LocalDate.now();
		for (Map.Entry<String,Oferta> oferta: manejadorOferta.getOfertas().entrySet()) {
			if (oferta.getValue().getEst().equals(EstadoOferta.Confirmada))
				if (fechaActual.compareTo(oferta.getValue().getVencimiento()) <= 0)
				switch (oferta.getValue().getTipodeoferta().getExposicion()) {
					case 1:
						lis1.add(oferta.getValue().getDtOferta());
					break;
					case 2:
						lis2.add(oferta.getValue().getDtOferta());
					break;
					case 3:
						lis3.add(oferta.getValue().getDtOferta());
					break;
					case 4:
						lis4.add(oferta.getValue().getDtOferta());
					break;
					case 5:
						lis5.add(oferta.getValue().getDtOferta());
					break;
				}
		}
		lis1.addAll(lis2);
		lis1.addAll(lis3);
		lis1.addAll(lis4);
		lis1.addAll(lis5);
		return lis1;
	}
	
	public List<DtOferta> listarOfertasPorKeyword(String keyword){
		List<DtOferta> lis1 = new ArrayList<>();
		List<DtOferta> lis2 = new ArrayList<>();
		List<DtOferta> lis3 = new ArrayList<>();
		List<DtOferta> lis4 = new ArrayList<>();
		List<DtOferta> lis5 = new ArrayList<>();
		LocalDate fechaActual = LocalDate.now();
		Keyword key;
		if ((key = manejadorKeyword.getKeyword(keyword)) != null) {
			Set<Oferta> ofertas = key.getOfertas();
			for (Oferta oferta: ofertas) {
				if (oferta.getEst().equals(EstadoOferta.Confirmada))
					if (fechaActual.compareTo(oferta.getVencimiento()) <= 0)
					switch (oferta.getTipodeoferta().getExposicion()) {
						case 1:
							lis1.add(oferta.getDtOferta());
						break;
						case 2:
							lis2.add(oferta.getDtOferta());
						break;
						case 3:
							lis3.add(oferta.getDtOferta());
						break;
						case 4:
							lis4.add(oferta.getDtOferta());
						break;
						case 5:
							lis5.add(oferta.getDtOferta());
						break;
					}
			}
		}
		lis1.addAll(lis2);
		lis1.addAll(lis3);
		lis1.addAll(lis4);
		lis1.addAll(lis5);
		return lis1;
	}

	public List<DtOferta> listarOfertasPorEmpresa(String nombre_empresa, EstadoOferta state){
		List<DtOferta> lis1 = new ArrayList<>();
		List<DtOferta> lis2 = new ArrayList<>();
		List<DtOferta> lis3 = new ArrayList<>();
		List<DtOferta> lis4 = new ArrayList<>();
        List<DtOferta> lis5 = new ArrayList<>();
		LocalDate fechaActual = LocalDate.now();
        Empresa emp;
        if ((emp = manejadorUsuario.getEmpresa(nombre_empresa)) != null) {
            for (Oferta oferta: emp.getOfertas()) {
                if (oferta.getEst().equals(state) && (fechaActual.compareTo(oferta.getVencimiento()) <= 0 || state.equals(EstadoOferta.Ingresada))) {
                    switch (oferta.getTipodeoferta().getExposicion()) {
                        case 1:
                            lis1.add(oferta.getDtOferta());
                            break;
                        case 2:
                            lis2.add(oferta.getDtOferta());
                            break;
                        case 3:
                            lis3.add(oferta.getDtOferta());
                            break;
                        case 4:
                            lis4.add(oferta.getDtOferta());
                            break;
                        case 5:
                            lis5.add(oferta.getDtOferta());
                            break;
                    }
                }
            }
        }
        lis1.addAll(lis2);
        lis1.addAll(lis3);
        lis1.addAll(lis4);
        lis1.addAll(lis5);
		return lis1;
	}

	public void comprarPaquete(String nombreEmpresa, String nombrePaquete, LocalDate fechaAltaPaquete) throws DuplicationException {
		Paquete paquete = manejadorPaquete.getPaquete(nombrePaquete);
		Empresa empresa = manejadorUsuario.getEmpresa(nombreEmpresa);
		empresa.addPaquete(paquete, fechaAltaPaquete);
	}
	
	public boolean tienePaquete(String nickEmpresa, String nombrePaquete) throws NonExistentException{
		Empresa empresa;
		if ((empresa = manejadorUsuario.getEmpresa(nickEmpresa)) == null) {
			throw new NonExistentException("no existe una empresa con el nombre '" + nickEmpresa + "'");
		}
		return empresa.adquirioPaquete(nombrePaquete);
	}
	
	public DtPostulacionCompleto getPostulanteDeOferta(String post, String nombreOferta){
		DtOfertaCompleto oferta = this.mostrarOfertaCompleto(nombreOferta);
		int aux = 0;
		while (aux < oferta.getPostulaciones().length && !oferta.getPostulaciones()[aux].getPostulante().equals(post)) {
			aux++;
		}

		if (aux == oferta.getPostulaciones().length) {
			throw new NonExistentException("No hay postulacion de la oferta con ese nickname.");
		} else {
			return oferta.getPostulaciones()[aux];
		}
	}
	
	public String[] listarTipoDeOfertaFaltante(String paquete) {
		Paquete pack = manejadorPaquete.getPaquete(paquete);
		if (pack != null) {
			Set<String> list = new HashSet<>();
			String[] tiposDeOferta = this.listarTipoDeOferta();
			Map<String, Cantidad> aux = pack.getCantidadTipoDeOferta();
			for (int aux2 = 0; aux2 < tiposDeOferta.length; aux2++ ) {
				if(aux.get(tiposDeOferta[aux2]) == null)
					list.add(tiposDeOferta[aux2]);
			}
			String[] res = new String[list.size()];
			int aux2 = 0;
			for(String aux3 : list) {
				res[aux2] = aux3;
				aux2++;
			}
			return res;
		} else return null;
		
	}
	
	public String[][] getOfertasMasVisitadas(int cantidad){
		if (cantidad > 0) {
			Map<String,Integer> visitasMap = manejadorOferta.getVisitasOfertas();
			List<Map.Entry<String, Integer>> sortedEntries = visitasMap.entrySet()
	                .stream()
	                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
	                .collect(Collectors.toList());

	        List<Map.Entry<String, Integer>> topNEntries = sortedEntries.subList(0, Math.min(cantidad, sortedEntries.size()));
			String[][] res = new String[cantidad][4];
	        int aux = 0;
	        for (Map.Entry<String, Integer> auxMap : topNEntries) {
	        	Oferta oferta = manejadorOferta.getOferta(auxMap.getKey());
	        	res[aux][0] = oferta.getNombre();
	        	res[aux][1] = oferta.getEmpresa().getNickname();
	        	res[aux][2] = oferta.getTipodeoferta().getNombre();
	        	res[aux][3] = auxMap.getValue().toString();
	        	aux++;
	        }
            while(aux != cantidad) {
                res[aux][0] = " ";
                res[aux][1] = " ";
                res[aux][2] = " ";
                res[aux][3] = " ";
                aux++;
            }
	        return res;
		}
		return null;
	}
	
	public void addVisita(String nombreOferta) {
		manejadorOferta.addVisita(nombreOferta);
	}
	
	public void setVisita(String nombreOferta, int cantidad) {
		manejadorOferta.setVisita(nombreOferta, cantidad);
	}
	
	public void addOfertaFavorito(String nombreOferta, String nickPostulante) {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		Postulante postulante = manejadorUsuario.getPostulante(nickPostulante);
		oferta.addPostulanteFavorito(postulante);
	}
	
	public void removeOfertaFavorito(String nombreOferta, String nickPostulante) {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		Postulante postulante = manejadorUsuario.getPostulante(nickPostulante);
		oferta.removePostulanteFavorito(postulante);
	}

	public int getReputacionOferta(String nombreOferta) {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		return oferta.getReputacion();
	}
	
	public boolean tienePostulanteFavorito(String nombreOferta, String nicknamePostulante) {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
		return oferta.tienePostulanteFavorito(nicknamePostulante);
	}
   
    public void cargarOrdenAPost(int orden, String nombreOferta, String nickPostulante) throws NonExistentException, DuplicationException { 
    	if (orden > 0) {
    		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
    		
    		if (oferta.ordenNoRepetido(orden)) { //chequear que el postulante se haya postulado antes
    			oferta.darOrdenPost(nickPostulante, orden);
    			oferta.setFechaResultado(LocalDate.now());
    		} else {
    			throw new DuplicationException("El orden de dos o mas postulantes esta repetido.");
    		}
    	} else {
			throw new NonExistentException("Debe darle orden a todos los postulantes.");
		}
    }
    
    public boolean seleccionFinalizada(String nombreOferta) {
    	Oferta oferta = manejadorOferta.getOferta(nombreOferta);
        return oferta.ordenCargado(); 
    }
    
    public void finalizarSeleccion(String nombreOferta) {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
        oferta.setFechaResultado(LocalDate.now()); 
    }
    
	public void limpiarOrden(String nombreOferta) {
		Oferta oferta = manejadorOferta.getOferta(nombreOferta);
        oferta.limpiarOrdenPost(); 
	}


}
