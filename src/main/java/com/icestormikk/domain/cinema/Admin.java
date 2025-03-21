package com.icestormikk.domain.cinema;

import com.icestormikk.utils.StrictHashSet;

import java.util.Collections;
import java.util.Set;

/**
 * Администратор кинотеатра
 */
public class Admin extends User {
    /**
     * Кинотеатры, которыми он может управлять
     */
    private final Set<Integer> cinemaIds;

    /**
     * Конструктор класса Admin. Создаёт нового Администратора кинотеатра
     * @param firstName Имя администратора
     * @param lastName Фамилия администратора
     * @param username Имя пользователя
     */
    public Admin(Integer id, String firstName, String lastName, String username, Set<Integer> ticketIds, Set<Integer> cinemaIds) {
        super(id, firstName, lastName, username, ticketIds);
        this.cinemaIds = Collections.unmodifiableSet(cinemaIds);
    }

    public Admin(String firstName, String lastName, String username, Set<Integer> ticketIds, Set<Integer> cinemaIds) {
        this(null, firstName, lastName, username, ticketIds, cinemaIds);
    }

    @Override
    public Admin withId(Integer id) {
        return new Admin(id, getFirstName(), getLastName(), getUsername(), getTicketIds(), getCinemaIds());
    }

    @Override
    public Admin withFirstName(String firstName) {
        return new Admin(getId(), firstName, getLastName(), getUsername(), getTicketIds(), getCinemaIds());
    }

    @Override
    public Admin withLastName(String lastName) {
        return new Admin(getId(), getFirstName(), lastName, getUsername(), getTicketIds(), getCinemaIds());
    }

    @Override
    public Admin withUsername(String username) {
        return new Admin(getId(), getFirstName(), getLastName(), username, getTicketIds(), getCinemaIds());
    }

    @Override
    public Admin withTicketIds(Set<Integer> ticketIds) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), ticketIds, getCinemaIds());
    }

    @Override
    public Admin addTicketId(Integer ticketId) {
        StrictHashSet<Integer> newTicketIds = new StrictHashSet<>(getTicketIds());
        newTicketIds.add(ticketId);
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), newTicketIds, getCinemaIds());
    }

    @Override
    public Admin removeTicketId(Integer ticketId) {
        StrictHashSet<Integer> newTicketIds = new StrictHashSet<>(getTicketIds());
        newTicketIds.remove(ticketId);
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), newTicketIds, getCinemaIds());
    }

    public StrictHashSet<Integer> getCinemaIds() {
        return new StrictHashSet<>(cinemaIds);
    }

    public Admin withCinemaIds(StrictHashSet<Integer> cinemaIds) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), cinemaIds);
    }

    public Admin addCinemaId(Integer id) {
        StrictHashSet<Integer> newCinemaIds = new StrictHashSet<>(cinemaIds);
        newCinemaIds.add(id);
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), newCinemaIds);
    }

    public Admin removeCinemaId(Integer id) {
        StrictHashSet<Integer> newCinemaIds = new StrictHashSet<>(cinemaIds);
        newCinemaIds.remove(id);
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), newCinemaIds);
    }
}
