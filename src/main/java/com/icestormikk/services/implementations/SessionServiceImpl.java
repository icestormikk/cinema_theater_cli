package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Session;
import com.icestormikk.repositories.SessionRepository;
import com.icestormikk.services.SessionService;
import com.icestormikk.utils.StrictHashSet;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public SessionService create(Integer movieId, Integer hallId, LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        Session session = new Session(null, movieId, hallId, startTime, endTime, new StrictHashSet<>());
        SessionRepository updatedRepo = (SessionRepository) sessionRepository.save(session);
        return new SessionServiceImpl(updatedRepo);
    }

    @Override
    public Set<Session> getAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session getById(int id) {
        return sessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    @Override
    public Set<Session> getByMovieId(Integer movieId) {
        return sessionRepository.findAll().stream().filter((session) -> session.getMovieId().equals(movieId)).collect(Collectors.toSet());
    }

    @Override
    public Session getByMovieIdAndHallId(Integer movieId, Integer hallId) {
        return sessionRepository.findAll().stream().filter((session) -> session.getMovieId().equals(movieId) && session.getHallId().equals(hallId)).findFirst().orElseThrow(() -> new RuntimeException("Session not found"));
    }

    @Override
    public SessionService updateById(Integer id, Session entity) throws Exception {
        SessionRepository updatedRepo = (SessionRepository) sessionRepository.updateById(id, entity);
        return new SessionServiceImpl(updatedRepo);
    }

    @Override
    public SessionService deleteById(Integer id) throws Exception {
        SessionRepository updatedRepo = (SessionRepository) sessionRepository.deleteById(id);
        return new SessionServiceImpl(updatedRepo);
    }
}
