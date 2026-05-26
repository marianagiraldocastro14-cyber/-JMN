package co.edu.cesde.ga.service;

import co.edu.cesde.ga.model.Period;

import java.util.List;

public interface PeriodService {

    Period create(Period period);

    boolean update(Period updatePeriod);

    Period findById(Long periodId);

    List<Period> findAll();

    boolean delete(Long periodId);
}