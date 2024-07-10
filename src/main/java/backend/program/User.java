package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
    private LocalDate registrationDate = LocalDate.now();  // Для каждого нового объекта при создании по умолчанию инициализируется датой создания
    private HashMap<String, Auto> purchases = new HashMap<>();   // Должен быть уникален для каждого объекта
    private ArrayList<TestDrive> testDrives = new ArrayList<>();// Лист с тест-драйвами на которые записался пользователь
    private HashMap<Long, Credit> credits = new HashMap<>(); //Лист с кредитами пользователя

    public User(String name, String password, String phoneNumber, String address, String eMail, int id) {
        super(name, password, phoneNumber, address, eMail, id);
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public ArrayList<TestDrive> getTestDrives() {
        return new ArrayList<>(testDrives);
    }

    public HashMap<String, Auto> getPurchases() {
        return new HashMap<>(purchases);
    }

    public HashMap<Long, Credit> getCredits() {
        return new HashMap<>(credits);
    }

    // Метод для добавления покупки пользователю
    public void addPurchase(Auto auto) {
        if (auto == null) {
            LOGGER.error("Object \"Auto\" is null");
            return;
        }

        Auto previousAuto = purchases.put(auto.getVinCode(), auto);

        if (previousAuto == null) {
            LOGGER.info("Auto " + auto.getModel() + " was added to purchases");
        } else {
            LOGGER.info("Auto in purchases list was updated");
        }
    }

    // Метод для добавления тест-драйва пользователю
    public void addTestDrive(TestDrive testDrive) {
        if (testDrive == null) {
            LOGGER.error("Object TestDrive is null");
            return;
        }

        // Добавление тест-драйва пользователю
        boolean add = testDrives.add(testDrive);
        if (add) {
            LOGGER.info("The user " + this.getName() + " was registered for a test drive.");

            // Сериализация данных пользователей после добавления тест-драйва
            PersonManager.users.put(this.getId(), this);
            PersonManager.serializeUsers();
        } else {
            LOGGER.info("Failed, please try again");
        }
    }

    //Метод удаления тест-драйва
    public void removeTestDriveByDetails(String vinCode, LocalDate date) {
        if (vinCode == null || date == null) {
            LOGGER.error("VIN code or date is null");
            return;
        }

        boolean found = false;
        for (int i = 0; i < testDrives.size(); i++) {
            TestDrive currentTestDrive = testDrives.get(i);
            if (currentTestDrive.getAuto().getVinCode().equalsIgnoreCase(vinCode) && currentTestDrive.getLocalDate().equals(date)) {
                testDrives.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            LOGGER.info("Test drive with VIN " + vinCode + " on date " + date + " was removed for user " + this.getName());
            PersonManager.users.put(this.getId(), this);
            PersonManager.serializeUsers();
        } else {
            LOGGER.error("Failed to remove test drive with VIN " + vinCode + " on date " + date + " for user " + this.getName());
        }
    }

    // Метод для добавления кредита пользователю
    public void addCredit(Credit credit) {
        if (credit == null) {
            LOGGER.error("Object \"Credit\" is null");
            return;
        }

        Credit previousCredit = credits.put(credit.getId(), credit);

        if (previousCredit == null) {
            LOGGER.info("Credit of " + credit.getCreditAmount() + " EURO was added to" + this.getName());
        } else {
            LOGGER.info("Credit with id " + credit.getId() +" was updated");
        }
    }

    @Override
    public String toString() {
        return ("User ID:   " + this.getId() + "\n" +
                "Name:      " + this.getName() + "\n" +
                "Phone:     " + this.getPhoneNumber() + "\n" +
                "E-mail:    " + this.getEmail() + "\n" +
                "Registered: "+ this.getRegistrationDate() + "\n");
    }
}
