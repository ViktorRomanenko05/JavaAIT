package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonManager.class);


    static HashMap<Integer, User> users = new HashMap<>(); // Для хранения юзеров
    static HashMap<Integer, Employee> employees = new HashMap<>(); // Для хранения сотрудников

    public HashMap<Integer, User> getUsers() {
        deserializeUsers();
        return new HashMap<>(users);
    }

    public HashMap<Integer, Employee> getEmployees() {
        deserializeEmployees();
        return new HashMap<>(employees);
    }

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
            users = (HashMap<Integer, User>) objectInputStream.readObject();
            LOGGER.info("Users have been deserialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
        catch (ClassNotFoundException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
    }

    //Сериализация сотрудников
    public static void serializeEmployees() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/employees.ser"))) {
            objectOutputStream.writeObject(employees);
            LOGGER.info("Employees have been serialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users serialization", exception.getMessage());
        }
    }

    //Десериализация сотрудников
    public static void deserializeEmployees() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/resources/employees.ser"))) {
            employees = (HashMap<Integer, Employee>) objectInputStream.readObject();
            LOGGER.info("Employees have been deserialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
        catch (ClassNotFoundException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }

    }

    // Метод для аутентификации пользователя
    public AccessLevel authentication(String eMail, String password) {
        deserializeUsers();

        User user = findUserByEmail(eMail);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                LOGGER.info("User " + eMail + " authenticated successfully.");
                return user.getAccessLevel();
            } else {
                LOGGER.warn("User " + eMail + " provided wrong password.");
                return AccessLevel.NONE;
            }
        }

        deserializeEmployees();
        Employee employee = findEmployeeByEmail(eMail);
        if (employee != null) {
            if (employee.getPassword().equals(password)) {
                LOGGER.info("Employee " + eMail + " authenticated successfully.");
                return employee.getAccessLevel();
            } else {
                LOGGER.warn("Employee " + eMail + " provided wrong password.");
                return AccessLevel.NONE;
            }
        }

        LOGGER.info("No user or employee found with email " + eMail);
        return AccessLevel.NONE;
    }

    // Метод для добавления пользователей в хранилище
    public void addUser(User user) {
        deserializeUsers();
        if (user == null) {
            LOGGER.error("Object customer is null");
        } else {
            User previousUser = users.put(user.getId(), user);
            if (previousUser == null) {
                LOGGER.info("User " + user.getName() + " successfully added");
            } else {
                LOGGER.info("User " + user.getName() + " was updated");
            }
        }
        serializeUsers();
    }

    // Создание профиля пользователя
    public void createUser(String name, String eMail, String phoneNumber, String address, String password) {
        deserializeUsers();
        deserializeEmployees();
        if ((users.size() + employees.size()) < 999999) {
            int newId;
            do {
                newId = 1 + (int) (Math.random() * 1000000);
            } while (users.containsKey(newId) || employees.containsKey(newId));
            User newUser = new User(name, password, phoneNumber, address, eMail, newId);
            users.put(newId, newUser);
            LOGGER.info("User was added with id: " + newId);
            serializeUsers();
            serializeEmployees();
        } else {
            LOGGER.warn("Memory is full");
        }
    }

    // Создание профиля сотрудника
    public void createEmployee(String name, String eMail, String phoneNumber, String address, String password, String position) {
        deserializeUsers();
        deserializeEmployees();
        if ((users.size() + employees.size()) < 1000000) {
            int newId;
            do {
                newId = 1 + (int) (Math.random() * 1000000);
            } while (users.containsKey(newId) || employees.containsKey(newId));
            Employee newEmployee = new Employee(name, password, phoneNumber, address, eMail, newId, position);
            employees.put(newId, newEmployee);
            LOGGER.info("Employee was added with id: " + newId);
            newEmployee.setAccessLevel(AccessLevel.MANAGER);
            serializeEmployees();
        } else {
            LOGGER.warn("Memory is full");
        }
    }

    // Метод для добавления сотрудников в хранилище
    public void addEmployee(Employee employee) {
        deserializeEmployees();
        if (employee == null) {
            LOGGER.error("Object employee is null");
        } else {
            Employee previousEmployee = employees.put(employee.getId(), employee);
            if (previousEmployee == null) {
                LOGGER.info("Employee " + employee.getName() + " successfully added");
            } else {
                LOGGER.info("Employee " + employee.getName() + " was updated");
            }
        }
        serializeEmployees();
    }

    // Поиск пользователя по имени
    public ArrayList<User> findUserByName(String name) {
        deserializeUsers();
        ArrayList<User> foundUsers = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getName().equalsIgnoreCase(name)) {
                foundUsers.add(user);
            }
        }
        if (foundUsers.isEmpty()) {
            LOGGER.info("User " + name + " was not found");
        } else {
            LOGGER.info("Users with the name " + name + " were found: " + foundUsers.size());
        }
        return foundUsers;
    }

    // Поиск пользователя по id
    public User findUserByID(int id) {
        deserializeUsers();
        if (users.containsKey(id)) {
            LOGGER.info("User with ID " + id + " was found");
            return users.get(id);
        } else {
            LOGGER.info("User with ID " + id + " was not found");
            return null;
        }
    }

    // Поиск сотрудника по id
    public Employee findEmployeeByID(int id) {
        deserializeEmployees();
        if (employees.containsKey(id)) {
            LOGGER.info("Employee with ID " + id + " was found");
            return employees.get(id);
        } else {
            LOGGER.info("Employee with ID " + id + " was not found");
            return null;
        }
    }


    // Поиск пользователя по e-mail
    public User findUserByEmail(String email) {
        deserializeUsers();
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                LOGGER.info("User with email " + email + " was found");
                return user;
            }
        }
        LOGGER.info("User with email " + email + " was not found");
        return null;
    }

    // Поиск сотрудника по e-mail
    public Employee findEmployeeByEmail(String email) {
        deserializeEmployees();
        for (Employee employee : employees.values()) {
            if (employee.getEmail().equalsIgnoreCase(email)) {
                LOGGER.info("Employee with email " + email + " was found");
                return employee;
            }
        }
        LOGGER.info("Employee with email " + email + " was not found");
        return null;
    }

    // Метод для удаления пользователя по ID
    public void removeUserById(int id) {
        deserializeUsers();
        if (users.containsKey(id)) {
            users.remove(id);
            LOGGER.info("User with ID " + id + " was successfully removed");
        } else {
            LOGGER.info("User with ID " + id + " was not found");
        }
        serializeUsers();
    }

    // Метод для удаления сотрудника по ID
    public void removeEmployeeById(int id) {
        deserializeEmployees();
        if (employees.containsKey(id)) {
            employees.remove(id);
            LOGGER.info("Employee with ID " + id + " was successfully removed");
        } else {
            LOGGER.info("Employee with ID " + id + " was not found");
        }
        serializeEmployees();
    }

    // Метод для изменения имени сотрудника
    public void changeEmployeeName(int id, String newName) {
        deserializeEmployees();
        if (employees.containsKey(id)) {
            employees.get(id).setName(newName);
            LOGGER.info("Name of employee " + id + " was successfully changed to " + newName);
        } else {
            LOGGER.info("Employee with ID " + id + " was not found");
        }
        serializeEmployees();
    }

    // Метод для изменения имени пользователя
    public void changeUserName(int id, String newName) {
        deserializeUsers();
        if (users.containsKey(id)) {
            users.get(id).setName(newName);
            LOGGER.info("Name of user " + id + " was successfully changed to " + newName);
        } else {
            LOGGER.info("User with ID " + id + " was not found");
        }
        serializeUsers();
    }

    // Метод для изменения E-mail сотрудника
    public void changeEmployeeEmail(int id, String newEmail) {
        deserializeEmployees();
        if (employees.containsKey(id)) {
            employees.get(id).setEmail(newEmail);
            LOGGER.info("E-mail of employee " + id + " was successfully changed");
        } else {
            LOGGER.info("Employee with ID " + id + " was not found");
        }
        serializeEmployees();
    }

    // Метод для изменения E-mail пользователя
    public void changeUserEmail(int id, String newEmail) {
        deserializeUsers();
        if (users.containsKey(id)) {
            users.get(id).setEmail(newEmail);
            LOGGER.info("E-mail of user " + id + " was successfully changed");
        } else {
            LOGGER.info("User with ID " + id + " was not found");
        }
        serializeUsers();
    }

    // Метод для изменения должности сотрудника
    public void changeEmployeePosition(int id, String newPosition) {
        deserializeEmployees();
        if (employees.containsKey(id)) {
            employees.get(id).setPosition(newPosition);
            LOGGER.info("Position of employee " + id + " was successfully changed");
        } else {
            LOGGER.info("Employee with ID " + id + " was not found");
        }
        serializeEmployees();
    }

    // Метод для изменения номера телефона сотрудника
    public void changeEmployeePhoneNumber(int id, String newPhoneNumber) {
        deserializeEmployees();
        if (employees.containsKey(id)) {
            employees.get(id).setPhoneNumber(newPhoneNumber);
            LOGGER.info("Phone number of employee " + id + " was successfully changed");
        } else {
            LOGGER.info("Employee with ID " + id + " was not found");
        }
        serializeEmployees();
    }

    // Метод для изменения номера телефона пользователя
    public void changeUserPhoneNumber(int id, String newPhoneNumber) {
        deserializeUsers();
        if (users.containsKey(id)) {
            users.get(id).setPhoneNumber(newPhoneNumber);
            LOGGER.info("Phone number of user " + id + " was successfully changed");
        } else {
            LOGGER.info("User with ID " + id + " was not found");
        }
        serializeUsers();
    }

    // Метод для изменения адреса сотрудника
    public void changeEmployeeAddress(int id, String newAddress) {
        deserializeEmployees();
        if (employees.containsKey(id)) {
            employees.get(id).setAddress(newAddress);
            LOGGER.info("Address of employee " + id + " was successfully changed");
        } else {
            LOGGER.info("Employee with ID " + id + " was not found");
        }
        serializeEmployees();
    }

    // Метод для изменения адреса пользователя
    public void changeUserAddress(int id, String newAddress) {
        deserializeUsers();
        if (users.containsKey(id)) {
            users.get(id).setAddress(newAddress);
            LOGGER.info("Address of user " + id + " was successfully changed");
        } else {
            LOGGER.info("User with ID " + id + " was not found");
        }
        serializeUsers();
    }

    // Метод для добавления продажи сотруднику
    public void addSaleToEmployeeByEmail(String email, Auto auto) {
        deserializeEmployees();
        Employee employee = findEmployeeByEmail(email);
        if (employee != null) {
            employee.addSale(auto);
            LOGGER.info("Sale added to employee with email " + email);
            serializeEmployees();
        } else {
            LOGGER.info("Employee with email " + email + " was not found");
        }
    }

    // Метод для добавления покупки пользователю
    public void addPurchaseToUserByEmail(String email, Auto auto) {
        deserializeUsers();
        User user = findUserByEmail(email);
        if (user != null) {
            user.addPurchase(auto);
            LOGGER.info("Purchase added to user with email " + email);
            serializeUsers();
        } else {
            LOGGER.info("User with email " + email + " was not found");
        }
    }

    // Метод для добавления тест-драйва пользователю
    public void addTestDriveToUserByEmail(String email, TestDrive testDrive) {
        deserializeUsers();
        User user = findUserByEmail(email);
        if (user != null) {
            user.addTestDrive(testDrive);
            LOGGER.info("Test drive added to user with email " + email);
            serializeUsers();
        } else {
            LOGGER.info("User with email " + email + " was not found");
        }
    }

    //Изменить уровень доступа пользователя (поиск по E-mail)
    public void changeEmployeeAccessLevelByEmail(String email, AccessLevel newAccessLevel) {
        deserializeEmployees();
        Employee employee = findEmployeeByEmail(email);
        if (employee != null) {
            employee.setAccessLevel(newAccessLevel);
            LOGGER.info("Access level of employee with email " + email + " was successfully changed to " + newAccessLevel);
        } else {
            LOGGER.info("Employee with email " + email + " was not found");
        }
        serializeEmployees();
    }

    //Изменить уровень доступа сотрудника (поиск по id)
    public void changeEmployeeAccessLevelById(int id, AccessLevel newAccessLevel) {
        deserializeEmployees();
        Employee employee = findEmployeeByID(id);
        if (employee != null) {
            employee.setAccessLevel(newAccessLevel);
            LOGGER.info("Access level of employee with ID " + id + " was successfully changed to " + newAccessLevel);
        } else {
            LOGGER.info("Employee with ID " + id + " was not found");
        }
        serializeEmployees();
    }
}
