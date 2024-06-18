package homeworkLesson42;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Task3_Route_Serializer {

    private static final String FILE_PATH = "/Users/viktorromanenko/Documents/Development/AITJavaLessons/src/main/java/homeworkLesson42/routes.ser";
    private static final Logger LOGGER = LoggerFactory.getLogger(Task3_Route_Serializer.class);

    public static void main(String[] args) {

        List<TourRoute> tourRoutes = new ArrayList<>();

        TourRoute route1 = new TourRoute("Historic Europe", "France", "Paris", 7, 1200.00);
        TourRoute route2 = new TourRoute("Alpine Adventure", "Switzerland", "Zurich", 5, 1500.00);
        TourRoute route3 = new TourRoute("Mediterranean Escape", "Italy", "Rome", 10, 2000.00);
        TourRoute route4 = new TourRoute("Northern Lights", "Norway", "Oslo", 6, 1800.00);
        TourRoute route5 = new TourRoute("Cultural Journey", "Spain", "Barcelona", 8, 1700.00);
        TourRoute route6 = new TourRoute("Castles and Cottages", "Germany", "Berlin", 9, 1600.00);
        TourRoute route7 = new TourRoute("Island Retreat", "Greece", "Athens", 7, 1400.00);
        TourRoute route8 = new TourRoute("Scenic Fjords", "Norway", "Bergen", 6, 1900.00);
        TourRoute route9 = new TourRoute("Baltic Discovery", "Estonia", "Tallinn", 5, 1300.00);
        TourRoute route10 = new TourRoute("Royal Capitals", "United Kingdom", "London", 8, 2100.00);

        tourRoutes.add(route1);
        tourRoutes.add(route2);
        tourRoutes.add(route3);
        tourRoutes.add(route4);
        tourRoutes.add(route5);
        tourRoutes.add(route6);
        tourRoutes.add(route7);
        tourRoutes.add(route8);
        tourRoutes.add(route9);
        tourRoutes.add(route10);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            objectOutputStream.writeObject(tourRoutes);
            LOGGER.info("Tour routes serialized successfully.");
        } catch (FileNotFoundException exception) {
            LOGGER.error(exception.getMessage());
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }
}
