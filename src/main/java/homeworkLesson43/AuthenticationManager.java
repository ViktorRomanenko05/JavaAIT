package homeworkLesson43;

import homeworkLesson41.Task1PacientFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManager.class);

    private static File file = Path.of("src","main", "java", "homeworkLesson43", "users.txt").toFile();

    private static HashMap<String, User> users = new HashMap<>();

    public static HashMap<String, User> getUsers() {
        return new HashMap<>(users);
    }

    //Метод для регистрации новых пользователей
    public static void registration(String username, String password) {
        parseUsers();
        if (users.containsKey(username)){
            System.out.println("Вы уже зарегистрированы\n" +
                    "Пожалуйста, войдите со своим паролем");
        }
        else {
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(username + ", " + password + ", " + "user\n");
                System.out.println("User " + username + " registered successfully");
                LOGGER.info("User " + username + " registered successfully");
            } catch (FileNotFoundException exception) {
                LOGGER.error(exception.getMessage());
            } catch (IOException exception) {
                LOGGER.error(exception.getMessage());
            }
        }
    }

    //Метод для парсинга данных из файла и добавления полученных объектов в HashMap
    public static void parseUsers (){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if(userData.length == 3){
                String username = userData[0].trim();
                String password = userData[1].trim();
                Role role = Role.fromStringRole(userData[2].trim());
                User user = new User(username, password, role);
                users.put(username, user);
                }
                else {
                    LOGGER.error("Invalid user data {}", line);
                }
            }
            LOGGER.info("Users parsed successfully");
        } catch (IOException exception) {
           LOGGER.error(exception.getMessage());
        }
    }

    //Метод для аутентификации
    public static User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("User " + username + " logged in successfully");
            LOGGER.info("User " + username + " logged in successfully");
        } else {
            System.out.println("Invalid username or password");
            LOGGER.info("User {} login failed", username);
        }return user;
    }

    //Смена роли пользователя
    public static void changeUserRole(String username, Role role) {
        parseUsers();
        User user = users.get(username);
        if (user != null) {
            user.setRole(role);
            System.out.println("User " + username + " role changed to admin");
            LOGGER.info("User " + username + " role changed to {}", user.getRole());
            try (FileWriter fileWriter = new FileWriter(file)) {
                for (User userBuffer : users.values()) {
                    fileWriter.write(userBuffer.getName() + ", " + userBuffer.getPassword() + ", " + Role.toStringRole(userBuffer.getRole()) + "\n");
                }
                System.out.println("User " + username + " has been updated successfully");
                LOGGER.info("User " + username + " has been updated successfully");
            } catch (FileNotFoundException exception) {
                LOGGER.error(exception.getMessage());
            } catch (IOException exception) {
                LOGGER.error(exception.getMessage());
            }
        } else {
            System.out.println("User not found");
            LOGGER.info("User" + username + " not found");
        }
    }



}
