package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Student;
import co.edu.cesde.ga.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class StudentRepositoryInMemory implements StudentRepository {

    private final List<Student> students;
    private Long nextStudentId;

    public StudentRepositoryInMemory() {
        this.students = new ArrayList<>();
        Long nextStudentId = 1L;
    }

    @Override
    public Student create(Student student) {

        if (student == null) {
            return null;
        }

        if (existsBydocumentNumber(student.getDocumentNumber())) {
            return null;
        }
        student.setStudentId(nextStudentId++);
        students.add(student);
        return student;
    }

    @Override
    public boolean existByDocumentNumber(String documentNumber){

        if (documentNumber == null || documentNumber.isEmpty()){
            return false;
        }

        return findByDocumentNumber(documentNumber) != null;

    }

    @Override
    public Student findByDocumentNumber(String documentNumber){
        if (documentNumber == null || documentNumber.isEmpty()){
            return null;
        }

        for (Student student : students){
            if (documentNumber.equals(student.getDocumentNumber())){
                return student;
            }
        }

        return null;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }
    @Override
    public Student findByid(Long studentid) {
        if (studentid == null) {
            return null;
        }
        for (Student student : students) {
            if (studentid.equals(student.getUserId())) {
                return student;
            }
        }
        return null;
    }

    @Override
    public boolean delete (Long studentId) {
        Student student = findByid((studentId));
        if (student == null) return false;
        students.remove(student);
        return true;
    }

    @Override
    public int count() {
        return students.size();

    }

    @Override
    public boolean update(Student updateStudent) {
        if (updateStudent == null) return false;
        if (findByDocumentNumber(updateStudent.getDocumentNumber()) != null) {
            return false;
        }

        for (int i=0; i<students.size(); i++) {

            if (updateStudent.getUserId().equals(students.get(i).getUserId())) {
                students.set(i, updateStudent);
                return true;
            }
        }
        return false;
    }
}