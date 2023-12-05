
package publicador;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoOferta.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="estadoOferta">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Confirmada"/>
 *     <enumeration value="Ingresada"/>
 *     <enumeration value="Rechazada"/>
 *     <enumeration value="Finalizada"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estadoOferta")
@XmlEnum
public enum EstadoOferta {

    @XmlEnumValue("Confirmada")
    CONFIRMADA("Confirmada"),
    @XmlEnumValue("Ingresada")
    INGRESADA("Ingresada"),
    @XmlEnumValue("Rechazada")
    RECHAZADA("Rechazada"),
    @XmlEnumValue("Finalizada")
    FINALIZADA("Finalizada");
    private final String value;

    EstadoOferta(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EstadoOferta fromValue(String v) {
        for (EstadoOferta c: EstadoOferta.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
