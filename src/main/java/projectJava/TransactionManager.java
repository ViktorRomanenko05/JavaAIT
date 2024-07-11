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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {

        private static final Logger LOGGER = LoggerFactory.getLogger(TransactionManager.class);
        PersonManager personManager = new PersonManager();
        GameManager gameManager = new GameManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        private static File file = Path.of("src","main", "java", "projectJava", "transactionsOfCasino.ser").toFile();
        private static HashMap<Long, Transaction> transactions  = new HashMap<>();


    public HashMap<Long, Transaction> getTransactions() {
        return new HashMap<>(transactions);
    }

    //Сериализация пользователей
        public void serializeTransactions() {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                objectOutputStream.writeObject(transactions);
                LOGGER.info("Users have been serialized");
            } catch (IOException exception) {
                LOGGER.error("Error during users serialization", exception.getMessage());
            }
        }

        //Десериализация пользователей
        public void deserializeTransactions() {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                transactions = (HashMap<Long, Transaction>) objectInputStream.readObject();
                LOGGER.info("Users have been deserialized");
            } catch (IOException exception) {
                LOGGER.error("Error during users deserialization", exception.getMessage());
            }
            catch (ClassNotFoundException exception) {
                LOGGER.error("Error during users deserialization", exception.getMessage());
            }
        }

        //Метод добавления новой транзакции
        public void createNewTransaction(String userName, String gameName, Double bet, Double win){
            personManager.deserializeUsers();
            gameManager.deserializeGames();

            Game game = gameManager.getGames().get(gameName);
            Person user = personManager.getUsers().get(userName);

            if(userName == null || gameName == null || bet == null || win == null){
                LOGGER.warn("Parameters cannot be null");
                System.out.println("Поля не могут быть пустыми");
                return; // Exit method on error
            }

            if (user == null) {
                LOGGER.info("User with name {} was not found", userName);
                System.out.println("Пользователь с именем " + userName + " не найден");
                return; // Exit method on error
            }

            if (game == null) {
                LOGGER.info("Game with name {} was not found", gameName);
                System.out.println("Игра с именем " + gameName + " не найдена");
                return; // Exit method on error
            }

            if (bet <= 0){
                LOGGER.warn("Attempt to enter a negative bet");
                System.out.println("Ставка не может быть отрицательной");
                return; // Exit method on error
            }

            if (bet < game.getMinimalBet() || bet > game.getMaximalBet()){
                LOGGER.info("Bet should be from {} to {}", game.getMinimalBet(), game.getMaximalBet());
                System.out.println("Ваша ставка должна быть в диапазоне от " + game.getMinimalBet() + "$ до " + game.getMaximalBet() + "$");
                return; // Exit method on error
            }

            Transaction newTransaction = new Transaction(user, game, bet, win);
            deserializeTransactions();
            if (transactions.containsKey(newTransaction.getId())){
                LOGGER.warn("Transaction with ID {} is present in storage", newTransaction.getId());
                System.out.println("Транзакция с таким id уже существует.\n" +
                        "Попробуйте создать транзакцию еще раз");
                return; // Exit method on error
            }
            transactions.put(newTransaction.getId(), newTransaction);
            LOGGER.info("Transaction with ID {} was created", newTransaction.getId());
            serializeTransactions();
        }

        //Вывод всех транзакций на экран
        public void sortByNameAndPrint(){
            deserializeTransactions();
            if (transactions.isEmpty()) {
                System.out.println("Нет доступных транзакций для отображения.");
                LOGGER.info("No transactions available for display");
                return;
            }

            List<Transaction> sortedTransactions = transactions.values().stream()
                    .sorted(Comparator.comparing(Transaction::getDate, Comparator.nullsLast(Comparator.naturalOrder())))
                    .collect(Collectors.toList());
            System.out.println("\nСписок транзакций:");
            line();
            System.out.printf("%-4s %-15s %-25s %-10s %-10s  %s%n", "№", "Time", "User name", "Bet", "Win", "Transaction ID");
            line();
            int counter = 0;
            for (Transaction transactionToPrint : sortedTransactions) {
                counter++;
                LocalDateTime date = transactionToPrint.getDate();
                String formattedDate = (date != null) ? date.format(formatter) : "Дата не указана";
                System.out.printf("%-4s %-15s %-25s %-10s %-10s %s%n", counter,
                        formattedDate,
                        transactionToPrint.getClient().getName(),
                        transactionToPrint.getBet(),
                        transactionToPrint.getWin(),
                        transactionToPrint.getId());
            }
            line();
        }

        private void line(){
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }

