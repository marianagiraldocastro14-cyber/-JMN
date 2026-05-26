package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Teacher;
import co.edu.cesde.ga.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryInMemory implements TeacherRepository {

    private final List<Teacher> teachers;
    private Long nextTeacherId;

    public TeacherRepositoryInMemory() {
        this.teachers = new ArrayList<>();
        this.nextTeacherId = 1L;
    }

    @Override
    public Teacher create(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        if (existsByDocumentNumber(teacher.getDocumentNumber())) {
            return null;
        }

        teacher.setTeacherId(nextTeacherId++);
        teachers.add(teacher);
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        return new ArrayList<>(teachers);
    }

    @Override
    public Teacher findById(Long teacherId) {
        if (teacherId == null) {
            return null;
        }

        for (Teacher teacher : teachers) {
            if (teacherId.equals(teacher.getTeacherId())) {
                return teacher;
            }
        }
        return null;
    }

    @Override
    public Teacher findByDocumentNumber(String documentNumber) {
        if (documentNumber == null || documentNumber.isBlank()) {
            return null;
        }

        for (Teacher teacher : teachers) {
            if (documentNumber.equals(teacher.getDocumentNumber())) {
                return teacher;
            }
        }
        return null;
    }

    @Override
    public boolean update(Teacher updatedTeacher) {
        if (updatedTeacher == null || updatedTeacher.getTeacherId() == null) {
            return false;
        }

        for (Teacher teacher : teachers) {
            if (!teacher.getTeacherId().equals(updatedTeacher.getTeacherId())
                    && teacher.getDocumentNumber().equals(updatedTeacher.getDocumentNumber())) {
                return false;
            }
        }

        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getTeacherId().equals(updatedTeacher.getTeacherId())) {
                teachers.set(i, updatedTeacher);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long teacherId) {
        Teacher teacher = findById(teacherId);
        if (teacher == null) {
            return false;
        }
        return teachers.remove(teacher);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return findByDocumentNumber(documentNumber) != null;
    }

    @Override
    public int count() {
        return teachers.size();
    }
}
