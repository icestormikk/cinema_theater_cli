package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;

public class CinemaRepository {
    private final StrictHashSet<Cinema> cinemas;

    public CinemaRepository() {
        this.cinemas = new StrictHashSet<>();
    }

    private CinemaRepository(StrictHashSet<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public static StrictHashSet<Cinema> findAll(CinemaRepository repository) {
        return new StrictHashSet<>(repository.cinemas);
    }

    public static Optional<Cinema> findById(CinemaRepository repository, int id) {
        return repository.cinemas.stream().filter((cinema) -> cinema.getId() == id).findFirst();
    }

    public static Optional<Cinema> findByTitle(CinemaRepository repository, String title) {
        return repository.cinemas.stream().filter((cinema) -> cinema.getTitle().equals(title)).findFirst();
    }

    public static CinemaRepository save(CinemaRepository repository, Cinema cinema) throws Exception {
        StrictHashSet<Cinema> oldCinemas = new StrictHashSet<>(repository.cinemas);
        Cinema newCinema = cinema.withId(oldCinemas.size() + 1);

        if(oldCinemas.contains(newCinema))
            return new CinemaRepository(oldCinemas);

        return new CinemaRepository(oldCinemas.with(newCinema));
    }

    public static CinemaRepository updateById(CinemaRepository repository, Integer id, Cinema cinema) throws Exception {
        StrictHashSet<Cinema> oldCinemas = new StrictHashSet<>(repository.cinemas);
        Optional<Cinema> oldCinema = CinemaRepository.findById(repository, id);

        if (oldCinema.isEmpty())
            return new CinemaRepository(oldCinemas);

        Cinema newCinema = oldCinema.get().withTitle(cinema.getTitle()).withAddress(cinema.getAddress())
                .withHallIds(cinema.getHallIds()).withMovieIds(cinema.getMovieIds()).withSessionIds(cinema.getSessionIds());

        StrictHashSet<Cinema> updatedCinemas = new StrictHashSet<>();
        oldCinemas.stream().map(c -> Objects.equals(c.getId(), cinema.getId()) ? newCinema : c).forEach(updatedCinemas::add);

        return new CinemaRepository(updatedCinemas);
    }

    public static CinemaRepository deleteById(CinemaRepository repository, Integer id) throws Exception {
        StrictHashSet<Cinema> oldCinemas = new StrictHashSet<>(repository.cinemas);
        Optional<Cinema> cinemaOpt = CinemaRepository.findById(repository, id);

        return cinemaOpt.map(cinema -> new CinemaRepository(oldCinemas.without(cinema))).orElseGet(() -> new CinemaRepository(oldCinemas));
    }
}
