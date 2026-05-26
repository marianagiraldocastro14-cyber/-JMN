package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Period;

import java.util.List;

public interface PeriodRepository {

    Period create(Period period);

    List<Period> findAll();

    Period findById(Long periodId);

    boolean update(Period updatePeriod);

    boolean delete(Long periodId);

    int count();
}