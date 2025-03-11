package com.icestormikk.domain.cinema;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Класс, описывающий зал кинотеатра
 */
public class Hall {
    /** Номер зала кинотеатра */
    private int hallNumber;
    /** Количество мест в зале. */
    private int seats;
    /** Список сеансов, проходящих в зале. */
    private List<Session> sessions;

    /**
     * Конструктор для создания зала.
     * @param hallNumber Номер зала.
     * @param seats Количество мест в зале.
     */
    public Hall(int hallNumber, int seats) {
        this.hallNumber = hallNumber;
        this.seats = seats;
        this.sessions = new LinkedList<>();
    }

    /**
     * Получить номер зала кинотеатра
     * @return Номер зала
     */
    public int getHallNumber() {
        return hallNumber;
    }

    /**
     * Обновить номер зала кинотеатра
     * @param hallNumber Новый номер зала кинотеатра
     * @return Оригинальный объект класса Hall с новым номером зала
     */
    public Hall setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
        return this;
    }

    /**
     * Получить количество мест в зале
     * @return Количество мест в зале
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Обновить количество мест в зале
     * @param seats Новое количество мест в зале
     * @return Оригинальный объект класса Hall с новым количеством мест в зале
     * @throws IllegalArgumentException Если новое количество мест отрицательно
     */
    public Hall setSeats(int seats) {
        if(seats < 0)
            throw new IllegalArgumentException("Seats must be greater than 0");

        this.seats = seats;
        return this;
    }

    /**
     * Получить список сеансов, проходящих в зале.
     * @return Список сеансов, проходящих в зале.
     */
    public List<Session> getSessions() {
        return sessions;
    }

    /**
     * Обновить список сеансов, проходящих в зале.
     * @param sessions Новый список сеансов, проходящих в зале.
     * @return Оригинальный объект класса Hall с новым списком сеансов
     */
    public Hall setSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "seats=" + seats +
                ", hallNumber=" + hallNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return getHallNumber() == hall.getHallNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getHallNumber());
    }
}
