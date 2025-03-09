package com.icestormikk.domain.cinema;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private List<Ticket> tickets;

    public User(Long id, String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.tickets = new LinkedList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public User setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    public void bookTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void cancelTicket(Ticket ticket) {
        tickets.remove(ticket);
    }
}
