package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

    abstract class Person implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);
        private static final long serialVersionUID = 1L;
    private String name;
    private String password;
    private String phoneNumber;
    private String address;
    private String eMail;
    private int id;
    private AccessLevel accessLevel;
    private TreeMap<LocalDateTime, Message> sentMessages = new TreeMap();
    private TreeMap<LocalDateTime,Message> receivedMessages = new TreeMap();

    public Person(String name, String password, String phoneNumber, String address, String eMail, int id) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.eMail = eMail;
        this.id = id;
        this.accessLevel = AccessLevel.USER;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public int getId() {
        return id;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public TreeMap<LocalDateTime, Message> getSentMessages() {
        return sentMessages;
    }

    public TreeMap <LocalDateTime, Message> getReceivedMessages() {
        return receivedMessages;
    }

    // Метод отправки сообщения
    public boolean sendMessage(Person recipient, String text) {
        // Десериализуем данные
        PersonManager.deserializeUsers();
        PersonManager.deserializeEmployees();

        if (recipient == null) {
            LOGGER.warn("Recipient is null");
            LOGGER.warn("Message not sent");
            return false;
        }
        if (text == null || text.isEmpty()) {
            LOGGER.warn("Message text is null or empty");
            LOGGER.warn("Message not sent");
            return false;
        }

        Message message = new Message(this, recipient, text);
        boolean result1 = this.addSentMessage(message);
        boolean result2 = recipient.addReceivedMessage(message);

        if (result1 && result2) {
            LOGGER.info("Message successfully sent");
        } else if (!result1 && !result2) {
            LOGGER.warn("Message not sent");
        } else if (!result1) {
            LOGGER.warn("Message was sent");
            LOGGER.warn("Message added to recipient only");
        } else {
            LOGGER.warn("Message not sent");
            LOGGER.warn("Message added to sender only");
        }

        // Сериализуем данные только если сообщение было отправлено
        if (result1 || result2) {
            PersonManager.serializeUsers();
            PersonManager.serializeEmployees();
        }

        return result1 && result2;
    }

    // Метод для добавления отправленного сообщения пользователю
    public boolean addSentMessage(Message message) {
        if (message == null) {
            LOGGER.info("Message is null");
            return false;
        } else {
            Message result = sentMessages.put(message.getLocalDateTime(), message);
            if (result == null) {
                LOGGER.info("Message was added to " + this.getName() + "`s sent messages");
                return true;
            } else {
                LOGGER.warn("Message was updated");
                return false;
            }
        }
    }

    // Метод для добавления полученного сообщения пользователю
    public boolean addReceivedMessage(Message message) {
        if (message == null) {
            LOGGER.info("Message is null");
            return false;
        } else {
            Message result = receivedMessages.put(message.getLocalDateTime(),message);
            if (result == null) {
                LOGGER.info("Message was added to " + this.getName() + "'s received messages");
                return true;
            } else {
                LOGGER.info("Message was updated");
                return false;
            }
        }
    }

    // equals & hashcode генерируем по id и E-mail
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(eMail, person.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eMail, id);
    }
}