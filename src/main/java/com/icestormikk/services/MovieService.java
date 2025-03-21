package com.icestormikk.services;

import com.icestormikk.domain.cinema.Movie;

import java.time.Duration;

public interface MovieService extends IService<Movie, Integer> {
    Movie getByTitle(String title);
    MovieService create(String title, String genre, Duration duration, Float rating) throws Exception;
}
