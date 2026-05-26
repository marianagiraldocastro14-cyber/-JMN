package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Programs;
import java.util.List;

public interface ProgramsRepository {

    Programs create (Programs programs);

    List <Programs> findAll();

    Programs findById (Long programsId);

    Programs findByCode(String code);

    boolean existsByCode(String code);

    boolean update (Programs updatePrograms);

    boolean delete (Long programsId);

    int count();

}
