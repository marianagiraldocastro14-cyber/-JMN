package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.model.Grades;
import co.edu.cesde.ga.repository.GradesRepository;
import co.edu.cesde.ga.service.GradesService;

import java.util.List;

public class GradesServiceImpl implements GradesService {

    private final GradesRepository repository;

    public GradesServiceImpl(GradesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Grades create(Grades grade) {

        if (isInvalidGrade(grade)) {
            throw new RuntimeException("Datos invalidos");
        }

        return repository.create(grade);
    }

    @Override
    public boolean update(Grades gradeUpdate) {

        if (isInvalidGrade(gradeUpdate)
                || gradeUpdate.getGroupSubjectId() == null
                || gradeUpdate.getGroupSubjectId() <= 0L) {

            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findById(gradeUpdate.getGroupSubjectId()) == null) {
            throw new RuntimeException("Nota no encontrada");
        }

        return repository.update(gradeUpdate);
    }

    @Override
    public Grades findById(Long gradeId) {

        if (gradeId == null || gradeId <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        Grades grade = repository.findById(gradeId);

        if (grade == null) {
            throw new RuntimeException("Nota no encontrada");
        }

        return grade;
    }

    @Override
    public List<Grades> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Grades> findByStudentId(Long studentId) {

        if (studentId == null || studentId <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        return repository.findByStudentId(studentId);
    }

    @Override
    public boolean delete(Long gradeId) {

        if (repository.findById(gradeId) == null) {
            throw new RuntimeException("Nota no encontrada");
        }

        return repository.delete(gradeId);
    }

    private boolean isInvalidGrade(Grades grade) {

        return grade == null
                || grade.getStudentId() == null
                || grade.getGroupSubjectId() == null;
        }
    }