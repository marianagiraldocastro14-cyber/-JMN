package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Student;

import java.util.List;

import co.edu.cesde.ga.model.Student;
import java.util.List;

    public interface StudentRepository {
        Student create(Student student);

        List<Student> findAlL();

        Student findByid(Long studentId);

        Student findByDocumentNumber(String documentNumber);

        boolean update(Student updateStudent);

        boolean delete(Long studentId);

        boolean existsBydocumentNumber(String documentNumber);
    }


