package com.icestormikk.domain.cinema;

import com.icestormikk.utils.SafeHashSet;

import java.util.Objects;

/**
 * Класс, описывающий зал кинотеатра
 */
public class Hall {
    private final Integer id;
    private final Integer cinemaId;
    /** Номер зала кинотеатра */
    private final int hallNumber;
    /** Количество мест в зале. */
    private final int seats;
    /** Список сеансов, проходящих в зале. */
    private final SafeHashSet<Integer> sessionIds;

    /**
     * Конструктор для создания зала.
     * @param hallNumber Номер зала.
     * @param seats Количество мест в зале.
     */
    public Hall(Integer id, Integer cinemaId, int hallNumber, int seats, SafeHashSet<Integer> sessionIds) {
        this.id = id;
        this.cinemaId = cinemaId;
        this.hallNumber = hallNumber;
        this.seats = seats;
        this.sessionIds = new SafeHashSet<>(sessionIds);
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
    public Hall withId(int id) {
        return new Hall(id, cinemaId, hallNumber, seats, sessionIds);
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public Hall withCinemaId(Integer cinemaId) {
        return new Hall(id, cinemaId, hallNumber, seats, sessionIds);
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
    public Hall withHallNumber(int hallNumber) {
        return new Hall(id, cinemaId, hallNumber, seats, sessionIds);
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
    public Hall withSeats(int seats) {
        if(seats < 0)
            throw new IllegalArgumentException("Seats must be greater than 0");
        return new Hall(id, cinemaId, hallNumber, seats, sessionIds);
    }

    /**
     * Получить список сеансов, проходящих в зале.
     * @return Список сеансов, проходящих в зале.
     */
    public SafeHashSet<Integer> getSessionIds() {
        return new SafeHashSet<>(sessionIds);
    }

    /**
     * Обновить список сеансов, проходящих в зале.
     * @param sessionIds Новый список сеансов, проходящих в зале.
     * @return Оригинальный объект класса Hall с новым списком сеансов
     */
    public Hall withSessionIds(SafeHashSet<Integer> sessionIds) {
        return new Hall(id, cinemaId, hallNumber, seats, sessionIds);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "cinemaId=" + cinemaId +
                ", hallNumber=" + hallNumber +
                ", seats=" + seats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return getHallNumber() == hall.getHallNumber() && Objects.equals(getCinemaId(), hall.getCinemaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCinemaId(), getHallNumber());
    }
}
