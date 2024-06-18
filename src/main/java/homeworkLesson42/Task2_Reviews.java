package homeworkLesson42;

import homeworkLesson41.Task1PacientFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2_Reviews {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task2_Reviews.class);

    private static Scanner scanner = new Scanner(System.in);

    private static String FILE_PATH = "/Users/viktorromanenko/Documents/Development/AITJavaLessons/src/main/java/homeworkLesson42/reviews.txt";

    private static List<Review> reviews = new ArrayList<>();

    public static void main(String[] args) {
        int counter = 0;

        while (true) {

            counter++;
            String travelerName;
            String country;
            String reviewText;
            String visitDate;

            LOGGER.info("Writing of review {} started", counter);

            System.out.println("Введте ваше имя");
            travelerName = scanner.nextLine().trim();
            System.out.println("Введите вашу страну");
            country = scanner.nextLine().trim();
            System.out.println("Введите ваш отзыв");
            reviewText = scanner.nextLine().trim();
            System.out.println("Введите дату посещения");
            visitDate = scanner.nextLine().trim();

            Review review = new Review(travelerName, country, reviewText, visitDate);
            reviews.add(review);


            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH, StandardCharsets.UTF_8, true))) {
                bufferedWriter.write(travelerName + ", " + country + ", " + reviewText + ", " + visitDate + "\n");
            } catch (FileNotFoundException exception) {
                LOGGER.error(exception.getMessage());
            } catch (IOException exception) {
                LOGGER.error(exception.getMessage());
            }

            System.out.println("Желаете добавить еще одну запись? (да/нет)");
            String next = scanner.nextLine().trim();
            if (next.equalsIgnoreCase("нет") || next.equalsIgnoreCase("no") || next.equalsIgnoreCase("ytn")) {
                System.out.println("Записанные отзывы: ");
                reviews.forEach(System.out::println);
                LOGGER.info("Writing session is over. Number of reviews: {}", counter);
                break;
            }


        }
        scanner.close();
    }
}

