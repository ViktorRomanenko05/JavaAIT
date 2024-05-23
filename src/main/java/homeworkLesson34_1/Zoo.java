package homeworkLesson34_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

public class Zoo {

    private static final Logger LOGGER = LoggerFactory.getLogger(Zoo.class);

    private HashSet<Animal> animals = new HashSet<>();

    public HashSet<Animal> getAnimals() {
        return new HashSet<>(animals);
    }

    public void add(Animal animal) {
        if(animal != null && animal.getAnimalName() != null && animal.getPetName() != null) {
            boolean result = animals.add(animal);
            if (result) {
                LOGGER.debug("Animal " + animal.getAnimalName() + " " + animal.getPetName() + " was successfully added");
            } else {
                LOGGER.warn("Animal " + animal.getAnimalName() + " " + animal.getPetName() + " was not added");
            }
        }
        else{
            if (animal == null){
                LOGGER.error("Object animal is null");
            } else if (animal.getAnimalName() == null) {
                LOGGER.error("Animal name is null");
            } else if (animal.getPetName() == null) {
                LOGGER.error("Animal petName is null");
            }
        }
    }

    public void checkAnimals (){
        for (Animal animal : animals){
            LOGGER.debug("Animal: " + animal.getAnimalName() + ", Name: " + animal.getPetName() + ", Energy: " + animal.getEnergy());
            if (animal.getEnergy()<0){
                LOGGER.error("The energy of the animal " + animal.getAnimalName() + " " + animal.getPetName() + " is less than 0");
            } else if (animal.getEnergy()>100) {
                LOGGER.error("The energy of the animal " + animal.getAnimalName() + " " + animal.getPetName() + " is more than 100");
            }
        }
    }
}
