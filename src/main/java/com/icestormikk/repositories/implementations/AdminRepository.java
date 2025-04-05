package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminRepository {
    private final SafeHashSet<Admin> admins;

    public AdminRepository() {
        this.admins = new SafeHashSet<>();
    }

    private AdminRepository(SafeHashSet<Admin> admins) {
        this.admins = admins;
    }

    public static SafeHashSet<Admin> findMany(AdminRepository repository, Predicate<Admin> condition) {
        return new SafeHashSet<>(repository.admins.stream().filter(condition).collect(Collectors.toList()));
    }

    public static Optional<Admin> findOne(AdminRepository repository, Predicate<Admin> condition) {
        return repository.admins.stream().filter(condition).findFirst();
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
        Optional<Admin> oldAdmin = AdminRepository.findOne(repository, (a) -> a.getId().equals(id));

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
        Optional<Admin> adminOpt = AdminRepository.findOne(repository, (a) -> a.getId().equals(id));

        return adminOpt.map(admin -> new AdminRepository(SafeHashSet.without(oldAdmins, admin))).orElseGet(() -> new AdminRepository(oldAdmins));

    }
}
