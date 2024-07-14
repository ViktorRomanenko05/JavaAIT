package projectJavaTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import projectJava.GameManager;
import projectJava.GameType;
import projectJava.PersonManager;
import projectJava.Transaction;
import projectJava.TransactionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TransactionTests {

    private TransactionManager transactionManager;
    private PersonManager personManager;
    private GameManager gameManager;

    @BeforeEach
    public void setUp() {
        transactionManager = new TransactionManager();
        personManager = new PersonManager();
        gameManager = new GameManager();

        personManager.createNewUser("Lucky", LocalDate.of(1992, 1, 1), 1000000.0);
        gameManager.createNewGame("Poker", GameType.CARD, 10.0, 1000.0);

        transactionManager.deserializeTransactions();
    }

    @Test
    @DisplayName("Create transaction test")
    public void testCreateNewTransaction() {
        boolean add;
        int oldTransactionSize = transactionManager.getTransactions().size();
        transactionManager.createNewTransaction("Lucky", "Poker", 100.0, 200.0);
        Assertions.assertFalse(transactionManager.getTransactions().isEmpty());
        Assertions.assertNotEquals(oldTransactionSize, transactionManager.getTransactions().size());
    }

    @Test
    public void testInvalidBetTransaction() {
        int oldTransactionSize = transactionManager.getTransactions().size();
        transactionManager.createNewTransaction("Big Joe", "Poker", 5.0, 50.0);
        assertEquals(oldTransactionSize, transactionManager.getTransactions().size());
    }
}