package model.exceptions;

@SuppressWarnings("serial")
public class NonExistentException extends RuntimeException {
    public NonExistentException(String descripcion_excepcion) {
        super(descripcion_excepcion);
    }
}
