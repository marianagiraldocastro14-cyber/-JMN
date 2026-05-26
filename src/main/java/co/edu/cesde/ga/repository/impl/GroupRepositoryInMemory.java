package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Group;
import co.edu.cesde.ga.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

public class GroupRepositoryInMemory implements GroupRepository {

    private final List<Group> groups;

    public GroupRepositoryInMemory() {
        this.groups = new ArrayList<>();
    }

    @Override
    public Group create(Group group) {

        if (group == null) {
            return null;
        }

        if (findByCode(group.getCode()) != null) {
            return null;
        }

        groups.add(group);

        return group;
    }

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(groups);
    }

    @Override
    public Group findByCode(String code) {

        if (code == null || code.isBlank()) {
            return null;
        }

        for (Group group : groups) {

            if (code.equals(group.getCode())) {
                return group;
            }
        }

        return null;
    }

    @Override
    public boolean update(Group groupUpdate) {

        if (groupUpdate == null) {
            return false;
        }

        for (int i = 0; i < groups.size(); i++) {

            if (groups.get(i).getCode().equals(groupUpdate.getCode())) {

                groups.set(i, groupUpdate);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(String groupCode) {

        Group group = findByCode(groupCode);

        if (group == null) {
            return false;
        }

        groups.remove(group);

        return true;
    }

    @Override
    public int count() {
        return groups.size();
    }
}