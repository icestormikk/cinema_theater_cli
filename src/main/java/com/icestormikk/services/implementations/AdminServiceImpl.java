package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.repositories.AdminRepository;
import com.icestormikk.services.AdminService;
import com.icestormikk.utils.StrictHashSet;

import java.util.Set;

public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Set<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getById(int id) {
        return adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    @Override
    public Admin getByUsername(String username) {
        return adminRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    @Override
    public AdminServiceImpl create(String firstName, String lastName, String username) throws Exception {
        Admin admin = new Admin(firstName, lastName, username, new StrictHashSet<>(), new StrictHashSet<>());
        AdminRepository updatedRepo = (AdminRepository) adminRepository.save(admin);
        return new AdminServiceImpl(updatedRepo);
    }

    @Override
    public AdminServiceImpl updateById(Integer id, Admin admin) throws Exception {
        AdminRepository updatedRepo = (AdminRepository) adminRepository.updateById(id, admin);
        return new AdminServiceImpl(updatedRepo);
    }

    @Override
    public AdminServiceImpl deleteById(Integer id) throws Exception {
        AdminRepository updatedRepo = (AdminRepository) adminRepository.deleteById(id);
        return new AdminServiceImpl(updatedRepo);
    }
}
