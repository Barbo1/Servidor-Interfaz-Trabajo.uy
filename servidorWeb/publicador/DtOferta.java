
package publicador;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtOferta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="dtOferta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horarioTrabajo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="remuneracion" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="tipoDeOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="expiracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="paquete" type="{http://publicador/}dtPaquete" minOccurs="0"/>
 *         <element name="estado" type="{http://publicador/}estadoOferta" minOccurs="0"/>
 *         <element name="dataPostulantesFavoritos">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="value" type="{http://publicador/}dtPostulante" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="reputacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="isVencido" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="fechaResultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtOferta", propOrder = {
    "nombre",
    "empresa",
    "descripcion",
    "ciudad",
    "departamento",
    "horarioTrabajo",
    "remuneracion",
    "fechaAlta",
    "costo",
    "tipoDeOferta",
    "keywords",
    "imagen",
    "expiracion",
    "paquete",
    "estado",
    "dataPostulantesFavoritos",
    "reputacion",
    "isVencido",
    "fechaResultado"
})
public class DtOferta {

    protected String nombre;
    protected String empresa;
    protected String descripcion;
    protected String ciudad;
    protected String departamento;
    protected String horarioTrabajo;
    protected Float remuneracion;
    protected String fechaAlta;
    protected float costo;
    protected String tipoDeOferta;
    @XmlElement(nillable = true)
    protected List<String> keywords;
    protected String imagen;
    protected String expiracion;
    protected DtPaquete paquete;
    @XmlSchemaType(name = "string")
    protected EstadoOferta estado;
    @XmlElement(required = true)
    protected DtOferta.DataPostulantesFavoritos dataPostulantesFavoritos;
    protected int reputacion;
    protected boolean isVencido;
    protected String fechaResultado;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpresa(String value) {
        this.empresa = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the ciudad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Sets the value of the ciudad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Gets the value of the horarioTrabajo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorarioTrabajo() {
        return horarioTrabajo;
    }

    /**
     * Sets the value of the horarioTrabajo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorarioTrabajo(String value) {
        this.horarioTrabajo = value;
    }

    /**
     * Gets the value of the remuneracion property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getRemuneracion() {
        return remuneracion;
    }

    /**
     * Sets the value of the remuneracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setRemuneracion(Float value) {
        this.remuneracion = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAlta(String value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the costo property.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Gets the value of the tipoDeOferta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDeOferta() {
        return tipoDeOferta;
    }

    /**
     * Sets the value of the tipoDeOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDeOferta(String value) {
        this.tipoDeOferta = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the keywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the keywords property.
     */
    public List<String> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<>();
        }
        return this.keywords;
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
     * Gets the value of the expiracion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpiracion() {
        return expiracion;
    }

    /**
     * Sets the value of the expiracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiracion(String value) {
        this.expiracion = value;
    }

    /**
     * Gets the value of the paquete property.
     * 
     * @return
     *     possible object is
     *     {@link DtPaquete }
     *     
     */
    public DtPaquete getPaquete() {
        return paquete;
    }

    /**
     * Sets the value of the paquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPaquete }
     *     
     */
    public void setPaquete(DtPaquete value) {
        this.paquete = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link EstadoOferta }
     *     
     */
    public EstadoOferta getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoOferta }
     *     
     */
    public void setEstado(EstadoOferta value) {
        this.estado = value;
    }

    /**
     * Gets the value of the dataPostulantesFavoritos property.
     * 
     * @return
     *     possible object is
     *     {@link DtOferta.DataPostulantesFavoritos }
     *     
     */
    public DtOferta.DataPostulantesFavoritos getDataPostulantesFavoritos() {
        return dataPostulantesFavoritos;
    }

    /**
     * Sets the value of the dataPostulantesFavoritos property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtOferta.DataPostulantesFavoritos }
     *     
     */
    public void setDataPostulantesFavoritos(DtOferta.DataPostulantesFavoritos value) {
        this.dataPostulantesFavoritos = value;
    }

    /**
     * Gets the value of the reputacion property.
     * 
     */
    public int getReputacion() {
        return reputacion;
    }

    /**
     * Sets the value of the reputacion property.
     * 
     */
    public void setReputacion(int value) {
        this.reputacion = value;
    }

    /**
     * Gets the value of the isVencido property.
     * 
     */
    public boolean isIsVencido() {
        return isVencido;
    }

    /**
     * Sets the value of the isVencido property.
     * 
     */
    public void setIsVencido(boolean value) {
        this.isVencido = value;
    }

    /**
     * Gets the value of the fechaResultado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaResultado() {
        return fechaResultado;
    }

    /**
     * Sets the value of the fechaResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaResultado(String value) {
        this.fechaResultado = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="value" type="{http://publicador/}dtPostulante" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class DataPostulantesFavoritos {

        protected List<DtOferta.DataPostulantesFavoritos.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtOferta.DataPostulantesFavoritos.Entry }
         * 
         * 
         * @return
         *     The value of the entry property.
         */
        public List<DtOferta.DataPostulantesFavoritos.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence>
         *         <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="value" type="{http://publicador/}dtPostulante" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected DtPostulante value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link DtPostulante }
             *     
             */
            public DtPostulante getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link DtPostulante }
             *     
             */
            public void setValue(DtPostulante value) {
                this.value = value;
            }

        }

    }

}
