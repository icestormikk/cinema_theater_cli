package com.icestormikk.domain.cinema;

import java.util.HashSet;
import java.util.Set;

/**
 * Администратор кинотеатра
 */
public class Admin extends User {
    /**
     * Кинотеатры, которыми он может управлять
     */
    private Set<Cinema> cinemas;

    /**
     * Конструктор класса Admin. Создаёт нового Администратора кинотеатра
     * @param firstName Имя администратора
     * @param lastName Фамилия администратора
     * @param username Имя пользователя
     */
    public Admin(String firstName, String lastName, String username) {
        super(firstName, lastName, username);
        this.cinemas = new HashSet<>();
    }

    public Set<Cinema> getCinemas() {
        return cinemas;
    }

    public Admin setCinemas(Set<Cinema> cinemas) {
        this.cinemas = cinemas;
        return this;
    }

    public Admin addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
        return this;
    }
}
