/**
 * class messages-entity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Message {
    private String text;
    private String topik;
    private String sender;
    private String recipient;
    private LocalDate localDate;
    private int id;

    public Message(){
    }

    public Message(String text, String topik, String sender, String recipient, LocalDate localDate){
        this.text = text;
        this.topik = topik;
        this.sender = sender;
        this.recipient = recipient;
        this.localDate = localDate;
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

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                Objects.equals(text, message.text) &&
                Objects.equals(topik, message.topik) &&
                Objects.equals(sender, message.sender) &&
                Objects.equals(recipient, message.recipient) &&
                Objects.equals(localDate, message.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, topik, sender, recipient, localDate, id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", topik='" + topik + '\'' +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", localDate=" + localDate +
                ", id=" + id +
                '}';
    }
}
