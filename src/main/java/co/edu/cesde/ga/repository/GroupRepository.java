package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Group;

import java.util.List;

public interface GroupRepository {

    Group create(Group group);

    List<Group> findAll();

    Group findByCode(String code);

    boolean update(Group groupUpdate);

    boolean delete(String groupCode);

    int count();
}