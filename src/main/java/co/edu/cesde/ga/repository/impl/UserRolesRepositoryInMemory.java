package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.UserRoles;
import co.edu.cesde.ga.repository.UserRolesRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRolesRepositoryInMemory implements UserRolesRepository {

    private final List<UserRoles> userRoles;

    private Long nextId;

    public UserRolesRepositoryInMemory() {

        this.userRoles = new ArrayList<>();

        this.nextId = 1L;
    }

    @Override
    public UserRoles create(UserRoles userRole) {

        if (userRole == null) {
            return null;
        }

        userRole.setRolesId(nextId++);

        userRoles.add(userRole);

        return userRole;
    }

    @Override
    public List<UserRoles> findAll() {
        return new ArrayList<>(userRoles);
    }

    @Override
    public UserRoles findById(Long id) {

        if (id == null) {
            return null;
        }

        for (UserRoles userRole : userRoles) {

            if (id.equals(userRole.getRolesId())) {
                return userRole;
            }
        }

        return null;
    }

    @Override
    public List<UserRoles> findByUserId(Long userId) {

        List<UserRoles> result = new ArrayList<>();

        if (userId == null) {
            return result;
        }

        for (UserRoles userRole : userRoles) {

            if (userId.equals(userRole.getUserId())) {
                result.add(userRole);
            }
        }

        return result;
    }

    @Override
    public boolean update(UserRoles userRoleUpdate) {

        if (userRoleUpdate == null) {
            return false;
        }

        for (int i = 0; i < userRoles.size(); i++) {

            if (userRoles.get(i).getRolesId()
                    .equals(userRoleUpdate.getRolesId())) {

                userRoles.set(i, userRoleUpdate);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {

        UserRoles userRole = findById(id);

        if (userRole == null) {
            return false;
        }

        userRoles.remove(userRole);

        return true;
    }

    @Override
    public int count() {
        return userRoles.size();
    }
}