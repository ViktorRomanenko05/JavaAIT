package homework34Tests;

import homeworkLesson34.PrecipitationLevels;
import homeworkLesson34.WeatherMetrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class homework34Tests {

    WeatherMetrics weatherMetrics;


    @BeforeEach
    void setUp() {
        weatherMetrics = new WeatherMetrics();
        PrecipitationLevels precipitationLevels;
    }

    //Тесты метода для вычисления среднего значения из входящего списка температур

    @ParameterizedTest
    @MethodSource("temperatureProvider1")
    @DisplayName("Test calculateAverageTemperature with various inputs without null, lists not empty")
    public void testCalculateAverageTemperatureNoyNullInput(List<Double> temperatures, Double expected) {
        Double result = weatherMetrics.calculateAverageTemperature(temperatures);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("temperatureProvider2")
    @DisplayName("Test calculateAverageTemperature with various inputs with null and empty list")
    public void testCalculateAverageTemperatureNullInput(List<Double> temperatures, Double expected) {
        Double result = weatherMetrics.calculateAverageTemperature(temperatures);
        assertNull(result);
    }

    //Тесты метода предупреждения о заморозках

    //@ValueSource не поддерживает передачу листов или передачу нескольких значений за один вызов тестируемого метода,
    //поэтому этот тест может проверить только случай, когда передаваемый список состоит из 1 значения.
    //Такой подход оказался неэффективным. Необходимо использовать CsvSource
    @ParameterizedTest
    @ValueSource(doubles = {23.0, 25.0, 20.0, 22.0, 24.0})
    @DisplayName("Test checkForFrostWarnings without frost")
    public void testCheckForFrostWarningsWithoutFrost(double temperature1) {
        List<Double> temperatures = new ArrayList<>(List.of(temperature1));
        boolean result = weatherMetrics.checkForFrostWarnings(temperatures);
        assertFalse(result);
    }

    //Тестируем работу метода с разными комбинациями температур на входе
    //Кроме null и empty list

    @ParameterizedTest(name = "{index} ==> TEMPERATURES: {0}, {1}, {2}, {3}, {4} - RESULT: {5}")
    @CsvFileSource(resources = "/temperatures.csv", numLinesToSkip = 2)
    @DisplayName("Test checkForFrostWarnings with various inputs")
    public void testCheckForFrostWarnings(double temp1, double temp2, double temp3, double temp4, double temp5, boolean expected) {
        List<Double> temperatures = List.of(temp1, temp2, temp3, temp4, temp5);
        boolean result = weatherMetrics.checkForFrostWarnings(temperatures);
        assertEquals(expected, result);
    }

    //Проверка случая передачи null в метод предупреждения о заморозках
    @Test
    @DisplayName("Test checkForFrostWarnings with null input")
    public void testCheckForFrostWarningsNullInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            weatherMetrics.checkForFrostWarnings(null);
        });
        assertEquals("Error! List of temperatures is null", exception.getMessage());
    }

    //Проверка случая передачи empty list в метод предупреждения о заморозках
    @Test
    @DisplayName("Test checkForFrostWarnings with empty list")
    public void testCheckForFrostWarningsEmptyList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            weatherMetrics.checkForFrostWarnings(new ArrayList<>());
        });
        assertEquals("Error! List of temperatures is empty", exception.getMessage());
    }

    // Тест для метода классификации уровня осадков
    //Так как метод возвращает лист дифференцированных значений - создадим несколько эталонных листов
    private static List<PrecipitationLevels> lowExpected = List.of(
            PrecipitationLevels.LOW, PrecipitationLevels.LOW, PrecipitationLevels.LOW);
    private static List<PrecipitationLevels> mediumExpected = List.of(
            PrecipitationLevels.MEDIUM, PrecipitationLevels.MEDIUM, PrecipitationLevels.MEDIUM);
    private static List<PrecipitationLevels> highExpected = List.of(
            PrecipitationLevels.HIGH, PrecipitationLevels.HIGH, PrecipitationLevels.HIGH);
    private static List<PrecipitationLevels> incorrectExpected = List.of(
            PrecipitationLevels.INCORRECT, PrecipitationLevels.INCORRECT, PrecipitationLevels.INCORRECT);
    private static List<PrecipitationLevels> differentExpected = List.of(
            PrecipitationLevels.HIGH, PrecipitationLevels.LOW, PrecipitationLevels.MEDIUM);

    @ParameterizedTest(name = "{index} ==> LEVELS: {0}, {1}, {2} - RESULT: {3}")
    @CsvFileSource(resources = "/precipitationLevels.csv", numLinesToSkip = 1)
    @DisplayName("Test evaluatePrecipitationLevels with various inputs")
    public void testEvaluatePrecipitationLevels(Double level1, Double level2, Double level3, String expected) {
        List<Double> precipitationLevels = new ArrayList<>();
        precipitationLevels.add(level1);
        precipitationLevels.add(level2);
        precipitationLevels.add(level3);

        List<PrecipitationLevels> result = weatherMetrics.evaluatePrecipitationLevels(precipitationLevels);
        assertEquals(getExpectedList(expected), result);
    }

    //Тесты для случаев передачи в тестируемый метод null или пустого листа
    @Test
    @DisplayName("Test evaluatePrecipitationLevels with empty list")
    public void testEvaluatePrecipitationLevelsEmptyList() {
        List<PrecipitationLevels> result = weatherMetrics.evaluatePrecipitationLevels(new ArrayList<>());
        List<PrecipitationLevels> expected = List.of(PrecipitationLevels.INCORRECT);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test evaluatePrecipitationLevels with null input")
    public void testEvaluatePrecipitationLevelsNullInput() {
        List<PrecipitationLevels> result = weatherMetrics.evaluatePrecipitationLevels(null);
        List<PrecipitationLevels> expected = List.of(PrecipitationLevels.INCORRECT);
        assertEquals(expected, result);
    }


    //Метод - поставщик данных для тестирования метода по вычислению среднего значения температуры
    //Передаваемые аргументы в различных вариациях кроме null и empty list
    static Stream<Arguments> temperatureProvider1() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(23.0, 25.0, 20.0, 22.0, 24.0)), 22.8),
                Arguments.of(new ArrayList<>(List.of(1.0, -8.5, -5.5, -3.0, 4.5)), -2.3),
                Arguments.of(new ArrayList<>(List.of(25.0)), 25.0),
                Arguments.of(new ArrayList<>(List.of(-10.0, -20.0, -30.0)), -20.0),
                Arguments.of(new ArrayList<>(List.of(0.0, 0.0, 0.0)), 0.0),
                Arguments.of(new ArrayList<>(List.of(15.0, 15.0, 15.0, 15.0)), 15.0)
        );
    }

    //Метод - поставщик данных для теста метода по вычислению среднего значения температуры
    //Передаваемые аргументы - null и empty list
    static Stream<Arguments> temperatureProvider2() {
        return Stream.of(

                Arguments.of(new ArrayList<>(), null),
                Arguments.of(null, null)
        );
    }

    //Метод передает нужный список значений для сравнения
    private List<PrecipitationLevels> getExpectedList(String expectedListName) {
        return switch (expectedListName) {
            case "lowExpected" -> lowExpected;
            case "mediumExpected" -> mediumExpected;
            case "highExpected" -> highExpected;
            case "incorrectExpected" -> incorrectExpected;
            case "differentExpected" -> differentExpected;
            default -> throw new IllegalArgumentException("Unknown expected list: " + expectedListName);
        };
    }
}
