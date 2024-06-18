package homeworkLesson37;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccount {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccount.class);

    @Getter
    private String accountNumber;

    @Getter
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        if (!isValidAccountNumber(accountNumber)) {
            LOGGER.error("Account number is incorrect");
            throw new IllegalArgumentException("Invalid account number. It must contain exactly 10 digits.");
        }
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    //Метод для проверки соответствия номера счета стандарту
    private boolean isValidAccountNumber(String accountNumber) {
        LOGGER.info("Account number check started");
        if (accountNumber == null) {
            LOGGER.warn("Account number is null");
            return false;
        }
        if (accountNumber.length() != 10) {
            LOGGER.warn("Length of account number is incorrect");
            return false;
        }
        for (int i = 0; i < accountNumber.length(); i++) {
            if (!Character.isDigit(accountNumber.charAt(i))) {
                LOGGER.warn("Not all characters in account number is digits");
                return false;
            }
        }
        LOGGER.info("Account number is correct");
        return true;
    }

    //Метод пополнения счета
    public void deposit(double amount) {
        LOGGER.info("The balance top-up operation has started.");
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount is less than 0");
            } else {
                balance += amount;
                LOGGER.info("{} euro have been added to the balance. Current balance: {} euro", amount, balance);
            }
        } catch (IllegalArgumentException exception) {
            LOGGER.error("Input amount is less than 0");
            LOGGER.error(exception.getMessage());
        } finally {
            LOGGER.info("Deposit operation is complete.");
        }
    }

    //Метод списания со счета
    public void withdraw(double amount) {
        LOGGER.info("The withdraw operation has started.");
        try {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount is less than 0");
            } else if (balance < amount) {
                double shortage = amount - balance;
                LOGGER.error("Insufficient funds: {} euros short to complete the transaction.", shortage);
                throw new InsufficientFundsException(shortage);
            } else {
                balance -= amount;
                LOGGER.info("{} euro have been withdrawn. Current balance: {} euro", amount, balance);
            }
        } catch (IllegalArgumentException exception) {
            LOGGER.error("Input amount is less than 0");
            LOGGER.error(exception.getMessage());
        } catch (InsufficientFundsException exception) {
            LOGGER.error("The account balance is insufficient to complete the transaction.");
            LOGGER.error(exception.getMessage());
        } finally {
            LOGGER.info("Withdraw operation is complete.");
        }
    }
}