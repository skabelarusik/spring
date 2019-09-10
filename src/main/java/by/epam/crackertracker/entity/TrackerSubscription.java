/**
 * class subscription-entity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import java.time.LocalDate;
import java.util.Objects;

public class TrackerSubscription {

    private int id;
    private String subscriber;
    private String nameProgram;
    private LocalDate start;
    private LocalDate finish;
    private String active;

    public TrackerSubscription(String subscriber, String nameProgram, LocalDate start, LocalDate finish){
        this.subscriber = subscriber;
        this.nameProgram = nameProgram;
        this.start = start;
        this.finish = finish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getNameProgram() {
        return nameProgram;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackerSubscription that = (TrackerSubscription) o;
        return id == that.id &&
                Objects.equals(subscriber, that.subscriber) &&
                Objects.equals(nameProgram, that.nameProgram) &&
                Objects.equals(start, that.start) &&
                Objects.equals(finish, that.finish) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subscriber, nameProgram, start, finish, active);
    }

    @Override
    public String toString() {
        return "TrackerSubscription{" +
                "id=" + id +
                ", subscriber='" + subscriber + '\'' +
                ", nameProgram='" + nameProgram + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", active='" + active + '\'' +
                '}';
    }
}
