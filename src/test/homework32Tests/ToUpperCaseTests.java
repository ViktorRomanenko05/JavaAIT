package homework32Tests;

import homeworkLesson32.StringCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ToUpperCaseTests {

    private StringCase wordCase;

    @BeforeEach
    void setUp(){
        wordCase = new StringCase();
    }

    @Test
    @DisplayName("проверка результата изменения регистра")
    void toUpperCaseTest(){
        assertEquals("CHECK", wordCase.stringToUpperCase("chEck"));
    }

    @Test
    @DisplayName("проверка результата изменения регистра - проверка негативного сценария")
    void toUpperCaseNegativeTest(){
        assertNotEquals("check", wordCase.stringToUpperCase("check"));
    }

    @Test
    @DisplayName("проверка результата изменения регистра - проверка на null")
    void toUpperCaseNotNull(){
        assertNotNull("check", wordCase.stringToUpperCase("cHeck"));
    }

    @Test
    @DisplayName("проверка результата если на входе null")
    void toUpperCaseNullTest(){
        assertNull(wordCase.stringToUpperCase(null));
    }
}