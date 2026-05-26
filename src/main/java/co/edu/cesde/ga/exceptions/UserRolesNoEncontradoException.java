package co.edu.cesde.ga.exceptions;

public class UserRolesNoEncontradoException extends RuntimeException {

    public UserRolesNoEncontradoException(Long id) {
        super("No se encontro la relacion UserRoles con id: " + id);
    }
}