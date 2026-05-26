package co.edu.cesde.ga.exceptions;

public class UsersDatosInvalidosException extends RuntimeException {

    public UsersDatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}