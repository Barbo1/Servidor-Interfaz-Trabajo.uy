
package publicador;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtOfertaCompleto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtOfertaCompleto">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="oferta" type="{http://publicador/}dtOferta" minOccurs="0"/>
 *         <element name="postulaciones" type="{http://publicador/}dtPostulacionCompleto" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtOfertaCompleto", propOrder = {
    "oferta",
    "postulaciones"
})
public class DtOfertaCompleto {

    protected DtOferta oferta;
    @XmlElement(nillable = true)
    protected List<DtPostulacionCompleto> postulaciones;

    /**
     * Obtiene el valor de la propiedad oferta.
     * 
     * @return
     *     possible object is
     *     {@link DtOferta }
     *     
     */
    public DtOferta getOferta() {
        return oferta;
    }

    /**
     * Define el valor de la propiedad oferta.
     * 
     * @param value
     *     allowed object is
     *     {@link DtOferta }
     *     
     */
    public void setOferta(DtOferta value) {
        this.oferta = value;
    }

    /**
     * Gets the value of the postulaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the postulaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostulaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPostulacionCompleto }
     * 
     * 
     * @return
     *     The value of the postulaciones property.
     */
    public List<DtPostulacionCompleto> getPostulaciones() {
        if (postulaciones == null) {
            postulaciones = new ArrayList<>();
        }
        return this.postulaciones;
    }

}
