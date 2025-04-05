package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.repositories.implementations.AdminRepository;
import com.icestormikk.utils.SafeHashSet;

public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public static SafeHashSet<Admin> getAll(AdminService service) {
        return AdminRepository.findMany(service.adminRepository, (a) -> true);
    }

    public static Admin getById(AdminService service, int id) {
        return AdminRepository.findOne(service.adminRepository, (a) -> a.getId().equals(id)).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    public static Admin getByUsername(AdminService service, String username) {
        return AdminRepository.findOne(service.adminRepository, (a) -> a.getUsername().equals(username)).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    public static AdminService create(AdminService service, String firstName, String lastName, String username) throws Exception {
        Admin admin = new Admin(firstName, lastName, username, new SafeHashSet<>(), new SafeHashSet<>());
        return new AdminService(AdminRepository.save(service.adminRepository, admin));
    }

    public static AdminService updateById(AdminService service, Integer id, Admin admin) throws Exception {
        return new AdminService(AdminRepository.updateById(service.adminRepository, id, admin));
    }

    public static AdminService deleteById(AdminService service, Integer id) throws Exception {
        return new AdminService(AdminRepository.deleteById(service.adminRepository, id));
    }
}
