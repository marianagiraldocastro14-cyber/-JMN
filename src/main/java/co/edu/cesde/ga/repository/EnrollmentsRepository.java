package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Enrollments;
import java.util.List;

public interface EnrollmentsRepository {

    Enrollments create (Enrollments enrollments);

    List <Enrollments> findAll();

    Enrollments findById (Long enrollmentId);

    Enrollments findByStudentGroupAndPeriod(String studentId, String groupId, String periodId);

    boolean existsByStudentGroupAndPeriod(String studentId, String groupId, String periodId);

    boolean update (Enrollments updateEnrollments);

    boolean delete (Long enrollmentId);

    int count();
}
