package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;
import java.util.Optional;

public class UserRepository {
    private final SafeHashSet<User> users;

    public UserRepository() {
        this.users = new SafeHashSet<>();
    }

    private UserRepository(SafeHashSet<User> users) {
        this.users = users;
    }

    public static SafeHashSet<User> findAll(UserRepository repository) {
        return new SafeHashSet<>(repository.users);
    }

    public static Optional<User> findById(UserRepository repository, int id) {
        return repository.users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public static Optional<User> findByUsername(UserRepository repository, String username) {
        return repository.users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    public static UserRepository save(UserRepository repository, User user) throws Exception {
        SafeHashSet<User> oldUsers = new SafeHashSet<>(repository.users);
        User newUser = user.withId(oldUsers.size() + 1);

        if(oldUsers.contains(newUser))
            return new UserRepository(oldUsers);

        return new UserRepository(SafeHashSet.with(oldUsers, newUser));
    }

    public static UserRepository updateById(UserRepository repository, Integer id, User user) throws Exception {
        SafeHashSet<User> oldUsers = new SafeHashSet<>(repository.users);
        Optional<User> oldUser = findById(repository, id);

        if (oldUser.isEmpty())
            return new UserRepository(oldUsers);

        User newUser = user.withFirstName(user.getFirstName()).withLastName(user.getLastName())
                .withUsername(user.getUsername()).withTicketIds(user.getTicketIds());

        SafeHashSet<User> updatedUsers = new SafeHashSet<>();
        oldUsers.stream().map(u -> Objects.equals(u.getId(), user.getId()) ? newUser : u).forEach(updatedUsers::add);

        return new UserRepository(updatedUsers);
    }

    public static UserRepository deleteById(UserRepository repository, Integer id) throws Exception {
        SafeHashSet<User> oldUsers = new SafeHashSet<>(repository.users);
        Optional<User> userOpt = findById(repository, id);

        return userOpt.map(user -> new UserRepository(SafeHashSet.without(oldUsers, user))).orElseGet(() -> new UserRepository(oldUsers));
    }
}
