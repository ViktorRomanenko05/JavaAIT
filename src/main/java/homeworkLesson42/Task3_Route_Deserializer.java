package homeworkLesson42;

import homeworkLesson41.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task3_Route_Deserializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task3_Route_Deserializer.class);
    private static final String FILE_PATH = "/Users/viktorromanenko/Documents/Development/AITJavaLessons/src/main/java/homeworkLesson42/routes.ser";
    private static List<TourRoute> deserializedRoutes = new ArrayList<>();


    public static void main(String[] args) {

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            deserializedRoutes = (ArrayList<TourRoute>) objectInputStream.readObject();
            LOGGER.info("Routes deserialized successfully");
        } catch (FileNotFoundException exception) {
            LOGGER.error(exception.getMessage());
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        } catch (ClassNotFoundException exception) {
            LOGGER.error(exception.getMessage());
        }

        deserializedRoutes.forEach(System.out::println);
    }
}
