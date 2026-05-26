package co.edu.cesde.ga.exceptions;

public class ProgramNoEncontradoException extends RuntimeException {

    public ProgramNoEncontradoException(Long programId) {
        super("Programa no encontrado. ID ingresado: " + programId);
    }

    public ProgramNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
