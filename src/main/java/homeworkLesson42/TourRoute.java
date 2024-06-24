package homeworkLesson42;

import java.io.Serializable;

public class TourRoute implements Serializable {

    private String routeName;
    private String country;
    private String city;
    private int durationDays;
    private double cost;

    public TourRoute(String routeName, String country, String city, int durationDays, double cost) {
        this.routeName = routeName;
        this.country = country;
        this.city = city;
        this.durationDays = durationDays;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "TourRoute{" +
                "routeName: " + "\""+ routeName +"\""+
                ", country: " + country +
                ", city: " + city +
                ", durationDays: " + durationDays +
                ", cost: " + cost +
                '}';
    }
}
