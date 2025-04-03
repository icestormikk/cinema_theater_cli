package com.icestormikk.domain.cinema;

import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;

/**
 * Класс, описывающий кинотеатры, в котором можно посещать киносеансы
 */
public class Cinema {
    private final Integer id;
    /** Название кинотеатра. */
    private final String title;
    /** Адрес кинотеатра. */
    private final String address;
    /** Список залов в кинотеатре. */
    private final SafeHashSet<Integer> hallIds;
    /** Список фильмов, доступных в кинотеатре. */
    private final SafeHashSet<Integer> movieIds;
    /** Список сеансов в кинотеатре. */
    private final SafeHashSet<Integer> sessionIds;

    /**
     * Конструктор для создания кинотеатра.
     *
     * @param title   Название кинотеатра.
     * @param address Адрес кинотеатра.
     */
    public Cinema(Integer id, String title, String address, SafeHashSet<Integer> hallIds, SafeHashSet<Integer> movieIds, SafeHashSet<Integer> sessionIds) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.hallIds = new SafeHashSet<>(hallIds);
        this.movieIds = new SafeHashSet<>(movieIds);
        this.sessionIds = new SafeHashSet<>(sessionIds);
    }

    /**
     * Получает уникальный идентификатор кинотеатра
     * @return Уникальный идентификатор кинотеатра
     */
    public int getId() {
        return id;
    }

    /**
     * Обновляет уникальный идентификатор кинотеатра
     * @param id Новый уникальный идентификатор кинотеатра
     * @return Оригинальный объект класса Cinema c обновлённым идентификатором
     */
    public Cinema withId(int id) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    /**
     * Получает название кинотеатра
     * @return Название кинотеатра
     */
    public String getTitle() {
        return title;
    }

    /**
     * Обновляет название кинотеатра
     * @param title Новое название
     * @return Оригинальный объект класса Cinema с новым названием
     */
    public Cinema withTitle(String title) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    /**
     * Получает адрес кинотеатра
     * @return Адрес кинотеатра
     */
    public String getAddress() {
        return address;
    }

    /**
     * Обновляет адрес кинотеатра
     * @param address Новое название
     * @return Оригинальный объект класса Cinema с новым адресом
     */
    public Cinema withAddress(String address) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    /**
     * Получает список всех залов кинотеатра
     * @return Список всех залов кинотеатра
     */
    public SafeHashSet<Integer> getHallIds() {
        return new SafeHashSet<>(hallIds);
    }

    /**
     * Обновляет список залов кинотеатра
     * @param hallIds Новый список залов
     * @return Оригинальный объект класса Cinema с новым списком залов
     */
    public Cinema withHallIds(SafeHashSet<Integer> hallIds) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    public Cinema addHallId(Integer hallId) {
        return new Cinema(id, title, address, SafeHashSet.with(hallIds, hallId), movieIds, sessionIds);
    }

    public Cinema removeHallId(Integer hallId) {
        return new Cinema(id, title, address, SafeHashSet.without(hallIds, hallId), movieIds, sessionIds);
    }

    /**
     * Получает список всех фильмов, доступных в кинотеатре
     * @return Список доступных фильмов
     */
    public SafeHashSet<Integer> getMovieIds() {
        return new SafeHashSet<>(movieIds);
    }

    /**
     * Обновляет список фильмов, доступных в кинотеатре
     * @return Оригинальный объект класса Cinema с новым списком сеансов
     */
    public Cinema withMovieIds(SafeHashSet<Integer> movieIds) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    public Cinema addMovieId(Integer movieId) {
        return new Cinema(id, title, address, hallIds, SafeHashSet.with(movieIds, movieId), sessionIds);
    }

    public Cinema removeMovieId(Integer movieId) {
        return new Cinema(id, title, address, hallIds, SafeHashSet.without(movieIds, movieId), sessionIds);
    }

    /**
     * Возвращает список всех сеансов в кинотеатре
     * @return Список сеансов
     */
    public SafeHashSet<Integer> getSessionIds() {
        return new SafeHashSet<>(sessionIds);
    }

    /**
     * Обновляет список сеансов в кинотеатре
     * @param sessionIds Новый список сеансов
     * @return Оригинальный объект класса Cinema с новым списком сеансов
     */
    public Cinema withSessionIds(SafeHashSet<Integer> sessionIds) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    public Cinema addSessionId(Integer sessionId) {
        return new Cinema(id, title, address, hallIds, movieIds, SafeHashSet.with(sessionIds, sessionId));
    }

    public Cinema removeSessionId(Integer sessionId) {
        return new Cinema(id, title, address, hallIds, movieIds, SafeHashSet.without(sessionIds, sessionId));
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(getTitle(), cinema.getTitle()) && Objects.equals(getAddress(), cinema.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAddress());
    }
}
