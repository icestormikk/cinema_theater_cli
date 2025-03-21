package com.icestormikk.repositories;

import com.icestormikk.domain.cinema.Cinema;

import java.util.Optional;

public interface CinemaRepository extends IRepository<Cinema, Integer> {
    Optional<Cinema> findByTitle(String title);
}
