package com.icestormikk.services;

import com.icestormikk.domain.cinema.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie addMovie(String title, String genre, int durationMin);
    Movie updateMovieById(Long id, String title, String genre, int durationMin) throws Exception;
    void deleteMovieById(Long id);
}
