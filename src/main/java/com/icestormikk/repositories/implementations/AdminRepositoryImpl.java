package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.repositories.AdminRepository;

import java.util.HashSet;
import java.util.Set;

public class AdminRepositoryImpl implements AdminRepository {
    private static final Set<Admin> admins = new HashSet<>();
    private static Integer nextId = 0;

    @Override
    public Set<Admin> findAll() {
        return admins;
    }

    @Override
    public Admin findById(int id) throws Exception {
        for (Admin admin : admins) {
            if (admin.getId() == id) {
                return admin;
            }
        }

        throw new Exception("Admin with such id was not found");
    }

    @Override
    public Admin findByUsername(String username) throws Exception {
        for (Admin admin : admins) {
            if (admin.getUsername().equalsIgnoreCase(username)) {
                return admin;
            }
        }

        throw new Exception("Admin with such username was not found");
    }

    @Override
    public Admin createAdmin(Admin admin) throws Exception {
        admin.setId(nextId++);

        boolean success = admins.add(admin);

        if (!success) {
            throw new Exception("Admin with such data already exists");
        }

        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) throws Exception {
        boolean success = admins.remove(admin);

        if (!success) {
            throw new Exception("Admin with such data not found");
        }

        admins.add(admin);
        return admin;
    }

    @Override
    public void deleteById(int id) throws Exception {
        Admin admin = findById(id);

        if (admin == null) {
            throw new Exception("Admin with such id was not found");
        }

        admins.remove(admin);
    }
}
