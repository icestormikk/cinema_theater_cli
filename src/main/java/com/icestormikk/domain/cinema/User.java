package com.icestormikk.domain.cinema;

import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;

/**
 * Класс, представляющий пользователя системы.
 */
public class User {
    private final Integer id;
    /** Имя пользователя. */
    private final String firstName;
    /** Фамилия пользователя. */
    private final String lastName;
    /** Никнейм пользователя. */
    private final String username;
    /** Список билетов, забронированных пользователем. */
    private final StrictHashSet<Integer> ticketIds;

    /**
     * Конструктор для создания пользователя.
     * @param firstName Имя пользователя.
     * @param lastName Фамилия пользователя.
     * @param username Никнейм пользователя.
     */
    public User(Integer id, String firstName, String lastName, String username, StrictHashSet<Integer> ticketIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.ticketIds = new StrictHashSet<>(ticketIds);
    }

    public User(String firstName, String lastName, String username, StrictHashSet<Integer> ticketIds) {
        this(null, firstName, lastName, username, ticketIds);
    }

    public User(String firstName, String lastName, String username) {
        this(null, firstName, lastName, username, new StrictHashSet<>());
    }

    /**
     * Получить идентификатор пользователя
     * @return Идентификатор пользователя
     */
    public Integer getId() {
        return id;
    }

    /**
     * Обновить идентификатор пользователя
     * @param id Новый идентификатор пользователя
     * @return Оригинальный объект класса User c обновлённым идентификатором
     */
    public User withId(Integer id) {
        return new User(id, firstName, lastName, username, ticketIds);
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
    public User withFirstName(String firstName) {
        return new User(id, firstName, lastName, username, ticketIds);
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
    public User withLastName(String lastName) {
        return new User(id, firstName, lastName, username, ticketIds);
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
    public User withUsername(String username) {
        return new User(id, firstName, lastName, username, ticketIds);
    }

    /**
     * Возвращает список билетов пользователя.
     * @return Список билетов пользователя.
     */
    public StrictHashSet<Integer> getTicketIds() {
        return new StrictHashSet<>(ticketIds);
    }

    /**
     * Обновляет список билетов пользователя.
     * @param ticketIds Список билетов пользователя
     * @return Оригинальный объект класса User с новым списком билетов
     */
    public User withTicketIds(StrictHashSet<Integer> ticketIds) {
        return new User(id, firstName, lastName, username, ticketIds);
    }

    public User addTicketId(Integer ticketId) {
        return new User(id, firstName, lastName, username, ticketIds.with(ticketId));
    }

    public User removeTicketId(Integer ticketId) {
        return new User(id, firstName, lastName, username, ticketIds.without(ticketId));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
