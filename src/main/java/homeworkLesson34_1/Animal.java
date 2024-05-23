package homeworkLesson34_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Animal {

    private static final Logger LOGGER = LoggerFactory.getLogger(Animal.class);

    private String animalName;

    private String petName;

    private Integer energy = 100;

    public String getAnimalName() {
        return animalName;
    }

    public String getPetName() {
        return petName;
    }

    public Integer getEnergy() {
        return energy;
    }

    public Animal(String animalName, String petName) {
        this.animalName = animalName;
        this.petName = petName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(animalName, animal.animalName) || Objects.equals(petName, animal.petName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalName) ^ Objects.hash(petName);
    }


    public void eat() {
        energy += 20;
        if (energy > 100) {
            energy = 100;
        }
        LOGGER.debug("Animal " + animalName + " " + petName + " has eaten and its energy is now " + energy + ".");
    }


    public void sleep() {
        energy += 30;
        if (energy > 100) {
            energy = 100;
        }
        LOGGER.debug("Animal " + animalName + " " + petName + " has slept and its energy is now " + energy + ".");
    }

    public void play() {
        energy -= 40;
        if (energy < 0) {
            LOGGER.warn("Animal " + animalName + " " + petName + " has played and its energy is now 0. The animal is tired and needs to rest.");
            energy = 0;
        } else {
            LOGGER.debug("Animal " + animalName + " " + petName + " has played and its energy is now " + energy + ".");
        }
    }
}
