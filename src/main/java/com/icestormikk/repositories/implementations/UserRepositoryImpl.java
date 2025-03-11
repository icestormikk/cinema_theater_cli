package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {
    private final Set<User> users = new HashSet<>();
    private static Integer nextId = 0;

    @Override
    public Set<User> findAll() {
        return users;
    }

    @Override
    public User findById(int id) throws Exception {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        throw new Exception("User with such id was not found");
    }

    @Override
    public User findByUsername(String username) throws Exception {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        throw new Exception("User with such username was not found");
    }

    @Override
    public User createUser(User user) throws Exception {
        user.setId(nextId++);

        boolean success = users.add(user);

        if (!success) {
            throw new Exception("User with such data already exists");
        }

        return user;
    }

    @Override
    public User updateUser(User user) throws Exception {
        boolean success = users.remove(user);

        if (!success) {
            throw new Exception("User with such data not found");
        }

        users.add(user);
        return user;
    }

    @Override
    public void deleteById(int id) throws Exception {
        User user = findById(id);

        if(user == null) {
            throw new Exception("Cannot find user to delete (id: " + id);
        }

        users.remove(user);
    }
}
