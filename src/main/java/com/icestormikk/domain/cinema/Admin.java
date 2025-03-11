package com.icestormikk.domain.cinema;

import com.icestormikk.utils.StrictHashSet;

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
        this.cinemas = new StrictHashSet<>();
    }

    public Set<Cinema> getCinemas() {
        return cinemas;
    }

    public Admin setCinemas(StrictHashSet<Cinema> cinemas) {
        this.cinemas = cinemas;
        return this;
    }
}
