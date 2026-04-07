package impl;

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
        if (isInValidStudent(student) || studentRepository.existByDocumentNumber(student.getDocumentNumber())) {
            ;
            return null;
        }
        return studentRepository.create(student);
    }

    @Override
    public boolean update(Student studentUpdate) {
        if (studentRepository.existByDocumentNumber(studentUpdate.getDocumentNumber())) {
            return false;
        }
        if (isInValidStudent(studentUpdate) || studentUpdate.getUserId() == null || studentUpdate.getUserId() <=0L){
            return false;
        }
        return studentRepository.update(studentUpdate);
    }


    @Override
    public Student findByid(Long studentId){
        if (studentId == null || studentId <= 0L) {
            return null;
        }
        return studentRepository.findByid(studentId);
    }


    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }


    @Override
    public boolean delete(Long studentId) {
        if (studentId == null || studentId <= 0L) {
            return false;
        }
       return studentRepository.delete(studentId);
    }

    public boolean isInValidStudent(Student student) {
        return student == null
              ||  isNotBlank(student.getDocumentNumber())
              ||  isNotBlank(student.getFirstName())
              ||  isNotBlank(student.getLastName())
              || isNotBlank(student.getBirthDate())
              ||  student.getStatus() == null;
    }

    private boolean isNotBlank(String value) {
        return value == null || value.trim().isBlank();
    }
}
