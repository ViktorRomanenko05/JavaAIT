package homework34_1Tests;

import homeworkLesson34_1.Animal;
import homeworkLesson34_1.Zoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalAndZooTests {
    Animal animal;
    Zoo zoo;

    @BeforeEach
    public void setUp() {
        animal = new Animal("Dog", "Rex");
        zoo = new Zoo();
    }

    //Тесты для класса Animal
    @Test
    @DisplayName("Test eat")
    public void testEat() {
        animal.eat();
        assertEquals(100, animal.getEnergy());
    }

    @Test
    @DisplayName("Test sleep")
    public void testSleep() {
        animal.sleep();
        assertEquals(100, animal.getEnergy());
    }

    @Test
    @DisplayName("Test play")
    public void testPlay() {
        animal.play();
        assertEquals(60, animal.getEnergy());

        animal.play();
        animal.play();
        assertEquals(0, animal.getEnergy());
    }

    //Тесты для класса Zoo
    @Test
    @DisplayName("Check adding of animals")
    public void testAddAnimal() {
        Zoo zoo = new Zoo();
        Animal animal2 = new Animal("Dog", "Max");
        Animal animal3 = new Animal("Dog", "Rex");
        Animal animal4 = new Animal("Cat", "Felix");

        zoo.add(animal);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        assertEquals(3, zoo.getAnimals().size()); // Should be 2 unique animals
    }

    @Test
    @DisplayName("Test adding with null object")
    public void testAddNullAnimal() {
        Zoo zoo = new Zoo();
        Animal animal = null;

        zoo.add(animal);
        assertEquals(0, zoo.getAnimals().size());
    }

    @Test
    @DisplayName("Test adding with null field")
    public void testAddAnimalWithNullFields() {
        Zoo zoo = new Zoo();
        Animal animal1 = new Animal(null, "Rex");
        Animal animal2 = new Animal("Dog", null);

        zoo.add(animal1);
        zoo.add(animal2);

        assertEquals(0, zoo.getAnimals().size());
    }

}
