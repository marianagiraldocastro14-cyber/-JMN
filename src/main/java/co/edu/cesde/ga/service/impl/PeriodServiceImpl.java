package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.model.Period;
import co.edu.cesde.ga.repository.PeriodRepository;
import co.edu.cesde.ga.service.PeriodService;

import java.util.List;

public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository repository;

    public PeriodServiceImpl(PeriodRepository repository) {
        this.repository = repository;
    }

    @Override
    public Period create(Period period) {

        if (isInvalidPeriod(period)) {
            throw new RuntimeException("Datos invalidos");
        }

        return repository.create(period);
    }

    @Override
    public boolean update(Period updatePeriod) {

        if (isInvalidPeriod(updatePeriod)
                || updatePeriod.getPeriodId() == null
                || updatePeriod.getPeriodId() <= 0L) {

            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findById(updatePeriod.getPeriodId()) == null) {
            throw new RuntimeException("Periodo no encontrado");
        }

        return repository.update(updatePeriod);
    }

    @Override
    public Period findById(Long periodId) {

        if (periodId == null || periodId <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        Period period = repository.findById(periodId);

        if (period == null) {
            throw new RuntimeException("Periodo no encontrado");
        }

        return period;
    }

    @Override
    public List<Period> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long periodId) {

        if (repository.findById(periodId) == null) {
            throw new RuntimeException("Periodo no encontrado");
        }

        return repository.delete(periodId);
    }

    private boolean isInvalidPeriod(Period period) {

        return period == null
                || isBlank(period.getStartDate())
                || isBlank(period.getEndDate());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isBlank();
    }
}