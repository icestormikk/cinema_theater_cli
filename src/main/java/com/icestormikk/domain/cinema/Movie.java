package com.icestormikk.domain.cinema;

import java.time.Duration;
import java.util.Objects;

/**
 * Класс, представляющий фильм.
 */
public class Movie {
    private final Integer id;
    /** Название фильма. */
    private final String title;
    /** Продолжительность фильма. */
    private final Duration duration;
    /** Жанр фильма. */
    private final String genre;
    /** Рейтинг фильма. */
    private final float rating;

    /**
     * Конструктор для создания фильма.
     * @param title Название фильма.
     * @param duration Продолжительность фильма.
     * @param genre Жанр фильма.
     * @param rating Рейтинг фильма.
     */
    public Movie(Integer id, String title, String genre, Duration duration, float rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    /**
     * Получить уникальный идентификатор фильма
     * @return Уникальный идентификатор фильма
     */
    public int getId() {
        return id;
    }

    /**
     * Обновить уникальный идентификатор фильма
     * @param id Новый уникальный идентификатор фильма
     * @return Оригинальный объект класса Movie c обновлённым идентификатором
     */
    public Movie withId(int id) {
        return new Movie(id, title, genre, duration, rating);
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
    public Movie withTitle(String title) {
        return new Movie(id, title, genre, duration, rating);
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
    public Movie withGenre(String genre) {
        return new Movie(id, title, genre, duration, rating);
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
    public Movie withDurationInMin(Duration duration) {
        return new Movie(id, title, genre, duration, rating);
    }

    /**
     * Получить рейтинг фильма
     * @return Рейтинг фильма
     */
    public float getRating() {
        return rating;
    }

    /**
     * Обновить рейтинг фильма
     * @param rating Значение нового рейтинга фильма
     * @return Оригинальный объект класса Movie с новым рейтингом
     * @throws IllegalArgumentException Если новое значение рейтинга отрицательное
     */
    public Movie withRating(float rating) {
        if(rating < 0.0)
            throw new IllegalArgumentException("Rating must be a positive number");

        return new Movie(id, title, genre, duration, rating);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", durationInMin=" + duration +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getTitle(), movie.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
