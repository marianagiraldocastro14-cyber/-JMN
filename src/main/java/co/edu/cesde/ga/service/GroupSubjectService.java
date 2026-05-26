package co.edu.cesde.ga.service;

import co.edu.cesde.ga.model.GroupSubject;

import java.util.List;

public interface GroupSubjectService {

    GroupSubject create(GroupSubject groupSubject);

    boolean update(GroupSubject groupSubjectUpdate);

    GroupSubject findById(Long id);

    List<GroupSubject> findAll();

    List<GroupSubject> findByGroupId(Long groupId);

    boolean delete(Long id);
}