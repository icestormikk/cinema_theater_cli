package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.services.IUserService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserService implements IUserService {
    public static final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Override
    public User createUser(String firstName, String lastName, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("User with such username already exists");
                return null;
            }
        }

        User user = new User((long) (users.size() + 1), firstName, lastName, username);
        users.add(user);
        return user;
    }

    @Override
    public User getUserByName(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
