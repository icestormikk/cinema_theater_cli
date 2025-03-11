package com.icestormikk.domain.cinema;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, представляющий сеанс в кинотеатре.
 */
public class Session {
    /** Фильм, который показывают на сеансе. */
    private Movie movie;
    /** Зал, в котором проходит сеанс. */
    private Hall hall;
    /** Время начала сеанса. */
    private LocalDateTime startTime;
    /** Время окончания сеанса. */
    private LocalDateTime endTime;
    /** Список забронированных мест на сеанс. */
    private List<Integer> bookedSeats;

    /**
     * Конструктор для создания сеанса.
     * @param movie     Фильм, который показывают на сеансе.
     * @param hall      Зал, в котором проходит сеанс.
     * @param startTime Время начала сеанса.
     * @param endTime   Время окончания сеанса.
     */
    public Session(Movie movie, Hall hall, LocalDateTime startTime, LocalDateTime endTime) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedSeats = new LinkedList<>();
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
    public List<Integer> getBookedSeats() {
        return this.bookedSeats;
    }

    /**
     * Обновлеяет список забронированных мест
     * @param bookedSeats Новый список забронированных мест
     * @return  Оригинальный объект класса Session c новым списком забронированных мест
     */
    public Session setBookedSeats(List<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
        return this;
    }

    /**
     * Бронирует место на сеансе.
     * @param seat Номер места для бронирования.
     * @return true, если бронирование успешно, иначе false.
     */
    public boolean bookSeat(int seat) {
        boolean isAlreadyBookedSeat = bookedSeats.contains(seat);

        if(seat < 1 || seat > hall.getSeats() || isAlreadyBookedSeat) {
            return false;
        }

        bookedSeats.add(seat);
        return true;
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
                "movie=" + movie.getTitle() +
                ", hall=" + hall.getHallNumber() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", bookedSeats=" + bookedSeats +
                '}';
    }
}
