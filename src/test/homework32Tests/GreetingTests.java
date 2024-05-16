package homework32Tests;

import homeworkLesson32.Greeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingTests {

    Greeting greetings;

    @BeforeEach
    void setUp(){
        greetings = new Greeting();
    }

    @Test
    @DisplayName("Тест приветствия по имени")
    void greetingTest (){
        String greetingNew = greetings.greeting("Alex");
        assertEquals("Hello Alex", greetingNew);
    }

    @Test
    @DisplayName("Тест приветствия если имя равно null")
    void greetingInputNullTest (){
        String greetingNew = greetings.greeting(null);
        assertEquals("Hello", greetingNew);
    }
}
