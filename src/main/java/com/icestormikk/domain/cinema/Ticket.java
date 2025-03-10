package com.icestormikk.domain.cinema;

public class Ticket {
    private Session session;
    private int seat;
    private TicketStatus status;

    public Ticket(Session session, int seat) {
        this.session = session;
        this.seat = seat;
        this.status = TicketStatus.Booked;
    }

    public Session getSession() {
        return session;
    }

    public Ticket setSession(Session session) {
        this.session = session;
        return this;
    }

    public int getSeat() {
        return seat;
    }

    public Ticket setSeat(int seat) {
        this.seat = seat;
        return this;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Ticket setStatus(TicketStatus status) {
        this.status = status;
        return this;
    }

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
}
