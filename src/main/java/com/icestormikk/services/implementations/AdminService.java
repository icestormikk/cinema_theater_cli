package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.services.IAdminService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdminService implements IAdminService {
    private static final CopyOnWriteArrayList<Admin> admins = new CopyOnWriteArrayList<>();

    @Override
    public Admin createAdmin(String firstName, String lastName, String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                System.out.println("Admin with such username already exists");
                return null;
            }
        }

        Admin admin = new Admin(firstName, lastName, username);
        admins.add(admin);
        return admin;
    }

    @Override
    public Admin getAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equalsIgnoreCase(username)) {
                return admin;
            }
        }

        return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return admins;
    }
}
