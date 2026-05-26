package co.edu.cesde.ga.repository.impl;

import co.edu.cesde.ga.model.Users;
import co.edu.cesde.ga.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryInMemory implements UsersRepository {

    private final List<Users> users;

    private Long nextUserId;

    public UsersRepositoryInMemory() {

        this.users = new ArrayList<>();

        this.nextUserId = 1L;
    }

    @Override
    public Users create(Users user) {

        if (user == null) {
            return null;
        }

        if (existsByUsername(user.getUsername())) {
            return null;
        }

        if (existsByEmail(user.getEmail())) {
            return null;
        }

        user.setUserId(nextUserId++);

        users.add(user);

        return user;
    }

    @Override
    public List<Users> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public Users findById(Long userId) {

        if (userId == null) {
            return null;
        }

        for (Users user : users) {

            if (userId.equals(user.getUserId())) {
                return user;
            }
        }

        return null;
    }

    @Override
    public Users findByUsername(String username) {

        if (username == null || username.isBlank()) {
            return null;
        }

        for (Users user : users) {

            if (username.equalsIgnoreCase(user.getUsername())) {
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public boolean existsByEmail(String email) {

        if (email == null || email.isBlank()) {
            return false;
        }

        for (Users user : users) {

            if (email.equalsIgnoreCase(user.getEmail())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean update(Users updateUser) {

        if (updateUser == null) {
            return false;
        }

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUserId()
                    .equals(updateUser.getUserId())) {

                users.set(i, updateUser);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(Long userId) {

        Users user = findById(userId);

        if (user == null) {
            return false;
        }

        users.remove(user);

        return true;
    }

    @Override
    public int count() {
        return users.size();
    }
}