package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;

public class MovieRepository {
    private final StrictHashSet<Movie> movies;

    public MovieRepository() {
        this.movies = new StrictHashSet<>();
    }

    private MovieRepository(StrictHashSet<Movie> movies) {
        this.movies = movies;
    }

    public static StrictHashSet<Movie> findAll(MovieRepository repository) {
        return new StrictHashSet<>(repository.movies);
    }

    public static Optional<Movie> findById(MovieRepository repository, int id) {
        return repository.movies.stream().filter((movie) -> movie.getId() == id).findFirst();
    }

    public static MovieRepository save(MovieRepository repository, Movie movie) throws Exception {
        StrictHashSet<Movie> oldMovies = new StrictHashSet<>(repository.movies);
        Movie newMovie = movie.withId(oldMovies.size() + 1);

        if(oldMovies.contains(newMovie))
            return new MovieRepository(oldMovies);

        return new MovieRepository(oldMovies.with(newMovie));
    }

    public static MovieRepository updateById(MovieRepository repository, Integer id, Movie movie) throws Exception {
        StrictHashSet<Movie> oldMovies = new StrictHashSet<>(repository.movies);
        Optional<Movie> oldMovie = findById(repository, id);

        if (oldMovie.isEmpty())
            return new MovieRepository(oldMovies);

        Movie newMovie = oldMovie.get().withTitle(movie.getTitle()).withDurationInMin(movie.getDurationInMin()).withGenre(movie.getGenre()).withRating(movie.getRating());

        StrictHashSet<Movie> updatedMovies = new StrictHashSet<>();
        oldMovies.stream().map(m -> Objects.equals(m.getId(), movie.getId()) ? newMovie : m).forEach(updatedMovies::add);

        return new MovieRepository(updatedMovies);
    }

    public static MovieRepository deleteById(MovieRepository repository, Integer id) throws Exception {
        StrictHashSet<Movie> oldMovies = new StrictHashSet<>(repository.movies);
        Optional<Movie> movieOpt = findById(repository, id);

        return movieOpt.map(movie -> new MovieRepository(oldMovies.without(movie))).orElseGet(() -> new MovieRepository(oldMovies));
    }
}
