package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Teacher;
import java.util.List;

public interface TeacherRepository {

    Teacher create (Teacher teacher);

    List <Teacher> findAll();

    Teacher findById (Long teacherId);

    Teacher findByDocumentNumber(String documentNumber);

    boolean update (Teacher updateTeacher);

    boolean delete (Long teacherId);

    boolean existsByDocumentNumber(String documentNumber);

    int count();
}