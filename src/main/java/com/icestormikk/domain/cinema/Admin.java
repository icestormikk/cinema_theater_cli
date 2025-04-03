package com.icestormikk.domain.cinema;

import com.icestormikk.utils.StrictHashSet;

/**
 * Администратор кинотеатра
 */
public class Admin extends User {
    /**
     * Кинотеатры, которыми он может управлять
     */
    private final StrictHashSet<Integer> cinemaIds;

    /**
     * Конструктор класса Admin. Создаёт нового Администратора кинотеатра
     * @param firstName Имя администратора
     * @param lastName Фамилия администратора
     * @param username Имя пользователя
     */
    public Admin(Integer id, String firstName, String lastName, String username, StrictHashSet<Integer> ticketIds, StrictHashSet<Integer> cinemaIds) {
        super(id, firstName, lastName, username, ticketIds);
        this.cinemaIds = new StrictHashSet<>(cinemaIds);
    }

    public Admin(String firstName, String lastName, String username, StrictHashSet<Integer> ticketIds, StrictHashSet<Integer> cinemaIds) {
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
    public Admin withTicketIds(StrictHashSet<Integer> ticketIds) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), ticketIds, getCinemaIds());
    }

    @Override
    public Admin addTicketId(Integer ticketId) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds().with(ticketId), getCinemaIds());
    }

    @Override
    public Admin removeTicketId(Integer ticketId) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds().without(ticketId), getCinemaIds());
    }

    public StrictHashSet<Integer> getCinemaIds() {
        return new StrictHashSet<>(cinemaIds);
    }

    public Admin withCinemaIds(StrictHashSet<Integer> cinemaIds) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), cinemaIds);
    }

    public Admin addCinemaId(Integer id) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), cinemaIds.with(id));
    }

    public Admin removeCinemaId(Integer id) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), cinemaIds.without(id));
    }
}
