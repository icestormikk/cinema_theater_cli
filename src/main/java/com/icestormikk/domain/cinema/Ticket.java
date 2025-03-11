package com.icestormikk.domain.cinema;

import java.util.Objects;

/**
 * Класс, представляющий билет на сеанс.
 */
public class Ticket {
    /** Сеанс, на который куплен билет. */
    private Session session;
    /** Номер места в зале. */
    private int seat;
    /** Статус билета. */
    private TicketStatus status;

    /**
     * Конструктор для создания билета.
     * @param session Сеанс, на который куплен билет.
     * @param seat    Номер места в зале.
     */
    public Ticket(Session session, int seat) {
        this.session = session;
        this.seat = seat;
        this.status = TicketStatus.Booked;
    }

    /**
     * Получить сеанс, на который куплен билет
     * @return Сеанс, на который куплен билет
     */
    public Session getSession() {
        return session;
    }

    /**
     * Обновить сеанс, на который куплен билет
     * @param session Новый сеанс
     * @return Оригинальный объект класса Ticket с новым полем session
     */
    public Ticket setSession(Session session) {
        this.session = session;
        return this;
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
    public Ticket setSeat(int seat) {
        if(seat < 0)
            throw new IllegalArgumentException("Seat number must be a positive integer");

        this.seat = seat;
        return this;
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
    public Ticket setStatus(TicketStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Купить билет
     */
    public void purchase() {
        status = TicketStatus.Purchased;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "session=" + session.getMovie().getTitle() +
                ", seat=" + seat +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getSeat() == ticket.getSeat() && Objects.equals(getSession(), ticket.getSession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSession(), getSeat());
    }
}
