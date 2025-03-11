package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.UserRepository;
import com.icestormikk.repositories.implementations.UserRepositoryImpl;
import com.icestormikk.services.UserService;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private static final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public Set<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String name) throws Exception {
        return userRepository.findByUsername(name);
    }

    @Override
    public User createUser(String firstName, String lastName, String username) throws Exception {
        User user = new User(firstName, lastName, username);
        return userRepository.createUser(user);
    }

    @Override
    public User updateUserById(int id, String firstName, String lastName, String username) throws Exception {
        User user = userRepository.findById(id);
        user.setFirstName(firstName).setLastName(lastName).setUsername(username);
        return userRepository.updateUser(user);
    }

    @Override
    public void deleteUserById(int id) throws Exception {
        userRepository.deleteById(id);
    }
}
