package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Cinema;
import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CinemaRepository {
    private final SafeHashSet<Cinema> cinemas;

    public CinemaRepository() {
        this.cinemas = new SafeHashSet<>();
    }

    private CinemaRepository(SafeHashSet<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public static SafeHashSet<Cinema> findMany(CinemaRepository repository, Predicate<Cinema> condition) {
        return new SafeHashSet<>(repository.cinemas.stream().filter(condition).collect(Collectors.toList()));
    }

    public static Optional<Cinema> findOne(CinemaRepository repository, Predicate<Cinema> condition) {
        return repository.cinemas.stream().filter(condition).findFirst();
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
        Optional<Cinema> oldCinema = CinemaRepository.findOne(repository, (c) -> c.getId() == id);

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
        Optional<Cinema> cinemaOpt = CinemaRepository.findOne(repository, (c) -> c.getId() == id);

        return cinemaOpt.map(cinema -> new CinemaRepository(SafeHashSet.without(oldCinemas, cinema))).orElseGet(() -> new CinemaRepository(oldCinemas));
    }
}
