package com.icestormikk.domain.cinema;

import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Set;

/**
 * Класс, описывающий кинотеатры, в котором можно посещать киносеансы
 */
public class Cinema {
    private int id;
    /** Название кинотеатра. */
    private String title;
    /** Адрес кинотеатра. */
    private String address;
    /** Список залов в кинотеатре. */
    private Set<Hall> halls;
    /** Список фильмов, доступных в кинотеатре. */
    private Set<Movie> movies;
    /** Список сеансов в кинотеатре. */
    private Set<Session> sessions;

    /**
     * Конструктор для создания кинотеатра.
     *
     * @param title   Название кинотеатра.
     * @param address Адрес кинотеатра.
     */
    public Cinema(Integer id, String title, String address) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.halls = new StrictHashSet<>();
        this.movies = new StrictHashSet<>();
        this.sessions = new StrictHashSet<>();
    }

    public Cinema(String title, String address) {
        this(null, title, address);
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
    public Cinema setId(int id) {
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
    public Set<Hall> getHalls() {
        return halls;
    }

    public Hall getHallByNumber(int number) {
        for (Hall hall : halls) {
            if(hall.getHallNumber() == number)
                return hall;
        }

        throw new RuntimeException("No Hall with number " + number + " found");
    }

    /**
     * Обновляет список залов кинотеатра
     * @param halls Новый список залов
     * @return Оригинальный объект класса Cinema с новым списком залов
     */
    public Cinema setHalls(Set<Hall> halls) {
        this.halls = halls;
        return this;
    }

    /**
     * Добавляет зал в кинотеатр.
     * @param hall Зал для добавления.
     */
    public Cinema addHall(Hall hall) throws Exception {
        this.halls.add(hall);
        return this;
    }

    /**
     * Получает список всех фильмов, доступных в кинотеатре
     * @return Список доступных фильмов
     */
    public Set<Movie> getMovies() {
        return movies;
    }

    public Movie getMovieByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Movie with such name not found"));
    }

    /**
     * Обновляет список фильмов, доступных в кинотеатре
     * @return Оригинальный объект класса Cinema с новым списком сеансов
     */
    public Cinema setMovies(Set<Movie> movies) {
        this.movies = movies;
        return this;
    }

    /**
     * Добавляет фильм в кинотеатр.
     * @param movie Фильм для добавления.
     */
    public Cinema addMovie(Movie movie) throws Exception {
        this.movies.add(movie);
        return this;
    }

    /**
     * Возвращает список всех сеансов в кинотеатре
     * @return Список сеансов
     */
    public Set<Session> getSessions() {
        return sessions;
    }

    /**
     * Обновляет список сеансов в кинотеатре
     * @param sessions Новый список сеансов
     * @return Оригинальный объект класса Cinema с новым списком сеансов
     */
    public Cinema setSessions(Set<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    /**
     * Добавляет сеанс в кинотеатр.
     * @param session Сеанс для добавления.
     */
    public Cinema addSession(Session session) throws Exception {
        this.sessions.add(session);
        return this;
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
