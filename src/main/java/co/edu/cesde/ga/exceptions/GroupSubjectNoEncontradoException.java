package co.edu.cesde.ga.exceptions;

public class GroupSubjectNoEncontradoException extends RuntimeException {

    public GroupSubjectNoEncontradoException(Long id) {
        super("No se encontro la relacion GroupSubject con id: " + id);
    }
}