package com.icestormikk.domain.cinema;

import java.util.LinkedList;
import java.util.List;

public class Admin extends User {
    private List<Cinema> cinemas;

    public Admin(Long id, String firstName, String lastName, String username) {
        super(id, firstName, lastName, username);
        this.cinemas = new LinkedList<>();
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public Admin setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
        return this;
    }

    public Admin addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
        return this;
    }
}
