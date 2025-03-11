package com.icestormikk.domain.cinema;

import java.time.Duration;

/**
 * Класс, представляющий фильм.
 */
public class Movie {
    /** Название фильма. */
    private String title;
    /** Продолжительность фильма. */
    private Duration duration;
    /** Жанр фильма. */
    private String genre;
    /** Рейтинг фильма. */
    private double rating;

    /**
     * Конструктор для создания фильма.
     * @param title Название фильма.
     * @param duration Продолжительность фильма.
     * @param genre Жанр фильма.
     * @param rating Рейтинг фильма.
     */
    public Movie(String title, String genre, Duration duration, double rating) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    /**
     * Получить название фильма
     * @return Название фильма
     */
    public String getTitle() {
        return title;
    }

    /**
     * Обновить название фильма
     * @param title Новое название фильма
     * @return Оригинальный объект класса Movie с новым названием
     */
    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Получить жанр фильма
     * @return Жанр фильма
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Обновить жанр фильма
     * @param genre Новый жанр фильма
     * @return Оригинальный объект класса Movie с новым жанром
     */
    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    /**
     * Получить продолжительность фильма
     * @return Продолжительность фильма
     */
    public Duration getDurationInMin() {
        return duration;
    }

    /**
     * Обновить продолжительность фильма
     * @param duration Новая продолжительность фильма
     * @return Оригинальный объект класса Movie с новой продолжительностью
     */
    public Movie setDurationInMin(Duration duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Получить рейтинг фильма
     * @return Рейтинг фильма
     */
    public double getRating() {
        return rating;
    }

    /**
     * Обновить рейтинг фильма
     * @param rating Значение нового рейтинга фильма
     * @return Оригинальный объект класса Movie с новым рейтингом
     * @throws IllegalArgumentException Если новое значение рейтинга отрицательное
     */
    public Movie setRating(double rating) {
        if(rating < 0.0)
            throw new IllegalArgumentException("Rating must be a positive number");

        this.rating = rating;
        return this;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", durationInMin=" + duration +
                ", rating=" + rating +
                '}';
    }
}
