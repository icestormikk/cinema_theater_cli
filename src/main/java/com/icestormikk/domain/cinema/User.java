package com.icestormikk.domain.cinema;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Класс, представляющий пользователя системы.
 */
public class User {
    /** Имя пользователя. */
    private String firstName;
    /** Фамилия пользователя. */
    private String lastName;
    /** Никнейм пользователя. */
    private String username;
    /** Список билетов, забронированных пользователем. */
    public List<Ticket> tickets;

    /**
     * Конструктор для создания пользователя.
     * @param firstName Имя пользователя.
     * @param lastName Фамилия пользователя.
     * @param username Никнейм пользователя.
     */
    public User(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.tickets = new LinkedList<>();
    }

    /**
     * Получить имя пользователя
     * @return Имя пользователя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Обновить имя пользователя
     * @param firstName Новое имя пользователя
     * @return Оригинальный объект класса User с новым именем пользователя
     */
    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Получить фамилию пользователя
     * @return Фамилия пользователя
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Обновить фамилию пользователя
     * @param lastName Новая фамилия пользователя
     * @return Оригинальный объект класса User с новой фамилией пользователя
     */
    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Получить никнейм пользователя
     * @return Никнейм пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Обновить фамилию пользователя
     * @param username Новый никнейм пользователя
     * @return Оригинальный объект класса User с новым никнеймом пользователя
     */
    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Возвращает список билетов пользователя.
     * @return Список билетов пользователя.
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Обновляет список билетов пользователя.
     * @param tickets Список билетов пользователя
     * @return Оригинальный объект класса User с новым списком билетов
     */
    public User setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    /**
     * Бронирует билет для пользователя.
     * @param ticket Билет для бронирования.
     */
    public void bookTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    /**
     * Отменяет бронирование билета.
     * @param ticket Билет для отмены бронирования.
     */
    public void cancelTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUsername());
    }
}
