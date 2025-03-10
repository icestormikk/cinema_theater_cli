package com.icestormikk.services;

import com.icestormikk.domain.cinema.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> getAllAdmins();
    Admin createAdmin(String firstName, String lastName, String username);
    Admin getAdminByUsername(String username);
}
