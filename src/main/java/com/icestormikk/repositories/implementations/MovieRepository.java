package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;
import java.util.Optional;

public class MovieRepository {
    private final SafeHashSet<Movie> movies;

    public MovieRepository() {
        this.movies = new SafeHashSet<>();
    }

    private MovieRepository(SafeHashSet<Movie> movies) {
        this.movies = movies;
    }

    public static SafeHashSet<Movie> findAll(MovieRepository repository) {
        return new SafeHashSet<>(repository.movies);
    }

    public static Optional<Movie> findById(MovieRepository repository, int id) {
        return repository.movies.stream().filter((movie) -> movie.getId() == id).findFirst();
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
        Optional<Movie> oldMovie = findById(repository, id);

        if (oldMovie.isEmpty())
            return new MovieRepository(oldMovies);

        Movie newMovie = oldMovie.get().withTitle(movie.getTitle()).withDurationInMin(movie.getDurationInMin()).withGenre(movie.getGenre()).withRating(movie.getRating());

        SafeHashSet<Movie> updatedMovies = new SafeHashSet<>();
        oldMovies.stream().map(m -> Objects.equals(m.getId(), movie.getId()) ? newMovie : m).forEach(updatedMovies::add);

        return new MovieRepository(updatedMovies);
    }

    public static MovieRepository deleteById(MovieRepository repository, Integer id) throws Exception {
        SafeHashSet<Movie> oldMovies = new SafeHashSet<>(repository.movies);
        Optional<Movie> movieOpt = findById(repository, id);

        return movieOpt.map(movie -> new MovieRepository(SafeHashSet.without(oldMovies, movie))).orElseGet(() -> new MovieRepository(oldMovies));
    }
}
