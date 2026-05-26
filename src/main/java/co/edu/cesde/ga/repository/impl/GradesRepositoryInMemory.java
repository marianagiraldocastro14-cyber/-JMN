package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Grades;
import co.edu.cesde.ga.repository.GradesRepository;

import java.util.ArrayList;
import java.util.List;

public class GradesRepositoryInMemory implements GradesRepository {

    private final List<Grades> grades;

    private Long nextGradeId;

    public GradesRepositoryInMemory() {

        this.grades = new ArrayList<>();

        this.nextGradeId = 1L;
    }

    @Override
    public Grades create(Grades grade) {

        if (grade == null) {
            return null;
        }

        grade.setGroupSubjectId(nextGradeId++);

        grades.add(grade);

        return grade;
    }

    @Override
    public List<Grades> findAll() {
        return new ArrayList<>(grades);
    }

    @Override
    public Grades findById(Long gradeId) {

        if (gradeId == null) {
            return null;
        }

        for (Grades grade : grades) {

            if (gradeId.equals(grade.getGroupSubjectId())) {
                return grade;
            }
        }

        return null;
    }

    @Override
    public List<Grades> findByStudentId(Long studentId) {

        List<Grades> result = new ArrayList<>();

        if (studentId == null) {
            return result;
        }

        for (Grades grade : grades) {

            if (studentId.equals(grade.getStudentId())) {
                result.add(grade);
            }
        }

        return result;
    }

    @Override
    public boolean update(Grades gradeUpdate) {

        if (gradeUpdate == null) {
            return false;
        }

        for (int i = 0; i < grades.size(); i++) {

            if (grades.get(i).getGroupSubjectId()
                    .equals(gradeUpdate.getGroupSubjectId())) {

                grades.set(i, gradeUpdate);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(Long gradeId) {

        Grades grade = findById(gradeId);

        if (grade == null) {
            return false;
        }

        grades.remove(grade);

        return true;
    }

    @Override
    public int count() {
        return grades.size();
    }
}