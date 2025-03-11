package com.icestormikk.services;

import com.icestormikk.domain.cinema.Admin;

import java.util.Set;

public interface AdminService {
    Set<Admin> getAdmins();
    Admin getAdminByName(String name) throws Exception;
    Admin createAdmin(String firstName, String lastName, String username) throws Exception;
    Admin updateAdminById(int id, String firstName, String lastName, String username) throws Exception;
    void deleteAdminById(int id) throws Exception;
}
