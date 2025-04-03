package com.icestormikk.domain.cinema;

import com.icestormikk.utils.SafeHashSet;

/**
 * Администратор кинотеатра
 */
public class Admin extends User {
    /**
     * Кинотеатры, которыми он может управлять
     */
    private final SafeHashSet<Integer> cinemaIds;

    /**
     * Конструктор класса Admin. Создаёт нового Администратора кинотеатра
     * @param firstName Имя администратора
     * @param lastName Фамилия администратора
     * @param username Имя пользователя
     */
    public Admin(Integer id, String firstName, String lastName, String username, SafeHashSet<Integer> ticketIds, SafeHashSet<Integer> cinemaIds) {
        super(id, firstName, lastName, username, ticketIds);
        this.cinemaIds = new SafeHashSet<>(cinemaIds);
    }

    public Admin(String firstName, String lastName, String username, SafeHashSet<Integer> ticketIds, SafeHashSet<Integer> cinemaIds) {
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
    public Admin withTicketIds(SafeHashSet<Integer> ticketIds) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), ticketIds, getCinemaIds());
    }

    @Override
    public Admin addTicketId(Integer ticketId) {
        getTicketIds();
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), SafeHashSet.with(getTicketIds(), ticketId), getCinemaIds());
    }

    @Override
    public Admin removeTicketId(Integer ticketId) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), SafeHashSet.without(getTicketIds(), ticketId), getCinemaIds());
    }

    public SafeHashSet<Integer> getCinemaIds() {
        return new SafeHashSet<>(cinemaIds);
    }

    public Admin withCinemaIds(SafeHashSet<Integer> cinemaIds) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), cinemaIds);
    }

    public Admin addCinemaId(Integer id) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), SafeHashSet.with(getTicketIds(), id));
    }

    public Admin removeCinemaId(Integer id) {
        return new Admin(getId(), getFirstName(), getLastName(), getUsername(), getTicketIds(), SafeHashSet.without(getCinemaIds(), id));
    }
}
