package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.repositories.implementations.MovieRepository;
import com.icestormikk.utils.SafeHashSet;

import java.time.Duration;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public static MovieService create(MovieService service, String title, String genre, Duration duration, Float rating) throws Exception {
        Movie movie = new Movie(null, title, genre, duration, rating);
        return new MovieService(MovieRepository.save(service.movieRepository, movie));
    }

    public static SafeHashSet<Movie> getAll(MovieService service) throws Exception {
        return MovieRepository.findMany(service.movieRepository, (m) -> true);
    }

    public static Movie getById(MovieService service, int id) {
        return MovieRepository.findOne(service.movieRepository, (m) -> m.getId() == id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public static Movie getByTitle(MovieService service, String title) {
        return MovieRepository.findOne(service.movieRepository, (m) -> m.getTitle().equals(title)).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public static MovieService updateById(MovieService service, Integer id, Movie movie) throws Exception {
        return new MovieService(MovieRepository.updateById(service.movieRepository, id ,movie));
    }

    public static MovieService deleteById(MovieService service, Integer id) throws Exception {
        return new MovieService(MovieRepository.deleteById(service.movieRepository, id));
    }
}
