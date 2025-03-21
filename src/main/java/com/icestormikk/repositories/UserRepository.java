package com.icestormikk.repositories;

import com.icestormikk.domain.cinema.User;

import java.util.Optional;

public interface UserRepository extends IRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
