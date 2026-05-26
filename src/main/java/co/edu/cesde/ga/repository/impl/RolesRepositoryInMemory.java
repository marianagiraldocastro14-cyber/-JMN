package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Roles;
import co.edu.cesde.ga.repository.RolesRepository;

import java.util.ArrayList;
import java.util.List;

public class RolesRepositoryInMemory implements RolesRepository {

    private final List<Roles> roles;

    private Long nextRoleId;

    public RolesRepositoryInMemory() {

        this.roles = new ArrayList<>();

        this.nextRoleId = 1L;
    }

    @Override
    public Roles create(Roles role) {

        if (role == null) {
            return null;
        }

        role.setRoleId(nextRoleId++);

        roles.add(role);

        return role;
    }

    @Override
    public List<Roles> findAll() {
        return new ArrayList<>(roles);
    }

    @Override
    public Roles findById(Long roleId) {

        if (roleId == null) {
            return null;
        }

        for (Roles role : roles) {

            if (roleId.equals(role.getRoleId())) {
                return role;
            }
        }

        return null;
    }

    @Override
    public Roles findByName(String name) {

        if (name == null || name.isBlank()) {
            return null;
        }

        for (Roles role : roles) {

            if (name.equalsIgnoreCase(role.getName())) {
                return role;
            }
        }

        return null;
    }

    @Override
    public boolean update(Roles roleUpdate) {

        if (roleUpdate == null) {
            return false;
        }

        for (int i = 0; i < roles.size(); i++) {

            if (roles.get(i).getRoleId()
                    .equals(roleUpdate.getRoleId())) {

                roles.set(i, roleUpdate);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(Long roleId) {

        Roles role = findById(roleId);

        if (role == null) {
            return false;
        }

        roles.remove(role);

        return true;
    }

    @Override
    public int count() {
        return roles.size();
    }
}