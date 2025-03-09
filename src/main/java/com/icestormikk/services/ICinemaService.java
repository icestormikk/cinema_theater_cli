package com.icestormikk.services;

import com.icestormikk.domain.cinema.Cinema;

import java.util.List;

public interface ICinemaService {
    List<Cinema> getAllCinemas();
    Cinema getCinemaById(Long id);
    Cinema getCinemaByTitle(String title);
    Cinema createCinema(String title, String address);
    Cinema updateCinemaById(Long id, String title, String address) throws Exception;
    void deleteCinemaById(Long id);
}
