package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.repositories.implementations.CinemaRepository;
import com.icestormikk.utils.SafeHashSet;

public class CinemaService {
    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public static SafeHashSet<Cinema> getAll(CinemaService service) {
        return CinemaRepository.findAll(service.cinemaRepository);
    }

    public static Cinema getById(CinemaService service, int id) {
        return CinemaRepository.findById(service.cinemaRepository, id).orElseThrow(() -> new RuntimeException("Cinema not found"));
    }

    public static Cinema getByTitle(CinemaService service, String title) {
        return CinemaRepository.findByTitle(service.cinemaRepository, title).orElseThrow(() -> new RuntimeException("Cinema not found"));
    }

    public static CinemaService create(CinemaService service, String title, String address) throws Exception {
        Cinema cinema = new Cinema(null, title, address, new SafeHashSet<>(), new SafeHashSet<>(), new SafeHashSet<>());
        return new CinemaService(CinemaRepository.save(service.cinemaRepository, cinema));
    }

    public static CinemaService updateById(CinemaService service, Integer id, Cinema cinema) throws Exception {
        return new CinemaService(CinemaRepository.updateById(service.cinemaRepository, id, cinema));
    }

    public static CinemaService deleteById(CinemaService service, Integer id) throws Exception {
        return new CinemaService(CinemaRepository.deleteById(service.cinemaRepository, id));
    }
}
