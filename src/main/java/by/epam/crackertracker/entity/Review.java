/**
 * class review-entity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Review {
    private int id;
    private String name;
    private LocalDate date;
    private String text;

    public Review(String name, String text, LocalDate date){
        this.name = name;
        this.date = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id &&
                Objects.equals(name, review.name) &&
                Objects.equals(date, review.date) &&
                Objects.equals(text, review.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, text);
    }

    @Override
    public String toString() {
        return "Review{name='" + name + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
