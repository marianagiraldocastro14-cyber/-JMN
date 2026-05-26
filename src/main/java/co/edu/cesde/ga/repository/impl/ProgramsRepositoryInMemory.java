package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Programs;
import co.edu.cesde.ga.repository.ProgramsRepository;

import java.util.ArrayList;
import java.util.List;

public class ProgramsRepositoryInMemory implements ProgramsRepository {

    private final List<Programs> programs;
    private Long nextProgramId;

    public ProgramsRepositoryInMemory() {
        this.programs = new ArrayList<>();
        this.nextProgramId = 1L;
    }

    @Override
    public Programs create(Programs program) {
        if (program == null) {
            return null;
        }

        if (existsByCode(program.getCode())) {
            return null;
        }

        program.setProgramId(nextProgramId++);
        programs.add(program);
        return program;
    }

    @Override
    public List<Programs> findAll() {
        return new ArrayList<>(programs);
    }

    @Override
    public Programs findById(Long programId) {
        if (programId == null) {
            return null;
        }

        for (Programs program : programs) {
            if (programId.equals(program.getProgramId())) {
                return program;
            }
        }
        return null;
    }

    @Override
    public Programs findByCode(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }

        for (Programs program : programs) {
            if (code.equalsIgnoreCase(program.getCode())) {
                return program;
            }
        }
        return null;
    }

    @Override
    public boolean existsByCode(String code) {
        return findByCode(code) != null;
    }

    @Override
    public boolean update(Programs updatedProgram) {
        if (updatedProgram == null || updatedProgram.getProgramId() == null) {
            return false;
        }

        for (Programs program : programs) {
            if (!program.getProgramId().equals(updatedProgram.getProgramId())
                    && program.getCode().equalsIgnoreCase(updatedProgram.getCode())) {
                return false;
            }
        }

        for (int i = 0; i < programs.size(); i++) {
            if (programs.get(i).getProgramId().equals(updatedProgram.getProgramId())) {
                programs.set(i, updatedProgram);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long programId) {
        Programs program = findById(programId);
        if (program == null) {
            return false;
        }
        return programs.remove(program);
    }

    @Override
    public int count() {
        return programs.size();
    }
}
