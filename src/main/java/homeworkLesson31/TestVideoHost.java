package homeworkLesson31;


import java.util.HashSet;

public class TestVideoHost {

    static private HashSet<User> users = new HashSet<>();

    public static void main(String[] args) {

        //Создадим несколько пользователей, из которых 1 - с дублирующимс id
        User user1 = new User(1, "Alex");
        User user2 = new User(2, "Emil");
        User user3 = new User(1, "Alan");

        //Добавим пользователей в список для проверки методов equals & hashcode
        users.add(user1);
        users.add(user2);
        users.add(user3);

        //Для контроля выведем на экран количество добавленных пользователей
        System.out.println(users.size() + " users was added\n");

        //Создадим несколько объектов Video
        Video videoAboutCats = new Video(3, "Video About Cats", 98);
        Video videoAboutDogs = new Video(4, "Video about Dogs", 374);

        //Добавим видео пользователю Alex
        user1.addVideo(videoAboutCats);

        //Создадим интерфейс для одного из видео
        Interactable interactable = videoAboutCats;

        //Поставим несколько лайков
        int quantity = 0;
        do {
            quantity++;
            interactable.like();
        } while (quantity < 1234);

        //Поставим несколько дизлайков
        quantity = 0;
        do {
            quantity++;
            interactable.dislike();
        } while (quantity < 57);

        //Добавим просмотров
        quantity = 0;
        do {
            quantity++;
            interactable.view();
        } while (quantity < 8888);

        //Добавим комментарии под видео
        user1.addComment(3,"Good movie");
        user2.addComment(3,"I like dogs");

        //Выведем на экран все видео пользователя и информацию о них
        user1.printAllVideos();
    }
}
