package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.model.Subject;
import co.edu.cesde.ga.repository.SubjectRepository;
import co.edu.cesde.ga.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subject create(Subject subject) {

        if (isInvalidSubject(subject)) {
            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findByCode(subject.getCode()) != null) {
            throw new RuntimeException("La materia ya existe");
        }

        return repository.create(subject);
    }

    @Override
    public boolean update(Subject updateSubject) {

        if (isInvalidSubject(updateSubject)
                || updateSubject.getSubjectId() == null
                || updateSubject.getSubjectId() <= 0L) {

            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findById(updateSubject.getSubjectId()) == null) {
            throw new RuntimeException("Materia no encontrada");
        }

        return repository.update(updateSubject);
    }

    @Override
    public Subject findById(Long subjectId) {

        if (subjectId == null || subjectId <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        Subject subject = repository.findById(subjectId);

        if (subject == null) {
            throw new RuntimeException("Materia no encontrada");
        }

        return subject;
    }

    @Override
    public Subject findByCode(String code) {

        if (code == null || code.isBlank()) {
            throw new RuntimeException("Codigo invalido");
        }

        Subject subject = repository.findByCode(code);

        if (subject == null) {
            throw new RuntimeException("Materia no encontrada");
        }

        return subject;
    }

    @Override
    public List<Subject> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long subjectId) {

        if (repository.findById(subjectId) == null) {
            throw new RuntimeException("Materia no encontrada");
        }

        return repository.delete(subjectId);
    }

    private boolean isInvalidSubject(Subject subject) {

        return subject == null
                || isBlank(subject.getCode())
                || isBlank(subject.getName())
                || subject.getCredits() == null
                || subject.getCredits() <= 0
                || subject.getProgramId() == null;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isBlank();
    }
}