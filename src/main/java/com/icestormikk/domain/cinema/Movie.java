package com.icestormikk.domain.cinema;

public class Movie {
    private String title;
    private String genre;
    private int durationInMin;
    private double rating;

    public Movie(String title, String genre, int durationInMin, double rating) {
        this.title = title;
        this.genre = genre;
        this.durationInMin = durationInMin;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public int getDurationInMin() {
        return durationInMin;
    }

    public Movie setDurationInMin(int durationInMin) {
        this.durationInMin = durationInMin;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public Movie setRating(double rating) {
        this.rating = rating;
        return this;
    }
}
