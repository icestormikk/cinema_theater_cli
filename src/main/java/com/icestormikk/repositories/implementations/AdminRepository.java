package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;
import java.util.Optional;

public class AdminRepository {
    private final SafeHashSet<Admin> admins;

    public AdminRepository() {
        this.admins = new SafeHashSet<>();
    }

    private AdminRepository(SafeHashSet<Admin> admins) {
        this.admins = admins;
    }

    public static SafeHashSet<Admin> findAll(AdminRepository repository) {
        return new SafeHashSet<>(repository.admins);
    }

    public static Optional<Admin> findById(AdminRepository repository, int id) {
        return repository.admins.stream().filter(admin -> admin.getId() == id).findFirst();
    }

    public static Optional<Admin> findByUsername(AdminRepository repository, String username) {
        return repository.admins.stream().filter(admin -> admin.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    public static AdminRepository save(AdminRepository repository, Admin admin) throws Exception {
        SafeHashSet<Admin> oldAdmins = new SafeHashSet<>(repository.admins);
        Admin newAdmin = admin.withId(oldAdmins.size() + 1);

        if (oldAdmins.contains(newAdmin))
            return new AdminRepository(oldAdmins);

        return new AdminRepository(SafeHashSet.with(oldAdmins, newAdmin));
    }

    public static AdminRepository updateById(AdminRepository repository, Integer id, Admin admin) throws Exception {
        SafeHashSet<Admin> oldAdmins = new SafeHashSet<>(repository.admins);
        Optional<Admin> oldAdmin = AdminRepository.findById(repository, id);

        if (oldAdmin.isEmpty())
            return new AdminRepository(oldAdmins);

        Admin newAdmin = oldAdmin.get().withFirstName(admin.getFirstName()).withLastName(admin.getLastName())
            .withUsername(admin.getUsername()).withTicketIds(admin.getTicketIds()).withCinemaIds(admin.getCinemaIds());

        SafeHashSet<Admin> updatedAdmins = new SafeHashSet<>();
        oldAdmins.stream().map(a -> Objects.equals(a.getId(), admin.getId()) ? newAdmin : a).forEach(updatedAdmins::add);

        return new AdminRepository(updatedAdmins);
    }

    public static AdminRepository deleteById(AdminRepository repository, Integer id) throws Exception {
        SafeHashSet<Admin> oldAdmins = new SafeHashSet<>(repository.admins);
        Optional<Admin> adminOpt = AdminRepository.findById(repository, id);

        return adminOpt.map(admin -> new AdminRepository(SafeHashSet.without(oldAdmins, admin))).orElseGet(() -> new AdminRepository(oldAdmins));

    }
}
