package com.icestormikk.repositories;

import com.icestormikk.domain.cinema.Admin;

import java.util.Set;

public interface AdminRepository {
    Set<Admin> findAll();
    Admin findById(int id) throws Exception;
    Admin findByUsername(String username) throws Exception;
    Admin createAdmin(Admin admin) throws Exception;
    Admin updateAdmin(Admin admin) throws Exception;
    void deleteById(int id) throws Exception;
}
