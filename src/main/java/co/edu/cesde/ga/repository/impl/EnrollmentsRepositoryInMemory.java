package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Enrollments;
import co.edu.cesde.ga.repository.EnrollmentsRepository;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentsRepositoryInMemory implements EnrollmentsRepository {

    private final List<Enrollments> enrollments;
    private Long nextEnrollmentId;

    public EnrollmentsRepositoryInMemory() {
        this.enrollments = new ArrayList<>();
        this.nextEnrollmentId = 1L;
    }

    @Override
    public Enrollments create(Enrollments enrollment) {
        if (enrollment == null) {
            return null;
        }

        if (existsByStudentGroupAndPeriod(enrollment.getStudentId(), enrollment.getGroupId(), enrollment.getPeriodId())) {
            return null;
        }

        enrollment.setEnrollmentId(nextEnrollmentId++);
        enrollments.add(enrollment);
        return enrollment;
    }

    @Override
    public List<Enrollments> findAll() {
        return new ArrayList<>(enrollments);
    }

    @Override
    public Enrollments findById(Long enrollmentId) {
        if (enrollmentId == null) {
            return null;
        }

        for (Enrollments enrollment : enrollments) {
            if (enrollmentId.equals(enrollment.getEnrollmentId())) {
                return enrollment;
            }
        }
        return null;
    }

    @Override
    public Enrollments findByStudentGroupAndPeriod(String studentId, String groupId, String periodId) {
        if (studentId == null || groupId == null || periodId == null) {
            return null;
        }

        for (Enrollments enrollment : enrollments) {
            if (studentId.equals(enrollment.getStudentId())
                    && groupId.equals(enrollment.getGroupId())
                    && periodId.equals(enrollment.getPeriodId())) {
                return enrollment;
            }
        }
        return null;
    }

    @Override
    public boolean existsByStudentGroupAndPeriod(String studentId, String groupId, String periodId) {
        return findByStudentGroupAndPeriod(studentId, groupId, periodId) != null;
    }

    @Override
    public boolean update(Enrollments updatedEnrollment) {
        if (updatedEnrollment == null || updatedEnrollment.getEnrollmentId() == null) {
            return false;
        }

        for (Enrollments enrollment : enrollments) {
            if (!enrollment.getEnrollmentId().equals(updatedEnrollment.getEnrollmentId())
                    && enrollment.getStudentId().equals(updatedEnrollment.getStudentId())
                    && enrollment.getGroupId().equals(updatedEnrollment.getGroupId())
                    && enrollment.getPeriodId().equals(updatedEnrollment.getPeriodId())) {
                return false;
            }
        }

        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getEnrollmentId().equals(updatedEnrollment.getEnrollmentId())) {
                enrollments.set(i, updatedEnrollment);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long enrollmentId) {
        Enrollments enrollment = findById(enrollmentId);
        if (enrollment == null) {
            return false;
        }
        return enrollments.remove(enrollment);
    }

    @Override
    public int count() {
        return enrollments.size();
    }
}
