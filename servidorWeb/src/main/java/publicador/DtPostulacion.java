
package publicador;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="curriculum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="motivacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="video" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="orden" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulacion", propOrder = {
    "curriculum",
    "motivacion",
    "fecha",
    "zip",
    "video",
    "orden"
})
public class DtPostulacion {

    protected String curriculum;
    protected String motivacion;
    protected String fecha;
    protected String zip;
    protected String video;
    protected int orden;

    /**
     * Obtiene el valor de la propiedad curriculum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurriculum() {
        return curriculum;
    }

    /**
     * Define el valor de la propiedad curriculum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurriculum(String value) {
        this.curriculum = value;
    }

    /**
     * Obtiene el valor de la propiedad motivacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivacion() {
        return motivacion;
    }

    /**
     * Define el valor de la propiedad motivacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivacion(String value) {
        this.motivacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad zip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Define el valor de la propiedad zip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * Obtiene el valor de la propiedad video.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideo() {
        return video;
    }

    /**
     * Define el valor de la propiedad video.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideo(String value) {
        this.video = value;
    }

    /**
     * Obtiene el valor de la propiedad orden.
     * 
     */
    public int getOrden() {
        return orden;
    }

    /**
     * Define el valor de la propiedad orden.
     * 
     */
    public void setOrden(int value) {
        this.orden = value;
    }

}
