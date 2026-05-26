package co.edu.cesde.ga.exceptions;

public class SubjectNoEncontradoException extends RuntimeException {

    public SubjectNoEncontradoException(Long subjectId) {
        super("No se encontro la materia con id: " + subjectId);
    }
}