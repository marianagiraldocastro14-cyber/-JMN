package co.edu.cesde.ga.repository;

import co.edu.cesde.ga.model.Users;
import java.util.List;

public interface UserRepository {

    Users create(Users user);

    List<Users> findAll();

    Users findById(Long userId);

    Users findByUsername(String username);

    boolean existsByUsername(String username);

    boolean update(Users user);

    boolean delete(Long userId);

    int count();
}