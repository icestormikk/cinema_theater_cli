package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.implementations.UserRepository;
import com.icestormikk.utils.SafeHashSet;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static SafeHashSet<User> getAll(UserService service) {
        return UserRepository.findMany(service.userRepository, (u) -> true);
    }

    public static User getById(UserService service, int id) {
        return UserRepository.findOne(service.userRepository, (u) -> u.getId() == id).orElse(null);
    }

    public static User getByUsername(UserService service, String name) {
        return UserRepository.findOne(service.userRepository, (u) -> u.getUsername().equals(name)).orElse(null);
    }

    public static UserService create(UserService service, String firstName, String lastName, String username) throws Exception {
        User user = new User(firstName, lastName, username);
        return new UserService(UserRepository.save(service.userRepository, user));
    }

    public static UserService updateById(UserService service, Integer id, User user) throws Exception {
        return new UserService(UserRepository.updateById(service.userRepository, id, user));
    }

    public static UserService deleteById(UserService service, Integer id) throws Exception {
        return new UserService(UserRepository.deleteById(service.userRepository, id));
    }
}
