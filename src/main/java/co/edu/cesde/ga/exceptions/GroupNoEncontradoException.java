package co.edu.cesde.ga.exceptions;

public class GroupNoEncontradoException extends RuntimeException {

    public GroupNoEncontradoException(String code) {
        super("No se encontro el grupo con codigo: " + code);
    }
}