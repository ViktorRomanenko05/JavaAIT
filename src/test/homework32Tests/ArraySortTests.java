package homework32Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ArraySortTests {

    int [] numbers;

    @BeforeEach
    void setUp(){
        numbers = new int[]{1,4,2,9,0,4,7,5};
    }

    //Тест согласно заданию
    @Test
    @DisplayName("Сравнение сортированного и эталонного массивов")
    void testOfSort (){
        int [] standard = {0,1,2,4,4,5,7,9};
        Arrays.sort(numbers);
        assertArrayEquals(standard, numbers);
    }

    //Несортированный массив не равен эталонному
    @Test
    @DisplayName("Сравнение массивов без сортировки")
    void testNotEquals (){
        int [] standard = {0,1,2,4,4,5,7,9};
        assertNotEquals(standard, numbers);
    }
}
