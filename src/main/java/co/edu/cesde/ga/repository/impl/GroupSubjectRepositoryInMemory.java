package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.GroupSubject;
import co.edu.cesde.ga.repository.GroupSubjectRepository;

import java.util.ArrayList;
import java.util.List;

public class GroupSubjectRepositoryInMemory implements GroupSubjectRepository {

    private final List<GroupSubject> groupSubjects;

    private Long nextId;

    public GroupSubjectRepositoryInMemory() {

        this.groupSubjects = new ArrayList<>();

        this.nextId = 1L;
    }

    @Override
    public GroupSubject create(GroupSubject groupSubject) {

        if (groupSubject == null) {
            return null;
        }

        groupSubject.setGroupSubjectId(nextId++);

        groupSubjects.add(groupSubject);

        return groupSubject;
    }

    @Override
    public List<GroupSubject> findAll() {
        return new ArrayList<>(groupSubjects);
    }

    @Override
    public GroupSubject findById(Long id) {

        if (id == null) {
            return null;
        }

        for (GroupSubject groupSubject : groupSubjects) {

            if (id.equals(groupSubject.getGroupSubjectId())) {
                return groupSubject;
            }
        }

        return null;
    }

    @Override
    public List<GroupSubject> findByGroupId(Long groupId) {

        List<GroupSubject> result = new ArrayList<>();

        if (groupId == null) {
            return result;
        }

        for (GroupSubject groupSubject : groupSubjects) {

            if (groupId.equals(groupSubject.getGroupId())) {
                result.add(groupSubject);
            }
        }

        return result;
    }

    @Override
    public boolean update(GroupSubject groupSubjectUpdate) {

        if (groupSubjectUpdate == null) {
            return false;
        }

        for (int i = 0; i < groupSubjects.size(); i++) {

            if (groupSubjects.get(i).getGroupSubjectId()
                    .equals(groupSubjectUpdate.getGroupSubjectId())) {

                groupSubjects.set(i, groupSubjectUpdate);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {

        GroupSubject groupSubject = findById(id);

        if (groupSubject == null) {
            return false;
        }

        groupSubjects.remove(groupSubject);

        return true;
    }

    @Override
    public int count() {
        return groupSubjects.size();
    }
}