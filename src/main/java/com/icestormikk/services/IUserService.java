package com.icestormikk.services;

import com.icestormikk.domain.cinema.User;

import java.util.List;

public interface IUserService {
    User createUser(String firstName, String lastName, String username);
    User getUserByName(String username);
    List<User> getAllUsers();
}
