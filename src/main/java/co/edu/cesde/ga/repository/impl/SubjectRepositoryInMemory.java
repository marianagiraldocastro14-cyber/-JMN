package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Subject;
import co.edu.cesde.ga.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryInMemory implements SubjectRepository {

    private final List<Subject> subjects;

    private Long nextSubjectId;

    public SubjectRepositoryInMemory() {

        this.subjects = new ArrayList<>();

        this.nextSubjectId = 1L;
    }

    @Override
    public Subject create(Subject subject) {

        if (subject == null) {
            return null;
        }

        if (findByCode(subject.getCode()) != null) {
            return null;
        }

        subject.setSubjectId(nextSubjectId++);

        subjects.add(subject);

        return subject;
    }

    @Override
    public List<Subject> findAll() {
        return new ArrayList<>(subjects);
    }

    @Override
    public Subject findById(Long subjectId) {

        if (subjectId == null) {
            return null;
        }

        for (Subject subject : subjects) {

            if (subjectId.equals(subject.getSubjectId())) {
                return subject;
            }
        }

        return null;
    }

    @Override
    public Subject findByCode(String code) {

        if (code == null || code.isBlank()) {
            return null;
        }

        for (Subject subject : subjects) {

            if (code.equalsIgnoreCase(subject.getCode())) {
                return subject;
            }
        }

        return null;
    }

    @Override
    public boolean update(Subject updateSubject) {

        if (updateSubject == null) {
            return false;
        }

        for (int i = 0; i < subjects.size(); i++) {

            if (subjects.get(i).getSubjectId()
                    .equals(updateSubject.getSubjectId())) {

                subjects.set(i, updateSubject);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(Long subjectId) {

        Subject subject = findById(subjectId);

        if (subject == null) {
            return false;
        }

        subjects.remove(subject);

        return true;
    }

    @Override
    public int count() {
        return subjects.size();
    }
}