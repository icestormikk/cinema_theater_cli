package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.domain.cinema.User;
import com.icestormikk.repositories.AdminRepository;
import com.icestormikk.repositories.UserRepository;
import com.icestormikk.repositories.implementations.AdminRepositoryImpl;
import com.icestormikk.repositories.implementations.UserRepositoryImpl;
import com.icestormikk.services.AdminService;

import java.util.Set;

public class AdminServiceImpl implements AdminService {
    private static final AdminRepository adminRepository = new AdminRepositoryImpl();

    @Override
    public Set<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminByName(String name) throws Exception {
        return adminRepository.findByUsername(name);
    }

    @Override
    public Admin createAdmin(String firstName, String lastName, String username) throws Exception {
        Admin admin = new Admin(firstName, lastName, username);
        return adminRepository.createAdmin(admin);
    }

    @Override
    public Admin updateAdminById(int id, String firstName, String lastName, String username) throws Exception {
        Admin admin = adminRepository.findById(id);
        admin.setFirstName(firstName).setLastName(lastName).setUsername(username);
        return adminRepository.updateAdmin(admin);
    }

    @Override
    public void deleteAdminById(int id) throws Exception {
        adminRepository.deleteById(id);
    }
}
