package com.icestormikk.services;

import com.icestormikk.domain.cinema.User;

import java.util.Optional;

public interface UserService extends IService<User, Integer> {
    User getByUsername(String username);
    UserService create(String firstName, String lastName, String username) throws Exception;
}
