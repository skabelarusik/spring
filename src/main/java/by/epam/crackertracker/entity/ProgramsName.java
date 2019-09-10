/**
 * class program name - entity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ProgramsName {

    private int id;
    private String name;
    private String curator;
    private BigDecimal cost;
    private int duration;

    public ProgramsName(String name, String curator, BigDecimal cost, int duration){
        this.name = name;
        this.curator = curator;
        this.cost = cost;
        this.duration = duration;
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

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramsName that = (ProgramsName) o;
        return id == that.id &&
                duration == that.duration &&
                Objects.equals(name, that.name) &&
                Objects.equals(curator, that.curator) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, curator, cost, duration);
    }

    @Override
    public String toString() {
        return "ProgramsName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", curator='" + curator + '\'' +
                ", cost=" + cost +
                ", duration=" + duration +
                '}';
    }
}
