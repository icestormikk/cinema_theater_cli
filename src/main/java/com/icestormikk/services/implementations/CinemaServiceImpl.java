package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.repositories.CinemaRepository;
import com.icestormikk.services.CinemaService;
import com.icestormikk.utils.StrictHashSet;

import java.util.Set;

public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public Set<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema getById(int id) {
        return cinemaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cinema not found"));
    }

    @Override
    public Cinema getByTitle(String title) {
        return cinemaRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Cinema not found"));
    }

    @Override
    public CinemaService create(String title, String address) throws Exception {
        Cinema cinema = new Cinema(null, title, address, new StrictHashSet<>(), new StrictHashSet<>(), new StrictHashSet<>());
        CinemaRepository updatedRepo = (CinemaRepository) cinemaRepository.save(cinema);
        return new CinemaServiceImpl(updatedRepo);
    }

    @Override
    public CinemaService updateById(Integer id, Cinema cinema) throws Exception {
        CinemaRepository updatedRepo = (CinemaRepository) cinemaRepository.updateById(id, cinema);
        return new CinemaServiceImpl(updatedRepo);
    }

    @Override
    public CinemaService deleteById(Integer id) throws Exception {
        CinemaRepository updatedRepo = (CinemaRepository) cinemaRepository.deleteById(id);
        return new CinemaServiceImpl(updatedRepo);
    }
}
