package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.exceptions.EnrollmentDatosInvalidosException;
import co.edu.cesde.ga.exceptions.EnrollmentNoEncontradoException;
import co.edu.cesde.ga.model.Enrollments;
import co.edu.cesde.ga.repository.EnrollmentsRepository;
import co.edu.cesde.ga.service.EnrollmentsService;
import java.util.List;

public class EnrollmentsServiceImpl implements EnrollmentsService {

    private final EnrollmentsRepository enrollmentsRepository;

    public EnrollmentsServiceImpl(EnrollmentsRepository enrollmentsRepository) {
        this.enrollmentsRepository = enrollmentsRepository;
    }

    @Override
    public Enrollments create(Enrollments enrollment) {
        if (isInvalidEnrollment(enrollment)) {
            throw new EnrollmentDatosInvalidosException("Los datos de la inscripcion son invalidos.");
        }
        if (enrollmentsRepository.existsByStudentGroupAndPeriod(enrollment.getStudentId(), enrollment.getGroupId(), enrollment.getPeriodId())) {
            throw new EnrollmentDatosInvalidosException("Ya existe una inscripcion para ese estudiante, grupo y periodo.");
        }
        return enrollmentsRepository.create(enrollment);
    }

    public boolean isInvalidEnrollment(Enrollments enrollment) {
        return enrollment == null
                || !isNotBlank(enrollment.getStudentId())
                || !isNotBlank(enrollment.getGroupId())
                || !isNotBlank(enrollment.getPeriodId())
                || !isNotBlank(enrollment.getStatus());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isBlank();
    }

    @Override
    public boolean delete(Long enrollmentId) {
        if (enrollmentId == null || enrollmentId <= 0L) {
            throw new EnrollmentDatosInvalidosException("El ID de la inscripcion es invalido.");
        }
        if (enrollmentsRepository.findById(enrollmentId) == null) {
            throw new EnrollmentNoEncontradoException(enrollmentId);
        }
        return enrollmentsRepository.delete(enrollmentId);
    }

    @Override
    public Enrollments findById(Long enrollmentId) {
        if (enrollmentId == null || enrollmentId <= 0L) {
            throw new EnrollmentDatosInvalidosException("El ID de la inscripcion es invalido.");
        }
        Enrollments enrollment = enrollmentsRepository.findById(enrollmentId);
        if (enrollment == null) {
            throw new EnrollmentNoEncontradoException(enrollmentId);
        }
        return enrollment;
    }

    @Override
    public List<Enrollments> findAll() {
        return enrollmentsRepository.findAll();
    }

    @Override
    public boolean update(Enrollments enrollmentUpdate) {
        if (isInvalidEnrollment(enrollmentUpdate) || enrollmentUpdate.getEnrollmentId() == null || enrollmentUpdate.getEnrollmentId() <= 0L) {
            throw new EnrollmentDatosInvalidosException("Los datos de la inscripcion son invalidos para actualizar.");
        }
        Enrollments currentEnrollment = enrollmentsRepository.findById(enrollmentUpdate.getEnrollmentId());
        if (currentEnrollment == null) {
            throw new EnrollmentNoEncontradoException(enrollmentUpdate.getEnrollmentId());
        }
        Enrollments duplicatedEnrollment = enrollmentsRepository.findByStudentGroupAndPeriod(
                enrollmentUpdate.getStudentId(),
                enrollmentUpdate.getGroupId(),
                enrollmentUpdate.getPeriodId()
        );
        if (duplicatedEnrollment != null && !duplicatedEnrollment.getEnrollmentId().equals(enrollmentUpdate.getEnrollmentId())) {
            throw new EnrollmentDatosInvalidosException("Ya existe otra inscripcion para ese estudiante, grupo y periodo.");
        }
        return enrollmentsRepository.update(enrollmentUpdate);
    }
}
