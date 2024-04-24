package homeworkLesson24;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.time.format.DateTimeFormatter;


public class SocialNetwork {

    private static HashMap<Integer, User> users = new HashMap<>();
    private static HashSet<Community> communities = new HashSet<>();

    public static HashMap<Integer, User> getUsers() {
        return users;
    }

    public static void setUsers(HashMap<Integer, User> users) {
        SocialNetwork.users = users;
    }

    public static HashSet<Community> getCommunities() {
        return communities;
    }

    public static void setCommunities(HashSet<Community> communities) {
        SocialNetwork.communities = communities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialNetwork that = (SocialNetwork) o;
        return Objects.equals(users, that.users) && Objects.equals(communities, that.communities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, communities);
    }

    //Метод для добавления новых пользователей
    public static void addUser(User user) {
        if (user == null) {
            System.out.println("Object user is null");
        } else {
            User previousUser = users.put(user.getIdentifier(), user);
            if (previousUser == null) {
                System.out.println("User " + user.getName() + " successfully added");
            } else {
                System.out.println("User " + user.getName() + " was updated");
            }
        }
    }

    //Метод для удаления пользователей
    public static void removeUser(User user) {
        if (user == null) {
            System.out.println("Object user is null");
        } else {
            User removedUser = users.remove(user.getIdentifier());
            if (removedUser != null) {
                System.out.println("User " + user.getName() + " was removed");
            } else {
                System.out.println("User " + user.getName() + " not found");
            }
        }
    }

    //Метод для добавления новых сообществ
    public static void addCommunity(Community community) {
        if (community == null) {
            System.out.println("Object community is null");
        } else {
            boolean addResult = communities.add(community);
            if (addResult) {
                System.out.println("Community \"" + community.getCommunityName() + "\" successfully added");
            } else {
                System.out.println("Community \"" + community.getCommunityName() + "\" was previously added");
            }
        }
    }

    //Метод для удаления сообществ
    public static void removeCommunity(Community community) {
        if (community == null) {
            System.out.println("Object community is null");
        } else {
            boolean addResult = communities.remove(community);
            if (addResult) {
                System.out.println("Community " + community.getCommunityName() + " was removed");
            } else {
                System.out.println("Community " + community.getCommunityName() + " was not found");
            }
        }
    }

    //Метод для добавления сообщения в сообщество
    public static void addMessageToCommunity(Community community, User sender, Message message) {
        if (community == null || sender == null || message == null) {
            System.out.println("Incorrect data. Object is null");
        } else {
            boolean addResult = community.getCommunityMessages().add(message);
            if (addResult) {
                sender.addMessage(message);
                System.out.println("Message was sent");
            } else {
                System.out.println("Sending is failed");
            }
        }
    }

    //Метод для удаления сообщения из сообщества
    public static void removeMessageFromCommunity(Community community, User sender, Message message) {
        if (community == null || sender == null || message == null) {
            System.out.println("Incorrect data. Object is null");
        } else {
            boolean result = community.getCommunityMessages().remove(message);
            if (result) {
                sender.removeMessage(message);
                System.out.println("Message was deleted");
            } else {
                System.out.println("Message was not found");
            }
        }
    }

    //Вывод всех пользователей на экран
    public static void printAllUsers() {
        line();
        System.out.printf("%-3s %-8s %s%n", "№", "User ID", "User name");
        line();
        int counterNum = 0;
        for (User user : SocialNetwork.getUsers().values()) {
            counterNum++;
            System.out.printf("%-3d %-8s %s%n", counterNum, user.getIdentifier(), user.getName());
        }
        line();
    }

    //Вывод всех сообществ на экран
    public static void printAllCommunities() {
        line();
        System.out.printf("%-3s %s%n", "№", "Community name");
        line();
        int counterNum = 0;
        for (Community community : communities) {
            counterNum++;
            System.out.printf("%-3d %s%n", counterNum, community.getCommunityName());
        }
        line();
    }

    //Вывод на экран всех сообщений в сообществе
    public static void printAllCommunityMessages(Community community) {
        lineBig();
        System.out.printf("%-3s %-30s %-20s %s%n", "№", "Message", "Sender", "Sent Time");
        lineBig();
        int counterNum = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Message message : community.getCommunityMessages()) {
            counterNum++;
            System.out.printf("%-3s %-30s %-20s %s%n", counterNum, message.getMessage(), message.getSender().getName(), message.getSentTime().format(formatter));
        }
        lineBig();
    }

    //Вывод на экран лайков для переданного в метод сообщения
    public static void printLikes(Message message) {
        line();
        System.out.println("Message: " + message.getMessage());
        System.out.println("Author: " + message.getSender().getName());
        System.out.println("Likes for the message:");
        line();

        for (User like : message.getUsersLike()) {
            System.out.printf("\u2764 %-20s%n", like.getName());
        }
        line();
    }

    //Добавление лайка в сообщение
    public static void addLike (User like, Message message){
        if (like == null){
            System.out.println("Object user is null");
        }
        else {
            boolean addResult = message.getUsersLike().add(like);
            if (addResult){
                System.out.println("Like from " + like.getName() + " successfully added");
            }
            else {
                System.out.println("Like from " + like.getName() + " was previously added");
            }
        }
    }

    //Удаление лайка из сообщения
    public void removeLike (User like, Message message){
        if (like == null){
            System.out.println("Object user is null");
        }
        else {
            boolean result = message.getUsersLike().remove(like);
            if (result){
                System.out.println("Like from " + like.getName() + " removed");
            }
            else {
                System.out.println("Like from " + like.getName() + " was not found");
            }
        }
    }

    //Поиск пользователя по имени
    public static User findUserByName(String name) {
        for (User user : users.values()) {
            if (user.getName().equals(name)) {
                System.out.println("User " + user.getName() + " was found");
                return user;
            }
        }
        System.out.println("User " + name + "was not found");
        return null;
    }

    //Поиск сообщества по имени
    public static Community findCommunityByName(String communityName) {
        for (Community community : communities) {
            if (community.getCommunityName().equals(communityName)) {
                System.out.println("Community " + community.getCommunityName() + " was found");
                return community;
            }

        }
        System.out.println("Community " + communityName + " was not found");
        return null;
    }


    private static void line() {
        System.out.println("--------------------------");
    }

    private static void lineBig() {System.out.println("----------------------------------------------------------------------------");}

    @Override
    public String toString() {
        return "SocialNetwork{}";
    }
}