package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.model.Roles;
import co.edu.cesde.ga.repository.RolesRepository;
import co.edu.cesde.ga.service.RolesService;

import java.util.List;

public class RolesServiceImpl implements RolesService {

    private final RolesRepository repository;

    public RolesServiceImpl(RolesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Roles create(Roles role) {

        if (isInvalidRole(role)) {
            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findByName(role.getName()) != null) {
            throw new RuntimeException("El rol ya existe");
        }

        return repository.create(role);
    }

    @Override
    public boolean update(Roles roleUpdate) {

        if (isInvalidRole(roleUpdate)
                || roleUpdate.getRoleId() == null
                || roleUpdate.getRoleId() <= 0L) {

            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findById(roleUpdate.getRoleId()) == null) {
            throw new RuntimeException("Rol no encontrado");
        }

        return repository.update(roleUpdate);
    }

    @Override
    public Roles findById(Long roleId) {

        if (roleId == null || roleId <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        Roles role = repository.findById(roleId);

        if (role == null) {
            throw new RuntimeException("Rol no encontrado");
        }

        return role;
    }

    @Override
    public Roles findByName(String name) {

        if (name == null || name.isBlank()) {
            throw new RuntimeException("Nombre invalido");
        }

        Roles role = repository.findByName(name);

        if (role == null) {
            throw new RuntimeException("Rol no encontrado");
        }

        return role;
    }

    @Override
    public List<Roles> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long roleId) {

        if (repository.findById(roleId) == null) {
            throw new RuntimeException("Rol no encontrado");
        }

        return repository.delete(roleId);
    }

    private boolean isInvalidRole(Roles role) {

        return role == null
                || isBlank(role.getName())
                || isBlank(role.getDescription());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isBlank();
    }
}