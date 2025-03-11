package com.icestormikk.services;

import com.icestormikk.domain.cinema.User;

import java.util.Set;

public interface UserService {
    Set<User> getUsers();
    User getUserByName(String name) throws Exception;
    User createUser(String firstName, String lastName, String username) throws Exception;
    User updateUserById(int id, String firstName, String lastName, String username) throws Exception;
    void deleteUserById(int id) throws Exception;
}
