package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.implementations.UserRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static StrictHashSet<User> getAll(UserService service) {
        return UserRepository.findAll(service.userRepository);
    }

    public static User getById(UserService service, int id) {
        return UserRepository.findById(service.userRepository, id).orElse(null);
    }

    public static User getByUsername(UserService service, String name) {
        return UserRepository.findByUsername(service.userRepository, name).orElse(null);
    }

    public static UserService create(UserService service, String firstName, String lastName, String username) throws Exception {
        User user = new User(firstName, lastName, username);
        return new UserService(UserRepository.save(service.userRepository, user));
    }

    public static UserService updateById(UserService service, Integer id, User user) throws Exception {
        Optional<User> existingUser = UserRepository.findById(service.userRepository, user.getId());

        if (existingUser.isEmpty())
            return new UserService(service.userRepository);

        return new UserService(UserRepository.updateById(service.userRepository, id, user));
    }

    public static UserService deleteById(UserService service, Integer id) throws Exception {
        return new UserService(UserRepository.deleteById(service.userRepository, id));
    }
}
