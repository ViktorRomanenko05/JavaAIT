package homeworkLesson42;

import homeworkLesson41.Task1PacientFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Task1_Bookings {
    private static final String FILE_PATH = "/Users/viktorromanenko/Documents/Development/AITJavaLessons/src/main/java/homeworkLesson42/bookings.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger(Task1_Bookings.class);


    public static void main(String[] args) {

        ArrayList<Booking> bookingsList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] bookings = line.split(",");

                Booking booking = new Booking(bookings [0],Integer.parseInt(bookings[1]),bookings[2],bookings[3], bookings[4]);
                bookingsList.add(booking);
            }
            LOGGER.info("List of bookings successfully created");
            bookingsList.forEach(System.out::println);
            
        }
        catch (FileNotFoundException exception){
            LOGGER.error (exception.getMessage());
        }
        catch (IOException exception){
            LOGGER.error(exception.getMessage());
        }
    }
}
