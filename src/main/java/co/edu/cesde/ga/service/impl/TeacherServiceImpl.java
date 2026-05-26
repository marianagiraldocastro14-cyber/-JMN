package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.exceptions.TeacherDatosInvalidosException;
import co.edu.cesde.ga.exceptions.TeacherNoEncontradoException;
import co.edu.cesde.ga.model.Teacher;
import co.edu.cesde.ga.repository.TeacherRepository;
import co.edu.cesde.ga.service.TeacherService;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher create(Teacher teacher) {
        if (isInvalidTeacher(teacher)) {
            throw new TeacherDatosInvalidosException("Los datos del profesor son invalidos.");
        }
        if (teacherRepository.existsByDocumentNumber(teacher.getDocumentNumber())) {
            throw new TeacherDatosInvalidosException("Ya existe un profesor con ese numero de documento.");
        }
        return teacherRepository.create(teacher);
    }

    public boolean isInvalidTeacher(Teacher teacher) {
        return teacher == null
                || !isNotBlank(teacher.getDocumentNumber())
                || !isNotBlank(teacher.getFirstName())
                || !isNotBlank(teacher.getLastName())
                || !isNotBlank(teacher.getStatus());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isBlank();
    }

    @Override
    public boolean delete(Long teacherId) {
        if (teacherId == null || teacherId <= 0L) {
            throw new TeacherDatosInvalidosException("El ID del profesor es invalido.");
        }
        if (teacherRepository.findById(teacherId) == null) {
            throw new TeacherNoEncontradoException(teacherId);
        }
        return teacherRepository.delete(teacherId);
    }

    @Override
    public Teacher findById(Long teacherId) {
        if (teacherId == null || teacherId <= 0L) {
            throw new TeacherDatosInvalidosException("El ID del profesor es invalido.");
        }
        Teacher teacher = teacherRepository.findById(teacherId);
        if (teacher == null) {
            throw new TeacherNoEncontradoException(teacherId);
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public boolean update(Teacher teacherUpdate) {
        if (isInvalidTeacher(teacherUpdate) || teacherUpdate.getTeacherId() == null || teacherUpdate.getTeacherId() <= 0L) {
            throw new TeacherDatosInvalidosException("Los datos del profesor son invalidos para actualizar.");
        }
        Teacher currentTeacher = teacherRepository.findById(teacherUpdate.getTeacherId());
        if (currentTeacher == null) {
            throw new TeacherNoEncontradoException(teacherUpdate.getTeacherId());
        }
        Teacher teacherWithSameDocument = teacherRepository.findByDocumentNumber(teacherUpdate.getDocumentNumber());
        if (teacherWithSameDocument != null && !teacherWithSameDocument.getTeacherId().equals(teacherUpdate.getTeacherId())) {
            throw new TeacherDatosInvalidosException("Ya existe otro profesor con ese numero de documento.");
        }
        return teacherRepository.update(teacherUpdate);
    }
}
