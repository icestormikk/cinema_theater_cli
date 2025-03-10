package com.icestormikk.domain.cinema;

import java.util.LinkedList;
import java.util.List;

public class Hall {
    private int hallNumber;
    private int seats;
    private List<Session> sessions;

    public Hall(int hallNumber, int seats) {
        this.hallNumber = hallNumber;
        this.seats = seats;
        this.sessions = new LinkedList<>();
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public Hall setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
        return this;
    }

    public int getSeats() {
        return seats;
    }

    public Hall setSeats(int seats) {
        this.seats = seats;
        return this;
    }

    public List<Session> getSessions() {
        return sessions;
    }

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
}
