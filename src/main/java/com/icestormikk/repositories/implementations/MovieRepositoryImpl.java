package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.repositories.MovieRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieRepositoryImpl implements MovieRepository {
    private final StrictHashSet<Movie> movies;
    private final AtomicInteger nextId;

    public MovieRepositoryImpl() {
        this.movies = new StrictHashSet<>();
        this.nextId = new AtomicInteger(0);
    }

    private MovieRepositoryImpl(StrictHashSet<Movie> movies, AtomicInteger nextId) {
        this.movies = movies;
        this.nextId = nextId;
    }

    @Override
    public Set<Movie> findAll() {
        return Set.copyOf(movies);
    }

    @Override
    public Optional<Movie> findById(int id) {
        return movies.stream().filter((movie) -> movie.getId() == id).findFirst();
    }

    @Override
    public MovieRepository save(Movie movie) throws Exception {
        Movie newMovie = movie.withId(nextId.getAndIncrement());

        if(movies.contains(newMovie))
            throw new Exception("Movie object with such data already exists");

        StrictHashSet<Movie> newMovies = new StrictHashSet<>(movies);
        newMovies.add(newMovie);

        return new MovieRepositoryImpl(newMovies, nextId);
    }

    @Override
    public MovieRepository updateById(Integer id, Movie movie) throws Exception {
        Optional<Movie> oldMovie = findById(id);

        if (oldMovie.isEmpty())
            throw new Exception("Movie object with such data not found");

        Movie newMovie = oldMovie.get().withTitle(movie.getTitle()).withDurationInMin(movie.getDurationInMin()).withGenre(movie.getGenre()).withRating(movie.getRating());

        StrictHashSet<Movie> updatedMovies = new StrictHashSet<>();
        for (Movie m : movies)
            updatedMovies.add(Objects.equals(m.getId(), movie.getId()) ? newMovie : m);

        return new MovieRepositoryImpl(updatedMovies, nextId);
    }

    @Override
    public MovieRepository deleteById(Integer id) throws Exception {
        Optional<Movie> movieOpt = findById(id);

        if (movieOpt.isEmpty())
            throw new Exception("Movie object with such id not found");

        StrictHashSet<Movie> updatedMovies = new StrictHashSet<>();
        for (Movie m : movies)
            if(!Objects.equals(m.getId(), movieOpt.get().getId()))
                updatedMovies.add(m);

        return new MovieRepositoryImpl(updatedMovies, nextId);
    }
}
