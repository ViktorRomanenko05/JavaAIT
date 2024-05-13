package homeworkLesson31;

import java.util.ArrayList;
import java.util.Objects;

public class Video extends Content implements Interactable{

    private int videoId;

    private String nameOfVideo;

    private int durationSec;

    private int viewCount = 0;

    private int likeCount = 0;

    private int dislikeCount = 0;

    private ArrayList<Comment> commentsToVideo = new ArrayList<>(); //Необходимо добавить это поле, хоть его и нет в задании,
                                                            //так как иначе по моему мнению комментарии не имеют смысла


    public Video(int videoId, String nameOfVideo, int durationSec) {
        this.videoId = videoId;
        this.nameOfVideo = nameOfVideo;
        this.durationSec = durationSec;
    }

    public int getVideoId() {
        return videoId;
    }

    public String getNameOfVideo() {
        return nameOfVideo;
    }

    public void setNameOfVideo(String nameOfVideo) {
        this.nameOfVideo = nameOfVideo;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return videoId == video.videoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(videoId);
    }

    public ArrayList<Comment> getCommentsToVideo() {
        return new ArrayList(commentsToVideo);
    }

    public void view(){
        viewCount += 1;
    }

    public void like (){
        likeCount += 1;
    }

    public void dislike(){
        dislikeCount += 1;
    }

    public void addComment(Comment comment){
        commentsToVideo.add(comment);
    }

    @Override
    public void displayInfo(){
        System.out.println("Info about video:\n" +
                "ID: " + this.getVideoId()+"\n" +
                "Name: " + this.getNameOfVideo()+"\n" +
                "Duration: " + this.durationSec/60 + "min. " + this.durationSec%60 + "sec.\n" +
                "Likes: " + this.likeCount + "\n" +
                "Dislikes: " + this.dislikeCount + "\n" +
                "Views: " + this.viewCount);

        System.out.println("\nComments to this video:\n");
        for (Comment comment : commentsToVideo){
            System.out.println("Author:\n" + comment.getAuthorOfComment() +"\n" +
                               "Comment:\n" + comment.getTextOfComment() +"\n");
        }
    }
}
