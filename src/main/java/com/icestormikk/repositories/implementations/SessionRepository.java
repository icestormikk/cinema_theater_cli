package com.icestormikk.repositories.implementations;

import com.icestormikk.domain.cinema.Session;
import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class SessionRepository {
    private final StrictHashSet<Session> sessions;

    public SessionRepository() {
        this.sessions = new StrictHashSet<>();
    }

    private SessionRepository(StrictHashSet<Session> sessions) {
        this.sessions = sessions;
    }

    public static Set<Session> findAll(SessionRepository repository) {
        return new StrictHashSet<>(repository.sessions);
    }

    public static Optional<Session> findById(SessionRepository repository, int id) {
        return repository.sessions.stream().filter((session) -> session.getId() == id).findFirst();
    }

    public static SessionRepository save(SessionRepository repository, Session session) throws Exception {
        StrictHashSet<Session> oldSessions = new StrictHashSet<>(repository.sessions);
        Session newSession = session.withId(oldSessions.size() + 1);

        if(oldSessions.contains(newSession))
            return new SessionRepository(oldSessions);

        return new SessionRepository(oldSessions.with(newSession));
    }

    public static SessionRepository updateById(SessionRepository repository, Integer id, Session session) throws Exception {
        StrictHashSet<Session> oldSessions = new StrictHashSet<>(repository.sessions);
        Optional<Session> oldSession = findById(repository, id);

        if (oldSession.isEmpty())
            return new SessionRepository(oldSessions);

        Session newSession = oldSession.get().withStartTime(session.getStartTime()).withEndTime(session.getEndTime())
                .withBookedSeats(session.getBookedSeats()).withMovieId(session.getMovieId()).withHallId(session.getHallId());

        StrictHashSet<Session> updatedSessions = new StrictHashSet<>();
        oldSessions.stream().map(s -> Objects.equals(s.getId(), session.getId()) ? newSession : s).forEach(updatedSessions::add);

        return new SessionRepository(updatedSessions);
    }

    public static SessionRepository deleteById(SessionRepository repository, Integer id) throws Exception {
        StrictHashSet<Session> oldSessions = new StrictHashSet<>(repository.sessions);
        Optional<Session> sessionOpt = findById(repository, id);

        return sessionOpt.map(session -> new SessionRepository(oldSessions.without(session))).orElseGet(() -> new SessionRepository(oldSessions));

    }
}
