package homework32Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Класс для тестирования методов класса ArrayList с использованием @BeforeEach

public class ArrayListTests {
    private ArrayList<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    @DisplayName("Проверка добавления элемента в лист")
    void addToListTest() {
        assertTrue(list.add("5"));
    }

    @Test
    @DisplayName("Проверка метода isEmpty если true")
    void emptyListTest() {
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Проверка метода isEmpty если false")
    void NotEmptyListTest() {
        list.add("1");
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("Проверка метода size")
    void sizeListTest() {
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
    }

}