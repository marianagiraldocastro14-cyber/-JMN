package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.exceptions.ProgramDatosInvalidosException;
import co.edu.cesde.ga.exceptions.ProgramNoEncontradoException;
import co.edu.cesde.ga.model.Programs;
import co.edu.cesde.ga.repository.ProgramsRepository;
import co.edu.cesde.ga.service.ProgramsService;
import java.util.List;

public class ProgramsServiceImpl implements ProgramsService {

    private final ProgramsRepository programsRepository;

    public ProgramsServiceImpl(ProgramsRepository programsRepository) {
        this.programsRepository = programsRepository;
    }

    @Override
    public Programs create(Programs program) {
        if (isInvalidProgram(program)) {
            throw new ProgramDatosInvalidosException("Los datos del programa son invalidos.");
        }
        if (programsRepository.existsByCode(program.getCode())) {
            throw new ProgramDatosInvalidosException("Ya existe un programa con ese codigo.");
        }
        return programsRepository.create(program);
    }

    public boolean isInvalidProgram(Programs program) {
        return program == null
                || !isNotBlank(program.getCode())
                || !isNotBlank(program.getName());
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isBlank();
    }

    @Override
    public boolean delete(Long programId) {
        if (programId == null || programId <= 0L) {
            throw new ProgramDatosInvalidosException("El ID del programa es invalido.");
        }
        if (programsRepository.findById(programId) == null) {
            throw new ProgramNoEncontradoException(programId);
        }
        return programsRepository.delete(programId);
    }

    @Override
    public Programs findById(Long programId) {
        if (programId == null || programId <= 0L) {
            throw new ProgramDatosInvalidosException("El ID del programa es invalido.");
        }
        Programs program = programsRepository.findById(programId);
        if (program == null) {
            throw new ProgramNoEncontradoException(programId);
        }
        return program;
    }

    @Override
    public List<Programs> findAll() {
        return programsRepository.findAll();
    }

    @Override
    public boolean update(Programs programUpdate) {
        if (isInvalidProgram(programUpdate) || programUpdate.getProgramId() == null || programUpdate.getProgramId() <= 0L) {
            throw new ProgramDatosInvalidosException("Los datos del programa son invalidos para actualizar.");
        }
        Programs currentProgram = programsRepository.findById(programUpdate.getProgramId());
        if (currentProgram == null) {
            throw new ProgramNoEncontradoException(programUpdate.getProgramId());
        }
        Programs programWithSameCode = programsRepository.findByCode(programUpdate.getCode());
        if (programWithSameCode != null && !programWithSameCode.getProgramId().equals(programUpdate.getProgramId())) {
            throw new ProgramDatosInvalidosException("Ya existe otro programa con ese codigo.");
        }
        return programsRepository.update(programUpdate);
    }
}
