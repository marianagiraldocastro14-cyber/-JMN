package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Subject;

import java.util.List;

public interface SubjectRepository {

    Subject create(Subject subject);

    List<Subject> findAll();

    Subject findById(Long subjectId);

    Subject findByCode(String code);

    boolean update(Subject updateSubject);

    boolean delete(Long subjectId);

    int count();
}