package com.icestormikk.services;

import com.icestormikk.domain.cinema.Cinema;

import java.util.Optional;

public interface CinemaService extends IService<Cinema, Integer> {
    Cinema getByTitle(String title);
    CinemaService create(String title, String address) throws Exception;
}
