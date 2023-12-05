
package publicador;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtPostulacionCompleto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtPostulacionCompleto">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="postulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="oferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="postulacion" type="{http://publicador/}dtPostulacion" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "dtPostulacionCompleto", propOrder = {
    "postulante",
    "oferta",
    "postulacion",
    "imagen",
    "orden"
})
public class DtPostulacionCompleto {

    protected String postulante;
    protected String oferta;
    protected DtPostulacion postulacion;
    protected String imagen;
    protected int orden;

    /**
     * Gets the value of the postulante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostulante() {
        return postulante;
    }

    /**
     * Sets the value of the postulante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostulante(String value) {
        this.postulante = value;
    }

    /**
     * Gets the value of the oferta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOferta() {
        return oferta;
    }

    /**
     * Sets the value of the oferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOferta(String value) {
        this.oferta = value;
    }

    /**
     * Gets the value of the postulacion property.
     * 
     * @return
     *     possible object is
     *     {@link DtPostulacion }
     *     
     */
    public DtPostulacion getPostulacion() {
        return postulacion;
    }

    /**
     * Sets the value of the postulacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPostulacion }
     *     
     */
    public void setPostulacion(DtPostulacion value) {
        this.postulacion = value;
    }

    /**
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Gets the value of the orden property.
     * 
     */
    public int getOrden() {
        return orden;
    }

    /**
     * Sets the value of the orden property.
     * 
     */
    public void setOrden(int value) {
        this.orden = value;
    }

}
