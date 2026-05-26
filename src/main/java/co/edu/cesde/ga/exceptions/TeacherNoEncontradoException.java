package co.edu.cesde.ga.exceptions;

public class TeacherNoEncontradoException extends RuntimeException {

    public TeacherNoEncontradoException(Long teacherId) {
        super("Profesor no encontrado. ID ingresado: " + teacherId);
    }

    public TeacherNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
