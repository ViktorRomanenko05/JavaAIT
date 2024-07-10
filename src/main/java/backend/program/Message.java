package backend.program;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Message implements Serializable {
    //Объекты
    private Person sender;

    //Вопрос, нужен ли нам объект получателя?
    private Person receiver;
    private String text;
    private LocalDateTime localDateTime;

    //Геттеры и Сеттеры (дата без сеттера)
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Text cannot be null or empty");
        this.text = text;
    }

    //equals and hashCode.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(sender, message.sender) && Objects.equals(localDateTime, message.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, localDateTime);
    }

    //Конструктор
    public Message(Person sender, Person receiver,
                   String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Сообщение от "
                + sender.getName() + " для " + receiver.getName() +
                ": " + text + '\'' +
                "время отправки: " + localDateTime +
                '}';
    }
}
