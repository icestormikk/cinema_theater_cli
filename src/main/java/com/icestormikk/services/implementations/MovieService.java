package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.repositories.implementations.MovieRepository;
import com.icestormikk.utils.StrictHashSet;

import java.time.Duration;
import java.util.Set;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public static MovieService create(MovieService service, String title, String genre, Duration duration, Float rating) throws Exception {
        Movie movie = new Movie(null, title, genre, duration, rating);
        return new MovieService(MovieRepository.save(service.movieRepository, movie));
    }

    public static StrictHashSet<Movie> getAll(MovieService service) throws Exception {
        return MovieRepository.findAll(service.movieRepository);
    }

    public static Movie getById(MovieService service, int id) {
        return MovieRepository.findById(service.movieRepository, id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public static Movie getByTitle(MovieService service, String title) {
        return MovieRepository.findAll(service.movieRepository).stream()
            .filter((movie) -> movie.getTitle().equals(title))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public static MovieService updateById(MovieService service, Integer id, Movie movie) throws Exception {
        return new MovieService(MovieRepository.updateById(service.movieRepository, id ,movie));
    }

    public static MovieService deleteById(MovieService service, Integer id) throws Exception {
        return new MovieService(MovieRepository.deleteById(service.movieRepository, id));
    }
}
