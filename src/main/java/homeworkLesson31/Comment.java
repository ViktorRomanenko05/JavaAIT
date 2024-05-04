package homeworkLesson31;

public class Comment extends Content {

    private String textOfComment;

    private String authorOfComment;

    public Comment(String textOfComment, String authorOfComment) {
        this.textOfComment = textOfComment;
        this.authorOfComment = authorOfComment;
    }

    public String getTextOfComment() {
        return textOfComment;
    }


    public String getAuthorOfComment() {
        return authorOfComment;
    }

    public void displayInfo(){
        System.out.println("Author: " +this.getAuthorOfComment()+"\n" +
                "Comment: " + this.getTextOfComment());
    }
}
