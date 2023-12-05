package model.datatype;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public enum EstadoOferta {
	Confirmada,
	Ingresada,
	Rechazada,
	Finalizada
}
