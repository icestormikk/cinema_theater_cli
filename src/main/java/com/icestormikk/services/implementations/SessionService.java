package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Session;
import com.icestormikk.repositories.implementations.SessionRepository;
import com.icestormikk.utils.StrictHashSet;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public static SessionService create(SessionService service, Integer movieId, Integer hallId, LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        Session session = new Session(null, movieId, hallId, startTime, endTime, new StrictHashSet<>());
        SessionRepository updatedRepo = SessionRepository.save(service.sessionRepository, session);
        return new SessionService(updatedRepo);
    }

    public static Set<Session> getAll(SessionService service) throws Exception {
        return SessionRepository.findAll(service.sessionRepository);
    }

    public static Session getById(SessionService service, int id) {
        return SessionRepository.findById(service.sessionRepository, id).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public static Set<Session> getByMovieId(SessionService service, Integer movieId) {
        return SessionRepository.findAll(service.sessionRepository).stream().filter((session) -> session.getMovieId().equals(movieId)).collect(Collectors.toSet());
    }

    public static Session getByMovieIdAndHallId(SessionService service, Integer movieId, Integer hallId) {
        return SessionRepository.findAll(service.sessionRepository).stream().filter((session) -> session.getMovieId().equals(movieId) && session.getHallId().equals(hallId)).findFirst().orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public static SessionService updateById(SessionService service, Integer id, Session entity) throws Exception {
        return new SessionService(SessionRepository.updateById(service.sessionRepository, id, entity));
    }

    public static SessionService deleteById(SessionService service, Integer id) throws Exception {
        return new SessionService(SessionRepository.deleteById(service.sessionRepository, id));
    }
}
