package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.UserRoles;

import java.util.List;

public interface UserRolesRepository {

    UserRoles create(UserRoles userRole);

    List<UserRoles> findAll();

    UserRoles findById(Long id);

    List<UserRoles> findByUserId(Long userId);

    boolean update(UserRoles userRoleUpdate);

    boolean delete(Long id);

    int count();
}