package co.edu.cesde.ga.exceptions;

public class StudentNoEncontradoException extends RuntimeException {

    public StudentNoEncontradoException(Long studentId) {
        super("Estudiante no encontrado. ID ingresado: " + studentId);
    }

    public StudentNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
