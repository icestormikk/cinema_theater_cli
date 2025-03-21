package com.icestormikk.services;

import com.icestormikk.domain.cinema.Session;

import java.time.LocalDateTime;
import java.util.Set;

public interface SessionService extends IService<Session, Integer> {
    Set<Session> getByMovieId(Integer movieId);
    Session getByMovieIdAndHallId(Integer movieId, Integer hallId);
    SessionService create(Integer movieId, Integer hallId, LocalDateTime startTime, LocalDateTime endTime) throws Exception;
}
