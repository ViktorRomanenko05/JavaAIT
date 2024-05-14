package homework32Tests;

import homeworkLesson32.Student;
import homeworkLesson32.StudentsFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentsFilterTest {

    StudentsFilter filter;
    ArrayList<Student> students;
    ArrayList<Student> result;

    @BeforeEach
    void setUp() {
        filter = new StudentsFilter();
        students = new ArrayList<>();
        result = new ArrayList<>();
    }

    @Test
    @DisplayName("Проверка случая передачи пустого списка")
    void testWithEmptyList(){
        result = filter.filterByGrade(students);
        assertEquals (0, students.size(),"The result Should be empty");
    }

    @Test
    @DisplayName("Проверка случая отсутствия оценок выше 75")
    void testFilterWithAllNotAbove75(){
        students.add(new Student("Anna", 56));
        students.add(new Student("Michael", 75));
        result = filter.filterByGrade(students);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Проверка случая когда все оценки выше 75")
    void testFilterWithAllAbove75(){
        students.add(new Student("Max", 76));
        students.add(new Student("Jack", 84));
        students.add(new Student("Lina", 87));
        result = filter.filterByGrade(students);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Проверка случая листа с разными вариантами оценок и передачей null")
    void testFilterWithMixedGrades(){
        students.add(new Student("Anna", 76));
        students.add(new Student("Peter", 75));
        students.add(new Student("Emilia", 87));
        students.add(null);
        students.add(new Student("Lucas", 61));
        students.add(new Student("Aidan", 77));
        result = filter.filterByGrade(students);
        assertEquals(3, result.size());
    }

    //Я думаю этот тест также можно объединить в один с предыдущим

    @Test
    @DisplayName("Проверка порядка сортировки и включение null в исходный список")
    void testFilterWithMixedGradesByNames(){
        students.add(new Student("Anna", 76));
        students.add(null);
        students.add(new Student("Peter", 75));
        students.add(new Student("Emilia", 87));
        students.add(new Student("Lucas", 61));
        students.add(new Student("Aidan", 77));

        result = filter.filterByGrade(students);
        assertEquals("Anna", result.get(0).getName());
        assertEquals("Emilia", result.get(1).getName());
        assertEquals("Aidan", result.get(2).getName());
    }

}
