package homeworkLesson24;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

public class Message {
    //Определяем необходимые поля в классе
    private User sender;
    private String message;
    private LocalDateTime sentTime = LocalDateTime.now();
    private HashSet <User> usersLike = new HashSet<>();

    public Message(User sender, String message, LocalDateTime sentTime, HashSet<User> usersLike) {
        this.sender = sender;
        this.message = message;
        this.sentTime = sentTime;
        this.usersLike = usersLike;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public HashSet<User> getUsersLike() {
        return usersLike;
    }

    public void setUsersLike(HashSet<User> usersLike) {
        this.usersLike = usersLike;
    }


    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", message='" + message + '\'' +
                ", sentTime=" + sentTime +
                ", usersLike=" + usersLike +
                '}';
    }
}
