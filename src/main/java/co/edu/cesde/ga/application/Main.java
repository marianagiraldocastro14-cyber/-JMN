package co.edu.cesde.ga.application;

import co.edu.cesde.ga.model.Student;
import co.edu.cesde.ga.repository.StudentRepository;
import co.edu.cesde.ga.repository.impl.StudentRepositoryInMemory;
import co.edu.cesde.ga.service.StudentService;
import impl.StudentServiceimpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepositoryInMemory() {
            @Override
            public List<Student> findAlL() {
                return List.of();
            }

            @Override
            public boolean existsBydocumentNumber(String documentNumber) {
                return false;
            }
        };
        StudentService studentService = new StudentServiceimpl(studentRepository);

    }

    Student student = new Student(null, "25/06/2006", "S001", "1234567", "87654", "Mariana", "Giraldo", "Activo" );

}