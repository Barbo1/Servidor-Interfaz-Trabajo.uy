package model.datatype;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtOferta {
	private String nombre;
	private String empresa; // Nickname de la empresa que creo la oferta
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horarioTrabajo;
	private Float remuneracion;
	private String fechaAlta;
	private float costo;
	private String tipoDeOferta;
	private String[] keywords;
	private String imagen;
	private String expiracion;
	private DtPaquete paquete;
	private EstadoOferta estado;
	Map<String, DtPostulante> dataPostulantesFavoritos;
	private int reputacion;
	private boolean isVencido;
	private String fechaResultado;
	
	public DtOferta() {
		this.nombre = "";
		this.empresa = "";
		this.descripcion = "";
		this.ciudad = "";
		this.departamento = "";
		this.horarioTrabajo = "";
		this.remuneracion = (float) 0;
		this.fechaAlta = "";
		this.costo = 0;
		this.tipoDeOferta = "";
		this.keywords = null;
		this.imagen = "";
		this.expiracion = "";
		this.paquete = null;
		this.estado = EstadoOferta.Confirmada;
		this.dataPostulantesFavoritos = new HashMap<String, DtPostulante>();
		this.reputacion = 0;
		this.isVencido = false;
		this.fechaResultado = "";
    }

	public DtOferta(String nombre, String empresaCreadora, DtPaquete paquete, String descripcion, String horarioTrabajo, Float remuneracion, String ciudad, String departamento, String fechaAlta, Float costo, String tipodeoferta, String[] keywords, String imagen, String vencimiento, EstadoOferta estado, Map<String, DtPostulante> dataPostulantesFavoritos, int reputacion, boolean vencido, String fechaResultado) {
		this.nombre = nombre;
		this.empresa = empresaCreadora;
		this.paquete = paquete;
		this.descripcion = descripcion;
		this.horarioTrabajo = horarioTrabajo;
		this.remuneracion = remuneracion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.fechaAlta = fechaAlta;
		this.costo = costo;
		this.tipoDeOferta = tipodeoferta;
		this.keywords = keywords;
		this.imagen = imagen;
		this.expiracion = vencimiento;
		this.estado = estado;
		this.dataPostulantesFavoritos = new HashMap<String, DtPostulante>();
		this.reputacion = reputacion;
		this.isVencido = vencido;
		this.fechaResultado = fechaResultado;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public String getHorarioTrabajo() {
		return horarioTrabajo;
	}
	
	public Float getRemuneracion() {
		return remuneracion;
	}
	
	public String getFechaAlta() {
		return fechaAlta;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public String[] getKeyword() {
		return keywords;
	}
	
	public Set<String> getKeywords2(){
		Set<String> keywordss = new HashSet<String>(); 
		for (int aux = 0; aux < this.keywords.length; aux++) {
			keywordss.add(this.keywords[aux]);
		}
		return keywordss;
	}
	
	public String getTipoDeOferta() {
		return tipoDeOferta;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public String getImagen() {
		return imagen;
	}

	public String getVencimiento() {
		return expiracion;
	}
	
	public DtPaquete getPaquete(){
		return paquete;
	}


	public EstadoOferta getEstado() {
		return estado;
	}

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public void setHorarioTrabajo(String horarios) {
		this.horarioTrabajo = horarios;
	}
	
    public void setRemuneracion(Float remuneracion) {
		this.remuneracion = remuneracion;
	}
	
	public void setFechaAlta(String fecha) {
		this.fechaAlta = fecha;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public void setKeyword(String[] keys) {
		this.keywords = keys;
	}
	
	public void setTipoDeOferta(String tipodeoferta) {
		this.tipoDeOferta = tipodeoferta;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setVencimiento(String expiracion) {
		this.expiracion = expiracion;
	}
	
	public void setPaquete(DtPaquete paquete){
		this.paquete = paquete;
	}

	public void setEstado(EstadoOferta estado) {
		this.estado = estado;
	}
	
	public void addPostulanteFavorito(DtPostulante dataPostulante) {
		if(!dataPostulantesFavoritos.containsKey(dataPostulante.getNickname())) {
			this.dataPostulantesFavoritos.put(dataPostulante.getNickname(), dataPostulante);
			this.reputacion ++;
			System.out.println("In DtOferta addPostulanteFavorito");
		}
	}
	
	public void removePostulanteFavorito(DtPostulante dataPostulante) {
		if(dataPostulantesFavoritos.containsKey(dataPostulante.getNickname())) {
			this.dataPostulantesFavoritos.remove(dataPostulante.getNickname());
			this.reputacion --;
			System.out.println("");
		}
	}
	
	public void setReputacion(int reputacion) {
		this.reputacion = reputacion;
	}
	
	public int getReputacion() {
		return this.reputacion;
	}

	public boolean isVencido() {
		return isVencido;
	}

	public void setVencido(boolean isVencido) {
		this.isVencido = isVencido;
	}

	public String getFechaResultado() {
		return fechaResultado;
	}

	public void setFechaResultado(String fechaResultado) {
		this.fechaResultado = fechaResultado;
	}
	
}
