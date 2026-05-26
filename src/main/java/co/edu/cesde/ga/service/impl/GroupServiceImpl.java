package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.model.Group;
import co.edu.cesde.ga.repository.GroupRepository;
import co.edu.cesde.ga.service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group create(Group group) {

        if (isInvalidGroup(group)) {
            throw new RuntimeException("Datos invalidos");
        }

        if (groupRepository.findByCode(group.getCode()) != null) {
            throw new RuntimeException("Ya existe un grupo con ese codigo");
        }

        return groupRepository.create(group);
    }

    @Override
    public boolean update(Group groupUpdate) {

        if (isInvalidGroup(groupUpdate)) {
            throw new RuntimeException("Datos invalidos");
        }

        if (groupRepository.findByCode(groupUpdate.getCode()) == null) {
            throw new RuntimeException("Grupo no encontrado");
        }

        return groupRepository.update(groupUpdate);
    }

    @Override
    public Group findByCode(String code) {

        if (code == null || code.isBlank()) {
            throw new RuntimeException("Codigo invalido");
        }

        Group group = groupRepository.findByCode(code);

        if (group == null) {
            throw new RuntimeException("Grupo no encontrado");
        }

        return group;
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public boolean delete(String code) {

        if (groupRepository.findByCode(code) == null) {
            throw new RuntimeException("Grupo no encontrado");
        }

        return groupRepository.delete(code);
    }

    private boolean isInvalidGroup(Group group) {

        return group == null
                || isBlank(group.getCode());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isBlank();
    }
}