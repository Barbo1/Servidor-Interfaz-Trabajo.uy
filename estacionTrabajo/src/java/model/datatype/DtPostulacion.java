package model.datatype;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPostulacion {
	private String curriculum;
	private String motivacion;
	private String fecha;
	private String zip;
	private String video;
	private int orden;
	
	public DtPostulacion() {
		this.curriculum = "";
		this.fecha = "";
		this.motivacion = "";
		this.zip = "";
		this.video="";
	}

	public DtPostulacion(String curriculumV, String motivacion, String fecha, String zip,String video, int orden) {
		this.curriculum = curriculumV;
		this.fecha = fecha;
		this.motivacion = motivacion;
		this.zip = zip;
		this.video=video;
		this.orden = orden;

	}
	
	public String getVideo() {
		return video;
	}
	
	public void setVideo() {
		this.video =video;
	}
	
	
	public String getCurriculum() {
		return curriculum;
	}

	public String getMotivacion() {
		return motivacion;
	}

	public String getFecha() {
		return fecha;
	}

	public String getZip() {
		return zip;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}

	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}
}

