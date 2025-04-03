package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;
import java.util.Optional;

public class CinemaRepository {
    private final SafeHashSet<Cinema> cinemas;

    public CinemaRepository() {
        this.cinemas = new SafeHashSet<>();
    }

    private CinemaRepository(SafeHashSet<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public static SafeHashSet<Cinema> findAll(CinemaRepository repository) {
        return new SafeHashSet<>(repository.cinemas);
    }

    public static Optional<Cinema> findById(CinemaRepository repository, int id) {
        return repository.cinemas.stream().filter((cinema) -> cinema.getId() == id).findFirst();
    }

    public static Optional<Cinema> findByTitle(CinemaRepository repository, String title) {
        return repository.cinemas.stream().filter((cinema) -> cinema.getTitle().equals(title)).findFirst();
    }

    public static CinemaRepository save(CinemaRepository repository, Cinema cinema) throws Exception {
        SafeHashSet<Cinema> oldCinemas = new SafeHashSet<>(repository.cinemas);
        Cinema newCinema = cinema.withId(oldCinemas.size() + 1);

        if(oldCinemas.contains(newCinema))
            return new CinemaRepository(oldCinemas);

        return new CinemaRepository(SafeHashSet.with(oldCinemas, newCinema));
    }

    public static CinemaRepository updateById(CinemaRepository repository, Integer id, Cinema cinema) throws Exception {
        SafeHashSet<Cinema> oldCinemas = new SafeHashSet<>(repository.cinemas);
        Optional<Cinema> oldCinema = CinemaRepository.findById(repository, id);

        if (oldCinema.isEmpty())
            return new CinemaRepository(oldCinemas);

        Cinema newCinema = oldCinema.get().withTitle(cinema.getTitle()).withAddress(cinema.getAddress())
                .withHallIds(cinema.getHallIds()).withMovieIds(cinema.getMovieIds()).withSessionIds(cinema.getSessionIds());

        SafeHashSet<Cinema> updatedCinemas = new SafeHashSet<>();
        oldCinemas.stream().map(c -> Objects.equals(c.getId(), cinema.getId()) ? newCinema : c).forEach(updatedCinemas::add);

        return new CinemaRepository(updatedCinemas);
    }

    public static CinemaRepository deleteById(CinemaRepository repository, Integer id) throws Exception {
        SafeHashSet<Cinema> oldCinemas = new SafeHashSet<>(repository.cinemas);
        Optional<Cinema> cinemaOpt = CinemaRepository.findById(repository, id);

        return cinemaOpt.map(cinema -> new CinemaRepository(SafeHashSet.without(oldCinemas, cinema))).orElseGet(() -> new CinemaRepository(oldCinemas));
    }
}
