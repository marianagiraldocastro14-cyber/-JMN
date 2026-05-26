package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.exceptions.StudentDatosInvalidosException;
import co.edu.cesde.ga.exceptions.StudentNoEncontradoException;
import co.edu.cesde.ga.model.Student;
import co.edu.cesde.ga.repository.StudentRepository;
import co.edu.cesde.ga.service.StudentService;

import java.util.List;

public class StudentServiceimpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceimpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student create (Student student) {
        if (isInvalidStudent(student)) {
            throw new StudentDatosInvalidosException("Los datos del estudiante son invalidos.");
        }
        if (studentRepository.existsByDocumentNumber(student.getDocumentNumber())) {
            throw new StudentDatosInvalidosException("Ya existe un estudiante con ese numero de documento.");
        }
        return studentRepository.create(student);
    }

    @Override
    public boolean update(Student studentUpdate) {
        if (isInvalidStudent(studentUpdate) || studentUpdate.getStudentId() == null || studentUpdate.getStudentId() <=0L){
            throw new StudentDatosInvalidosException("Los datos del estudiante son invalidos para actualizar.");
        }
        Student currentStudent = studentRepository.findById(studentUpdate.getStudentId());
        if (currentStudent == null) {
            throw new StudentNoEncontradoException(studentUpdate.getStudentId());
        }
        Student studentWithSameDocument = studentRepository.findByDocumentNumber(studentUpdate.getDocumentNumber());
        if (studentWithSameDocument != null && !studentWithSameDocument.getStudentId().equals(studentUpdate.getStudentId())) {
            throw new StudentDatosInvalidosException("Ya existe otro estudiante con ese numero de documento.");
        }
        return studentRepository.update(studentUpdate);
    }

    @Override
    public Student findById(Long studentId){
        if (studentId == null || studentId <= 0L) {
            throw new StudentDatosInvalidosException("El ID del estudiante es invalido.");
        }
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new StudentNoEncontradoException(studentId);
        }
        return student;
    }


    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }


    @Override
    public boolean delete(Long studentId) {
        if (studentId == null || studentId <= 0L) {
            throw new StudentDatosInvalidosException("El ID del estudiante es invalido.");
        }
        if (studentRepository.findById(studentId) == null) {
            throw new StudentNoEncontradoException(studentId);
        }
       return studentRepository.delete(studentId);
    }

    public boolean isInvalidStudent(Student student) {
        return student == null
              ||  isBlank(student.getDocumentNumber())
              ||  isBlank(student.getFirstName())
              ||  isBlank(student.getLastName())
              ||  isBlank(student.getBirthDate())
              ||  isBlank(student.getStatus());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isBlank();
    }
}
