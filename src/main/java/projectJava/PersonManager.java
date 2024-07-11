package projectJava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PersonManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonManager.class);
    private static File file = Path.of("src","main", "java", "projectJava", "usersOfCasino.ser").toFile();
    private static HashMap <String, Person> users  = new HashMap<>();

    public HashMap<String, Person> getUsers() {
        return new HashMap<>(users);
    }

    //Сериализация пользователей
    public void serializeUsers() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(users);
            LOGGER.info("Users have been serialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users serialization", exception.getMessage());
        }
    }

    //Десериализация пользователей
    public void deserializeUsers() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            users = (HashMap <String, Person>) objectInputStream.readObject();
            LOGGER.info("Users have been dese`rialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
        catch (ClassNotFoundException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
    }

    //Метод добавления нового пользователя
    public void createNewUser(String name, LocalDate birthday, Double balance){

        int age = Period.between(birthday, LocalDate.now()).getYears();

        if(name == null || birthday == null || balance == null){
            LOGGER.warn("Parameters cannot be null");
            System.out.println("Поля не могут быть пустыми");
        }
        else if (age < 18){
            LOGGER.info("Trying to register user younger 18 years");
            System.out.println("Пользователи моложе 18 лет не могут быть зарегистрированы");
        }
        else if (balance < 0){
            LOGGER.warn("Attempt to enter a negative balance");
            System.out.println("Баланс не может быть отрицательным");
        }

        else {
            deserializeUsers();
            Person newUser = new Person(name, birthday, balance);
            Person previousUser = users.put(name, newUser);
            serializeUsers();
            if (previousUser == null){
                LOGGER.info("Profile of user {} was successfully created and added with ID: {}", name, newUser.getId());
                System.out.println("Профиль пользователя " + name + " был успешно создан с ID: " + newUser.getId());
            }
            else {
                LOGGER.info("Profile of user " + name + " was updated");
                System.out.println("Профиль пользователя " + name + " был обновлен");
            }
        }
    }

    //Сортировка стран по алфавиту
    public void sortByNameAndPrint(){
        deserializeUsers();
        List<Person> sortedUsers = users.values().stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
        System.out.println("\nСписок пользователей:");
        line();
        System.out.printf("%-4s %-25s %-4s %-10s %s%n", "№", "User name", "Age", "Balance", "User ID");
        line();
        int counter = 0;
        for (Person userToPrint : sortedUsers) {
            counter++;
            System.out.printf("%-4s %-25s %-4s %-10s %s%n", counter, userToPrint.getName(), userToPrint.getAge(), userToPrint.getBalance(), userToPrint.getId());
            //line();

        }
        line();
    }

    private void line(){
        System.out.println("------------------------------------------------------------------------------------");
    }
}
