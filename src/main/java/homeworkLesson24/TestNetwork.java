package homeworkLesson24;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedMap;

public class TestNetwork {

    public static void main(String[] args) {



        // Создание хранилищ пользователей и сообществ
        HashMap<Integer, User> usersMap = new HashMap<>();
        HashSet<Community> communitiesSet = new HashSet<>();

        System.out.println("\nДобавим 10 пользователей в социальную сеть\n");

        // Создание пользователей
        User user1 = new User("Sofia Rossi", 5673, new HashSet<>(), new ArrayList<>());
        User user2 = new User("Liam O'Connor", 8362, new HashSet<>(), new ArrayList<>());
        User user3 = new User("Anika Patel", 3038, new HashSet<>(), new ArrayList<>());
        User user4 = new User("Juan Martínez", 4176, new HashSet<>(), new ArrayList<>());
        User user5 = new User("Yara Da Silva", 3895, new HashSet<>(), new ArrayList<>());
        User user6 = new User("Noah Müller", 6983, new HashSet<>(), new ArrayList<>());
        User user7 = new User("Mei Chen", 7025, new HashSet<>(), new ArrayList<>());
        User user8 = new User("Nathan Dubois", 8221, new HashSet<>(), new ArrayList<>());
        User user9 = new User("Ayaan Sharma", 9931, new HashSet<>(), new ArrayList<>());
        User user10 = new User("Emily Johnson", 1009, new HashSet<>(), new ArrayList<>());
        User user11 = new User("Emily Johnson", 1009, new HashSet<>(), new ArrayList<>());

        SocialNetwork.addUser(user1);
        SocialNetwork.addUser(user2);
        SocialNetwork.addUser(user3);
        SocialNetwork.addUser(user4);
        SocialNetwork.addUser(user5);
        SocialNetwork.addUser(user6);
        SocialNetwork.addUser(user7);
        SocialNetwork.addUser(user8);
        SocialNetwork.addUser(user9);
        SocialNetwork.addUser(user11);
        SocialNetwork.addUser(user10);

        System.out.println("\nВыводим на экран список добавленных пользователей\n");

        SocialNetwork.printAllUsers();

        System.out.println("\nДобавляем 5 пользователей в список друзей пользователя user1\n");

        user1.addFriend(user2);
        user1.addFriend(user4);
        user1.addFriend(user1);
        user1.addFriend(user9);
        user1.addFriend(user7);

        System.out.println("\nВыводим на экран список друзей пользователя user1\n");

        User.printFriends(user1);

        System.out.println("\nСоздадим три сообщества\n");

        Community community1 = new Community("Java Coders",new HashSet<>(),new ArrayList<>());
        Community community2 = new Community("Our pets",new HashSet<>(),new ArrayList<>());
        Community community3 = new Community("Maxi Cooking",new HashSet<>(),new ArrayList<>());

        SocialNetwork.addCommunity(community1);
        SocialNetwork.addCommunity(community2);
        SocialNetwork.addCommunity(community3);

        System.out.println("\nВыводим на экран список всех добавленных сообществ\n");

        SocialNetwork.printAllCommunities();

        System.out.println("\nДобавляем сообщения в сообщества\n");

        Message message1 = new Message(user1, "Hello everyone", LocalDateTime.now(),new HashSet<>());
        Message message2 = new Message(user2, "I'm happy to be here", LocalDateTime.now(),new HashSet<>());
        Message message3 = new Message(user3, "My name is Anika. I'm hungry", LocalDateTime.now(),new HashSet<>());
        Message message4 = new Message(user1, "I love cats", LocalDateTime.now(),new HashSet<>());
        Message message5 = new Message(user1, "I'm found new friends here", LocalDateTime.now(),new HashSet<>());
        Message message6 = new Message(user8, "My dog is back", LocalDateTime.now(),new HashSet<>());
        Message message7 = new Message(user8, "Hallo Hallo my darling :)", LocalDateTime.now(),new HashSet<>());

        SocialNetwork.addMessageToCommunity(community1, user1, message1);
        SocialNetwork.addMessageToCommunity(community1, user2, message2);
        SocialNetwork.addMessageToCommunity(community3, user3, message3);
        SocialNetwork.addMessageToCommunity(community2, user1, message4);
        SocialNetwork.addMessageToCommunity(community1, user1, message5);
        SocialNetwork.addMessageToCommunity(community3, user8, message6);
        SocialNetwork.addMessageToCommunity(community3, user8, message7);

        System.out.println("\nВыведем все сообщения написанные в сообществе 1 (\"Java Coders\")\n");

        SocialNetwork.printAllCommunityMessages(community1);

        System.out.println("\nПоставим несколько лайков сообщению\n");

        SocialNetwork.addLike(user2, message2);
        SocialNetwork.addLike(user3, message2);
        SocialNetwork.addLike(user7, message2);
        SocialNetwork.addLike(user7, message2);

        System.out.println("\nВыведем список лайков для сообщения");

        SocialNetwork.printLikes(message2);

        System.out.println("\nИщем пользователя по имени. Передаем имя Yara Da Silva в метод\n");

        User search = SocialNetwork.findUserByName("Yara Da Silva");

        System.out.println("\nПроверяем корректно ли метод вернул искомого пользователя:\n");

        if (search == null){
            System.out.println("User = null");
        }
        else {
            System.out.println(search.getName());
        }

        System.out.println("\nИщем сообщество по имени. Передаем имя Java Coders в метод\n");
        Community searchCommunity = SocialNetwork.findCommunityByName("Java Coders");

        System.out.println("\nПроверяем корректно ли метод вернул искомое сообщество:\n");
        if (searchCommunity == null){
            System.out.println("Community = null");
        }
        else {
            System.out.println(searchCommunity.getCommunityName());
        }
    }
}
