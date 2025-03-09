package com.icestormikk.services;

import com.icestormikk.domain.cinema.Admin;

import java.util.concurrent.CopyOnWriteArrayList;

public interface IAdminService {
    Admin createAdmin(String firstName, String lastName, String username);
    Admin findAdminByUsername(String username);
}
