package model.utils;

public class Compare {
    /* Devuelve true si la tira "esquema" es contenida en "cadena". */
	public boolean comparar(String cadena, String esquema) {
        if (cadena.length() < esquema.length()) {
            return false;
        }
        int i=0;
        while (i < esquema.length() && cadena.charAt(i) == esquema.charAt(i)) { i++; }
        return esquema.length() == i;
    }
}
