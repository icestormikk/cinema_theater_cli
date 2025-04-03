package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {
    private final StrictHashSet<User> users;

    public UserRepository() {
        this.users = new StrictHashSet<>();
    }

    private UserRepository(StrictHashSet<User> users) {
        this.users = users;
    }

    public static StrictHashSet<User> findAll(UserRepository repository) {
        return new StrictHashSet<>(repository.users);
    }

    public static Optional<User> findById(UserRepository repository, int id) {
        return repository.users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public static Optional<User> findByUsername(UserRepository repository, String username) {
        return repository.users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    public static UserRepository save(UserRepository repository, User user) throws Exception {
        StrictHashSet<User> oldUsers = new StrictHashSet<>(repository.users);
        User newUser = user.withId(oldUsers.size() + 1);

        if(oldUsers.contains(newUser))
            return new UserRepository(oldUsers);

        return new UserRepository(oldUsers.with(newUser));
    }

    public static UserRepository updateById(UserRepository repository, Integer id, User user) throws Exception {
        StrictHashSet<User> oldUsers = new StrictHashSet<>(repository.users);
        Optional<User> oldUser = findById(repository, id);

        if (oldUser.isEmpty())
            return new UserRepository(oldUsers);

        User newUser = user.withFirstName(user.getFirstName()).withLastName(user.getLastName())
                .withUsername(user.getUsername()).withTicketIds(user.getTicketIds());

        StrictHashSet<User> updatedUsers = new StrictHashSet<>();
        oldUsers.stream().map(u -> Objects.equals(u.getId(), user.getId()) ? newUser : u).forEach(updatedUsers::add);

        return new UserRepository(updatedUsers);
    }

    public static UserRepository deleteById(UserRepository repository, Integer id) throws Exception {
        StrictHashSet<User> oldUsers = new StrictHashSet<>(repository.users);
        Optional<User> userOpt = findById(repository, id);

        return userOpt.map(user -> new UserRepository(oldUsers.without(user))).orElseGet(() -> new UserRepository(oldUsers));
    }
}
