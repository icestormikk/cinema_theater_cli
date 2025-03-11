package com.icestormikk.domain.cinema;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Класс, описывающий кинотеатры, в котором можно посещать киносеансы
 */
public class Cinema {
    /** Название кинотеатра. */
    private String title;
    /** Адрес кинотеатра. */
    private String address;
    /** Список залов в кинотеатре. */
    private List<Hall> halls;
    /** Список фильмов, доступных в кинотеатре. */
    private List<Movie> movies;
    /** Список сеансов в кинотеатре. */
    private List<Session> sessions;

    /**
     * Конструктор для создания кинотеатра.
     *
     * @param title    Название кинотеатра.
     * @param address Адрес кинотеатра.
     */
    public Cinema(String title, String address) {
        this.title = title;
        this.address = address;
        this.halls = new LinkedList<>();
        this.movies = new LinkedList<>();
        this.sessions = new LinkedList<>();
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
    public Cinema setTitle(String title) {
        this.title = title;
        return this;
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
    public Cinema setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Получает список всех залов кинотеатра
     * @return Список всех залов кинотеатра
     */
    public List<Hall> getHalls() {
        return halls;
    }

    /**
     * Обновляет список залов кинотеатра
     * @param halls Новый список залов
     * @return Оригинальный объект класса Cinema с новым списком залов
     */
    public Cinema setHalls(List<Hall> halls) {
        this.halls = halls;
        return this;
    }

    /**
     * Добавляет зал в кинотеатр.
     * @param hall Зал для добавления.
     */
    public Cinema addHall(Hall hall) {
        this.halls.add(hall);
        return this;
    }

    /**
     * Получает список всех фильмов, доступных в кинотеатре
     * @return Список доступных фильмов
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Обновляет список фильмов, доступных в кинотеатре
     * @return Оригинальный объект класса Cinema с новым списком сеансов
     */
    public Cinema setMovies(List<Movie> movies) {
        this.movies = movies;
        return this;
    }

    /**
     * Добавляет фильм в кинотеатр.
     * @param movie Фильм для добавления.
     */
    public Cinema addMovie(Movie movie) {
        this.movies.add(movie);
        return this;
    }

    /**
     * Возвращает список всех сеансов в кинотеатре
     * @return Список сеансов
     */
    public List<Session> getSessions() {
        return sessions;
    }

    /**
     * Обновляет список сеансов в кинотеатре
     * @param sessions Новый список сеансов
     * @return Оригинальный объект класса Cinema с новым списком сеансов
     */
    public Cinema setSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    /**
     * Добавляет сеанс в кинотеатр.
     * @param session Сеанс для добавления.
     */
    public Cinema addSession(Session session) {
        this.sessions.add(session);
        return this;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "title='" + title + '\'' +
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
