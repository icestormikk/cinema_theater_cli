package com.icestormikk.services;

import com.icestormikk.domain.cinema.Admin;

public interface AdminService extends IService<Admin, Integer> {
    Admin getByUsername(String username);
    AdminService create(String firstName, String lastName, String username) throws Exception;
}
