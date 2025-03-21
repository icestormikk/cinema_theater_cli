package com.icestormikk.repositories;

import com.icestormikk.domain.cinema.Admin;

import java.util.Optional;

public interface AdminRepository extends IRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);
}
