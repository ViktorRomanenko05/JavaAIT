package projectJava;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class Person implements Serializable{

    private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);
    private static final long serialVersionUID = 1L;
   // private static Faker faker = new Faker();

    private String name;
    private final LocalDate birthday;
    private double balance;
    private long id;

    public Person(String name, LocalDate birthday, double balance) {
        this.name = name;
        this.birthday = birthday;
        this.balance = balance;
        this.id = parseLong(generateId());
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    //Геттер устанавливает баланс на 0? Спорно, но другого варианта не нашел
    public double getBalance() {
        if (this.balance < 0) {
            LOGGER.warn("Balance is negative. Correcting balance to 0.");
            this.balance = 0;
        }
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            LOGGER.warn("Balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Метод для генерации ID
    private String generateId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return LocalDateTime.now().format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
