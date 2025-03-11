package com.icestormikk.domain.cinema;

import com.icestormikk.utils.StrictHashSet;

import java.util.Objects;
import java.util.Set;

/**
 * Класс, описывающий зал кинотеатра
 */
public class Hall {
    private int id;
    /** Номер зала кинотеатра */
    private int hallNumber;
    /** Количество мест в зале. */
    private int seats;
    /** Список сеансов, проходящих в зале. */
    private Set<Session> sessions;

    /**
     * Конструктор для создания зала.
     * @param hallNumber Номер зала.
     * @param seats Количество мест в зале.
     */
    public Hall(Integer id, int hallNumber, int seats) {
        this.id = id;
        this.hallNumber = hallNumber;
        this.seats = seats;
        this.sessions = new StrictHashSet<>();
    }

    public Hall(int hallNumber, int seats) {
        this(null, hallNumber, seats);
    }

    /**
     * Получить уникальный идентификатор зала
     * @return Уникальный идентификатор зала
     */
    public int getId() {
        return id;
    }

    /**
     * Обновить уникальный идентификатор зала
     * @param id Новый уникальный идентификатор зала
     * @return Оригинальный объект класса Hall c обновлённым идентификатором
     */
    public Hall setId(int id) {
        this.id = id;
        return this;
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
    public Set<Session> getSessions() {
        return sessions;
    }

    /**
     * Обновить список сеансов, проходящих в зале.
     * @param sessions Новый список сеансов, проходящих в зале.
     * @return Оригинальный объект класса Hall с новым списком сеансов
     */
    public Hall setSessions(Set<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", seats=" + seats +
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
        return Objects.hash(getHallNumber());
    }
}
