package co.edu.cesde.ga.service;

import co.edu.cesde.ga.model.Group;
import java.util.List;

public interface GroupService {

    Group create(Group group);

    boolean update(Group updateGroup);

    boolean delete(String groupCode);

    Group findByCode(String groupCode);

    List<Group> findAll();
}