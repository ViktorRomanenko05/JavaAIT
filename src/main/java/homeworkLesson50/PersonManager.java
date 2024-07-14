package homeworkLesson50;

import backend.program.CreditManager;
import backend.program.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class PersonManager {

    private static HashMap <String, Person> users = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditManager.class);
    ProductManager productManager = new ProductManager();

    //Сериализация пользователей
    public static void serializeUsers() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.ser"))) {
            objectOutputStream.writeObject(users);
            LOGGER.info("Users have been serialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users serialization", exception.getMessage());
        }
    }

    //Десериализация пользователей
    public static void deserializeUsers() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/resources/users.ser"))) {
            users = (HashMap<String, Person>) objectInputStream.readObject();
            LOGGER.info("Users have been deserialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
        catch (ClassNotFoundException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
    }

    //Метод добавления пользователя в список
    public void addUser(Person person) {
        deserializeUsers();
        if (person == null) {
            LOGGER.error("Object customer is null");
        } else {
            Person previousUser = users.put(person.eMail, person);
            if (previousUser == null) {
                LOGGER.info("User " + person.getName() + " successfully added");
            } else {
                LOGGER.info("User " + person.getName() + " was updated");
            }
        }
        serializeUsers();
    }

    //Метод добавления покупки пользователю
    public void addPurchaseToUser (long userEmail, String itemId){
        Person user = users.get(userEmail);
        Product item = productManager.getProducts().get(itemId);

        if (user !=null && item != null){
            user
        }

    }
}
