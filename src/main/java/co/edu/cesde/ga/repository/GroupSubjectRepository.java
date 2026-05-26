package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.GroupSubject;

import java.util.List;

public interface GroupSubjectRepository {

    GroupSubject create(GroupSubject groupSubject);

    List<GroupSubject> findAll();

    GroupSubject findById(Long id);

    List<GroupSubject> findByGroupId(Long groupId);

    boolean update(GroupSubject groupSubjectUpdate);

    boolean delete(Long id);

    int count();
}