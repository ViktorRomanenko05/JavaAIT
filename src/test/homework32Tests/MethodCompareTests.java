package homework32Tests;

import homeworkLesson32.MethodCompare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodCompareTests {
    private MethodCompare compare;

    @BeforeEach
    void setUp() {
        compare = new MethodCompare();
    }

    //Тест отключен согласно заданию
    //Например, если функционал метода в части реакции на null находится в доработке
    @Disabled("Test was disabled, bug #65645 was found")
    @Test
    @DisplayName("Проверка на null")
    void checkingForNull() {
        assertNotNull(compare.max(2, 55));
    }

    @Test
    @DisplayName("Проверка случая a > b")
    void checkingAmax(){
        assertEquals(10, compare.max(10,8));
    }

    @Test
    @DisplayName("Проверка случая a < b")
    void checkingAmin(){
        assertEquals(16, compare.max(10,16));
    }

    @Test
    @DisplayName("Проверка случая a - отрицательное, b - положительное число")
    void checkingAnegativeBpositive(){
        assertEquals(8, compare.max(-10,8));
    }

    @Test
    @DisplayName("Проверка случая b - отрицательное, a - положительное число")
    void checkingApositiveBnegative(){
        assertEquals(10, compare.max(10,-8));
    }

    @Test
    @DisplayName("Проверка случая a - negative")
    void checkingAnegativeMinBnegativeMax(){
        assertEquals(-21, compare.max(-35,-21));
    }

    @Test
    @DisplayName("Проверка случая a - negative")
    void checkingAnegativeMaxBnegativeMin(){
        assertEquals(-17, compare.max(-17,-74));
    }
}