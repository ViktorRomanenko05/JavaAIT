package projectJavaTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import projectJava.Person;
import projectJava.PersonManager;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PersonTests {

    private PersonManager personManager;

    @BeforeEach
    public void setUp() {
        personManager = new PersonManager();
        personManager.deserializeUsers();
    }

    @Test
    @DisplayName("Age Calculation")
    public void testAgeCalculation() {
        Person person = new Person("Lycky Man", LocalDate.of(1990, 1, 1), 1000.0);
        assertEquals(person.getAge(), LocalDate.now().getYear() - 1990);
    }

    @Test
    @DisplayName("Test negative balance")
    public void testNegativeBalance(){
        Person person2 = new Person("Unlycky Man", LocalDate.of(1975, 1, 1), -1000.0);
        assertEquals(0, person2.getBalance());
    }

    @Test
    @DisplayName("Set negative balance")
    public void testSetNegativeBalance() {
        Person person3 = new Person("Average Man", LocalDate.of(1985, 1, 1), 500.0);
        person3.setBalance(-1000.0);
        assertEquals(0, person3.getBalance());
    }

    @Test
    @DisplayName("Adding duplicate user")
    public void testDuplicateUser() {
        personManager.createNewUser("GameMan", LocalDate.of(1992, 2, 2), 1500.0);
        personManager.createNewUser("GameMan", LocalDate.of(1992, 2, 2), 2000.0);

        Person user = personManager.getUsers().get("Jane Doe");
        assertEquals(2000.0, user.getBalance()); // User balance should be updated
    }

    @Test
    @DisplayName("Test underage user")
    public void testUnderageUser() {
        int quantity = personManager.getUsers().size();
        personManager.createNewUser("Young", LocalDate.now().minusYears(17), 1000.0);
        assertEquals(quantity, personManager.getUsers().size());
    }


}
