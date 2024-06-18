package homeworkLesson37Tests;

import homeworkLesson37.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import nl.altindag.log.LogCaptor;
// Почемк-то не импортируется и не работает LogCaptor, хотя библиотека есть, как в Вашем последнем коммите
//Поэтому пока без тестирования логов, хотя при таком способе обработки ошибок, который реализован - оно необхлдимо здесь
public class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    void setUp(){
        bankAccount = new BankAccount("1234567890",0);
    }

    @Test
    void testValidAccountCreation() {
        assertDoesNotThrow(() -> new BankAccount("1010101010", 100.0));
    }

    @Test
    void testInvalidAccountCreation() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new BankAccount("12345", 100.0));
        assertEquals("Invalid account number. It must contain exactly 10 digits.", exception.getMessage());
    }

    @Test
    @DisplayName("Deposit positive amount")
    void testDepositPositiveAmount() {
        assertDoesNotThrow(() -> bankAccount.deposit(50.0));
        assertEquals(50.0, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Deposit negative amount")
    void testDepositNegativeAmount() {
        assertDoesNotThrow(() -> bankAccount.deposit(-50.0));
        assertEquals(00.0, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Deposit zero amount")
    void testDepositZeroAmount() {
        assertDoesNotThrow(() -> bankAccount.deposit(0.0));
        assertEquals(0.0, bankAccount.getBalance());
    }


    @Test
    @DisplayName("Withdraw valid amount")
    void testWithdrawValidAmount() {
        bankAccount.deposit(100);
        assertDoesNotThrow(() -> bankAccount.withdraw(50.0));
        assertEquals(50.0, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Withdraw big amount")
    void testWithdrawBigAmount() {
        bankAccount.deposit(100);
        assertDoesNotThrow(() -> bankAccount.withdraw(150.0));
        assertEquals(100.0, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Withdraw invalid amount")
    void testWithdrawInvalidAmount() {
        bankAccount.deposit(100);
        assertDoesNotThrow(() -> bankAccount.withdraw(-10.0));
        assertEquals(100.0, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Withdraw zero amount")
    void testWithdrawZeroAmount() {
        assertDoesNotThrow(() -> bankAccount.withdraw(0.0));
        assertEquals(0.0, bankAccount.getBalance());
    }


}

