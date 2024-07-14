package backend.program;

import org.apache.commons.validator.routines.RegexValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Run {

    private static final Logger LOGGER = LoggerFactory.getLogger(Run.class);
    private static Scanner scanner = new Scanner(System.in);
    private static PersonManager personManager = new PersonManager();
    private static AutoCatalog autoCatalog = new AutoCatalog();
    private static CreditManager creditManager = new CreditManager();
    private static Statistics statistics = new Statistics();
    private static TestDriveManager testDriveManager = new TestDriveManager();

    public static void main(String[] args) {
        String choice;
        String choiceDirector;
        String name;
        String password;
        String phoneNumber;
        String address;
        String eMail;
        User user = null;
        Employee employee = null;
        Person person = null;
        Auto auto;
        TestDrive testDrive;
        Credit credit;
        AccessLevel accessLevel = AccessLevel.NONE;
//
//        Employee director = new Employee("Automann", "1180505", "+491705567438","9983, Berlin, Hauptstrasse 19","autotrade@gmail.com",1,"CEO");
//        director.setAccessLevel(AccessLevel.DIRECTOR);
//        personManager.addEmployee(director);

        PersonManager.deserializeUsers();
        PersonManager.deserializeEmployees();
        autoCatalog.readAutoCatalogFromFile();
        autoCatalog.readSoldCarsFromFile();
//        System.out.println(AutoCatalog.getSoldCars());


        System.out.println("\nWELCOME TO THE AUTOTRADER APPLICATION\n" +
                "\nPlease choose the desired action:\n" +
                "REGISTRATION - enter `1`\n" +
                "LOGIN - enter any other value\n");

        choice = scanner.nextLine();
        if (choice.equals("1")) {
            System.out.println("\nREGISTRATION\n");
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            System.out.print("Enter your password: ");
            RegexValidator passwordValidator = new RegexValidator("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
            boolean passwordIsValid = false;
            do {
                password = scanner.nextLine();
                passwordIsValid = passwordValidator.isValid(password);
                if (!passwordIsValid) {
                    System.out.println("The password you entered does not meet security requirements:");
                    System.out.println("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
                }
            } while (!passwordIsValid);

            System.out.print("Enter your e-mail: ");
            do {
                eMail = scanner.nextLine();
                user = personManager.findUserByEmail(eMail);
                employee = personManager.findEmployeeByEmail(eMail);
                if (user != null || employee != null) {
                    System.out.println("User with email " + eMail + " already exists. Please choose another one.");
                }
            } while (user != null || employee != null);

            System.out.print("Enter your address: ");
            address = scanner.nextLine();

            System.out.print("Enter your phone number: ");
            phoneNumber = scanner.nextLine();

            personManager.createUser(name, eMail, phoneNumber, address, password);
            System.out.println("User with name " + name + " was registered successfully.");
            LOGGER.info("New user registered: {}", name);
        }

        while (true) {
            System.out.print("Enter your E-mail: ");
            eMail = scanner.nextLine();
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            accessLevel = personManager.authentication(eMail, password);

            if (accessLevel == AccessLevel.USER) {
                System.out.println("You have successfully logged in as a user!");
                user = personManager.findUserByEmail(eMail);
                LOGGER.info("User logged in: {}", eMail);
                break;
            } else if (accessLevel == AccessLevel.MANAGER) {
                System.out.println("You have successfully logged in as a manager!");
                employee = personManager.findEmployeeByEmail(eMail);
                LOGGER.info("Manager logged in: {}", eMail);
                break;
            } else if (accessLevel == AccessLevel.DIRECTOR) {
                System.out.println("You have successfully logged in as a director!");
                employee = personManager.findEmployeeByEmail(eMail);
                LOGGER.info("Director logged in: {}", eMail);
                break;
            } else {
                System.out.println("Login error. Check your username and password\n" +
                        "Would you like to try again?\n" +
                        "NO - enter `no`\n" +
                        "YES - enter any other value");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("no")) {
                    LOGGER.warn("User failed to log in and chose not to retry.");
                    break;
                }
            }
        }
        if (user != null && user.getAccessLevel() == AccessLevel.USER) {
            while (true) {
                System.out.println("\nPlease choose the desired action:\n" +
                        "1 - View auto catalog\n" +
                        "2 - Buy auto\n" +
                        "3 - Sign up for a test drive\n" +
                        "4 - Messages\n" +
                        "5 - Edit your information\n" +
                        "6 - Your purchases\n" +
                        "7 - Your test drives\n" +
                        "8 - Credits\n" +
                        "9 - FAQ\n" +
                        "10 - Exit");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        displayAutoCatalogMenu();
                        break;
                    case "2":
                        buyCar(user);
                        break;
                    case "3":
                        signUpForTestDrive(user);
                        break;
                    case "4":
                        manageMessages(user);
                        break;
                    case "5":
                        editUserInfo(user);
                        break;
                    case "6":
                        printUserPurchases(user);
                        break;
                    case "7":
                        printUserTestDrives(user);
                        break;
                    case "8":
                        displayUserCredits(user);
                        break;
                    case "9":
                        Faq.showAllFaq();
                        break;
                    case "10":
                        System.out.println("Thank you! Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid value entered, please try again");
                        break;
                }
            }
        }

        if (employee != null &&employee.getAccessLevel()== AccessLevel.DIRECTOR) {
            while (true) {
                System.out.println("\nDirector Menu - Please choose the desired action:\n" +
                        "1 - View auto catalog\n" +
                        "2 - Sell auto to user\n" +
                        "3 - Sign up user for a test drive\n" +
                        "4 - Messages\n" +
                        "5 - Find user\n" +
                        "6 - Edit user information\n" +
                        "7 - Find employee\n" +
                        "8 - Edit employee information\n" +
                        "9 - Create employee account\n" +
                        "10 - View statistics\n" +
                        "11 - Manage test-Drives\n" +
                        "12 - Manage car catalog\n" +
                        "13 - Exit");
                choiceDirector = scanner.nextLine();
                switch (choiceDirector) {
                    case "1":
                        displayAutoCatalogMenu();
                        break;
                    case "2":
                        User byer = findUserByEmail();
                        if (byer != null) {
                            sellCarByEmployee(byer, employee);
                        }
                        else {
                            System.out.println("User was not found");
                        }
                        break;
                    case "3":
                        User byer1 = findUserByEmail();
                        if (byer1 != null) {
                            signUpForTestDrive(byer1);
                        }
                        else {
                            System.out.println("User was not found");
                        }
                        break;
                    case "4":
                        manageMessages(employee);
                        break;
                    case "5":
                        User foundUser = findUserByEmail();
                        if(foundUser!=null){
                        System.out.println(foundUser);
                        printUserTestDrives(foundUser);
                        printUserPurchases(foundUser);
                        displayUserCredits(foundUser);}
                        break;
                    case "6":
                        editUserInfo(findUserByEmail());
                        break;
                    case "7":
                        Employee foundEmployee = findEmployeeByEmail();
                        if(foundEmployee!=null) {
                            System.out.println(foundEmployee);
                            printEmployeeSalesInPeriod(foundEmployee);
                        }
                        break;
                    case "8":
                        editEmployeeInfo();
                        break;
                    case "9":
                        createEmployeeProfile();
                        break;
                    case "10":
                        generateReportScenario();
                        break;
                    case "11":
                        manageTestDrives();
                        break;
                    case "12":
                        manageCarCatalog();
                        break;
                    case "13":
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        }

        if (employee != null && employee.getAccessLevel() == AccessLevel.MANAGER) {
            while (true) {
                System.out.println("\nManager Menu - Please choose the desired action:\n" +
                        "1 - View auto catalog\n" +
                        "2 - Sell auto to user\n" +
                        "3 - Sign up user for a test drive\n" +
                        "4 - Messages\n" +
                        "5 - Find user information\n" +
                        "6 - View report\n" +
                        "7 - Manage test-Drives\n" +
                        "8 - Manage car catalog\n" +
                        "9 - FAQ\n" +
                        "10 - Exit");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        displayAutoCatalogMenu();
                        break;
                    case "2":
                        User buyer = findUserByEmail();
                        if(buyer != null) {
                            sellCarByEmployee(buyer, employee);
                        }
                        break;
                    case "3":
                        User testDriveUser = findUserByEmail();
                        if (testDriveUser !=null) {
                            signUpForTestDrive(testDriveUser);
                        }
                        break;
                    case "4":
                        manageMessages(employee);
                        break;
                    case "5":
                        User foundUser = findUserByEmail();
                        if (foundUser != null) {
                            System.out.println(foundUser);
                            printUserTestDrives(foundUser);
                            printUserPurchases(foundUser);
                            displayUserCredits(foundUser);
                        }
                        else {
                            System.out.println("Incorrect user E-mail");
                        }

                        break;
                    case "6":
                        generateReportScenario();
                        break;
                    case "7":
                        manageTestDrives();
                        break;
                    case "8":
                        manageCarCatalog();
                        break;
                    case "9":
                        Faq.showAllFaq();
                        break;
                    case "10":
                        System.out.println("Thank you! Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid value entered, please try again");
                        break;
                }
            }
        }
    }

    //Автокаталог
    public static void displayAutoCatalogMenu() {
        autoCatalog.readAutoCatalogFromFile();
        while (true) {
            System.out.println("\nPlease choose the desired action:\n" +
                    "1 - Display all cars\n" +
                    "2 - Search cars by brand\n" +
                    "3 - Search cars by model\n" +
                    "4 - Filter cars by price range\n" +
                    "5 - Filter cars by year of production\n" +
                    "6 - Display all sold cars\n" +
                    "7 - Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    autoCatalog.displayAutoCatalog();
                    break;
                case "2":
                    System.out.print("Enter the brand: ");
                    String brand = scanner.nextLine();
                    autoCatalog.searchAllAutosByBrand(brand);
                    break;
                case "3":
                    System.out.print("Enter the model: ");
                    String model = scanner.nextLine();
                    autoCatalog.searchByModel(model);
                    break;
                case "4":
                    int minPrice = 0;
                    int maxPrice = 0;
                    while (true) {
                        try {
                            System.out.print("Enter the minimum price: ");
                            minPrice = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter the maximum price: ");
                            maxPrice = Integer.parseInt(scanner.nextLine());
                            if (minPrice > maxPrice) {
                                System.out.println("Minimum price cannot be greater than maximum price. Please try again.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price entered. Please enter valid numeric values.");
                        }
                    }
                    autoCatalog.searchByPriceCategory(minPrice, maxPrice);
                    break;
                case "5":
                    int year = 0;
                    while (true) {
                        try {
                            System.out.print("Enter the year of production: ");
                            year = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid year entered. Please enter a valid year.");
                        }
                    }
                    autoCatalog.searchByYear(year);
                    break;
                case "6":
                    autoCatalog.displaySoldAutoCatalog();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    //Редактирование каталога
    public static void manageCarCatalog() {
        Scanner scanner = new Scanner(System.in);
        AutoCatalog autoCatalog = new AutoCatalog();

        while (true) {
            System.out.println("Manage Car Catalog:\n" +
                    "1 - Add new car to catalog\n" +
                    "2 - Remove car from catalog\n" +
                    "3 - Edit car details\n" +
                    "4 - Return to main menu");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addNewCarToCatalog();
                    break;
                case "2":
                    autoCatalog.displayAutoCatalog();
                    removeCarFromCatalog();
                    break;
                case "3":
                    autoCatalog.displayAutoCatalog();
                    editCarDetails();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid value entered, please try again");
                    break;
            }
        }
    }

    //Добавление новой машины в каталог
    public static void addNewCarToCatalog() {
        Scanner scanner = new Scanner(System.in);
        AutoCatalog autoCatalog = new AutoCatalog();

        System.out.println("Enter VIN code:");
        String vinCode = scanner.nextLine();

        System.out.println("Enter brand:");
        String brand = scanner.nextLine();

        System.out.println("Enter model:");
        String model = scanner.nextLine();

        System.out.println("Enter price:");
        int price = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter year of production:");
        int yearOfProduction = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter short characteristics:");
        String shortCharacteristics = scanner.nextLine();

        System.out.println("Enter full characteristics:");
        String fullCharacteristics = scanner.nextLine();

        System.out.println("Enter color:");
        String color = scanner.nextLine();

        Auto newAuto = new Auto(vinCode, brand, model, price, yearOfProduction, shortCharacteristics, fullCharacteristics, color);

        autoCatalog.addNewAutoInCatalog(newAuto);
    }

    //Удаление машины из каталога
    public static void removeCarFromCatalog() {
        Scanner scanner = new Scanner(System.in);
        AutoCatalog autoCatalog = new AutoCatalog();

        System.out.println("Enter VIN code of the car to remove:");
        String vinCode = scanner.nextLine();

        Auto auto = autoCatalog.searchAutoForSaleByVinCode(vinCode);

        if (auto != null) {
            autoCatalog.removeAutoFromCatalog(auto);
        } else {
            System.out.println("Car with VIN code " + vinCode + " not found in the catalog.");
        }
    }

    //Редактирование данных авто
    public static void editCarDetails() {
        Scanner scanner = new Scanner(System.in);
        AutoCatalog autoCatalog = new AutoCatalog();

        System.out.println("Enter VIN code of the car to edit:");
        String vinCode = scanner.nextLine();

        Auto auto = autoCatalog.searchAutoForSaleByVinCode(vinCode);

        if (auto != null) {
            System.out.println("Edit Car Details:\n" +
                    "1 - Edit price\n" +
                    "2 - Edit short characteristics\n" +
                    "3 - Edit full characteristics\n" +
                    "4 - Return to previous menu");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter new price:");
                    int newPrice = Integer.parseInt(scanner.nextLine());
                    autoCatalog.editingCarPrice(vinCode, newPrice);
                    break;
                case "2":
                    System.out.println("Enter new short characteristics:");
                    String newShortCharacteristics = scanner.nextLine();
                    autoCatalog.editingCarShortCharacteristics(vinCode, newShortCharacteristics);
                    break;
                case "3":
                    System.out.println("Enter new full characteristics:");
                    String newFullCharacteristics = scanner.nextLine();
                    autoCatalog.edidtingCarFullCharacteristics(vinCode, newFullCharacteristics);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid value entered, please try again");
                    break;
            }
        } else {
            System.out.println("Car with VIN code " + vinCode + " not found in the catalog.");
        }
    }

    //Покупка машины пользователем
    private static void buyCar(User user) {
        // Считывание списка сотрудников
        PersonManager.deserializeEmployees();
        autoCatalog.readAutoCatalogFromFile();
        Employee director = new PersonManager().findEmployeeByID(1);

        autoCatalog.readAutoCatalogFromFile();
        autoCatalog.displayAutoCatalog();
        System.out.println("Please, enter VIN-code of the chosen car: ");
        String vinCode;
        Auto auto = null;

        while (true) {
            vinCode = scanner.nextLine();
            auto = autoCatalog.searchAutoForSaleByVinCode(vinCode);
            if (auto != null) {
                break;
            } else {
                System.out.println("Invalid VIN-code. Please, enter a valid VIN-code: ");
            }
        }

        System.out.println("You chose auto: " + auto.toString());
        System.out.println("Do you need credit?\n" +
                "NO - enter \"no\" \n" +
                "YES - enter any other value");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("no")) {
            System.out.println("Do you confirm the purchase? (yes/no)");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                autoCatalog.buyCar(vinCode, user.getId(), director.getId());
                user.addPurchase(auto);
                director.addSale(auto);
                PersonManager.serializeUsers();
                PersonManager.serializeEmployees();
                System.out.println("Car purchased successfully.");
            } else {
                System.out.println("Purchase cancelled.");
            }
        } else {
            LocalDate startDate = null;
            LocalDate endDate = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            while (startDate == null) {
                try {
                    System.out.println("Enter the start date of the credit (dd.MM.yyyy):");
                    String startDateStr = scanner.nextLine();
                    startDate = LocalDate.parse(startDateStr, formatter);
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
                }
            }

            while (endDate == null) {
                try {
                    System.out.println("Enter the end date of the credit (dd.MM.yyyy):");
                    String endDateStr = scanner.nextLine();
                    endDate = LocalDate.parse(endDateStr, formatter);
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
                }
            }

            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atStartOfDay();

            Credit credit = new Credit(auto.getPrice(), startDateTime, endDateTime, user);

            // Display credit details
            credit.logCreditDetails();

            System.out.println("Do you confirm the purchase with credit? (yes/no)");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                creditManager.addCredit(startDateTime, endDateTime, auto.getPrice(), user.getId());
                user.addCredit(credit);
                autoCatalog.buyCar(vinCode, user.getId(), director.getId());
                user.addPurchase(auto);
                director.addSale(auto);
                PersonManager.serializeUsers();
                PersonManager.serializeEmployees();
                System.out.println("Car purchased with credit successfully.");
                System.out.println("Please wait for a call from the manager to confirm the purchase");
            } else {
                System.out.println("Purchase cancelled.");
            }
        }
    }
    //продажа машины директором или менеджером
    private static void sellCarByEmployee(User user, Employee employee) {
        // Считывание списка сотрудников
        PersonManager.deserializeEmployees();
        autoCatalog.readAutoCatalogFromFile();
        Employee seller = employee;

        autoCatalog.readAutoCatalogFromFile();
        autoCatalog.displayAutoCatalog();
        System.out.println("Please, enter VIN-code of the chosen car: ");
        String vinCode;
        Auto auto = null;

        while (true) {
            vinCode = scanner.nextLine();
            auto = autoCatalog.searchAutoForSaleByVinCode(vinCode);
            if (auto != null) {
                break;
            } else {
                System.out.println("Invalid VIN-code. Please, enter a valid VIN-code: ");
            }
        }

        System.out.println("You chose auto: " + auto.toString());
        System.out.println("s a credit required?\n" +
                "NO - enter \"no\" \n" +
                "YES - enter any other value");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("no")) {
            System.out.println("Do you confirm the purchase? (yes/no)");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                autoCatalog.buyCar(vinCode, user.getId(), seller.getId());
                user.addPurchase(auto);
                seller.addSale(auto);
                PersonManager.serializeUsers();
                PersonManager.serializeEmployees();
                System.out.println("Car purchased successfully.");
            } else {
                System.out.println("Purchase cancelled.");
            }
        } else {
            LocalDate startDate = null;
            LocalDate endDate = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            while (startDate == null) {
                try {
                    System.out.println("Enter the start date of the credit (dd.MM.yyyy):");
                    String startDateStr = scanner.nextLine();
                    startDate = LocalDate.parse(startDateStr, formatter);
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
                }
            }

            while (endDate == null) {
                try {
                    System.out.println("Enter the end date of the credit (dd.MM.yyyy):");
                    String endDateStr = scanner.nextLine();
                    endDate = LocalDate.parse(endDateStr, formatter);
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
                }
            }

            LocalDateTime startDateTime = startDate.atStartOfDay();
            LocalDateTime endDateTime = endDate.atStartOfDay();

            Credit credit = new Credit(auto.getPrice(), startDateTime, endDateTime, user);

            // Вывод деталей кредита на экран
            credit.logCreditDetails();

            System.out.println("Do you confirm the purchase with credit? (yes/no)");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                creditManager.addCredit(startDateTime, endDateTime, auto.getPrice(), user.getId());
                user.addCredit(credit);
                autoCatalog.buyCar(vinCode, user.getId(), seller.getId());
                user.addPurchase(auto);
                seller.addSale(auto);
                PersonManager.serializeUsers();
                PersonManager.serializeEmployees();
                System.out.println("Car purchased with credit successfully.");
            } else {
                System.out.println("Purchase cancelled.");
            }
        }
    }

    //Запись на тест-драйв
    private static void signUpForTestDrive(User user) {
        autoCatalog.readAutoCatalogFromFile();
        autoCatalog.displayAutoCatalog();
        System.out.println("Please, enter VIN-code of the chosen car: ");
        String vinCode;
        Auto auto = null;

        while (true) {
            vinCode = scanner.nextLine();
            auto = autoCatalog.searchAutoForSaleByVinCode(vinCode);
            if (auto != null) {
                break;
            } else {
                System.out.println("Invalid VIN-code. Please, enter a valid VIN-code: ");
            }
        }

        System.out.println("You chose auto: " + auto.toString());

        LocalDate testDriveDate = null;
        while (testDriveDate == null) {
            System.out.println("Enter the date for the test drive (dd.MM.yyyy):");
            String dateStr = scanner.nextLine();
            try {
                testDriveDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
            }
        }

        TestDrive testDrive = new TestDrive(user, auto, testDriveDate);
        TestDriveManager testDriveManager = new TestDriveManager();
        testDriveManager.addTestDrive(testDrive);

        System.out.println("The test drive request for " + auto.getModel() + " has been processed on " + testDriveDate);
        LOGGER.info("User {}'s request for a test drive of car {} on {} has been processed", user.getName(), auto.getModel(), testDriveDate);
    }

    //Сценарий изменения данных сотрудника
    private static void editEmployeeInfo() {
        Employee employee = findEmployeeByEmail();
        if (employee == null) {
            return;
        }

        while (true) {
            System.out.println("\nWhat information would you like to edit?\n" +
                    "1 - Name\n" +
                    "2 - E-mail\n" +
                    "3 - Phone number\n" +
                    "4 - Address\n" +
                    "5 - Position\n" +
                    "6 - Access Level\n" +
                    "7 - Return to previous menu");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    personManager.changeEmployeeName(employee.getId(), newName);
                    PersonManager.serializeEmployees();
                    System.out.println("Name updated successfully.");
                    LOGGER.info("Employee {} updated their name to {}", employee.getEmail(), newName);
                    break;
                case "2":
                    String newEmail;
                    while (true) {
                        System.out.print("Enter new e-mail: ");
                        newEmail = scanner.nextLine();
                        if (personManager.findUserByEmail(newEmail) != null || personManager.findEmployeeByEmail(newEmail) != null) {
                            System.out.println("E-mail already exists. Please enter a different e-mail.");
                        } else {
                            break;
                        }
                    }
                    personManager.changeEmployeeEmail(employee.getId(), newEmail);
                    PersonManager.serializeEmployees();
                    System.out.println("E-mail updated successfully.");
                    LOGGER.info("Employee {} updated their e-mail to {}", employee.getEmail(), newEmail);
                    break;
                case "3":
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    personManager.changeEmployeePhoneNumber(employee.getId(), newPhoneNumber);
                    PersonManager.serializeEmployees();
                    System.out.println("Phone number updated successfully.");
                    LOGGER.info("Employee {} updated their phone number to {}", employee.getEmail(), newPhoneNumber);
                    break;
                case "4":
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    personManager.changeEmployeeAddress(employee.getId(), newAddress);
                    PersonManager.serializeEmployees();
                    System.out.println("Address updated successfully.");
                    LOGGER.info("Employee {} updated their address to {}", employee.getEmail(), newAddress);
                    break;
                case "5":
                    System.out.print("Enter new position: ");
                    String newPosition = scanner.nextLine();
                    personManager.changeEmployeePosition(employee.getId(), newPosition);
                    PersonManager.serializeEmployees();
                    System.out.println("Position updated successfully.");
                    LOGGER.info("Employee {} updated their position to {}", employee.getEmail(), newPosition);
                    break;
                case "6":
                    System.out.print("Enter new access level (USER, MANAGER, DIRECTOR): ");
                    String newAccessLevelStr = scanner.nextLine().toUpperCase();
                    AccessLevel newAccessLevel;
                    try {
                        newAccessLevel = AccessLevel.valueOf(newAccessLevelStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid access level. Please enter a valid access level.");
                        break;
                    }
                    personManager.changeEmployeeAccessLevelById(employee.getId(), newAccessLevel);
                    PersonManager.serializeEmployees();
                    System.out.println("Access level updated successfully.");
                    LOGGER.info("Employee {} updated their access level to {}", employee.getEmail(), newAccessLevel);
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    //Сценарий изменения данных пользователя
    private static void editUserInfo(User user) {
        PersonManager personManager = new PersonManager();
        PersonManager.deserializeUsers();

        while (true) {
            System.out.println("\nWhat information would you like to edit?\n" +
                    "1 - Name\n" +
                    "2 - E-mail\n" +
                    "3 - Phone number\n" +
                    "4 - Address\n" +
                    "5 - Return to previous menu");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    personManager.changeUserName(user.getId(), newName);
                    PersonManager.serializeUsers();
                    System.out.println("Name updated successfully.");
                    LOGGER.info("User {} updated their name to {}", user.getEmail(), newName);
                    break;
                case "2":
                    String newEmail;
                    while (true) {
                        System.out.print("Enter new e-mail: ");
                        newEmail = scanner.nextLine();
                        if (personManager.findUserByEmail(newEmail) != null || personManager.findEmployeeByEmail(newEmail) != null) {
                            System.out.println("E-mail already exists. Please enter a different e-mail.");
                        } else {
                            break;
                        }
                    }
                    personManager.changeUserEmail(user.getId(), newEmail);
                    PersonManager.serializeUsers();
                    System.out.println("E-mail updated successfully.");
                    LOGGER.info("User {} updated their e-mail to {}", user.getEmail(), newEmail);
                    break;
                case "3":
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    personManager.changeUserPhoneNumber(user.getId(), newPhoneNumber);
                    PersonManager.serializeUsers();
                    System.out.println("Phone number updated successfully.");
                    LOGGER.info("User {} updated their phone number to {}", user.getEmail(), newPhoneNumber);
                    break;
                case "4":
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    personManager.changeUserAddress(user.getId(), newAddress);
                    PersonManager.serializeUsers();
                    System.out.println("Address updated successfully.");
                    LOGGER.info("User {} updated their address to {}", user.getEmail(), newAddress);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    //Выводим на экран список тест-драйвов пользователя
    public static void printUserTestDrives(User user) {
        PersonManager.deserializeUsers();
        List<TestDrive> testDrives = user.getTestDrives();

        if (testDrives.isEmpty()) {
            System.out.println("No test drives found for user: " + user.getName());
            return;
        }

        System.out.println("\nTest Drives for user: " + user.getName());
        line();
        System.out.printf("%-4s %-20s %-20s %-25s%n", "№", "Car Model", "Color", "Date");
        line();
        int counter = 0;
        for (TestDrive testDrive : testDrives) {
            counter++;
            System.out.printf("%-4s %-20s %-20s %-25s%n", counter, testDrive.getAuto().getModel(), testDrive.getAuto().getColor(), testDrive.getLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        }
        line();
    }

    //Выводим все продажи найденного менеджера за выбранный период
    public static void printEmployeeSalesInPeriod(Employee employee) {
        if (employee == null) {
            System.out.println("Invalid employee provided.");
            return;
        }

        LocalDate startDate = null;
        LocalDate endDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        while (startDate == null) {
            try {
                System.out.print("Enter the start date (dd.MM.yyyy): ");
                String startDateStr = scanner.nextLine();
                startDate = LocalDate.parse(startDateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
            }
        }

        while (endDate == null) {
            try {
                System.out.print("Enter the end date (dd.MM.yyyy): ");
                String endDateStr = scanner.nextLine();
                endDate = LocalDate.parse(endDateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
            }
        }

        List<Auto> salesInPeriod = new ArrayList<>();
        for (Auto sale : employee.getSales().values()) {
            if (sale.getDate() != null && !sale.getDate().isBefore(startDate) && !sale.getDate().isAfter(endDate)) {
                salesInPeriod.add(sale);
            }
        }

        if (salesInPeriod.isEmpty()) {
            System.out.println("No sales found for the employee in the specified period.");
            return;
        }

        System.out.println("\nSales for employee: " + employee.getName() + " from " + startDate.format(formatter) + " to " + endDate.format(formatter));
        line();
        System.out.printf("%-4s %-20s %-20s %-20s %-10s %s%n", "№", "VIN Code", "Brand", "Model", "Price", "Sale Date");
        line();
        int counter = 1;
        for (Auto sale : salesInPeriod) {
            System.out.printf("%-4s %-20s %-20s %-20s %-10d %s%n", counter++, sale.getVinCode(), sale.getBrand(), sale.getModel(), sale.getPrice(), sale.getDate() != null ? sale.getDate().format(formatter) : "N/A");
        }
        line();
    }

    //Выводим список всех тест-драйвов автосалона с запросом - прошлые, будущие или все вместе
    public static void manageTestDrives() {
        Statistics statistics = new Statistics();
        testDriveManager.deserializeTestDrives();
        HashSet<TestDrive> testDrives = testDriveManager.getTestDriveList();

        if (testDrives.isEmpty()) {
            System.out.println("No test drives found.");
            return;
        }

        while (true) {
            System.out.println("What would you like to do?\n" +
                    "1 - View past test drives\n" +
                    "2 - View upcoming test drives\n" +
                    "3 - View all test drives\n" +
                    "4 - View test drives in a specific period\n" +
                    "5 - Delete a user's test drive\n" +
                    "6 - Exit");
            String choice = scanner.nextLine();

            Stream<TestDrive> filteredStream = testDrives.stream();
            LocalDate now = LocalDate.now();

            switch (choice) {
                case "1":
                    filteredStream = filteredStream.filter(testDrive -> testDrive.getLocalDate().isBefore(now));
                    break;
                case "2":
                    filteredStream = filteredStream.filter(testDrive -> testDrive.getLocalDate().isAfter(now) || testDrive.getLocalDate().isEqual(now));
                    break;
                case "3":
                    // Для вывода всех тест-драйвов не применяем фильтр
                    break;
                case "4":
                    LocalDate startDate = getDateFromUser("Enter start date (dd.MM.yyyy):");
                    LocalDate endDate = getDateFromUser("Enter end date (dd.MM.yyyy):");
                    if (startDate == null || endDate == null) {
                        System.out.println("One or both of the dates are null. Please enter valid dates.");
                        continue;
                    }
                    filteredStream = statistics.testDrivesInPeriod(startDate, endDate).stream();
                    break;
                case "5":
                    System.out.println("Enter the user's email:");
                    String email = scanner.nextLine();
                    User user = personManager.findUserByEmail(email);
                    if (user == null) {
                        System.out.println("No user found with this email.");
                        continue;
                    }
                    List<TestDrive> userTestDrives = user.getTestDrives();
                    if (userTestDrives.isEmpty()) {
                        System.out.println("No test drives found for this user.");
                    } else {
                        System.out.println("User's test drives:");
                        System.out.printf("%-20s %-20s %-15s%n", "Model", "VIN Code", "Test Drive Date");

                        line();

                        userTestDrives.forEach(testDrive -> System.out.printf("%-20s %-20s %-15s%n",
                                testDrive.getAuto().getModel(),
                                testDrive.getAuto().getVinCode(),
                                testDrive.getLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        ));
                        System.out.println("Enter the VIN code of the car:");
                        String vinCode = scanner.nextLine();
                        LocalDate date = getDateFromUser("Enter the date of the test drive (dd.MM.yyyy):");
                        if (date == null) {
                            System.out.println("Invalid date format.");
                            continue;
                        }
                        TestDrive testDriveToRemove = null;
                        for (TestDrive testDrive : userTestDrives) {
                            if (testDrive.getAuto().getVinCode().equalsIgnoreCase(vinCode) && testDrive.getLocalDate().equals(date)) {
                                testDriveToRemove = testDrive;
                                break;
                            }
                        }
                        if (testDriveToRemove != null) {
                            user.removeTestDriveByDetails(vinCode, date);
                            testDriveManager.removeTestDrive(testDriveToRemove);
                            System.out.println("Test drive removed successfully.");
                        } else {
                            System.out.println("No matching test drive found.");
                        }
                    }
                    continue;
                case "6":
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    continue;
            }

            List<TestDrive> filteredTestDrives = filteredStream.sorted(Comparator.comparing(TestDrive::getLocalDate)).collect(Collectors.toList());

            if (filteredTestDrives.isEmpty()) {
                System.out.println("No matching test drives found.");
            } else {
                System.out.println("\nList of Test Drives:");
                line3();
                System.out.printf("%-4s %-20s %-20s %-20s %-25s %s%n", "№", "User", "User ID", "Car Model", "Car VIN", "Date");
                line3();
                final int[] counter = {1};
                filteredTestDrives.forEach(testDrive -> {
                    System.out.printf("%-4s %-20s %-20s %-20s %-25s %s%n", counter[0]++, testDrive.getUser().getName(), testDrive.getUser().getId(), testDrive.getAuto().getModel(), testDrive.getAuto().getVinCode(), testDrive.getLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                });
                line3();
            }
        }
    }
    //Метод ввода и проверки даты! Он не везде используется пока, в некоторых сценариях его нужно применить
    //Чтобы не дублировать код
    private static LocalDate getDateFromUser(String message) {
        System.out.println(message);
        while (true) {
            String dateStr = scanner.nextLine();
            try {
                return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
            }
        }
    }

//Вывод на экран кредитов пользователя
    private static void displayUserCredits(User user) {
        HashMap<Long, Credit> credits = user.getCredits();

        if (credits.isEmpty()) {
            System.out.println("No credits found for the user.");
            return;
        }

        System.out.println("\nUser Credits:");
        line3();
        System.out.printf("%-4s %-15s %-20s %-20s %-20s %-20s %-20s %n", "№", "Credit Amount", "Interest Rate", "Monthly Payment", "Remaining Term (months)", "Remaining Amount", "Status");
        line3();
        final int[] counter = {1};//Заменитель int counter++ для стрима
        credits.values().stream()
                .sorted(Comparator.comparing(Credit::getStartDate))
                .forEach(credit -> {
                    double annualInterestRate = credit.getAnnualInterestRate(credit.calculateLoanTermInMonths());
                    int remainingTerm = credit.calculateRemainingTermInMonths();
                    double remainingAmount = credit.calculateRemainingTotalPayment();
                    double monthlyPayment = credit.calculateMonthlyPayment();

                    System.out.printf("%-4s %-15.2f %-20.2f %-20.2f %-20d %-20.2f %-20s %n",
                            counter[0]++,
                            credit.getCreditAmount(),
                            annualInterestRate,
                            monthlyPayment,
                            remainingTerm,
                            remainingAmount,
                            credit.getStatus());
                });
        line3();
    }

    //Выводим на экран все покупки пользователя в виде таблицы
    public static void printUserPurchases(User user) {
        Map<String, Auto> purchases = user.getPurchases();

        if (purchases.isEmpty()) {
            System.out.println("No purchases found for user: " + user.getName());
            return;
        }

        System.out.println("\nUser purchases: " + user.getName());
        line2();
        System.out.printf("%-4s %-20s %-20s %-20s %-10s %s%n", "№", "VIN Code", "Brand", "Model", "Price", "Purchase Date");
        line2();

        final int[] counter = {1}; // int counter внутри лямбды не работает. Нашел такой способ обойти.
        purchases.values().stream()
                .sorted(Comparator.comparing(Auto::getDate, Comparator.nullsLast(LocalDate::compareTo)))
                .forEach(auto -> {
                    String purchaseDate = (auto.getDate() != null) ? auto.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "N/A";
                    System.out.printf("%-4s %-20s %-20s %-20s %-10d %s%n", counter[0]++, auto.getVinCode(), auto.getBrand(), auto.getModel(), auto.getPrice(), purchaseDate);
                });

        line2();
    }

    //Метод для поиска юзера по e-mail
    private static User findUserByEmail() {
        System.out.print("Enter the user's email: ");
        String email = scanner.nextLine();
        User user = personManager.findUserByEmail(email);

        if (user != null) {
            System.out.println("User found: " + user.getName());
        } else {
            System.out.println("No user found with the email: " + email);
        }
        return user;
    }

    // Метод для нахождения сотрудника по e-mail
    private static Employee findEmployeeByEmail() {
        System.out.print("Enter the employee's email: ");
        String email = scanner.nextLine();
        Employee employee = personManager.findEmployeeByEmail(email);

        if (employee != null) {
            System.out.println("Employee found: " + employee.getName());
        } else {
            System.out.println("No employee found with the email: " + email);
        }
        return employee;
    }

    //Создание профиля сотрудника
    public static void createEmployeeProfile() {
        PersonManager.deserializeEmployees();

        String name;
        String eMail;
        String phoneNumber;
        String address;
        String password;
        String position;

        Scanner scanner = new Scanner(System.in);
        PersonManager personManager = new PersonManager();

        System.out.print("Enter the employee's name: ");
        name = scanner.nextLine();

        System.out.print("Enter the employee's email: ");
        while (true) {
            eMail = scanner.nextLine();
            if (personManager.findUserByEmail(eMail) != null || personManager.findEmployeeByEmail(eMail) != null) {
                System.out.println("Email already exists. Please enter a different email.");
            } else {
                break;
            }
        }

        System.out.print("Enter the employee's phone number: ");
        phoneNumber = scanner.nextLine();

        System.out.print("Enter the employee's address: ");
        address = scanner.nextLine();

        System.out.print("Enter the employee's position: ");
        position = scanner.nextLine();

        System.out.print("Enter the employee's password: ");
        RegexValidator passwordValidator = new RegexValidator("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        boolean passwordIsValid = false;
        do {
            password = scanner.nextLine();
            passwordIsValid = passwordValidator.isValid(password);
            if (!passwordIsValid) {
                System.out.println("The password you entered does not meet security requirements:");
                System.out.println("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            }
        } while (!passwordIsValid);

        personManager.createEmployee(name, eMail, phoneNumber, address, password, position);
        System.out.println("Employee with name " + name + " was registered successfully.");
        LOGGER.info("New employee registered: {}", name);

        PersonManager.serializeEmployees();
    }

    private static void generateReportScenario() {
        LocalDate startDate = null;
        LocalDate endDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        while (startDate == null) {
            try {
                System.out.print("Enter the start date (dd.MM.yyyy): ");
                String startDateStr = scanner.nextLine();
                startDate = LocalDate.parse(startDateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
            }
        }

        while (endDate == null) {
            try {
                System.out.print("Enter the end date (dd.MM.yyyy): ");
                String endDateStr = scanner.nextLine();
                endDate = LocalDate.parse(endDateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in format dd.MM.yyyy:");
            }
        }

        while (true) {
            System.out.println("\nReport Menu - Please choose the desired action:\n" +
                    "1 - Display report on screen\n" +
                    "2 - Generate report to file\n" +
                    "3 - Exit to main menu");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayReportOnScreen(startDate, endDate);
                    break;
                case "2":
                    generateReportToFile(startDate, endDate);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void displayReportOnScreen(LocalDate startDate, LocalDate endDate) {
        System.out.println("Generating report for period from " + startDate + " to " + endDate + "\n");

        // Общее количество юзеров
        int totalUsers = statistics.usersQuantity();
        System.out.println("Total number of registered users: " + totalUsers);

        // Зарегистрированные за период
        int registeredUsers = statistics.usersRegisteredInPeriod(startDate, endDate);
        System.out.println("Number of users registered in period: " + registeredUsers);

        // Количество активных юзеров
        int activeUsers = statistics.activeUsersQuantity();
        System.out.println("Number of active users: " + activeUsers);

        // Количество постоянных клиенотв
        int regularCustomers = statistics.regularCustomers().size();
        System.out.println("Number of regular customers: " + regularCustomers);

        // Количество проданных машин
        List<Auto> soldCarsInPeriod = autoCatalog.getAutosSoldBetweenDates(startDate, endDate);
        int soldCars = soldCarsInPeriod.size();
        System.out.println("Number of sold cars in period: " + soldCars);

        // Количество тест-драйвов за период
        int testDrives = statistics.testDrivesInPeriod(startDate, endDate).size();
        System.out.println("Number of test-drives in period: " + testDrives);

        // Конверсия за период
        double conversionRate = statistics.conversionRateForPeriod(startDate, endDate);
        System.out.println("Conversion rate: " + conversionRate + "%");

        // Выручка за период
        double totalRevenue = statistics.revenueForPeriod(startDate, endDate);
        System.out.println("Total revenue: $" + totalRevenue);

        // Выручка сотрудника за период
        System.out.println("Revenue by employees:");
        for (Employee employee : personManager.getEmployees().values()) {
            double employeeRevenue = statistics.employeeRevenueForPeriod(employee, startDate, endDate);
            System.out.println(employee.getName() + ": $" + employeeRevenue);
        }

        // Самый популярный бренд за период
        String popularBrand = statistics.mostPopularBrandInPeriod(startDate, endDate);
        System.out.println("Most popular brand: " + popularBrand);

        // Самая популярная модель за период
        String popularModel = statistics.mostPopularModelInPeriod(startDate, endDate);
        System.out.println("Most popular model: " + popularModel);
    }

    private static void generateReportToFile(LocalDate startDate, LocalDate endDate) {
        statistics.generateReportForPeriod(startDate, endDate);
        System.out.println("Report has been generated to file for the period from " + startDate + " to " + endDate);
    }

    // Отправка сообщения
    private static void sendMessage(Person sender) {
        System.out.println("Please, enter recipient's email address:");
        String eMail = scanner.nextLine();
        System.out.println("Please, enter your message:");
        String text = scanner.nextLine();
        User user = personManager.findUserByEmail(eMail);
        Employee employee = personManager.findEmployeeByEmail(eMail);

        if (employee != null) {
            sender.sendMessage(employee, text);
        } else if (user != null) {
            sender.sendMessage(user, text);
        } else {
            System.out.println("User with email " + eMail + " does not exist. Please choose another one.");
        }
    }

    // Просмотр сообщений
    private static void manageMessages(Person person) {
        while (true) {
            System.out.println("\nMessages Menu - Please choose the desired action:\n" +
                    "1 - Send a message\n" +
                    "2 - View received messages\n" +
                    "3 - View sent messages\n" +
                    "4 - Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    sendMessage(person);
                    break;
                case "2":
                    viewReceivedMessages(person);
                    break;
                case "3":
                    viewSentMessages(person);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Просмотр отправленных сообщений
    private static void viewSentMessages(Person person) {
        TreeMap<LocalDateTime, Message> sentMessages = person.getSentMessages();
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages.");
            return;
        }

        System.out.println("\nSent Messages:");
        for (Message message : sentMessages.values()) {
            System.out.println("To: " + message.getReceiver().getName() + " - " + message.getText() + " - " + message.getLocalDateTime());
        }
    }

    // Просмотр полученных сообщений
    private static void viewReceivedMessages(Person person) {
        TreeMap<LocalDateTime, Message> receivedMessages = person.getReceivedMessages();
        if (receivedMessages.isEmpty()) {
            System.out.println("No received messages.");
            return;
        }

        System.out.println("\nReceived Messages:");
        for (Message message : receivedMessages.values()) {
            System.out.println("From: " + message.getSender().getName() + " - " + message.getText() + " - " + message.getLocalDateTime());
        }
    }

    private static void line() {
        System.out.println("-------------------------------------------------------------");
    }

    private static void line2() {
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    private static void line3() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }
}