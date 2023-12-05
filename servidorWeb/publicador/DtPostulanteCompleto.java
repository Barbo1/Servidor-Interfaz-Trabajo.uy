
package publicador;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtPostulanteCompleto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtPostulanteCompleto">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="postulante" type="{http://publicador/}dtPostulante" minOccurs="0"/>
 *         <element name="ofertas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="dtofertas" type="{http://publicador/}dtOferta" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulanteCompleto", propOrder = {
    "postulante",
    "ofertas",
    "dtofertas"
})
public class DtPostulanteCompleto {

    protected DtPostulante postulante;
    @XmlElement(nillable = true)
    protected List<String> ofertas;
    @XmlElement(nillable = true)
    protected List<DtOferta> dtofertas;

    /**
     * Gets the value of the postulante property.
     * 
     * @return
     *     possible object is
     *     {@link DtPostulante }
     *     
     */
    public DtPostulante getPostulante() {
        return postulante;
    }

    /**
     * Sets the value of the postulante property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPostulante }
     *     
     */
    public void setPostulante(DtPostulante value) {
        this.postulante = value;
    }

    /**
     * Gets the value of the ofertas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the ofertas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfertas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the ofertas property.
     */
    public List<String> getOfertas() {
        if (ofertas == null) {
            ofertas = new ArrayList<>();
        }
        return this.ofertas;
    }

    /**
     * Gets the value of the dtofertas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dtofertas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtofertas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtOferta }
     * 
     * 
     * @return
     *     The value of the dtofertas property.
     */
    public List<DtOferta> getDtofertas() {
        if (dtofertas == null) {
            dtofertas = new ArrayList<>();
        }
        return this.dtofertas;
    }

}
