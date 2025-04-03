package com.icestormikk.domain.cinema;

import com.icestormikk.utils.SafeHashSet;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Класс, представляющий сеанс в кинотеатре.
 */
public class Session {
    private final Integer id;
    /** Фильм, который показывают на сеансе. */
    private final Integer movieId;
    /** Зал, в котором проходит сеанс. */
    private final Integer hallId;
    /** Время начала сеанса. */
    private final LocalDateTime startTime;
    /** Время окончания сеанса. */
    private final LocalDateTime endTime;
    /** Список забронированных мест на сеанс. */
    private final Set<Integer> bookedSeats;

    /**
     * Конструктор для создания сеанса.
     *
     * @param movieId     Фильм, который показывают на сеансе.
     * @param hallId    Зал, в котором проходит сеанс.
     * @param startTime Время начала сеанса.
     * @param endTime   Время окончания сеанса.
     */
    public Session(Integer id, Integer movieId, Integer hallId, LocalDateTime startTime, LocalDateTime endTime, Set<Integer> bookedSeats) {
        this.id = id;
        this.movieId = movieId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedSeats = Collections.unmodifiableSet(new SafeHashSet<>(bookedSeats));
    }

    /**
     * Получить идентификатор сессии
     * @return Идентификатор сессии
     */
    public int getId() {
        return id;
    }

    /**
     * Обновить идентификатор сессии
     * @param id Новый идентификатор сессии
     * @return Оригинальный объект класса Session c обновлённым идентификатором
     */
    public Session withId(int id) {
        return new Session(id, movieId, hallId, startTime, endTime, bookedSeats);
    }

    /**
     * Получить фильм, который показывают на сеансе.
     * @return Фильм, который показывают на сеансе.
     */
    public Integer getMovieId() {
        return movieId;
    }

    /**
     * Обновить фильм, который показывают на сеансе.
     * @param movieId Новый фильм
     * @return Оригинальный объект класса Session c новым значением поля movie
     */
    public Session withMovieId(Integer movieId) {
        return new Session(id, movieId, hallId, startTime, endTime, bookedSeats);
    }

    /**
     * Получить зал, в котором проходит сеанс
     * @return Зал, в котором проходит сеанс
     */
    public Integer getHallId() {
        return hallId;
    }

    /**
     * Обновить зал, в котором проходит сеанс
     * @param hallId Новый зал
     * @return Оригинальный объект класса Session c новым значением поля hall
     */
    public Session withHallId(Integer hallId) {
        return new Session(id, movieId, hallId, startTime, endTime, bookedSeats);
    }

    /**
     * Получить время начала сеанса
     * @return Время начала сеанса
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Обновить время начала сеанса
     * @param startTime Новое время начала сеанса
     * @return Оригинальный объект класса Session c новым временем начала сеанса
     */
    public Session withStartTime(LocalDateTime startTime) {
        return new Session(id, movieId, hallId, startTime, endTime, bookedSeats);
    }

    /**
     * Получить время конца сеанса
     * @return Время конца сеанса
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Обновить время начала сеанса
     * @param endTime Новое время конца сеанса
     * @return Оригинальный объект класса Session c новым временем конца сеанса
     */
    public Session withEndTime(LocalDateTime endTime) {
        return new Session(id, movieId, hallId, startTime, endTime, bookedSeats);
    }

    /**
     * Получить список забронированных мест.
     * @return Список забронированных мест.
     */
    public SafeHashSet<Integer> getBookedSeats() {
        return new SafeHashSet<>(bookedSeats);
    }

    /**
     * Обновлеяет список забронированных мест
     * @param bookedSeats Новый список забронированных мест
     * @return  Оригинальный объект класса Session c новым списком забронированных мест
     */
    public Session withBookedSeats(SafeHashSet<Integer> bookedSeats) {
        return new Session(id, movieId, hallId, startTime, endTime, bookedSeats);
    }

    public Session bookSeat(int seat) {
        if (bookedSeats.contains(seat))
            throw new RuntimeException("Booked seat " + seat + " is already booked");

        Set<Integer> newBookedSeats = new SafeHashSet<>(bookedSeats);
        newBookedSeats.add(seat);
        return new Session(id, movieId, hallId, startTime, endTime, newBookedSeats);
    }

    public Session cancelBookSeat(int seat) {
        if (!bookedSeats.contains(seat))
            throw new RuntimeException("Booked seat " + seat + " is not booked");

        Set<Integer> newBookedSeats = new SafeHashSet<>(bookedSeats);
        newBookedSeats.remove(seat);
        return new Session(id, movieId, hallId, startTime, endTime, newBookedSeats);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", hallId=" + hallId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", bookedSeats=" + bookedSeats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(getMovieId(), session.getMovieId()) && Objects.equals(getHallId(), session.getHallId()) && Objects.equals(getStartTime(), session.getStartTime()) && Objects.equals(getEndTime(), session.getEndTime()) && Objects.equals(getBookedSeats(), session.getBookedSeats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovieId(), getHallId(), getStartTime(), getEndTime(), getBookedSeats());
    }
}
