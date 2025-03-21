package com.icestormikk.domain.cinema;

import com.icestormikk.utils.StrictHashSet;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Класс, описывающий кинотеатры, в котором можно посещать киносеансы
 */
public class Cinema {
    private Integer id;
    /** Название кинотеатра. */
    private final String title;
    /** Адрес кинотеатра. */
    private final String address;
    /** Список залов в кинотеатре. */
    private final Set<Integer> hallIds;
    /** Список фильмов, доступных в кинотеатре. */
    private final Set<Integer> movieIds;
    /** Список сеансов в кинотеатре. */
    private final Set<Integer> sessionIds;

    /**
     * Конструктор для создания кинотеатра.
     *
     * @param title   Название кинотеатра.
     * @param address Адрес кинотеатра.
     */
    public Cinema(Integer id, String title, String address, Set<Integer> hallIds, Set<Integer> movieIds, Set<Integer> sessionIds) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.hallIds = Collections.unmodifiableSet(hallIds);
        this.movieIds = Collections.unmodifiableSet(movieIds);
        this.sessionIds = Collections.unmodifiableSet(sessionIds);
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
        this.id = id;
        return this;
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
    public StrictHashSet<Integer> getHallIds() {
        return new StrictHashSet<>(hallIds);
    }

    /**
     * Обновляет список залов кинотеатра
     * @param hallIds Новый список залов
     * @return Оригинальный объект класса Cinema с новым списком залов
     */
    public Cinema withHallIds(StrictHashSet<Integer> hallIds) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    public Cinema addHallId(Integer hallId) {
        Set<Integer> newHallIds = new StrictHashSet<>(hallIds);
        newHallIds.add(hallId);
        return new Cinema(id, title, address, newHallIds, movieIds, sessionIds);
    }

    public Cinema removeHallId(Integer hallId) {
        Set<Integer> newHallIds = new StrictHashSet<>(hallIds);
        newHallIds.remove(hallId);
        return new Cinema(id, title, address, newHallIds, movieIds, sessionIds);
    }

    /**
     * Получает список всех фильмов, доступных в кинотеатре
     * @return Список доступных фильмов
     */
    public StrictHashSet<Integer> getMovieIds() {
        return new StrictHashSet<>(movieIds);
    }

    /**
     * Обновляет список фильмов, доступных в кинотеатре
     * @return Оригинальный объект класса Cinema с новым списком сеансов
     */
    public Cinema withMovieIds(StrictHashSet<Integer> movieIds) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    public Cinema addMovieId(Integer movieId) {
        Set<Integer> newMovieIds = new StrictHashSet<>(movieIds);
        newMovieIds.add(movieId);
        return new Cinema(id, title, address, hallIds, newMovieIds, sessionIds);
    }

    public Cinema removeMovieId(Integer movieId) {
        Set<Integer> newMovieIds = new StrictHashSet<>(movieIds);
        newMovieIds.remove(movieId);
        return new Cinema(id, title, address, hallIds, newMovieIds, sessionIds);
    }

    /**
     * Возвращает список всех сеансов в кинотеатре
     * @return Список сеансов
     */
    public StrictHashSet<Integer> getSessionIds() {
        return new StrictHashSet<>(sessionIds);
    }

    /**
     * Обновляет список сеансов в кинотеатре
     * @param sessionIds Новый список сеансов
     * @return Оригинальный объект класса Cinema с новым списком сеансов
     */
    public Cinema withSessionIds(StrictHashSet<Integer> sessionIds) {
        return new Cinema(id, title, address, hallIds, movieIds, sessionIds);
    }

    public Cinema addSessionId(Integer sessionId) {
        Set<Integer> newSessionIds = new StrictHashSet<>(sessionIds);
        newSessionIds.add(sessionId);
        return new Cinema(id, title, address, hallIds, movieIds, newSessionIds);
    }

    public Cinema removeSessionId(Integer sessionId) {
        Set<Integer> newSessionIds = new StrictHashSet<>(sessionIds);
        newSessionIds.remove(sessionId);
        return new Cinema(id, title, address, hallIds, movieIds, newSessionIds);
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
