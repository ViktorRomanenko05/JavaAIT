package homeworkLesson37;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankOperations {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankOperations.class);

    public static void main(String[] args) {
        try {
            BankAccount account = new BankAccount("2201500932", 0);
            //Перенес обработку ошибок в класс BankAccount так как при выполнении по заданию - после выбрасывания исключения
            //следующие за этим строки в блоке try не выполняются и нужно создавать несколько try/catch блоков,
            //что не совсем удобно
            account.deposit(100);
            account.deposit(-100); // Ошибка
            account.deposit(1000);

            account.withdraw(500);
            account.withdraw(-100); // Ошибка
            account.withdraw(700); // Ошибка

            LOGGER.info("All operations are complete.");

            //Дополнительно для проверки пробуем создать аккаунт с неподходящим номером
            BankAccount accountBad = new BankAccount("2201500932a", 0);

        } catch (IllegalArgumentException exception) {
            LOGGER.error("Failed to create bank account: {}", exception.getMessage());
        }
        finally {
            LOGGER.info("The program is completed");
        }
    }
}