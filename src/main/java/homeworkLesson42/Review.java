package homeworkLesson42;

public class Review {

    private String travelerName;
    private String country;
    private String reviewText;
    private String visitDate;

    public Review(String travelerName, String country, String reviewText, String visitDate) {
        this.travelerName = travelerName;
        this.country = country;
        this.visitDate = visitDate;
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "Review{" +
                "Имя гостя: " + travelerName +
                ", страна: " + country +
                ", отзыв: " + reviewText +
                ", дата визита: " + visitDate +
                '}';
    }
}
