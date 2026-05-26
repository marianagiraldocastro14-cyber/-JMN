package co.edu.cesde.ga.exceptions;

public class RolesNoEncontradoException extends RuntimeException {

    public RolesNoEncontradoException(Long roleId) {
        super("No se encontro el rol con id: " + roleId);
    }
}