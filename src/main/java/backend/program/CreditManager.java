package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreditManager implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditManager.class);
    private HashMap<Long, Credit> credits = new HashMap<>();
    private static final String FILE_PATH = "src/main/resources/credits.ser";

    // Метод для сериализации хранилища в файл
    private void serializeCredits() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            objectOutputStream.writeObject(credits);
        } catch (IOException exception) {
            LOGGER.error("Error serializing credits to file", exception.getMessage());
        }
    }

    // Метод для десериализации хранилища из файла.
    private void deserializeCredits() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            credits = (HashMap<Long, Credit>) objectInputStream.readObject();
        } catch (IOException exception) {
            LOGGER.error("Error serializing credits to file", exception.getMessage());
        }
        catch (ClassNotFoundException exception){
            LOGGER.error("Class `Credit` was not found", exception.getMessage());
        }
    }

    // Метод добавления кредита.
    public void addCredit(LocalDateTime startDate, LocalDateTime endDate, double amount, int userId) {
        deserializeCredits();
        PersonManager.deserializeUsers();
        PersonManager personManager = new PersonManager();
        User user = personManager.findUserByID(userId);
        if (user != null) {
            Credit credit = new Credit(amount, startDate, endDate, user);
            if (credit == null) {
                LOGGER.error("Object credit is null");
            } else {
                Credit previousCredit = credits.put(credit.getId(), credit);
                credit.getUser().addCredit(credit);
                if (previousCredit == null) {
                    LOGGER.info("Credit for " + credit.getUser().getName() + " of " + credit.getCreditAmount() + " has been successfully added");
                } else {
                    LOGGER.info("Credit for " + credit.getUser().getName() + " was previously added");
                }
            }
        }
        else {
            LOGGER.error("User with ID " + userId + " was not found");
        }
        serializeCredits();
        personManager.serializeUsers();
    }

    // Метод для поиска кредитов по ID пользователя
    public List<Credit> findCreditsByUserId(int userId) {
        deserializeCredits();
        List<Credit> userCredits = new ArrayList<>();
        for (Credit credit : credits.values()) {
            if (credit.getUser().getId() == userId) {
                userCredits.add(credit);
            }
        }
        if (userCredits.isEmpty()) {
            LOGGER.info("No credits found for user ID " + userId);
        } else {
            LOGGER.info("Found " + userCredits.size() + " credits for user ID " + userId);
        }
        return userCredits;
    }

    // Метод для поиска кредита по ID кредита
    public Credit findCreditById(long creditId) {
        deserializeCredits();
        Credit credit = credits.get(creditId);
        if (credit != null) {
            LOGGER.info("Credit with ID " + creditId + " was found");
        } else {
            LOGGER.info("Credit with ID " + creditId + " was not found");
        }
        return credit;
    }

    // Метод для изменения статуса кредита
    public void changeCreditStatus(long creditId, CreditStatus newStatus) {
        deserializeCredits();
        Credit credit = credits.get(creditId);
        if (credit != null) {
            credit.setStatus(newStatus);
            LOGGER.info("Status of credit with ID " + creditId + " has been changed to " + newStatus);
        } else {
            LOGGER.info("Credit with ID " + creditId + " was not found");
        }
        serializeCredits();
    }
    // Метод для получения всех активных кредитов
    public List<Credit> getAllActiveCredits() {
        deserializeCredits();
        List<Credit> activeCredits = new ArrayList<>();
        for (Credit credit : credits.values()) {
            if (credit.getStatus() == CreditStatus.ACTIVE) {
                activeCredits.add(credit);
            }
        }
        LOGGER.info("Found " + activeCredits.size() + " active credits");
        return activeCredits;
    }

    // Метод для получения всех архивных кредитов
    public List<Credit> getAllArchiveCredits() {
        deserializeCredits();
        List<Credit> archiveCredits = new ArrayList<>();
        for (Credit credit : credits.values()) {
            if (credit.getStatus() == CreditStatus.ARCHIVE) {
                archiveCredits.add(credit);
            }
        }
        LOGGER.info("Found " + archiveCredits.size() + " archive credits");
        return archiveCredits;
    }

    // Метод для получения всех просроченных кредитов
    public List<Credit> getAllOverdueCredits() {
        deserializeCredits();
        List<Credit> overdueCredits = new ArrayList<>();
        for (Credit credit : credits.values()) {
            if (credit.getStatus() == CreditStatus.OVERDUE) {
                overdueCredits.add(credit);
            }
        }
        LOGGER.info("Found " + overdueCredits.size() + " overdue credits");
        return overdueCredits;
    }

}
