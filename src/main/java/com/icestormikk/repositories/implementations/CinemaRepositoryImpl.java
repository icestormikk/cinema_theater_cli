package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.repositories.CinemaRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class CinemaRepositoryImpl implements CinemaRepository {
    private final StrictHashSet<Cinema> cinemas;
    private final AtomicInteger nextId;

    public CinemaRepositoryImpl() {
        this.cinemas = new StrictHashSet<>();
        this.nextId = new AtomicInteger(0);
    }

    private CinemaRepositoryImpl(StrictHashSet<Cinema> cinemas, AtomicInteger nextId) {
        this.cinemas = cinemas;
        this.nextId = nextId;
    }

    @Override
    public Set<Cinema> findAll() {
        return Set.copyOf(cinemas);
    }

    @Override
    public Optional<Cinema> findById(int id) {
        return cinemas.stream().filter((cinema) -> cinema.getId() == id).findFirst();
    }

    @Override
    public Optional<Cinema> findByTitle(String title) {
        return cinemas.stream().filter((cinema) -> cinema.getTitle().equals(title)).findFirst();
    }

    @Override
    public CinemaRepository save(Cinema cinema) throws Exception {
        Cinema newCinema = cinema.withId(nextId.getAndIncrement());

        if(cinemas.contains(newCinema))
            throw new Exception("Cinema object with such data already exists");

        StrictHashSet<Cinema> newCinemas = new StrictHashSet<>(cinemas);
        newCinemas.add(cinema);

        return new CinemaRepositoryImpl(newCinemas, nextId);
    }

    @Override
    public CinemaRepository updateById(Integer id, Cinema cinema) throws Exception {
        Optional<Cinema> oldCinema = findById(id);

        if (oldCinema.isEmpty())
            throw new Exception("Cinema with such data not found");

        Cinema newCinema = oldCinema.get().withTitle(cinema.getTitle()).withAddress(cinema.getAddress())
                .withHallIds(cinema.getHallIds()).withMovieIds(cinema.getMovieIds()).withSessionIds(cinema.getSessionIds());

        StrictHashSet<Cinema> updatedCinemas = new StrictHashSet<>();
        cinemas.stream().map(c -> Objects.equals(c.getId(), cinema.getId()) ? newCinema : c).forEach(updatedCinemas::add);

        return new CinemaRepositoryImpl(updatedCinemas, nextId);
    }

    @Override
    public CinemaRepository deleteById(Integer id) throws Exception {
        Optional<Cinema> cinemaOpt = findById(id);

        if (cinemaOpt.isEmpty())
            throw new Exception("Cinema with such id not found");

        StrictHashSet<Cinema> updatedCinemas = new StrictHashSet<>();
        cinemas.stream().filter(u -> !Objects.equals(u.getId(), cinemaOpt.get().getId())).forEach(updatedCinemas::add);

        return new CinemaRepositoryImpl(updatedCinemas, nextId);
    }
}
