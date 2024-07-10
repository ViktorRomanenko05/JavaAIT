package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;


public class TestDriveManager implements Serializable {
    private HashSet<TestDrive> testDriveList = new HashSet<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDriveManager.class);

    public HashSet<TestDrive> getTestDriveList() {
        return new HashSet<>(testDriveList);
    }

    //Сериализация тест-драйвов
    public void serializeTestDrives() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/testDrives.ser"))) {
            objectOutputStream.writeObject(testDriveList);
            LOGGER.info("Test-drives have been serialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users serialization", exception.getMessage());
        }
    }

    //Десериализация тест-драйвов
    public void deserializeTestDrives() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/resources/testDrives.ser"))) {
            testDriveList = (HashSet<TestDrive>) objectInputStream.readObject();
            LOGGER.info("Users have been deserialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
        catch (ClassNotFoundException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
    }

    //Добавляем запись в список Тест Драйв
    public void addTestDrive(TestDrive testDrive) {
        deserializeTestDrives();
        PersonManager.deserializeUsers();
        if (testDrive == null) {
            LOGGER.error("Error!!! Empty value");
            throw new NullPointerException("Value is null");
        }
        LOGGER.info("Test Drive addition started");
        boolean checkDate = false;
        //Проверка на свободную Дату и Машины по VinCode
        for (TestDrive driveDate : testDriveList) {
            if (testDrive.getLocalDate().equals(driveDate.getLocalDate()) &&
                    testDrive.getAuto().getVinCode().equalsIgnoreCase(driveDate.getAuto().getVinCode())) {
                LOGGER.error("{} Auto {} {} is busy on this date", testDrive.getLocalDate(), testDrive.getAuto().getBrand(), testDrive.getAuto().getModel());
                System.out.println(testDrive.getLocalDate() + " Auto " + testDrive.getAuto().getBrand() + " " + testDrive.getAuto().getModel() + " is busy on this date");
                checkDate = true;
                break;
            }
        }
        if (!checkDate) {
            testDriveList.add(testDrive);
            testDrive.getUser().addTestDrive(testDrive);
            // Для логгера
            LOGGER.info("User {} was scheduled for a test drive of {} on {}", testDrive.getUser().getName(), testDrive.getAuto().getModel(), testDrive.getLocalDate());

// Для консоли
            System.out.println("User " + testDrive.getUser().getName() + " was scheduled for a test drive of " + testDrive.getAuto().getModel() + " on " + testDrive.getLocalDate());
        }
        serializeTestDrives();
        PersonManager.serializeUsers();
    }

    //Удаляем запись из списка на Тест Драйв
    public void removeTestDrive(TestDrive testDrive) {
        deserializeTestDrives();
        if (testDrive == null) {
            LOGGER.error("Error!!! Empty value");
        }
        LOGGER.info("Remove Test Drive");
        boolean checkResult = false;
        for (TestDrive test : testDriveList) {
            if (test.getLocalDate().equals(testDrive.getLocalDate()) &&
                    test.getAuto().getVinCode().equalsIgnoreCase(testDrive.getAuto().getVinCode())) {
                checkResult = testDriveList.remove(test);
                LOGGER.info("Test drive {} {} was removed.", test.getAuto(), test.getLocalDate());
                serializeTestDrives();
                break;
            }
        }
        if (!checkResult) {
            LOGGER.error("Test drive {} {} request not found", testDrive.getAuto().getModel(), testDrive.getUser().getName());
        }

    }

    //Вывод на экран всех записей Тест Драйв
    public void displayTestDrive() {
        deserializeTestDrives();
        if (testDriveList.isEmpty()) {
            LOGGER.error("No test drive appointments found");
        } else {
            for (TestDrive test : testDriveList) {
                System.out.println(test);
            }
        }
    }

    //Поиск Test Drive по Email
    public HashSet<TestDrive> scoursTestDrive(String eMail) {
        deserializeTestDrives();
        if (eMail == null) {
            LOGGER.error("Error!!! Empty value");
            throw new NullPointerException("Value is null");
        }
        LOGGER.info("Scours Test Drive");
        // Список записей User на Test Drive
        HashSet<TestDrive> userTestDriveList = new HashSet<>();
        for (TestDrive testDrive : testDriveList) {
            if (testDrive.getUser().getEmail().equalsIgnoreCase(eMail)) {
                userTestDriveList.add(testDrive);
                LOGGER.debug("Test Drive was found: {} {} {}", testDrive.getAuto().getBrand(), testDrive.getAuto().getModel(), testDrive.getLocalDate());
            }
        }
        return userTestDriveList;
    }
}
