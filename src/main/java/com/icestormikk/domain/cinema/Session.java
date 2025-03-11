package com.icestormikk.domain.cinema;

import com.icestormikk.utils.StrictHashSet;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Класс, представляющий сеанс в кинотеатре.
 */
public class Session {
    private int id;
    /** Фильм, который показывают на сеансе. */
    private Movie movie;
    /** Зал, в котором проходит сеанс. */
    private Hall hall;
    /** Время начала сеанса. */
    private LocalDateTime startTime;
    /** Время окончания сеанса. */
    private LocalDateTime endTime;
    /** Список забронированных мест на сеанс. */
    private Set<Integer> bookedSeats;

    /**
     * Конструктор для создания сеанса.
     * @param movie     Фильм, который показывают на сеансе.
     * @param hall      Зал, в котором проходит сеанс.
     * @param startTime Время начала сеанса.
     * @param endTime   Время окончания сеанса.
     */
    public Session(Integer id, Movie movie, Hall hall, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedSeats = new StrictHashSet<>();
    }

    public Session(Movie movie, Hall hall, LocalDateTime startTime, LocalDateTime endTime) {
        this(null, movie, hall, startTime, endTime);
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
    public Session setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Получить фильм, который показывают на сеансе.
     * @return Фильм, который показывают на сеансе.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Обновить фильм, который показывают на сеансе.
     * @param movie Новый фильм
     * @return Оригинальный объект класса Session c новым значением поля movie
     */
    public Session setMovie(Movie movie) {
        this.movie = movie;
        return this;
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
    public Session setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
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
    public Session setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Получить зал, в котором проходит сеанс
     * @return Зал, в котором проходит сеанс
     */
    public Hall getHall() {
        return hall;
    }

    /**
     * Обновить зал, в котором проходит сеанс
     * @param hall Новый зал
     * @return Оригинальный объект класса Session c новым значением поля hall
     */
    public Session setHall(Hall hall) {
        this.hall = hall;
        return this;
    }

    /**
     * Получить список забронированных мест.
     * @return Список забронированных мест.
     */
    public Set<Integer> getBookedSeats() {
        return this.bookedSeats;
    }

    /**
     * Обновлеяет список забронированных мест
     * @param bookedSeats Новый список забронированных мест
     * @return  Оригинальный объект класса Session c новым списком забронированных мест
     */
    public Session setBookedSeats(Set<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
        return this;
    }

    /**
     * Бронирует место на сеансе.
     * @param seat Номер места для бронирования.
     */
    public void bookSeat(int seat) {
        boolean isAlreadyBookedSeat = bookedSeats.contains(seat);

        if(isAlreadyBookedSeat)
            throw new RuntimeException("Booked seat " + seat + " is already booked");

        if(seat < 1 || seat > hall.getSeats())
            throw new RuntimeException("Booked seat " + seat + " is not in hall " + hall);

        bookedSeats.add(seat);
    }

    /**
     * Отменяет бронирование места на сеансе.
     * @param seat Номер места для отмены бронирования.
     * @return true, если отмена прошла успешно, иначе false.
     */
    public boolean cancelSeat(int seat) {
        for (Integer bookedSeat : bookedSeats) {
            if(bookedSeat == seat) {
                return bookedSeats.remove(bookedSeat);
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", movie=" + movie.getTitle() +
                ", hall=" + hall.getHallNumber() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", bookedSeats=" + bookedSeats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(getMovie(), session.getMovie()) && Objects.equals(getHall(), session.getHall()) && Objects.equals(getStartTime(), session.getStartTime()) && Objects.equals(getEndTime(), session.getEndTime()) && Objects.equals(getBookedSeats(), session.getBookedSeats());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie(), getHall(), getStartTime(), getEndTime(), getBookedSeats());
    }
}
