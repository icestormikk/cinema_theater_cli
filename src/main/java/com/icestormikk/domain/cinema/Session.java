package com.icestormikk.domain.cinema;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Session {
    private Movie movie;
    private Hall hall;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Integer> bookedSeats;

    public Session(Movie movie, Hall hall, LocalDateTime startTime, LocalDateTime endTime) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedSeats = new LinkedList<>();
    }

    public Movie getMovie() {
        return movie;
    }

    public Session setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Session setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Session setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public Hall getHall() {
        return hall;
    }

    public Session setHall(Hall hall) {
        this.hall = hall;
        return this;
    }

    public List<Integer> getBookedSeats() {
        return this.bookedSeats;
    }

    public Session setBookedSeats(List<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
        return this;
    }

    public boolean bookSeat(int seat) {
        boolean isAlreadyBookedSeat = bookedSeats.contains(seat);

        if(seat < 1 || seat > hall.getSeats() || isAlreadyBookedSeat) {
            return false;
        }

        bookedSeats.add(seat);
        return true;
    }

    public boolean cancelSeat(int seat) {
        for (Integer bookedSeat : bookedSeats) {
            if(bookedSeat == seat) {
                return bookedSeats.remove(bookedSeat);
            }
        }

        return false;
    }
}
