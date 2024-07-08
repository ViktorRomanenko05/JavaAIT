package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Statistics {

    private static final Logger LOGGER = LoggerFactory.getLogger(Statistics.class);
    PersonManager persons = new PersonManager();
    AutoCatalog autoCatalog = new AutoCatalog();

    // Метод для определения количества зарегистрированных пользователей
    public int usersQuantity() {
        PersonManager.deserializeUsers();
        int quantity = persons.getUsers().size();
        LOGGER.info("Quantity of registered users: {}", quantity);
        return quantity;
    }

    // Метод для определения пользователей зарегистрировавшихся за определенный период
    public int usersRegisteredInPeriod(LocalDate startDate, LocalDate endDate) {
        PersonManager.deserializeUsers();
        int count = 0;

        for (User user : persons.getUsers().values()) {
            LocalDate registrationDate = user.getRegistrationDate();
            if (!registrationDate.isBefore(startDate) && !registrationDate.isAfter(endDate)) {
                count++;
            }
        }

        LOGGER.info("Number of users registered from {} to {}: {}", startDate, endDate, count);
        return count;
    }

    // Метод для определения активных пользователей (сделавших хотя бы одну покупку)
    public HashSet<User> activeCustomers() {
        PersonManager.deserializeUsers();
        HashSet<User> activeUsers = new HashSet<>();
        for (User user : persons.getUsers().values()) {
            if (!user.getPurchases().isEmpty()) {
                activeUsers.add(user);
            }
        }
        LOGGER.info("Quantity of active users: {}", activeUsers.size());
        return activeUsers;
    }

    // Количество активных пользователей
    public int activeUsersQuantity() {
        return activeCustomers().size();
    }

    // Постоянные клиенты (от 5 покупок)
    public HashSet<User> regularCustomers() {
        PersonManager.deserializeUsers();
        HashSet<User> regularUsers = new HashSet<>();
        for (User user : persons.getUsers().values()) {
            if (user.getPurchases().size() > 4) {
                regularUsers.add(user);
            }
        }
        LOGGER.info("Quantity of regular users: {}", regularUsers.size());
        return regularUsers;
    }

    // Количество проданных авто
    public int soldAutosQuantity() {
        PersonManager.deserializeUsers();
        int count = 0;
        for (User user : persons.getUsers().values()) {
            count += user.getPurchases().size();
        }
        LOGGER.info("Quantity of sold cars: {}", count);
        return count;
    }

    // Общее количество тест-драйвов
    public HashSet<TestDrive> testDrives() {
        PersonManager.deserializeUsers();
        HashSet<TestDrive> testDrives = new HashSet<>();
        for (User user : persons.getUsers().values()) {
            testDrives.addAll(user.getTestDrives());
        }
        LOGGER.info("Quantity of test-drives: {}", testDrives.size());
        return testDrives;
    }

    // Метод для тест-драйвов за определенный период
    public HashSet<TestDrive> testDrivesInPeriod(LocalDate startDate, LocalDate endDate) {
        PersonManager.deserializeUsers();
        HashSet<TestDrive> testDrivesInPeriod = new HashSet<>();
        for (User user : persons.getUsers().values()) {
            for (TestDrive testDrive : user.getTestDrives()) {
                LocalDate testDriveDate = testDrive.getLocalDate();
                if (!testDriveDate.isBefore(startDate) && !testDriveDate.isAfter(endDate)) {
                    testDrivesInPeriod.add(testDrive);
                }
            }
        }
        LOGGER.info("Number of test-drives from {} to {}: {}", startDate, endDate, testDrivesInPeriod.size());
        return testDrivesInPeriod;
    }

    // Конверсия (процент покупок по отношению к тест-драйвам)
    public double testsBuys() {
        int totalSoldCars = soldAutosQuantity();
        int totalTestDrives = testDrives().size();
        double conversion = 0.0;

        if (totalTestDrives > 0) {
            conversion = (double) totalSoldCars / totalTestDrives * 100;
        }

        LOGGER.info("Conversion rate (percentage of purchases after test-drives): {}", conversion);
        return conversion;
    }

    // Метод для подсчета конверсии за определенный период
    public double conversionRateForPeriod(LocalDate startDate, LocalDate endDate) {
        // Подсчет тест-драйвов за период
        int totalTestDrivesInPeriod = testDrivesInPeriod(startDate, endDate).size();

        // Подсчет покупок за период
        int totalPurchasesInPeriod = 0;
        List<Auto> soldCarsInPeriod = autoCatalog.getAutosSoldBetweenDates(startDate, endDate);
        totalPurchasesInPeriod = soldCarsInPeriod.size();

        // Рассчет конверсии
        double conversionRate = 0.0;
        if (totalTestDrivesInPeriod > 0) {
            conversionRate = (double) totalPurchasesInPeriod / totalTestDrivesInPeriod * 100;
        }

        LOGGER.info("Conversion rate from {} to {}: {}%", startDate, endDate, conversionRate);
        return conversionRate;
    }

    // Вычисление общей выручки сотрудника
    public double revenue(Employee employee) {
        PersonManager.deserializeEmployees();
        int employeeRevenue = 0;
        for (Auto auto : employee.getSales().values()) {
            employeeRevenue += auto.getPrice();
        }

        return employeeRevenue;
    }

    // Выручка сотрудника за определенный период
    public double employeeRevenueForPeriod(Employee employee, LocalDate startDate, LocalDate endDate) {
        double totalRevenue = 0;

        for (Auto auto : employee.getSales().values()) {
            LocalDate saleDate = auto.getDate();
            if (saleDate != null && !saleDate.isBefore(startDate) && !saleDate.isAfter(endDate)) {
                totalRevenue += auto.getPrice();
            }
        }

        LOGGER.info("Total revenue for employee {} from {} to {}: {}", employee.getName(), startDate, endDate, totalRevenue);
        return totalRevenue;
    }

    // Вычисление общей выручки автосалона
    public double autohausRevenue() {
        PersonManager.deserializeEmployees();
        double autohausRevenue = 0;
        for (Employee employee : PersonManager.employees.values()) {
            autohausRevenue += revenue(employee);
        }
        return autohausRevenue;
    }

    // Выручка за определенный период
    public double revenueForPeriod(LocalDate startDate, LocalDate endDate) {
        PersonManager.deserializeEmployees();
        double totalRevenue = 0;

        for (Employee employee : PersonManager.employees.values()) {
            for (Auto auto : employee.getSales().values()) {
                LocalDate saleDate = auto.getDate();
                if (saleDate != null && !saleDate.isBefore(startDate) && !saleDate.isAfter(endDate)) {
                    totalRevenue += auto.getPrice();
                }
            }
        }

        LOGGER.info("Total revenue from {} to {}: {}", startDate, endDate, totalRevenue);
        return totalRevenue;
    }

    // Самый популярный бренд за период
    public String mostPopularBrandInPeriod(LocalDate startDate, LocalDate endDate) {
        List<Auto> soldCarsInPeriod = autoCatalog.getAutosSoldBetweenDates(startDate, endDate);

        if (soldCarsInPeriod.isEmpty()) {
            LOGGER.info("No cars have been sold in the given period from {} to {}.", startDate, endDate);
            return null;
        }

        HashMap<String, Integer> brandCount = new HashMap<>();
        for (Auto auto : soldCarsInPeriod) {
            String brand = auto.getBrand();
            brandCount.put(brand, brandCount.getOrDefault(brand, 0) + 1);
        }

        String mostPopularBrand = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : brandCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostPopularBrand = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        LOGGER.info("Most popular brand from {} to {}: {}", startDate, endDate, mostPopularBrand);
        return mostPopularBrand;
    }

    // Метод для поиска самой популярной модели среди проданных авто за определенный период
    public String mostPopularModelInPeriod(LocalDate startDate, LocalDate endDate) {
        List<Auto> soldCarsInPeriod = autoCatalog.getAutosSoldBetweenDates(startDate, endDate);

        if (soldCarsInPeriod.isEmpty()) {
            LOGGER.info("No cars have been sold in the given period from {} to {}.", startDate, endDate);
            return null;
        }

        HashMap<String, Integer> modelCount = new HashMap<>();
        for (Auto auto : soldCarsInPeriod) {
            String model = auto.getModel();
            modelCount.put(model, modelCount.getOrDefault(model, 0) + 1);
        }

        String mostPopularModel = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : modelCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostPopularModel = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        LOGGER.info("Most popular model from {} to {}: {}", startDate, endDate, mostPopularModel);
        return mostPopularModel;
    }

    //Отчет: передаем в метод начальную и конечную даты, потом вызываем по очереди все методы
    public void generateReportForPeriod(LocalDate startDate, LocalDate endDate) {
        File report = Path.of("src", "main", "resources", "report.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report))) {
            // Общая информация
            writer.write("Report for period from " + startDate + " to " + endDate + "\n");
            writer.write("===============================\n");

            // Общее количество зарегистрированных пользователей
            int totalUsers = usersQuantity();
            writer.write("Total number of registered users: " + totalUsers);
            writer.newLine();

            // Количество зарегистрированных пользователей за период
            int registeredUsers = usersRegisteredInPeriod(startDate, endDate);
            writer.write("Number of users registered: " + registeredUsers);
            writer.newLine();

            // Количество активных пользователей
            int activeUsers = activeUsersQuantity();
            writer.write("Number of active users: " + activeUsers);
            writer.newLine();

            // Количество постоянных клиентов
            int regularCustomers = regularCustomers().size();
            writer.write("Number of regular customers: " + regularCustomers);
            writer.newLine();

            // Количество проданных авто за период
            int soldCars = autoCatalog.getAutosSoldBetweenDates(startDate, endDate).size();
            writer.write("Number of sold cars: " + soldCars);
            writer.newLine();

            // Количество тест-драйвов за период
            int testDrives = testDrivesInPeriod(startDate, endDate).size();
            writer.write("Number of test-drives: " + testDrives);
            writer.newLine();

            // Конверсия за период
            double conversionRate = conversionRateForPeriod(startDate, endDate);
            writer.write("Conversion rate: " + conversionRate + "%");
            writer.newLine();

            // Общая выручка автосалона за период
            double totalRevenue = revenueForPeriod(startDate, endDate);
            writer.write("Total revenue: $" + totalRevenue);
            writer.newLine();

            // Выручка по сотрудникам за период
            writer.write("Revenue by employees:");
            writer.newLine();
            for (Employee employee : persons.getEmployees().values()) {
                double employeeRevenue = employeeRevenueForPeriod(employee, startDate, endDate);
                writer.write(employee.getName() + ": $" + employeeRevenue);
                writer.newLine();
            }

            // Самый популярный бренд за период
            String popularBrand = mostPopularBrandInPeriod(startDate, endDate);
            writer.write("Most popular brand: " + popularBrand);
            writer.newLine();

            // Самая популярная модель за период
            String popularModel = mostPopularModelInPeriod(startDate, endDate);
            writer.write("Most popular model: " + popularModel);
            writer.newLine();

            writer.write("===============================\n");
            writer.write("End of report.\n");

            LOGGER.info("Report generated successfully for period from {} to {}", startDate, endDate);
        } catch (IOException exception) {
            LOGGER.error("Error writing report: ", exception.getMessage());
        }
    }
}