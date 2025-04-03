package com.icestormikk.domain.cinema;

import java.util.Objects;

/**
 * Класс, представляющий билет на сеанс.
 */
public class Ticket {
    private final Integer id;
    /** Сеанс, на который куплен билет. */
    private final Integer sessionId;
    /** Номер места в зале. */
    private final int seat;
    /** Статус билета. */
    private final TicketStatus status;

    /**
     * Конструктор для создания билета.
     * @param sessionId Сеанс, на который куплен билет.
     * @param seat      Номер места в зале.
     */
    public Ticket(Integer id, Integer sessionId, int seat, TicketStatus status) {
        this.id = id;
        this.sessionId = sessionId;
        this.seat = seat;
        this.status = status;
    }

    /**
     * Получить идентификатор билета
     * @return Идентификатор билета
     */
    public int getId() {
        return id;
    }

    /**
     * Обновить идентификатор билета
     * @param id Новый идентификатор билета
     * @return Оригинальный объект класса Ticket c обновлённым идентификатором
     */
    public Ticket withId(Integer id) {
        return new Ticket(id, sessionId, seat, status);
    }

    /**
     * Получить сеанс, на который куплен билет
     * @return Сеанс, на который куплен билет
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     * Обновить сеанс, на который куплен билет
     * @param sessionId Новый сеанс
     * @return Оригинальный объект класса Ticket с новым полем session
     */
    public Ticket withSessionId(Integer sessionId) {
        return new Ticket(id, sessionId, seat, status);
    }

    /**
     * Получить номер места в зале, на которое куплен билет
     * @return Номер места в зале, на которое куплен билет
     */
    public int getSeat() {
        return seat;
    }

    /**
     * Обновить номер места в зале, на которое куплен билет
     * @param seat Новый номер места в зале
     * @return Оригинальный объект класса Ticket с новым полем seat
     */
    public Ticket withSeat(int seat) {
        if(seat < 0)
            throw new IllegalArgumentException("Seat number must be a positive integer");

        return new Ticket(id, sessionId, seat, status);
    }

    /**
     * Получить текущий статус билета
     * @return Текущий статус билета
     */
    public TicketStatus getStatus() {
        return status;
    }

    /**
     * Обновить статус билета
     * @param status Новый статус билета
     * @return Оригинальный объект класса Ticket с новым статусом
     */
    public Ticket withStatus(TicketStatus status) {
        return new Ticket(id, sessionId, seat, status);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", seat=" + seat +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getSeat() == ticket.getSeat() && Objects.equals(getSessionId(), ticket.getSessionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionId(), getSeat());
    }
}
