package com.icestormikk.services;

import com.icestormikk.domain.cinema.User;

public interface IUserService {
    User createUser(String firstName, String lastName, String username);
    User getUserByName(String username);
}
