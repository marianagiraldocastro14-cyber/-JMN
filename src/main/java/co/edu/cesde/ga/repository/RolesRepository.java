package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Roles;

import java.util.List;

public interface RolesRepository {

    Roles create(Roles role);

    List<Roles> findAll();

    Roles findById(Long roleId);

    Roles findByName(String name);

    boolean update(Roles roleUpdate);

    boolean delete(Long roleId);

    int count();
}