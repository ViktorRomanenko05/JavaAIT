package homeworkLesson31;

import java.util.HashMap;
import java.util.Objects;

public class User {

    private int userId;

    private String userName;

    private static HashMap<Integer, Video> uploadedVideos = new HashMap<>();

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashMap<Integer, Video> getDownloadedVideos() {
        return new HashMap(uploadedVideos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", downloadedVideos=" + uploadedVideos +
                '}';
    }

    //Метод для добавления видео
    public void addVideo(Video video) {
        if (uploadedVideos.containsKey(video.getVideoId())) {
            System.out.println("Video " + video.getNameOfVideo() + " already added");
        } else {
            uploadedVideos.put(video.getVideoId(), video);
            System.out.println("Video " + video.getNameOfVideo() + " successfully added");
        }
    }

    //Метод для удаления видео
    public void removeVideo(int videoId) {
        if (!uploadedVideos.containsKey(videoId)) {
            System.out.println("Video  was not found");
        } else {
            uploadedVideos.remove(videoId);
            System.out.println("Video " + uploadedVideos.get(videoId).getNameOfVideo() + " was removed");
        }
    }

    //Метод для вывода информации о всех видео пользователя
    public void printAllVideos() {
        for (Video video : uploadedVideos.values()) {
            video.displayInfo();
        }
    }

    //Метод по добавлению комментария к видео
    public void addComment(int videoId, String comment) {
        Comment newComment = new Comment(comment, this.getUserName());
        uploadedVideos.get(videoId).addComment(newComment);
    }
}
