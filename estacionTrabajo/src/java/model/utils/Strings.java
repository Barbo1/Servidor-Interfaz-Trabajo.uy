package model.utils;
import java.text.Normalizer;

public class Strings {
	public String unico(String cadena) {
		 if (cadena != null) {
	 	// Convertir a minúsculas
			 cadena =  cadena.toLowerCase()
        
        // Reemplazar caracteres especiales por ""
        				.replace(" ", "_")
        				.replace("/", "")
        				.replace("\\", "")
        				.replace("|", "")
        				.replace("(", "")
        				.replace(")", "")
        				.replace("[", "")
        				.replace("]", "")
				        .replace(",", "")
			            .replace(";", "")
        				.replace(".", "")
        				.replace("-", "");
       
			 cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
			 cadena = cadena.replaceAll("[\\p{M}]", "");
		 }

		 	return cadena;
        }
}
