package homeworkLesson43;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Scanner scanner = new Scanner(System.in);
    private static Faker faker = new Faker();

    public static void main(String[] args) {

        String choice;
        String name;
        String password;
        User user;

//        for (int i = 0; i < 1000; i++) {
//            AuthenticationManager.registration(name = faker.name().username(), faker.internet().password());
//        }

        System.out.println("\nЗдравствуйте!\n" +
                           "Выберите пожалуйста желаемое действие:\n" +
                           "РЕГИСТРАЦИЯ - введите `1`\n" +
                           "ВХОД - любое другое значение\n");
        choice = scanner.nextLine();
        if (choice.equals("1")) {
            System.out.println("\nРЕГИСТРАЦИЯ\n");
            System.out.print("Введите имя пользователя: ");
            name = scanner.nextLine();
            System.out.print("Введите пароль: ");
            password = scanner.nextLine();
            AuthenticationManager.registration(name, password);

        }
        AuthenticationManager.parseUsers();

        System.out.println("\nВХОД\n");
        while (true) {
            System.out.print("Введите имя пользователя: ");
            name = scanner.nextLine();
            System.out.print("Введите пароль: ");
            password = scanner.nextLine();
            user = AuthenticationManager.login(name, password);

            if (user != null) {
                System.out.println(user.getName() + ", вы успешно вошли в систему");
                break;
            } else {
                System.out.println("Ошибка входа. Проверьте имя пользователя и пароль\n"+
                        "{Хотите ввести еще раз?\n"+
                        "НЕТ - введите `нет`\n" +
                        "ДА - введите любое другое значение");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("нет")) {
                    break;
                }

            }
        }
        if (user.getRole().isUser()){
            while (true){
                System.out.println("\nВыберите желаемое действие:\n" +
                        "\n1 - просмотреть список всех стран по алфавиту\n" +
                        "2 - поиск страны и вывод информации о ней\n" +
                        "3 - выход");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1": CountryManager.sortByNameAndPrint();
                              break;
                    case "2": userCountryLook();
                              break;
                    case "3": return;
                    default:  System.out.println("Введено неверное значение, попробуйте еще раз");
                              break;
                }
            }
        }

        else if(user.getRole().isAdmin()) {
            while (true) {
                System.out.println("\nВыберите желаемое действие:\n" +
                        "\n1 - просмотреть список всех стран по алфавиту\n" +
                        "2 - поиск страны и вывод информации о ней\n" +
                        "3 - добавление новой страны\n" +
                        "4 - редактирование данных о стране\n" +
                        "5 - удаление страны\n" +
                        "6 - изменение статуса пользователя\n" +
                        "7 - выход");
                choice = scanner.nextLine();
                switch (choice){
                    case "1": CountryManager.sortByNameAndPrint();
                              break;
                    case "2": userCountryLook();
                              break;
                    case "3": addNewCountry();
                              break;
                    case "4": updateCountry();
                              break;
                    case "5": deleteCountry();
                              break;
                    case "6": changeUsersRole();
                              break;
                    case "7": return;

                }
            }
        }
        else {
            System.out.println("Ваши права не определены");
            System.out.println("Пожалуйста, обратитесь к администратору");
            LOGGER.warn("Role of user is incorrect");
        }

        System.out.println("Спасибо! До новых встреч!");
        scanner.close();
    }


    private static void userCountryLook(){
        while (true) {
            System.out.println("Введите название интересующей страны");
            String country = scanner.nextLine();
            CountryManager.searchCountry(country);
            System.out.println("Желаете ли повторить поиск?\n" +
                    "ДА - введите `да`\n" +
                    "НЕТ - введите любое другое значение");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("да")){
                continue;
            }
            else {
                break;
            }
        }
    }

    private static void addNewCountry(){
        while (true) {
            System.out.println("Введите данные новой страны");
            System.out.print("\nВведите название страны: ");
            String name = scanner.nextLine();
            System.out.print("Введите название столицы: ");
            String capital = scanner.nextLine();
            System.out.print("Введите численность населения");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка, введите целое число");
                scanner.next();
            }
            int population = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Введите плошадь территории в км^2");
            while (!scanner.hasNextDouble()) {
                System.out.println("Ошибка, введите число");
                scanner.next();
            }
            double area = scanner.nextDouble();
            scanner.nextLine();
            CountryManager.addCountry(name, capital, population, area);
            System.out.println("Желаете внести еще страну?\n" +
                    "ДА - введите `да`\n" +
                    "НЕТ - введите любое другое значение");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("да")){
                continue;
            }
            else {
                break;
            }
        }
    }

    private static void updateCountry(){
        while (true) {
            System.out.print("\nВведите название страны: ");
            String name = scanner.nextLine().trim();
            CountryManager.parseCountries();

            if(!CountryManager.getCountries().containsKey(name)){
                System.out.println("Страны с таким названием нет в списке");
                continue;
            }

            System.out.println("Выберите данные которые необходимо обновить:\n" +
                    "1 - название страны\n" +
                    "2 - название столицы страны\n" +
                    "3 - значение численности населения\n" +
                    "4 - значение площади территории\n" +
                    "5 - все данные");
            String choiceToChange = scanner.nextLine().trim();

            if (choiceToChange.equals("1")) {
                System.out.print("Введите новое название страны: ");
                String newName = scanner.nextLine().trim();
                CountryManager.updateCountryName(name, newName);
            }
            else if (choiceToChange.equals("2")) {
                System.out.print("Введите новое название столицы страны: ");
                String newCapital = scanner.nextLine().trim();
                CountryManager.updateCountryCapital(name, newCapital);
            }
            else if (choiceToChange.equals("3")) {
                System.out.print("Введите новое значение численности населения: ");
                while (!scanner.hasNextInt()){
                    System.out.println("Пожалуйста, введите целое число");
                    scanner.next();
                }
                int newPopulation = scanner.nextInt();
                scanner.nextLine();
                CountryManager.updateCountryPopulation(name, newPopulation);
            }
            else if (choiceToChange.equals("4")) {
                System.out.print("Введите новое значение площади территории: ");
                while (!scanner.hasNextDouble()){
                    System.out.println("Пожалуйста, введите число");
                    scanner.next();
                }
                double newArea = scanner.nextDouble();
                scanner.nextLine();
                CountryManager.updateCountryArea(name, newArea);
            }
            else if (choiceToChange.equals("5")) {
                System.out.print("Введите новое название страны: ");
                String newName = scanner.nextLine().trim();
                System.out.print("Введите новое название столицы страны: ");
                String newCapital = scanner.nextLine().trim();
                System.out.print("Введите новое значение численности населения: ");
                while (!scanner.hasNextInt()){
                    System.out.println("Пожалуйста, введите целое число");
                    scanner.next();
                }
                int newPopulation = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Введите новое значение площади территории: ");
                while (!scanner.hasNextDouble()){
                    System.out.println("Пожалуйста, введите число");
                    scanner.next();
                }
                double newArea = scanner.nextDouble();
                scanner.nextLine();
                CountryManager.updateCountry(name, newCapital, newPopulation, newArea);
                CountryManager.updateCountryName(name, newName);
            }
            else {
                System.out.println("Значение выбора не верно, попробуйте снова");
                continue;
            }

            System.out.println("Желаете обновить данные еще одной страны?\n" +
                    "ДА - введите 'да'\n" +
                    "НЕТ - введите любое другое значение");
            if (!scanner.nextLine().equalsIgnoreCase("да")) {
                break;
            }
        }
    }


    private static void deleteCountry(){
        while (true) {
            System.out.println("Введите название страны,\n" +
                    "запись о которой необходимо удалить:");
            String name = scanner.nextLine().trim();
            CountryManager.removeCountry(name);
            System.out.println("Желаете удалить еще запись?\n" +
                    "ДА - введите `да`\n" +
                    "НЕТ - введите любое другое значение");
            if (scanner.nextLine().equalsIgnoreCase("да")){
                continue;
            }
            else {
                break;
            }
        }
    }

    private static void changeUsersRole(){
        while (true) {
            System.out.println("\nВыберите пользователя, чью роль вы хотите изменить");
            String username = scanner.nextLine().trim();
            System.out.println("Выберите новую роль для пользователя " + username);
            System.out.println("1 - USER");
            System.out.println("2 - ADMIN");
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка, введите целое число");
                scanner.next();
            }
            int role = scanner.nextInt();
            scanner.nextLine();
            switch (role) {
                case 1:
                    AuthenticationManager.changeUserRole(username, Role.USER);
                    break;
                case 2:
                    AuthenticationManager.changeUserRole(username, Role.ADMIN);
                    break;
                default:
                    System.out.println("Вы ввели не верное число, попробуйте снова");
                    continue;
            }
            System.out.println("Желаете продолжить работу со статусами пользователей?\n" +
                    "ДА - введите `да`\n" +
                    "НЕТ - введите любое другое значение");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("да")){
                continue;
            }
            else {
                break;
            }
        }

    }
}
