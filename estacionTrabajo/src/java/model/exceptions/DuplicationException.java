package model.exceptions;

@SuppressWarnings("serial")
public class DuplicationException extends RuntimeException {
    public DuplicationException(String descripcion_excepcion) {
        super(descripcion_excepcion);
    }
}
