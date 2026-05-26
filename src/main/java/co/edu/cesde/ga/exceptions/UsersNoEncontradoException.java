package co.edu.cesde.ga.exceptions;

public class UsersNoEncontradoException extends RuntimeException {

    public UsersNoEncontradoException(Long userId) {
        super("No se encontro el usuario con id: " + userId);
    }
}