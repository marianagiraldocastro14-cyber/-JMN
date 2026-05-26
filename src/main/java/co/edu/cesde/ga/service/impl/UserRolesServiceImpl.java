package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.model.UserRoles;
import co.edu.cesde.ga.repository.UserRolesRepository;
import co.edu.cesde.ga.service.UserRolesService;

import java.util.List;

public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository repository;

    public UserRolesServiceImpl(UserRolesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserRoles> getAll() {
        return List.of();
    }

    @Override
    public UserRoles create(UserRoles userRole) {

        if (isInvalidUserRole(userRole)) {
            throw new RuntimeException("Datos invalidos");
        }

        return repository.create(userRole);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean update(UserRoles userRoleUpdate) {

        if (isInvalidUserRole(userRoleUpdate)
                || userRoleUpdate.getRoleId() == null
                || userRoleUpdate.getRoleId() <= 0L) {

            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findById(userRoleUpdate.getRolesId()) == null) {
            throw new RuntimeException("Relacion no encontrada");
        }

        return repository.update(userRoleUpdate);
    }

    @Override
    public UserRoles findById(Long id) {

        if (id == null || id <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        UserRoles userRole = repository.findById(id);

        if (userRole == null) {
            throw new RuntimeException("Relacion no encontrada");
        }

        return userRole;
    }

    @Override
    public List<UserRoles> findByUserId(Long userId) {

        if (userId == null || userId <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        return repository.findByUserId(userId);
    }

    @Override
    public List<UserRoles> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long id) {

        if (repository.findById(id) == null) {
            throw new RuntimeException("Relacion no encontrada");
        }

        return repository.delete(id);
    }

    private boolean isInvalidUserRole(UserRoles userRole) {

        return userRole == null
                || userRole.getUserId() == null
                || userRole.getRoleId() == null;
    }
}