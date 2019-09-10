/**
 * class advice-entity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import java.util.Objects;

public class Advice {
    private int id;
    private String text;

    public Advice(String text){
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Advice advice = (Advice) o;
        return id == advice.id &&
                Objects.equals(text, advice.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
