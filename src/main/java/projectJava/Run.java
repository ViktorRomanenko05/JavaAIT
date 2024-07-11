package projectJava;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Run {
    static PersonManager personManager = new PersonManager();
    static TransactionManager transactionManager = new TransactionManager();
    static GameManager gameManager = new GameManager();
    private static final Logger LOGGER = LoggerFactory.getLogger(Run.class);
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        LOGGER.info("Start program");
        String choice;

        System.out.println("Добро пожаловать в систему управления казино!");

        while (true) {
            System.out.println("\nПожалуйста, выберите действие:\n" +
                    "1 - Регистрация нового клиента\n" +
                    "2 - Добавить новую игру\n" +
                    "3 - Записать транзакцию\n" +
                    "4 - Просмотреть всех клиентов\n" +
                    "5 - Просмотреть все игры\n" +
                    "6 - Просмотреть все транзакции\n" +
                    "7 - Выход\n");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    newUserRegistration();
                    break;
                case "2":
                    addNewGame();
                    break;
                case "3":
                    createNewTransaction();
                    break;
                case "4":
                    personManager.sortByNameAndPrint();
                    break;
                case "5":
                    gameManager.sortByNameAndPrint();
                    break;
                case "6":
                    transactionManager.sortByNameAndPrint();
                    break;
                case "7":
                    System.out.println("Спасибо за использование системы управления казино. До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                    continue;
            }
            LOGGER.info("End program");
        }
    }

    public static void newUserRegistration() {
        System.out.println("\nРегистрация нового клиента:");

        String name;
        while (true) {
            System.out.print("Введите имя клиента: ");
            name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Имя не может быть пустым.");
                LOGGER.info("Null name entered");
            } else {
                break;
            }
        }

        LocalDate birthday;
        while (true) {
            System.out.print("Введите дату рождения клиента (dd.MM.yyyy): ");
            String birthdayInput = scanner.nextLine();
            try {
                birthday = LocalDate.parse(birthdayInput, formatter);
                int age = Period.between(birthday, LocalDate.now()).getYears();
                if (age < 18) {
                    System.out.println("Пользователи моложе 18 лет не могут быть зарегистрированы.");
                    LOGGER.info("Trying to register of user younger 18 y.o.");
                } else if (age > 130) {
                    System.out.println("Пожалуйста, введите правильный возраст");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты. Пожалуйста, введите дату в формате dd.MM.yyyy.");
            }
        }

        double balance;
        while (true) {
            System.out.print("Введите начальный счет клиента (в евро): ");
            try {
                balance = Double.parseDouble(scanner.nextLine());
                if (balance < 0) {
                    System.out.println("Баланс не может быть отрицательным. Пожалуйста, введите положительное значение.");
                    LOGGER.info("Trying to enter negative balance.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
                System.out.println("Неверный формат баланса. Пожалуйста, введите числовое значение.");
            }
        }

        personManager.createNewUser(name, birthday, balance);
    }

    public static void addNewGame() {
        System.out.println("\nДобавление новой игры:");

        String name;
        while (true) {
            System.out.print("Введите название игры: ");
            name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Название игры не может быть пустым.");
                LOGGER.info("Null name entered");
            } else {
                break;
            }
        }

        GameType type;
        while (true) {
            System.out.print("Введите тип игры (CARD, ROULETTE, SLOT, KENO): ");
            try {
                type = GameType.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                LOGGER.error(e.getMessage());
                System.out.println("Неверный тип игры. Пожалуйста, введите один из следующих типов: CARD, ROULETTE, SLOT, KENO.");
            }
        }

        double minimalBet;
        while (true) {
            System.out.print("Введите минимальную ставку: ");
            try {
                minimalBet = Double.parseDouble(scanner.nextLine());
                if (minimalBet <= 0) {
                    System.out.println("Минимальная ставка должна быть больше 0.");
                    LOGGER.info("Trying to enter negative bet.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
                System.out.println("Неверный формат ставки. Пожалуйста, введите числовое значение.");
            }
        }

        double maximalBet;
        while (true) {
            System.out.print("Введите максимальную ставку: ");
            try {
                maximalBet = Double.parseDouble(scanner.nextLine());
                if (maximalBet <= minimalBet) {
                    System.out.println("Максимальная ставка должна быть больше минимальной ставки.");
                    LOGGER.info("Maximal bet is lower than minimal bet.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
                System.out.println("Неверный формат ставки. Пожалуйста, введите числовое значение.");
            }
        }

        gameManager.createNewGame(name, type, minimalBet, maximalBet);
    }

    public static void createNewTransaction() {
        System.out.println("\nЗапись транзакции:");

        String userName;
        while (true) {
            System.out.print("Введите имя клиента: ");
            userName = scanner.nextLine();
            if (userName == null || userName.trim().isEmpty()) {
                System.out.println("Имя клиента не может быть пустым.");
                LOGGER.info("Null name entered");
            } else {
                break;
            }
        }

        String gameName;
        while (true) {
            System.out.print("Введите название игры: ");
            gameName = scanner.nextLine();
            if (gameName == null || gameName.trim().isEmpty()) {
                System.out.println("Название игры не может быть пустым.");
                LOGGER.info("Null game name entered");
            } else {
                break;
            }
        }

        double bet;
        while (true) {
            System.out.print("Введите ставку: ");
            try {
                bet = Double.parseDouble(scanner.nextLine());
                if (bet <= 0) {
                    System.out.println("Ставка должна быть больше 0.");
                    LOGGER.info("Invalid bet entered");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage());
                System.out.println("Неверный формат ставки. Пожалуйста, введите числовое значение.");
            }
        }

        double winOrLoss;
        while (true) {
            System.out.print("Введите результат (выигрыш/проигрыш): ");
            String result = scanner.nextLine().toLowerCase();
            if (result.equals("выигрыш")) {
                while (true) {
                    System.out.print("Введите сумму выигрыша: ");
                    try {
                        winOrLoss = Double.parseDouble(scanner.nextLine());
                        if (winOrLoss < 0) {
                            System.out.println("Сумма выигрыша не может быть отрицательной.");
                            LOGGER.info("Negative win entered");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.error(e.getMessage());
                        System.out.println("Неверный формат суммы выигрыша. Пожалуйста, введите числовое значение.");
                    }
                }
                break;
            } else if (result.equals("проигрыш")) {
                while (true) {
                    System.out.print("Введите сумму проигрыша (отрицательное значение): ");
                    try {
                        winOrLoss = Double.parseDouble(scanner.nextLine());
                        if (winOrLoss >= 0) {
                            System.out.println("Сумма проигрыша должна быть отрицательной.");
                            LOGGER.info("Positive loss entered");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.error(e.getMessage());
                        System.out.println("Неверный формат суммы проигрыша. Пожалуйста, введите числовое значение.");
                    }
                }
                break;
            } else {
                System.out.println("Неверный результат. Пожалуйста, введите 'выигрыш' или 'проигрыш'.");
            }
        }

        transactionManager.createNewTransaction(userName, gameName, bet, winOrLoss);
    }
}