package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Student;
import java.util.List;

public interface StudentRepository {

    Student create(Student student);

    List<Student> findAll();

    Student findById(Long studentId);

    boolean existsByDocumentNumber(String documentNumber);

    Student findByDocumentNumber(String documentNumber);

    boolean update(Student updateStudent);

    boolean delete(Long studentId);

    int count();
}
