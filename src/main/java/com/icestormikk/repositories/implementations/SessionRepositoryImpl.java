package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Session;
import com.icestormikk.repositories.SessionRepository;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SessionRepositoryImpl implements SessionRepository {
    private final StrictHashSet<Session> sessions;
    private final AtomicInteger nextId;

    public SessionRepositoryImpl() {
        this.sessions = new StrictHashSet<>();
        this.nextId = new AtomicInteger(0);
    }

    private SessionRepositoryImpl(StrictHashSet<Session> sessions, AtomicInteger nextId) {
        this.sessions = sessions;
        this.nextId = nextId;
    }

    @Override
    public Set<Session> findAll() {
        return Set.copyOf(sessions);
    }

    @Override
    public Optional<Session> findById(int id) {
        return sessions.stream().filter((session) -> session.getId() == id).findFirst();
    }

    @Override
    public SessionRepository save(Session session) throws Exception {
        Session newSession = session.withId(nextId.getAndIncrement());

        if(sessions.contains(newSession))
            throw new Exception("Session object with such data already exists");

        StrictHashSet<Session> newSessions = new StrictHashSet<>(sessions);
        newSessions.add(newSession);

        return new SessionRepositoryImpl(newSessions, nextId);
    }

    @Override
    public SessionRepository updateById(Integer id, Session session) throws Exception {
        Optional<Session> oldSession = findById(id);

        if (oldSession.isEmpty())
            throw new Exception("Session object with such data not found");

        Session newSession = oldSession.get().withStartTime(session.getStartTime()).withEndTime(session.getEndTime())
                .withBookedSeats(session.getBookedSeats()).withMovieId(session.getMovieId()).withHallId(session.getHallId());

        StrictHashSet<Session> updatedSessions = new StrictHashSet<>();
        sessions.stream().map(s -> Objects.equals(s.getId(), session.getId()) ? newSession : s).forEach(updatedSessions::add);

        return new SessionRepositoryImpl(updatedSessions, nextId);
    }

    @Override
    public SessionRepository deleteById(Integer id) throws Exception {
        Optional<Session> sessionOpt = findById(id);

        if (sessionOpt.isEmpty())
            throw new Exception("Movie object with such id not found");

        StrictHashSet<Session> updatedSessions = new StrictHashSet<>();
        sessions.stream().filter(s -> !Objects.equals(s.getId(), sessionOpt.get().getId())).forEach(updatedSessions::add);

        return new SessionRepositoryImpl(updatedSessions, nextId);

    }
}
