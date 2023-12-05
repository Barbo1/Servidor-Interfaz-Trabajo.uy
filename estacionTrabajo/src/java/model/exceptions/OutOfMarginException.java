package model.exceptions;

@SuppressWarnings("serial")
public class OutOfMarginException extends RuntimeException {
    public OutOfMarginException(String descripcion_excepcion) {
        super(descripcion_excepcion);
    }
}
