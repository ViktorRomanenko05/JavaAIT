package homeworkLesson24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.HashMap;
import java.util.stream.Collectors;

public class User {
    //Обозначаем поля в классе
    private String name;
    private int identifier;
    private HashSet<User> friends;

    private ArrayList <Message> messages;

    //Конструктор

    public User(String name, int identifier, HashSet<User> friends, ArrayList<Message> messages) {
        this.name = name;
        this.identifier = identifier;
        this.friends = new HashSet<>();
        this.messages = new ArrayList<>();
    }


    //Геттеры / сеттеры

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public HashSet<User> getFriends() {
        return friends;
    }

    public void setFriends(HashSet<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }


    //генерируем методы equals и hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return identifier == user.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    //Метод для добавления новых друзей в список пользователя
    public void addFriend (User friend){
        if (friend == null){
            System.out.println("Object user is null");
        }
        else {
            boolean addResult = friends.add(friend);
            if (addResult){
                System.out.println("Friend " + friend.getName() + " successfully added");
            }
            else {
                System.out.println("Friend " + friend.getName() + " The user was previously added");
            }
        }
    }

    //Метод для удаления друзей из списка пользователя
    public void removeFriend (User friend){
        if (friend == null){
            System.out.println("Object user is null");
        }
        else {
            boolean addResult = friends.remove(friend);
            if (addResult){
                System.out.println("Friend " + friend.getName() + " removed");
            }
            else {
                System.out.println("Friend " + friend.getName() + " The user was not found");
            }
        }
    }

    public void addMessage (Message message){
        if (message == null){
            System.out.println("Object user is null");
        }
        else {
            boolean addResult = messages.add(message);
            if (addResult){
                System.out.println("Message from " + message.getSender().getName() + " successfully added");
            }
            else {
                System.out.println("Message from " + message.getSender().getName() + " was previously added");
            }
        }
    }

    public void removeMessage (Message message){
        if (message == null){
            System.out.println("Object user is null");
        }
        else {
            boolean result = messages.remove(message);
            if (result){
                System.out.println("Message from " + message.getSender().getName() + " was removed");
            }
            else {
                System.out.println("Message from " + message.getSender().getName() + " not found");
            }
        }
    }

    public static void printFriends(User user) {
        line();
        System.out.printf("%-3s %-10s %s%n", "№", "Friend ID", "Friend name");
        line();
        int counterNum = 0;
        for (User friend : user.getFriends()) {
            counterNum++;
            System.out.printf("%-3d %-10d %s%n", counterNum, friend.getIdentifier(), friend.getName());
        }
        line();
    }

    private static void line() {
        System.out.println("---------------------------------");
    }

}
