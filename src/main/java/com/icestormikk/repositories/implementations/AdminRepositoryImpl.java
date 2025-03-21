package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Admin;
import com.icestormikk.repositories.AdminRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AdminRepositoryImpl implements AdminRepository {
    private final StrictHashSet<Admin> admins;
    private final AtomicInteger nextId;

    public AdminRepositoryImpl() {
        this.admins = new StrictHashSet<>();
        this.nextId = new AtomicInteger(0);
    }

    private AdminRepositoryImpl(StrictHashSet<Admin> admins, AtomicInteger nextId) {
        this.admins = admins;
        this.nextId = nextId;
    }

    @Override
    public Set<Admin> findAll() {
        return Set.copyOf(admins);
    }

    @Override
    public Optional<Admin> findById(int id) {
        return admins.stream().filter(admin -> admin.getId() == id).findFirst();
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        return admins.stream().filter(admin -> admin.getUsername().equalsIgnoreCase(username)).findFirst();
    }

    @Override
    public AdminRepository save(Admin admin) throws Exception {
        Admin newAdmin = (Admin) admin.withId(nextId.getAndIncrement());

        if (admins.contains(newAdmin)) {
            throw new Exception("Admin with such data already exists");
        }

        StrictHashSet<Admin> updatedAdmins = new StrictHashSet<>(admins);
        updatedAdmins.add(newAdmin);

        return new AdminRepositoryImpl(updatedAdmins, nextId);
    }

    @Override
    public AdminRepository updateById(Integer id, Admin admin) throws Exception {
        Optional<Admin> oldAdmin = findById(id);

        if (oldAdmin.isEmpty())
            throw new Exception("Admin with such id does not exist");

        Admin newAdmin = oldAdmin.get().withFirstName(admin.getFirstName()).withLastName(admin.getLastName())
            .withUsername(admin.getUsername()).withTicketIds(admin.getTicketIds()).withCinemaIds(admin.getCinemaIds());

        StrictHashSet<Admin> updatedAdmins = new StrictHashSet<>();
        for (Admin a : admins)
            updatedAdmins.add(Objects.equals(a.getId(), admin.getId()) ? newAdmin : a);

        return new AdminRepositoryImpl(updatedAdmins, nextId);
    }

    @Override
    public AdminRepository deleteById(Integer id) throws Exception {
        Optional<Admin> adminOpt = findById(id);

        if (adminOpt.isEmpty())
            throw new Exception("Admin with such id was not found");

        StrictHashSet<Admin> updatedAdmins = new StrictHashSet<>();
        for (Admin a : admins)
            if (!Objects.equals(a.getId(), id))
               updatedAdmins.add(a);

        return new AdminRepositoryImpl(updatedAdmins, nextId);
    }
}
