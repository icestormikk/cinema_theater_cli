package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Hall;
import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieRepository {
    private final SafeHashSet<Movie> movies;

    public MovieRepository() {
        this.movies = new SafeHashSet<>();
    }

    private MovieRepository(SafeHashSet<Movie> movies) {
        this.movies = movies;
    }

    public static SafeHashSet<Movie> findMany(MovieRepository repository, Predicate<Movie> condition) {
        return new SafeHashSet<>(repository.movies.stream().filter(condition).collect(Collectors.toList()));
    }

    public static Optional<Movie> findOne(MovieRepository repository, Predicate<Movie> condition) {
        return repository.movies.stream().filter(condition).findFirst();
    }

    public static MovieRepository save(MovieRepository repository, Movie movie) throws Exception {
        SafeHashSet<Movie> oldMovies = new SafeHashSet<>(repository.movies);
        Movie newMovie = movie.withId(oldMovies.size() + 1);

        if(oldMovies.contains(newMovie))
            return new MovieRepository(oldMovies);

        return new MovieRepository(SafeHashSet.with(oldMovies, newMovie));
    }

    public static MovieRepository updateById(MovieRepository repository, Integer id, Movie movie) throws Exception {
        SafeHashSet<Movie> oldMovies = new SafeHashSet<>(repository.movies);
        Optional<Movie> oldMovie = findOne(repository, (m) -> m.getId() == id);

        if (oldMovie.isEmpty())
            return new MovieRepository(oldMovies);

        Movie newMovie = oldMovie.get().withTitle(movie.getTitle()).withDurationInMin(movie.getDurationInMin()).withGenre(movie.getGenre()).withRating(movie.getRating());

        SafeHashSet<Movie> updatedMovies = new SafeHashSet<>();
        oldMovies.stream().map(m -> Objects.equals(m.getId(), movie.getId()) ? newMovie : m).forEach(updatedMovies::add);

        return new MovieRepository(updatedMovies);
    }

    public static MovieRepository deleteById(MovieRepository repository, Integer id) throws Exception {
        SafeHashSet<Movie> oldMovies = new SafeHashSet<>(repository.movies);
        Optional<Movie> movieOpt = findOne(repository, (m) -> m.getId() == id);

        return movieOpt.map(movie -> new MovieRepository(SafeHashSet.without(oldMovies, movie))).orElseGet(() -> new MovieRepository(oldMovies));
    }
}
