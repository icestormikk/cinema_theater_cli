package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.UserRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements UserRepository {
    private final StrictHashSet<User> users;
    private final AtomicInteger nextId;

    public UserRepositoryImpl() {
        this.users = new StrictHashSet<>();
        this.nextId = new AtomicInteger(0);
    }

    private UserRepositoryImpl(StrictHashSet<User> users, AtomicInteger nextId) {
        this.users = users;
        this.nextId = nextId;
    }

    @Override
    public Set<User> findAll() {
        return new StrictHashSet<>(users);
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    @Override
    public UserRepository save(User user) throws Exception {
        User newUser = user.withId(nextId.getAndIncrement());

        if (users.contains(newUser)) {
            throw new Exception("User with such data already exists");
        }

        StrictHashSet<User> newUsers = new StrictHashSet<>(users);
        newUsers.add(newUser);

        return new UserRepositoryImpl(newUsers, nextId);
    }

    @Override
    public UserRepository updateById(Integer id, User user) throws Exception {
        Optional<User> oldUser = findById(id);

        if (oldUser.isEmpty())
            throw new Exception("User with such data not found");

        User newUser = user.withFirstName(user.getFirstName()).withLastName(user.getLastName())
                .withUsername(user.getUsername()).withTicketIds(user.getTicketIds());

        StrictHashSet<User> updatedUsers = new StrictHashSet<>();
        for (User u : users)
            updatedUsers.add(Objects.equals(u.getId(), user.getId()) ? newUser : u);

        return new UserRepositoryImpl(updatedUsers, nextId);
    }

    @Override
    public UserRepository deleteById(Integer id) throws Exception {
        Optional<User> userOpt = findById(id);

        if (userOpt.isEmpty())
            throw new Exception("User with such id not found");

        StrictHashSet<User> updatedUsers = new StrictHashSet<>();
        for (User u : users)
            if(!Objects.equals(u.getId(), userOpt.get().getId()))
                updatedUsers.add(u);

        return new UserRepositoryImpl(updatedUsers, nextId);
    }
}
