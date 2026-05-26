package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Period;
import co.edu.cesde.ga.repository.PeriodRepository;

import java.util.ArrayList;
import java.util.List;

public class PeriodRepositoryInMemory implements PeriodRepository {

    private final List<Period> periods;

    private Long nextPeriodId;

    public PeriodRepositoryInMemory() {

        this.periods = new ArrayList<>();

        this.nextPeriodId = 1L;
    }

    @Override
    public Period create(Period period) {

        if (period == null) {
            return null;
        }

        period.setPeriodId(nextPeriodId++);

        periods.add(period);

        return period;
    }

    @Override
    public List<Period> findAll() {
        return new ArrayList<>(periods);
    }

    @Override
    public Period findById(Long periodId) {

        if (periodId == null) {
            return null;
        }

        for (Period period : periods) {

            if (periodId.equals(period.getPeriodId())) {
                return period;
            }
        }

        return null;
    }

    @Override
    public boolean update(Period updatePeriod) {

        if (updatePeriod == null) {
            return false;
        }

        for (int i = 0; i < periods.size(); i++) {

            if (periods.get(i).getPeriodId()
                    .equals(updatePeriod.getPeriodId())) {

                periods.set(i, updatePeriod);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(Long periodId) {

        Period period = findById(periodId);

        if (period == null) {
            return false;
        }

        periods.remove(period);

        return true;
    }

    @Override
    public int count() {
        return periods.size();
    }
}