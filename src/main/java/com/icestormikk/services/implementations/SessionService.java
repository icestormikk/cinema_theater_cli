package com.icestormikk.services.implementations;

import com.icestormikk.domain.cinema.Session;
import com.icestormikk.repositories.implementations.SessionRepository;
import com.icestormikk.utils.SafeHashSet;

import java.time.LocalDateTime;
import java.util.Set;

public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public static SessionService create(SessionService service, Integer movieId, Integer hallId, LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        Session session = new Session(null, movieId, hallId, startTime, endTime, new SafeHashSet<>());
        SessionRepository updatedRepo = SessionRepository.save(service.sessionRepository, session);
        return new SessionService(updatedRepo);
    }

    public static Set<Session> getAll(SessionService service) throws Exception {
        return SessionRepository.findMany(service.sessionRepository, (s) -> true);
    }

    public static Session getById(SessionService service, int id) {
        return SessionRepository.findOne(service.sessionRepository, (s) -> s.getId() == id).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public static SafeHashSet<Session> getByMovieId(SessionService service, Integer movieId) {
        return SessionRepository.findMany(service.sessionRepository, (s) -> s.getMovieId().equals(movieId));
    }

    public static Session getByMovieIdAndHallId(SessionService service, Integer movieId, Integer hallId) {
        return SessionRepository.findOne(service.sessionRepository, (s) -> s.getMovieId().equals(movieId) && s.getHallId().equals(hallId)).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public static SessionService updateById(SessionService service, Integer id, Session entity) throws Exception {
        return new SessionService(SessionRepository.updateById(service.sessionRepository, id, entity));
    }

    public static SessionService deleteById(SessionService service, Integer id) throws Exception {
        return new SessionService(SessionRepository.deleteById(service.sessionRepository, id));
    }
}
