package co.edu.cesde.ga.service;

import co.edu.cesde.ga.model.Enrollments;

import java.util.List;

public interface EnrollmentsService {

    Enrollments create(Enrollments enrollment);

    boolean update(Enrollments updateEnrollment);

    boolean delete(Long enrollmentId);

    Enrollments findById(Long enrollmentId);

    List<Enrollments> findAll();

}