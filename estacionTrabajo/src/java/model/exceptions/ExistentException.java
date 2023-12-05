package model.exceptions;

@SuppressWarnings("serial")
public class ExistentException extends RuntimeException {
    public ExistentException(String descripcion_excepcion) {
        super(descripcion_excepcion);
    }
}
