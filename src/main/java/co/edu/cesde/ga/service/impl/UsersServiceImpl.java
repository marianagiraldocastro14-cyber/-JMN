package co.edu.cesde.ga.service.impl;

import co.edu.cesde.ga.model.Users;
import co.edu.cesde.ga.repository.UsersRepository;
import co.edu.cesde.ga.service.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;

    public UsersServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public Users create(Users user) {

        if (isInvalidUser(user)) {
            throw new RuntimeException("Datos invalidos");
        }

        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("El username ya existe");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya existe");
        }

        return repository.create(user);
    }

    @Override
    public boolean update(Users updateUser) {

        if (isInvalidUser(updateUser)
                || updateUser.getUserId() == null
                || updateUser.getUserId() <= 0L) {

            throw new RuntimeException("Datos invalidos");
        }

        if (repository.findById(updateUser.getUserId()) == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return repository.update(updateUser);
    }

    @Override
    public Users findById(Long userId) {

        if (userId == null || userId <= 0L) {
            throw new RuntimeException("ID invalido");
        }

        Users user = repository.findById(userId);

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return user;
    }

    @Override
    public Users findByUsername(String username) {

        if (username == null || username.isBlank()) {
            throw new RuntimeException("Username invalido");
        }

        Users user = repository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return user;
    }

    @Override
    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long userId) {

        if (repository.findById(userId) == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        return repository.delete(userId);
    }

    private boolean isInvalidUser(Users user) {

        return user == null
                || isBlank(user.getUsername())
                || isBlank(user.getEmail())
                || isBlank(user.getPasswordHash())
                || isBlank(user.getStatus())
                || isBlank(user.getCreatedAt());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isBlank();
    }
}