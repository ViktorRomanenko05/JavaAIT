package homeworkLesson34_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimalMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(Animal.class);

    public static void main(String[] args) {

        LOGGER.info("The ZOO application has been started");

        Zoo zoo = new Zoo();

        Animal crocodile = new Animal("Crocodile", "Gena");
        Animal elephant = new Animal("Elephant", "Jumbo");
        Animal fish = new Animal("Fish", "Nemo");
        Animal snake = null; //проверим реакцию при добавлении null
        Animal fish1 = new Animal("Fish", "Nemo"); //проверим реакцию метода на добавление объекта с не уникальными полями
        Animal rabbit = new Animal("Rabbit", null);

        //Добавим животных в список
        zoo.add(crocodile);
        zoo.add(elephant);
        zoo.add(fish);
        zoo.add(snake);
        zoo.add(fish1);
        zoo.add(rabbit);

        //Иммитируем действия
        crocodile.play();
        crocodile.play();
        elephant.play();
        fish.play();
        crocodile.sleep();
        elephant.eat();
        fish.eat();
        fish.eat();

        //Выполним проверку статуса для всех животных
        zoo.checkAnimals();
    }
}
