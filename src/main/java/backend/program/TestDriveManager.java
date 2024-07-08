package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;


public class TestDriveManager {
    private HashSet<TestDrive> testDriveList = new HashSet<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDriveManager.class);

    public HashSet<TestDrive> getTestDriveList() {
        return new HashSet<>(testDriveList);
    }

    //Добавляем запись в список Тест Драйв
    public void addTestDrive(TestDrive testDrive) {
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
                checkDate = true;
                break;
            }
        }
        if (!checkDate) {
            testDriveList.add(testDrive);
            testDrive.getUser().addTestDrive(testDrive);
            LOGGER.info("Test Drive was added{} {}", testDrive.getAuto().getModel(), testDrive.getLocalDate());
        }
    }

    //Удаляем запись из списка на Тест Драйв
    public void removeTestDrive(TestDrive testDrive) {
        if (testDrive == null) {
            LOGGER.error("Error!!! Empty value");
            throw new NullPointerException("Value is null");
        }
        LOGGER.info("Remove Test Drive");
        boolean checkResult = false;
        for (TestDrive test : testDriveList) {
            if (test.getLocalDate().equals(testDrive.getLocalDate()) &&
                    test.getAuto().getVinCode().equalsIgnoreCase(testDrive.getAuto().getVinCode())) {
                checkResult = testDriveList.remove(test);
                LOGGER.info("Test drive {} {} was removed.", test.getAuto(), test.getLocalDate());
                break;
            }
        }
        if (!checkResult) {
            LOGGER.error("Test drive {} {} request not found", testDrive.getAuto().getModel(), testDrive.getUser().getName());
        }
    }

    //Вывод на экран всех записей Тест Драв
    public void displayTestDrive() {
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
