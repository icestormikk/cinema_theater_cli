package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;

public class AdminRepository {
    private final StrictHashSet<Admin> admins;

    public AdminRepository() {
        this.admins = new StrictHashSet<>();
    }

    private AdminRepository(StrictHashSet<Admin> admins) {
        this.admins = admins;
    }

    public static StrictHashSet<Admin> findAll(AdminRepository repository) {
        return new StrictHashSet<>(repository.admins);
    }

    public static Optional<Admin> findById(AdminRepository repository, int id) {
        return repository.admins.stream().filter(admin -> admin.getId() == id).findFirst();
    }

    public static Optional<Admin> findByUsername(AdminRepository repository, String username) {
        return repository.admins.stream().filter(admin -> admin.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    public static AdminRepository save(AdminRepository repository, Admin admin) throws Exception {
        StrictHashSet<Admin> oldAdmins = new StrictHashSet<>(repository.admins);
        Admin newAdmin = admin.withId(oldAdmins.size() + 1);

        if (oldAdmins.contains(newAdmin))
            return new AdminRepository(oldAdmins);

        return new AdminRepository(oldAdmins.with(newAdmin));
    }

    public static AdminRepository updateById(AdminRepository repository, Integer id, Admin admin) throws Exception {
        StrictHashSet<Admin> oldAdmins = new StrictHashSet<>(repository.admins);
        Optional<Admin> oldAdmin = AdminRepository.findById(repository, id);

        if (oldAdmin.isEmpty())
            return new AdminRepository(oldAdmins);

        Admin newAdmin = oldAdmin.get().withFirstName(admin.getFirstName()).withLastName(admin.getLastName())
            .withUsername(admin.getUsername()).withTicketIds(admin.getTicketIds()).withCinemaIds(admin.getCinemaIds());

        StrictHashSet<Admin> updatedAdmins = new StrictHashSet<>();
        oldAdmins.stream().map(a -> Objects.equals(a.getId(), admin.getId()) ? newAdmin : a).forEach(updatedAdmins::add);

        return new AdminRepository(updatedAdmins);
    }

    public static AdminRepository deleteById(AdminRepository repository, Integer id) throws Exception {
        StrictHashSet<Admin> oldAdmins = new StrictHashSet<>(repository.admins);
        Optional<Admin> adminOpt = AdminRepository.findById(repository, id);

        return adminOpt.map(admin -> new AdminRepository(oldAdmins.without(admin))).orElseGet(() -> new AdminRepository(oldAdmins));

    }
}
