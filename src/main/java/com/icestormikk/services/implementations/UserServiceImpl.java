package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.UserRepository;
import com.icestormikk.services.UserService;

import java.util.Optional;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserService create(String firstName, String lastName, String username) throws Exception {
        User user = new User(firstName, lastName, username);
        UserRepository updatedRepo = (UserRepository) userRepository.save(user);
        return new UserServiceImpl(updatedRepo);
    }

    @Override
    public UserService updateById(Integer id, User user) throws Exception {
        Optional<User> existingUser = userRepository.findById(user.getId());

        if (existingUser.isEmpty())
            throw new Exception("User not found");

        UserRepository updatedRepo = (UserRepository) userRepository.updateById(id, user);
        return new UserServiceImpl(updatedRepo);
    }

    @Override
    public UserService deleteById(Integer id) throws Exception {
        UserRepository updatedRepo = (UserRepository) userRepository.deleteById(id);
        return new UserServiceImpl(updatedRepo);
    }
}
