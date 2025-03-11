package com.icestormikk.repositories;

import com.icestormikk.domain.cinema.User;

import java.util.Set;

public interface UserRepository {
    Set<User> findAll();
    User findById(int id) throws Exception;
    User findByUsername(String username) throws Exception;
    User createUser(User user) throws Exception;
    User updateUser(User user) throws Exception;
    void deleteById(int id) throws Exception;
}
