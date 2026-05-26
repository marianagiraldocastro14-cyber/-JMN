package co.edu.cesde.ga.exceptions;

public class EnrollmentNoEncontradoException extends RuntimeException {

    public EnrollmentNoEncontradoException(Long enrollmentId) {
        super("Inscripcion no encontrada. ID ingresado: " + enrollmentId);
    }

    public EnrollmentNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
