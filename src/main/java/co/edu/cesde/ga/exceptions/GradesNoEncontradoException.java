package co.edu.cesde.ga.exceptions;

public class GradesNoEncontradoException extends RuntimeException {

    public GradesNoEncontradoException(Long gradeId) {
        super("No se encontro la nota con id: " + gradeId);
    }
}