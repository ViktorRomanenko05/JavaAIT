package projectJava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameManager.class);
    private static File file = Path.of("src","main", "java", "projectJava", "gamesInCasino.ser").toFile();
    private static HashMap <String, Game> games = new HashMap<>();

    public HashMap<String, Game> getGames() {
        return new HashMap<>(games);
    }

    //Сериализация игр
    public void serializeGames() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(games);
            LOGGER.info("Games have been serialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users serialization", exception.getMessage());
        }
    }

    //Десериализация пользователей
    public void deserializeGames() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            games = (HashMap <String, Game>) objectInputStream.readObject();
            LOGGER.info("Games have been deserialized");
        } catch (IOException exception) {
            LOGGER.error("Error during games deserialization", exception.getMessage());
        }
        catch (ClassNotFoundException exception) {
            LOGGER.error("Error during games deserialization", exception.getMessage());
        }
    }

    /*
    private String name;
    private GameType type;
    double minimalBet;
    double maximalBet;
     */

    //Создание новой игры
    public void createNewGame (String name, GameType type, Double minimalBet, Double maximalBet){

        if(name == null || type == null || minimalBet == null || maximalBet == null){
            LOGGER.warn("Parameters cannot be null");
            System.out.println("Поля не могут быть пустыми");
        }

        else if (minimalBet <= 0){
            LOGGER.info("Minimal bet must be greater than 0");
            System.out.println("Минимальная ставка не может быть больше 0");
        }

        else {
            deserializeGames();
            Game newGame = new Game(name, type, minimalBet, maximalBet);
            Game previousGame = games.put(name, newGame);
            serializeGames();
            if (previousGame == null){
                LOGGER.info("Game {} was successfully created", name);
                System.out.println("Игра " + name + " была успешно создана и добавлена");
            }
            else {
                LOGGER.info("Game {} was updated", name);
                System.out.println("Игра " + name +" была обновлена");
            }
        }
    }

    //Вывод всех транзакций на экран
    public void sortByNameAndPrint(){
        deserializeGames();
        List<Game> sortedUsers = games.values().stream()
                .sorted(Comparator.comparing(Game::getName))
                .collect(Collectors.toList());
        System.out.println("\nСписок транзакций:");
        line();
        System.out.printf("%-4s %-25s %-15s %-5s  %s%n", "№", "Name", "Type", "Min bet", "Max bet");
        line();
        int counter = 0;
        for (Game gameToPrint : sortedUsers) {
            counter++;
            System.out.printf("%-4s %-25s %-15s %-5s %s%n", counter, gameToPrint.getName(), gameToPrint.getType().getDescription(), gameToPrint.getMinimalBet() ,gameToPrint.getMaximalBet());
            //line();

        }
        line();
    }


    private static void line(){
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
