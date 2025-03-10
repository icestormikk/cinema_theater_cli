package com.icestormikk.domain.cinema;

import java.util.LinkedList;
import java.util.List;

public class Cinema {
    private String title;
    private String address;
    private List<Hall> halls;
    private List<Movie> movies;
    private List<Session> sessions;

    public Cinema(String name, String address) {
        this.title = name;
        this.address = address;
        this.halls = new LinkedList<>();
        this.movies = new LinkedList<>();
        this.sessions = new LinkedList<>();
    }

    public String getTitle() {
        return title;
    }

    public Cinema setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Cinema setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public Cinema setHalls(List<Hall> halls) {
        this.halls = halls;
        return this;
    }

    public Cinema addHall(Hall hall) {
        this.halls.add(hall);
        return this;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Cinema setMovies(List<Movie> movies) {
        this.movies = movies;
        return this;
    }

    public Cinema addMovie(Movie movie) {
        this.movies.add(movie);
        return this;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Cinema setSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    public Cinema addSession(Session session) {
        this.sessions.add(session);
        return this;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
