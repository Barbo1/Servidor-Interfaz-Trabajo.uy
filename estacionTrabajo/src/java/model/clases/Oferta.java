package model.clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import model.datatype.DtOferta;
import model.datatype.DtPaquete;
import model.datatype.DtPostulacionCompleto;
import model.datatype.EstadoOferta;
import model.datatype.DtPostulante;
import model.datatype.DtUsuario;

@Entity
public class Oferta {
	
	@Id 
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@Column
	private String horarioTrabajo;
	
	@Column
	private Float remuneracion;
	
	@Column
	private String ciudad;
	
	@Column
	private String departamento;
	
	@Column
	private LocalDate fechaAlta;
	
	@Column
	private float costo;
	
	@Column
	private LocalDate fechaResultado; 
	
	@Column
	private LocalDate fechaFinalizada;
	
	@ManyToOne
	private Empresa empresa;
	
	@Column
	private String tipoDeOferta;
	
	@Transient
	private TipoDeOferta tipodeoferta;
	
	@OneToMany
	private Set<Postulacion> postulaciones = new HashSet<Postulacion>();
	
	@Transient
	private Set<Keyword> keywords = new HashSet<Keyword>();
	
	@Column
	private String imagen;
	
	@Transient
	private EstadoOferta estado;
	
	@Column
	private String paquete;
	
	@Transient
	private PaqueteAdquirido compraPaquete;
	
	@Transient
	private Map<String, Postulante> postulantesFavoritos = new HashMap<String,Postulante>();
	
	public Oferta() {
		
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
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getHorarioTrabajo() {
		return horarioTrabajo;
	}
	public void setHorarioTrabajo(String horarioTrabajo) {
		this.horarioTrabajo = horarioTrabajo;
	}
	public Float getRemuneracion() {
		return remuneracion;
	}
	public void setRemuneracion(Float remuneracion) {
		this.remuneracion = remuneracion;
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
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public TipoDeOferta getTipodeoferta() {
		return tipodeoferta;
	}
	public void setTipodeoferta(TipoDeOferta tipodeoferta) {
		this.tipodeoferta = tipodeoferta;
		this.tipoDeOferta = tipodeoferta.getNombre();
	}
	public int getDuracionTipo() {
		return this.tipodeoferta.getDuracion();
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public EstadoOferta getEst() {
		return estado;
	}
	public void setEst(EstadoOferta state) {
		this.estado = state;
	}
	public PaqueteAdquirido getCompraPaquete() {
		return compraPaquete;
	}
	public void setCompraPaquete(PaqueteAdquirido compraPaquete) {
		this.compraPaquete = compraPaquete;
		this.paquete = compraPaquete.getPaquete().getNombre();
	}
	
	public Oferta(Empresa empresa, TipoDeOferta tipoOferta, String nombreOferta, String descripcion, String horario, Float remuneracion, String ciudad, String departamento, LocalDate fechaAlta,  Set<Keyword> keywords, String imagen, EstadoOferta state) {
		this.empresa = empresa;
		this.tipodeoferta = tipoOferta;
		this.nombre = nombreOferta;
    	this.descripcion = descripcion;
    	this.horarioTrabajo = horario;
    	this.remuneracion = remuneracion;
    	this.ciudad = ciudad;
    	this.departamento = departamento;
    	this.fechaAlta = fechaAlta;
    	this.keywords = keywords;
    	this.costo = tipoOferta.getCosto();
    	this.imagen = imagen;
    	this.estado = state;
    	this.fechaResultado= LocalDate.of(1, 1, 1);
    	this.fechaFinalizada = LocalDate.of(1, 1, 1);
    	this.tipoDeOferta = tipoOferta.getNombre();
	}
	
	public DtPostulacionCompleto[] getDtPostulacionesCompleto() {
		int aux = 0;
		DtPostulacionCompleto[] dtpc = new DtPostulacionCompleto[this.postulaciones.size()];
		for (Postulacion postulacion : this.postulaciones) {
			dtpc[aux] = postulacion.getDtPostulacionCompleto();
			aux++;
		}
		return dtpc;
	}
	
	public Set<Postulacion> getPostulaciones(){
		return this.postulaciones;
	}
	
	public LocalDate getVencimiento() {
		LocalDate fechaVencimiento = this.fechaAlta;
		int validez = this.tipodeoferta.getDuracion();
		return fechaVencimiento.plusDays(validez);
	}
	
	public DtOferta getDtOferta() {
		String[] keywordsString = new String[keywords.size()];
		int aux = 0;
		for (Keyword key : keywords) {
			keywordsString[aux] = key.getClave();
			aux++;
		}
		DtPaquete paquete = null;
		if (compraPaquete != null)
			paquete = compraPaquete.getPaquete().getDtPaquete();
		
		LocalDate fecha = this.fechaAlta;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fecha.format(formatter);
        LocalDate fecha2 = this.getVencimiento();
        String fechaFormateada2 = fecha2.format(formatter);
    	
        String fechaFormateada4 = this.fechaResultado.format(formatter);

        Map<String, DtPostulante> dataPostulantesFavoritos = new HashMap<String, DtPostulante>();
                
        for (Postulante postulante : postulantesFavoritos.values()) {
        	
        	LocalDate fechaNacimiento = postulante.getNacimiento();
    		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaFormateada3 = fechaNacimiento.format(formatter);
            Map<String, Usuario> seguidos = postulante.getSeguidos();
            Map<String, Usuario> seguidores = postulante.getSeguidores();
            
            List<DtUsuario> listaSeguidos = new ArrayList<DtUsuario>();
            List<DtUsuario> listaSeguidores = new ArrayList<DtUsuario>();

            for(Usuario seguido : seguidos.values()) {
            	DtUsuario dataSeguido = new DtUsuario(seguido.getNombre(), seguido.getApellido(), seguido.getNickname(), seguido.getCorreoElectronico(), seguido.getPassword(), seguido.getImagen()); 
            	listaSeguidos.add(dataSeguido);
            }
            
            for(Usuario seguidor : seguidores.values()) {
            	DtUsuario dataSeguidor = new DtUsuario(seguidor.getNombre(), seguidor.getApellido(), seguidor.getNickname(), seguidor.getCorreoElectronico(), seguidor.getPassword(), seguidor.getImagen()); 
            	listaSeguidores.add(dataSeguidor);
            }
            
        	DtPostulante dataPostulante = new DtPostulante(postulante.getNombre(), postulante.getApellido(), postulante.getNickname(), postulante.getCorreoElectronico(), fechaFormateada3, postulante.getNacionalidad(), postulante.getPassword(), postulante.getImagen(), listaSeguidos, listaSeguidores);
            dataPostulantesFavoritos.put(postulante.getNickname(), dataPostulante);
        }
        
		return new DtOferta(nombre, empresa.getNickname(), paquete , descripcion, horarioTrabajo, remuneracion, ciudad, departamento, fechaFormateada, costo, tipodeoferta.getNombre(), keywordsString, imagen, fechaFormateada2, this.estado, dataPostulantesFavoritos, getReputacion(), isVencido(), fechaFormateada4);

	}
	
	public Postulacion agregarPostulacion(String curriculum, String motivacion, LocalDate fecha, String video) {
		Postulacion postulacion = new Postulacion(curriculum, motivacion, fecha, video);
		postulacion.setOferta(this);
		this.postulaciones.add(postulacion);
		return postulacion;
	}

	public DtPostulacionCompleto[] getDtPostulacion(String nickname) {
		DtPostulacionCompleto[] datosPostulacion = new DtPostulacionCompleto[1];
		for (Postulacion postulacion : this.postulaciones) {
			if (postulacion.getPostulante().getNickname().equals(nickname)) {
				datosPostulacion[0] = postulacion.getDtPostulacionCompleto();
				break;
			}
		}
		return datosPostulacion;
	}
	
	public void addPostulanteFavorito(Postulante postulante) {
		if(!postulantesFavoritos.containsKey(postulante.getNickname())) {
			postulantesFavoritos.put(postulante.getNickname(), postulante);
		}
	}		

	public void removePostulanteFavorito(Postulante postulante) {
		if(postulantesFavoritos.containsKey(postulante.getNickname())) {
			postulantesFavoritos.remove(postulante.getNickname());
		}
	}
	
	public int getReputacion() {
		return postulantesFavoritos.size();
	}
	
	
	public boolean tienePostulanteFavorito(String nicknamePostulante) {
		return postulantesFavoritos.containsKey(nicknamePostulante);
	}
	
	public boolean isVencido() {
		if (LocalDate.now().compareTo(getVencimiento()) > 0)
			return true;
		else
			return false;
	}
	
	public void setFechaResultado(LocalDate fechaRes) {
			this.fechaResultado=fechaRes; 
	}
	
	public LocalDate getFechaResultado() {
		return fechaResultado; 
	}
	
	public boolean ordenNoRepetido(int orden) { //Devuelve true si el numero elegido no aparece en ninguna otra postulacion
		boolean correcto = true;
		for (Postulacion postulacion : this.postulaciones) {
			if (postulacion.getOrden() == orden) {
				correcto=false;
				break;
			}
		}
		return correcto;
	}
	
	public boolean ordenCargado() { //Devuelve true si todas las postulaciones tienen un orden elegido
		boolean correcto = true;
		for (Postulacion postulacion : this.postulaciones) {
			if (postulacion.getOrden() == 0) {
				correcto=false;
				break;
			}
		}
		return correcto;
	}
	
	
	public void limpiarOrdenPost() { 

		for (Postulacion postulacion : this.postulaciones) {
			postulacion.setOrden(0);
				
		}

	}
	
	public void darOrdenPost(String nickname, int orden) { //Le pongo el orden a la postulacion correcta
		for (Postulacion postulacion : this.postulaciones) {
			if (postulacion.getPostulante().getNickname().equals(nickname)) {
				postulacion.setOrden(orden);
				break;
			}
		}
	}

	public LocalDate getFechaFinalizada() {
		return fechaFinalizada;
	}

	public void setFechaFinalizada(LocalDate fechaFinalizada) {
		this.fechaFinalizada = fechaFinalizada;
	}
}
