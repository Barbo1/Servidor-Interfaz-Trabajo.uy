
package publicador;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtOferta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad nombre.
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
     * Define el valor de la propiedad nombre.
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
     * Obtiene el valor de la propiedad empresa.
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
     * Define el valor de la propiedad empresa.
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
     * Obtiene el valor de la propiedad descripcion.
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
     * Define el valor de la propiedad descripcion.
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
     * Obtiene el valor de la propiedad ciudad.
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
     * Define el valor de la propiedad ciudad.
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
     * Obtiene el valor de la propiedad departamento.
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
     * Define el valor de la propiedad departamento.
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
     * Obtiene el valor de la propiedad horarioTrabajo.
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
     * Define el valor de la propiedad horarioTrabajo.
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
     * Obtiene el valor de la propiedad remuneracion.
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
     * Define el valor de la propiedad remuneracion.
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
     * Obtiene el valor de la propiedad fechaAlta.
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
     * Define el valor de la propiedad fechaAlta.
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
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDeOferta.
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
     * Define el valor de la propiedad tipoDeOferta.
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
     * Obtiene el valor de la propiedad imagen.
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
     * Define el valor de la propiedad imagen.
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
     * Obtiene el valor de la propiedad expiracion.
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
     * Define el valor de la propiedad expiracion.
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
     * Obtiene el valor de la propiedad paquete.
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
     * Define el valor de la propiedad paquete.
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
     * Obtiene el valor de la propiedad estado.
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
     * Define el valor de la propiedad estado.
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
     * Obtiene el valor de la propiedad dataPostulantesFavoritos.
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
     * Define el valor de la propiedad dataPostulantesFavoritos.
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
     * Obtiene el valor de la propiedad reputacion.
     * 
     */
    public int getReputacion() {
        return reputacion;
    }

    /**
     * Define el valor de la propiedad reputacion.
     * 
     */
    public void setReputacion(int value) {
        this.reputacion = value;
    }

    /**
     * Obtiene el valor de la propiedad isVencido.
     * 
     */
    public boolean isIsVencido() {
        return isVencido;
    }

    /**
     * Define el valor de la propiedad isVencido.
     * 
     */
    public void setIsVencido(boolean value) {
        this.isVencido = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaResultado.
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
     * Define el valor de la propiedad fechaResultado.
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
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
             * Obtiene el valor de la propiedad key.
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
             * Define el valor de la propiedad key.
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
             * Obtiene el valor de la propiedad value.
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
             * Define el valor de la propiedad value.
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
