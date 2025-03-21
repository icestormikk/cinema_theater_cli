package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Movie;
import com.icestormikk.repositories.MovieRepository;
import com.icestormikk.services.MovieService;

import java.time.Duration;
import java.util.Set;

public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieService create(String title, String genre, Duration duration, Float rating) throws Exception {
        Movie movie = new Movie(null, title, genre, duration, rating);
        MovieRepository repository = (MovieRepository) movieRepository.save(movie);
        return new MovieServiceImpl(repository);
    }

    @Override
    public Set<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(int id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public Movie getByTitle(String title) {
        return movieRepository.findAll().stream()
            .filter((movie) -> movie.getTitle().equals(title))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public MovieService updateById(Integer id, Movie movie) throws Exception {
        MovieRepository updatedRepo = (MovieRepository) movieRepository.updateById(id ,movie);
        return new MovieServiceImpl(updatedRepo);
    }

    @Override
    public MovieService deleteById(Integer id) throws Exception {
        MovieRepository updatedRepo = (MovieRepository) movieRepository.deleteById(id);
        return new MovieServiceImpl(updatedRepo);
    }
}
