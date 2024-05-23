package homework33Tests;

import homeworkLesson33.ClothingItem;
import homeworkLesson33.ClothingManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClothingItemManagerTests {

    ClothingManager clothingManager;
    List <ClothingItem> result;

    @BeforeEach
    void setUp(){
        clothingManager = new ClothingManager();
        result = new ArrayList<>();
    }


    //Тест для метода добавления одежды в список

    /*
    Так как @ValueSource работает только с примитивными типами данных и не может содержать объекты,
    а тему Stream, чтобы реализовать данный метод с помощью @MethodSource мы еще не проходили,
    реализация теста пока такая, не Parameterized.

    Особенности:
    Один из объектов передаваемых для добавления - null.
     */
    @Test
    @DisplayName("Add items, test 1")
    void addTest(){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Snickers", "45", 115));
        clothingManager.addClothingItem(new ClothingItem("Nike", "T-Shirt", "L",  50));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Jacket", "M", 80));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Shorts", "S", 35));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Cap", "M", 25));
        clothingManager.addClothingItem(null);

        assertEquals(5,clothingManager.getClothingItems().size());
        assertEquals("Jacket", clothingManager.getClothingItems().get(2).getType());
    }


    //Тесты для метода удаления одежды по названию

    /*
    Тест №1 для метода удаления одежды по названию
    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Среди элементов передается один null
    Данные на входе метода - не null
     */

    @ParameterizedTest
    @ValueSource (strings = {"NikE", "AdiDaS"})
    @DisplayName("removeClothingItem, test 1 (list not empty, input not null, case check)")
    void removeClothingItemTest1(String input){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "T-Shirt", "S", 45));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Jacket", "L", 120));
        clothingManager.addClothingItem(null);
        clothingManager.addClothingItem(new ClothingItem("Reebok", "T-Shirt", "XL", 40));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Shorts", "M", 30));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Cap", "L", 20));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Snickers", "46", 110));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Shorts", "S", 25));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Jacket", "M", 90));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "T-Shirt", "L", 35));

        assertTrue(clothingManager.removeClothingItem(input));
        assertEquals(7, clothingManager.getClothingItems().size());
    }

    /*
    Тест №2 для метода удаления одежды по названию
    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Среди элементов передается один null
    Данные на входе метода - null

     */
    @Test
    @DisplayName("removeClothingItem, test 2 (list not empty, input is null)")
    void removeClothingItemTest2(){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "T-Shirt", "S", 45));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Jacket", "L", 120));
        clothingManager.addClothingItem(null);
        clothingManager.addClothingItem(new ClothingItem("Reebok", "T-Shirt", "XL", 40));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Shorts", "M", 30));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Cap", "L", 20));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Snickers", "46", 110));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Shorts", "S", 25));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Jacket", "M", 90));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "T-Shirt", "L", 35));

        assertFalse(clothingManager.removeClothingItem(null));
        assertEquals(9, clothingManager.getClothingItems().size());
    }

    /*
    Тест №3 для метода удаления одежды по названию
    Особенности:
    Лист предварительно не заполняется элементами и перед началом теста - пустой
    Данные на входе метода - не null
     */
    @ParameterizedTest
    @ValueSource (strings = {"Reebok", "Puma"})
    @DisplayName("removeClothingItem, test 4 (list is empty, input not null)")
    void removeClothingItemTest3(String input){

        assertFalse(clothingManager.removeClothingItem(input));
        assertEquals(0, clothingManager.getClothingItems().size());
    }

    /*
    Тест №4 для метода удаления одежды по названию
    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Среди элементов передается один null
    Данные на входе метода - не null
    Данные на входе отличны от данных в списке и не могут быть найдены
     */

    @ParameterizedTest
    @ValueSource (strings = {"TestData#1", "TestData#2", "TestData#3"})
    @DisplayName("removeClothingItem, test 5 (list not empty, input not null)")
    void removeClothingItemTest4(String input){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Snickers", "43", 105));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Cap", "M", 15));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Jacket", "XL", 85));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Snickers", "44", 115));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Shorts", "L", 40));
        clothingManager.addClothingItem(null);
        clothingManager.addClothingItem(new ClothingItem("Nike", "Snickers", "42", 100));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Cap", "S", 20));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Shorts", "XL", 35));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Jacket", "L", 95));

        assertFalse(clothingManager.removeClothingItem(input));
        assertEquals(9, clothingManager.getClothingItems().size());
    }


    //Тесты для метода фильтрации одежды по типу

    /*
    Тест №1 для метода фильтрации одежды по типу

    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Среди элементов передается один null
    Данные на входе метода - не null
     */

    @ParameterizedTest
    @EnumSource(ClothesType.class)
    @DisplayName("filterByType, test1 (list not empty, input not null, case check)")
    void filterByTypeCheckTest1 (ClothesType type){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Snickers", "41", 110));
        clothingManager.addClothingItem(new ClothingItem("Nike", "T-Shirt", "S", 45));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Shorts", "M", 30));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Cap", "L", 10));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Snickers", "44", 120));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Jacket", "XL", 100));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Shorts", "M", 25));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Jacket", "L", 55));
        clothingManager.addClothingItem(null);
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Cap", "S", 15));

        result = clothingManager.filterByType(type.description);
        assertEquals(2, result.size());
    }

    /*
    Тест №2 для метода фильтрации одежды по типу

    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Данные на входе метода - не null
    Данные на входе отличны от данных в списке и не могут быть найдены
     */
    @ParameterizedTest
    @ValueSource(strings = {"TestType1", "TestType2"})
    @DisplayName("filterByType, test2 (list not empty, input not null)")
    void filterByTypeCheckTest2 (String type){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Shorts", "M", 35));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Jacket", "L", 95));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Snickers", "42", 110));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "T-Shirt", "XL", 45));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Shorts", "S", 20));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Cap", "L", 10));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Snickers", "46", 115));

        result = clothingManager.filterByType(type);
        assertTrue(result.isEmpty());
    }

    /*
    Тест №3 для метода фильтрации одежды по типу

    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Данные на входе метода - null
     */
    @Test
    @DisplayName("filterByType, test3 (list not empty, input is null)")
    void filterByTypeTest3(){
        clothingManager.addClothingItem(new ClothingItem("Puma", "Shorts", "M", 25));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Jacket", "S", 90));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "T-Shirt", "L", 50));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Snickers", "45", 115));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Shorts", "M", 35));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Cap", "S", 15));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "T-Shirt", "XL", 40));

        result = clothingManager.filterByType(null);
        assertTrue(result.isEmpty());
    }

    /*
    Тест №4 для метода фильтрации одежды по типу

    Особенности:
    Лист предварительно не заполняется элементами и перед началом теста - пустой
    Данные на входе метода - не null
     */

    @ParameterizedTest
    @EnumSource(ClothesType.class)
    @DisplayName("filterByType, test1 (list not empty, input not null, case check)")
    void filterByTypeCheckTest4 (ClothesType type){

        result = clothingManager.filterByType(type.description);
        assertTrue(result.isEmpty());
    }

    //Тесты для метода фильтрации одежды по размеру

    /*
    Тест №1 для метода фильтрации одежды по размеру

    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Среди элементов передается один null
    Данные на входе метода - не null
     */

    @ParameterizedTest
    @EnumSource(SizeForTest.class)
    @DisplayName("filterBySize, test1 (list not empty, input not null, ignore case check)")
    void filterBySizeCheckTest1 (SizeForTest size){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Snickers", "41", 110));
        clothingManager.addClothingItem(new ClothingItem("Nike", "T-Shirt", "XL", 45));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Shorts", "M", 30));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Cap", "L", 10));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Snickers", "44", 120));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Jacket", "XL", 100));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Shorts", "M", 25));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Jacket", "L", 55));
        clothingManager.addClothingItem(null);
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Cap", "S", 15));

        result = clothingManager.filterBySize(size.description);
        assertEquals(2, result.size());
    }

    /*
    Тест №2 для метода фильтрации одежды по размеру

    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Данные на входе метода - не null
    Данные на входе отличны от данных в списке и не могут быть найдены
     */
    @ParameterizedTest
    @ValueSource(strings = {"TestSize1", "TestSize2"})
    @DisplayName("filterBySize, test2 (list not empty, input not null)")
    void filterBySizeCheckTest2 (String size){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Shorts", "M", 35));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Jacket", "L", 95));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Snickers", "42", 110));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "T-Shirt", "XL", 45));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Shorts", "S", 20));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Cap", "L", 10));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Snickers", "46", 115));

        result = clothingManager.filterBySize(size);
        assertTrue(result.isEmpty());
    }

    /*
    Тест №3 для метода фильтрации одежды по размеру

    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
    Данные на входе метода - null
     */
    @Test
    @DisplayName("filterBySize, test3 (list not empty, input is null)")
    void filterBySizeTest3(){
        clothingManager.addClothingItem(new ClothingItem("Puma", "Shorts", "M", 25));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Jacket", "S", 90));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "T-Shirt", "L", 50));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Snickers", "45", 115));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Shorts", "M", 35));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Cap", "S", 15));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "T-Shirt", "XL", 40));

        result = clothingManager.filterBySize(null);
        assertTrue(result.isEmpty());
        assertEquals(7, clothingManager.getClothingItems().size());
    }

    /*
    Тест №4 для метода фильтрации одежды по размеру

    Особенности:
    Лист предварительно не заполняется элементами и перед началом теста - пустой
    Данные на входе метода - не null
     */

    @ParameterizedTest
    @EnumSource(SizeForTest.class)
    @DisplayName("filterByType, test1 (list not empty, input not null, case check)")
    void filterBySizeCheckTest4 (SizeForTest size){

        result = clothingManager.filterBySize(size.description);
        assertTrue(result.isEmpty());
        assertTrue(clothingManager.getClothingItems().isEmpty());
    }

    //Тесты для метода поиска самого дешевого элемента одежды

    /*
    Тест №1 для метода поиска самого дешевого элемента одежды

    Особенности:
    Лист предварительно заполняется элементами и перед началом теста - не пустой
     */

    @Test
    @DisplayName("FindCheapestItem, test 1")
    void findCheapestItemTest1(){
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Snickers", "41", 110));
        clothingManager.addClothingItem(new ClothingItem("Nike", "T-Shirt", "S", 45));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Shorts", "M", 30));
        clothingManager.addClothingItem(new ClothingItem("Reebok", "Cap", "L", 10));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Snickers", "44", 120));
        clothingManager.addClothingItem(new ClothingItem("Adidas", "Jacket", "XL", 100));
        clothingManager.addClothingItem(new ClothingItem("Nike", "Shorts", "M", 25));
        clothingManager.addClothingItem(new ClothingItem("Puma", "Jacket", "L", 55));
        clothingManager.addClothingItem(new ClothingItem("Under Armour", "Cap", "S", 15));

        double result = clothingManager.findCheapestItem().getPrice();
        assertEquals(10, result);
    }

     /*
    Тест №2 для метода поиска самого дешевого элемента одежды

    Особенности:
    Лист предварительно не заполняется элементами и перед началом теста - пустой
     */

    @Test
    @DisplayName("FindCheapestItem, test 2")
    void findCheapestItemTest2(){

        ClothingItem result = clothingManager.findCheapestItem();
        assertEquals(null, result);
    }
}
