package model.clases;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import model.datatype.DtOferta;
import model.datatype.DtPostulante;
import model.datatype.DtPostulanteCompleto;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import model.datatype.DtUsuario;
import java.util.ArrayList;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Postulante")
public class Postulante extends Usuario {
	
	@Column
	private LocalDate nacimiento;
	
	@Column
	private String nacionalidad;
	
	@Transient
	private Set<Postulacion> postulaciones = new HashSet<Postulacion>();
	
	public Postulante() {
		
	}
	
	public Postulante(LocalDate fecha, String nacionalidad, String nombre, String apellido, String nickname, String correo, String imagen, String password) {
        super(nombre, apellido, nickname, correo, imagen, password);
    	this.nacimiento = fecha;
        this.nacionalidad = nacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }


    public void setNacionalidad(String nationaly) {
        nacionalidad = nationaly;
    }

    public void setNacimiento(LocalDate fecha) {
        nacimiento = fecha;
    }

	public Set<Postulacion> getPostulaciones() {
		return postulaciones;
	}
	

	public DtPostulanteCompleto getDatosCompletos() {
		DtPostulante dtPostulante = this.getDtPostulante();
		String[] ofertas = new String[this.postulaciones.size()];
		Set<DtOferta> dtOfertas = new HashSet<DtOferta>();
		int aux = 0;
		for (Postulacion postulacion : this.postulaciones) {
			dtOfertas.add(postulacion.getDtOferta());
			ofertas[aux] = postulacion.getNombreOferta();
			aux++;
		}
		return new DtPostulanteCompleto(dtPostulante, ofertas, dtOfertas);
	}

	public DtPostulante getDtPostulante() {
		LocalDate fecha = this.nacimiento;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fecha.format(formatter);
		List<DtUsuario> seguidoresNuevo = new ArrayList<DtUsuario>();
		List<DtUsuario> seguidosNuevo = new ArrayList<DtUsuario>();
		for(Map.Entry<String, Usuario> user : this.getSeguidores().entrySet())
			seguidoresNuevo.add(user.getValue().getDtUsuarioCorto());
		for(Map.Entry<String, Usuario> user : this.getSeguidos().entrySet())
			seguidosNuevo.add(user.getValue().getDtUsuarioCorto());
		return new DtPostulante(this.getNombre(), this.getApellido(), this.getNickname(), this.getCorreoElectronico(), fechaFormateada, this.getNacionalidad(), this.getPassword(), this.getImagen(), seguidosNuevo, seguidoresNuevo);
	}
	
	public void agregarPostulacion(Postulacion postulacion){
		postulacion.setPostulante(this);
		this.postulaciones.add(postulacion);
	}

}