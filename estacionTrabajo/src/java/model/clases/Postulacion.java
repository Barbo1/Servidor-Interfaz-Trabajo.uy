package model.clases;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import model.datatype.DtOferta;
import model.datatype.DtPostulacion;
import model.datatype.DtPostulacionCompleto;

@Entity
public class Postulacion {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
	private String curriculum;
    
	@Column
	private String motivacion;
	
	@Column
	private LocalDate fecha;
	
	@Column
	private String zip;
	
    @ManyToOne
    @JoinColumn(name = "postulante_id")
	private Postulante postulante;
    
    @Transient
    private Oferta oferta;
	
	@Column
	private String video;
	
	@Column
	private int orden; //Va en la base de datos?
	
	public Postulacion() {
		
	}
	
	public Postulacion(String curriculumV, String motivacion, LocalDate fecha, String video) {
		this.curriculum = curriculumV;
		this.fecha = fecha;
		this.motivacion = motivacion;
		this.video=video;
		this.orden=0;
		
	}
	
	public Postulacion(String curriculumV, String motivacion, LocalDate fecha, String zip, String video) {
		this.curriculum = curriculumV;
		this.fecha = fecha;
		this.motivacion = motivacion;
		this.zip = zip;
		this.video=video;
		this.orden=0;
	}
	
	public String getMotivacion(){
		return motivacion;
	}
	
	public String getCurriculum() {
		return curriculum;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setMotivacion(String mot) {
		this.motivacion = mot;
	}
	
	public void setCurriculum(String curriculumV) {
		this.curriculum = curriculumV;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public void setFecha(LocalDate dtf) {
		this.fecha = dtf;
	}

	public Postulante getPostulante() {
		return postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	public String getNombreOferta() {
		return this.oferta.getNombre();
	}

	public DtPostulacionCompleto getDtPostulacionCompleto() {
		return new DtPostulacionCompleto(this.postulante.getNickname(), this.getOferta().getNombre(), this.getDtPostulacion(), this.postulante.getImagen());
	}

	public DtPostulacion getDtPostulacion() {
		LocalDate fecha1 = this.fecha;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fecha1.format(formatter);
		return new DtPostulacion(curriculum, motivacion, fechaFormateada, zip, video, orden);
	}
	
	public DtOferta getDtOferta() {
		return this.oferta.getDtOferta();
	}
	
	public void setOrden(int orden) {
		this.orden=orden;			
		
	}
	
	public int getOrden() {
		return orden;
	}

}
