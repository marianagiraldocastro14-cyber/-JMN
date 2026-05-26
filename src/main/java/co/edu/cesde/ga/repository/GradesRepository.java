package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Grades;

import java.util.List;

public interface GradesRepository {

    Grades create(Grades grade);

    List<Grades> findAll();

    Grades findById(Long gradeId);

    List<Grades> findByStudentId(Long studentId);

    boolean update(Grades gradeUpdate);

    boolean delete(Long gradeId);

    int count();
}