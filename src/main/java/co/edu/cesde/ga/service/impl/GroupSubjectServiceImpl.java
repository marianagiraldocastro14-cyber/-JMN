package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.model.GroupSubject;
import co.edu.cesde.ga.repository.GroupSubjectRepository;
import co.edu.cesde.ga.service.GroupSubjectService;

import java.util.List;

public class GroupSubjectServiceImpl implements GroupSubjectService {

    private final GroupSubjectRepository repository;

    public GroupSubjectServiceImpl(GroupSubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public GroupSubject create(GroupSubject groupSubject) {

        if (isInvalidGroupSubject(groupSubject)) {
            throw new RuntimeException("Datos invalidos");
        }

        return repository.create(groupSubject);
    }

    @Override
    public boolean update(GroupSubject groupSubjectUpdate) {

        if (isInvalidGroupSubject(groupSubjectUpdate)
                || groupSubjectUpdate.getGroupSubjectId() == null
                || groupSubjectUpdate.getGroupSubjectId() <= 0L) {

            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findById(groupSubjectUpdate.getGroupSubjectId()) == null) {
            throw new RuntimeException("Relacion no encontrada");
        }

        return repository.update(groupSubjectUpdate);
    }

    @Override
    public GroupSubject findById(Long id) {

        if (id == null || id <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        GroupSubject groupSubject = repository.findById(id);

        if (groupSubject == null) {
            throw new RuntimeException("Relacion no encontrada");
        }

        return groupSubject;
    }

    @Override
    public List<GroupSubject> findAll() {
        return repository.findAll();
    }

    @Override
    public List<GroupSubject> findByGroupId(Long groupId) {

        if (groupId == null || groupId <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        return repository.findByGroupId(groupId);
    }

    @Override
    public boolean delete(Long id) {

        if (repository.findById(id) == null) {
            throw new RuntimeException("Relacion no encontrada");
        }

        return repository.delete(id);
    }

    private boolean isInvalidGroupSubject(GroupSubject groupSubject) {

        return groupSubject == null
                || groupSubject.getGroupId() == null
                || groupSubject.getSubjectId() == null
                || groupSubject.getTeacherId() == null;
    }
}