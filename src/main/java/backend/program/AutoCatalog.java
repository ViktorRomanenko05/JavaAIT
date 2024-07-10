package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//тут можно указать только implements от ManagerInterfaceAutoCatalog, т.к он являестя наследником LookerInterfaceCatalog
public class AutoCatalog implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoCatalog.class);

    File autoCatalogTxt = Path.of("src", "main", "resources", "AutoCatalog.txt").toFile();
    File soldCarsTxt = Path.of("src", "main", "resources", "SoldCars.txt").toFile();

    private static final Function<String, Auto> autoMapFunction = line -> {
        String[] parts = line.split("\\|");
        String vinCode = parts[0];
        String brand = parts[1];
        String model = parts[2];
        int price = Integer.parseInt(parts[3]);
        int yearOfProduction = Integer.parseInt(parts[4]);
        String shortCharacteristics = parts[5];
        String fullCharacteristics = parts[6];
        String color = parts[7];
        LocalDate date = null;

        // Проверяем, есть ли дата и не равна ли она 'null' или пустая строка
        if (parts.length > 8 && parts[8] != null && !parts[8].trim().isEmpty() && !parts[8].equalsIgnoreCase("null")) {
            date = LocalDate.parse(parts[8], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        Auto auto = new Auto(vinCode, brand, model, price, yearOfProduction, shortCharacteristics, fullCharacteristics, color);
        auto.setDate(date); // Убедитесь, что есть сеттер для даты в классе Auto
        return auto;
    };

    private static final Function<Auto, String> autoMapFunctionToString = auto -> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return auto.getVinCode() + "|" +
                auto.getBrand() + "|" +
                auto.getModel() + "|" +
                auto.getPrice() + "|" +
                auto.getYearOfProduction() + "|" +
                auto.getShortCharacteristics() + "|" +
                auto.getFullCharacteristics() + "|" +
                auto.getColor() + "|" +
                (auto.getDate() != null ? auto.getDate().format(formatter) : "");
    };

    //Каталог всех автомобилей в салоне
    //Каталог приватный, работа с ним только через методы
    //Получить доступ к каталогу можно через getter
    private static HashMap<String, Auto> autoCatalog = new HashMap<>();

    //Хранилише информации о проданных автомобилях
    private static HashMap<String, Auto> soldCars = new HashMap<>();

    //Геттер для каталога
    //Возвращаем копию каталога, чтоб обеспечить безопасность и избежать преднамереное изменение оригинального каталога
    public static HashMap<String, Auto> getAutoCatalog() {
        return new HashMap<>(autoCatalog);
    }

    public static HashMap<String, Auto> getSoldCars() {
        return new HashMap<>(soldCars);
    }

    //Метод для считывания построчного информации о машинах с файла и преобразования каждой строки файла в объект Auto
    // и сбор всех прочитанных объектов в autoCatalog
    // Метод для чтения каталога из файла
    public void readAutoCatalogFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(autoCatalogTxt))) {
            autoCatalog = reader.lines()
                    .map(autoMapFunction)
                    .filter(auto -> auto != null)  // filter out any null values resulting from parsing errors
                    .collect(Collectors.toMap(Auto::getVinCode, auto -> auto, (existing, replacement) -> existing, HashMap::new));
        } catch (IOException exception) {
            LOGGER.error("Error reading auto catalog: {}", exception.getMessage());
        }
    }

    // Метод для чтения проданных машин из файла
    public void readSoldCarsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(soldCarsTxt))) {
            soldCars = reader.lines()
                    .map(autoMapFunction)
                    .filter(auto -> auto != null)  // filter out any null values resulting from parsing errors
                    .collect(Collectors.toMap(Auto::getVinCode, auto -> auto, (existing, replacement) -> existing, HashMap::new));
        } catch (IOException exception) {
            LOGGER.error("Error reading sold cars: {}", exception.getMessage());
        }
    }

    // Метод для записи каталога в файл
    public void writeAutoCatalogToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(autoCatalogTxt))) {
            autoCatalog.values().stream()
                    .map(autoMapFunctionToString)
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            LOGGER.error("Error writing to auto catalog: {}", e.getMessage());
                        }
                    });
        } catch (IOException exception) {
            LOGGER.error("Error writing auto catalog: {}", exception.getMessage());
        }
    }

    // Метод для записи проданных машин в файл
    public void writeSoldAutoToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(soldCarsTxt))) {
            soldCars.values().stream()
                    .map(autoMapFunctionToString)
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            LOGGER.error("Error writing to sold cars: {}", e.getMessage());
                        }
                    });
        } catch (IOException exception) {
            LOGGER.error("Error writing sold cars: {}", exception.getMessage());
        }
    }


    //метод просмотра списка автомобилей
    public void displayAutoCatalog() {
        readAutoCatalogFromFile();
        for (Auto auto : AutoCatalog.autoCatalog.values()) {
            if (!AutoCatalog.autoCatalog.isEmpty()) {
                System.out.println(auto);
            } else {
                System.out.println("Auto Catalog is empty!");
            }
        }
    }

    //Метод просмотра списка проданных авто
    public void displaySoldAutoCatalog() {
        readAutoCatalogFromFile();
        for (Auto auto : AutoCatalog.soldCars.values()) {
            if (!AutoCatalog.soldCars.isEmpty()) {
                System.out.println(auto);
            } else {
                System.out.println("Sold cars was not found!");
            }
        }
    }

    //метод фильтрации по марке (возвращает список машин определенной марки и выводит список на экран)
    public HashSet<Auto> searchAllAutosByBrand(String autoBrand) {
        readAutoCatalogFromFile();
        HashSet<Auto> foundAutos = new HashSet<>();
        if (!AutoCatalog.autoCatalog.isEmpty()) {
            for (Auto auto : AutoCatalog.autoCatalog.values()) {
                if (auto.getBrand().equals(autoBrand)) {
                    foundAutos.add(auto);
                }
            }
            if (foundAutos.isEmpty()) {
                System.out.println("Auto of this brand: " + autoBrand + " was not found!");
            }
            else{
                //Если каталог не пустой, выводим на экран список найденых машин
                printHashSet(foundAutos);
            }
        }
        else{
            System.out.println("Auto Catalog is empty!");
        }
        return foundAutos;
    }

    //Метод фильтрации по модели (возвращает список машин определенной модели и выводит список на экран)
    public HashSet<Auto> searchByModel(String autoModel) {
        readAutoCatalogFromFile();
        HashSet<Auto> foundAutos = new HashSet<>();
        if (!AutoCatalog.autoCatalog.isEmpty()) {
            for (Auto auto : AutoCatalog.autoCatalog.values()) {
                if (auto.getModel().equals(autoModel)) {
                    foundAutos.add(auto);
                }
            }
            if (foundAutos.isEmpty()) {
                System.out.println("Auto of this brand: " + autoModel + " was not found!");
            } else {
                //Если каталог не пустой, выводим на экран список найденых машин
                printHashSet(foundAutos);
            }
        }
        else{
            System.out.println("Auto Catalog is empty!");
        }
        return foundAutos;
    }

    //фильтрация проданных авто по модели
    public HashSet<Auto> searchSoldByModel(String autoModel) {
        readAutoCatalogFromFile();
        HashSet<Auto> foundAutos = new HashSet<>();
        if (!AutoCatalog.soldCars.isEmpty()) {
            for (Auto auto : AutoCatalog.soldCars.values()) {
                if (auto.getModel().equals(autoModel)) {
                    foundAutos.add(auto);
                }
            }
            if (foundAutos.isEmpty()) {
                System.out.println("Auto of this brand: " + autoModel + " was not found!");
            } else {
                //Если каталог не пустой, выводим на экран список найденых машин
                printHashSet(foundAutos);
            }
        }
        else{
            System.out.println("Auto Catalog is empty!");
        }
        return foundAutos;
    }

    //Метод фильтрации машин по определенной ценовой категории от min до max
    //(возвращает список машин определенной ценовой категории и выводит список на экран)
    public HashSet<Auto> searchByPriceCategory(int minPrice, int maxPrice) {
        readAutoCatalogFromFile();
        HashSet<Auto> foundAutos = new HashSet<>();
        if (!AutoCatalog.autoCatalog.isEmpty()) {
            for(Auto auto:AutoCatalog.autoCatalog.values()){
                if(auto.getPrice()>=minPrice && auto.getPrice()<=maxPrice){
                    foundAutos.add(auto);
                }
            }
            if(foundAutos.isEmpty()){
                System.out.println("No auto found in this pricecategory: " + minPrice + " - " + maxPrice);
            } else {
            //Если каталог не пустой, выводим на экран список найденых машин
            printHashSet(foundAutos);
        }
        }
        else{
            System.out.println("Auto Catalog is empty!");
        }
        return foundAutos;
    }
    //Метод фильтрации по году выпуска авто (возвращает список машин определенного года выпуска и выводит его на экран)
    public HashSet<Auto> searchByYear(int year) {
        readAutoCatalogFromFile();
        HashSet<Auto> foundAutos = new HashSet<>();
        if (!AutoCatalog.autoCatalog.isEmpty()) {
            for(Auto auto:AutoCatalog.autoCatalog.values()){
                if(auto.getYearOfProduction()==year){
                    foundAutos.add(auto);
                }
            }
            if(foundAutos.isEmpty()){
                System.out.println("No auto year " + year + "found");
            }else {
            //Если каталог не пустой, выводим на экран список найденых машин
            printHashSet(foundAutos);
        }
        }
        else{
            System.out.println("Auto Catalog is empty!");
        }
        return foundAutos;
    }

    //метод добавления нового автомобиля в наш каталог
    public void addNewAutoInCatalog(Auto auto) {
        readAutoCatalogFromFile();
       if(autoCatalog.containsKey(auto.getVinCode())) {
           System.out.println("ERROR!!It is unable to add auto in the catalog!!");
           System.out.println("Auto with the same VinCode: " + auto.getVinCode() + " is already exists in the catalog");
       }
       else{
           autoCatalog.put(auto.getVinCode(),auto);
           writeAutoCatalogToFile();
           System.out.println("The car " + auto.getBrand() + " " + auto.getModel() + " was successfully added to the catalog");
       }
    }

    ////Метод удаления автомобиля из каталога
    public void removeAutoFromCatalog(Auto auto) {
        readAutoCatalogFromFile();
        if(autoCatalog.containsKey(auto.getVinCode())){
            autoCatalog.remove(auto.getVinCode());
            writeAutoCatalogToFile();
            System.out.println("The car " + auto.getBrand() + " " + auto.getModel() + " was successfully removed from the catalog");
        }
        else{
            System.out.println("ERROR!!It is unable to remove auto from the catalog!!");
            System.out.println("Auto with the VinCode: " + auto.getVinCode() + " is not exists in the catalog");
        }
    }


    //редактирование цены машины
    public void editingCarPrice(String vinCode, int newPrice){
        readAutoCatalogFromFile();
        if(autoCatalog.containsKey(vinCode)){
            autoCatalog.get(vinCode).setPrice(newPrice);
            writeAutoCatalogToFile();
        }
        else{
            System.out.println("Auto with the VinCode: " + vinCode + " is not exists in the catalog");
        }
    }
    //редактирование краткой инфы о машине
    public void editingCarShortCharacteristics(String vinCode, String newShortCharacteristics){
        readAutoCatalogFromFile();
        if(autoCatalog.containsKey(vinCode)){
            autoCatalog.get(vinCode).setShortCharacteristics(newShortCharacteristics);
            writeAutoCatalogToFile();
        }
        else{
            System.out.println("Auto with the VinCode: " + vinCode + " is not exists in the catalog");
        }

    }
    //Редактирование полной инфы о машине
    public void edidtingCarFullCharacteristics(String vinCode, String newFullCharacteristics){
        readAutoCatalogFromFile();
        if(autoCatalog.containsKey(vinCode)){
            autoCatalog.get(vinCode).setFullCharacteristics(newFullCharacteristics);
            writeAutoCatalogToFile();
        }
        else{
            System.out.println("Auto with the VinCode: " + vinCode + " is not exists in the catalog");
        }
    }

    //Поиск авто на продажу по вин-коду
    public Auto searchAutoForSaleByVinCode(String vinCode) {
        readAutoCatalogFromFile();

        Auto autoInCatalog = autoCatalog.get(vinCode);

        if (autoInCatalog != null) {
            System.out.println("Auto was found");
            System.out.println("Status: Auto is for sale.");
            return autoInCatalog;
        }  else {
            System.out.println("Auto with VIN-code " + vinCode + " not found in catalog or sold cars.");
            return null;
        }
    }

    //Поиск авто по вин-коду
    public Auto searchByVinCode(String vinCode) {
        readAutoCatalogFromFile();
        readSoldCarsFromFile();

        Auto autoInCatalog = autoCatalog.get(vinCode);
        Auto autoSold = soldCars.get(vinCode);

        if (autoInCatalog != null) {
            System.out.println("Auto in catalog: " + autoInCatalog);
            System.out.println("Status: Auto is for sale.");
            return autoInCatalog;
        } else if (autoSold != null) {
            System.out.println("Auto in sold cars: " + autoSold);
            System.out.println("Status: Auto has been sold.");
            return autoSold;
        } else {
            System.out.println("Auto with VIN-code " + vinCode + " not found in catalog or sold cars.");
            return null;
        }
    }

    //Метод фильтрации по дате продажи
    public List<Auto> getAutosSoldBetweenDates(LocalDate startDate, LocalDate endDate) {
        readSoldCarsFromFile(); // Обновляем коллекцию проданных машин

        return soldCars.values().stream()
                .filter(auto -> auto.getDate() != null &&
                        (auto.getDate().isEqual(startDate) || auto.getDate().isAfter(startDate)) &&
                        (auto.getDate().isEqual(endDate) || auto.getDate().isBefore(endDate)))
                .collect(Collectors.toList());
    }

    // Метод для подсчета проданных автомобилей по марке за определенный период
    public Map<String, Long> countAutosSoldByBrandBetweenDates(LocalDate startDate, LocalDate endDate) {
        readSoldCarsFromFile();

        return soldCars.values().stream()
                .filter(auto -> auto.getDate() != null &&
                        (auto.getDate().isEqual(startDate) || auto.getDate().isAfter(startDate)) &&
                        (auto.getDate().isEqual(endDate) || auto.getDate().isBefore(endDate)))
                .collect(Collectors.groupingBy(Auto::getBrand, Collectors.counting()));
    }



    //приватный метод для вывода хэшзетов на экран
    private void printHashSet(HashSet<Auto> list) {
        for (Auto auto : list) {
            System.out.println(auto.toString());
        }
    }

    //Метод продажи авто
    public void buyCar(String vinCode, int userId, int employeeId) {
        readAutoCatalogFromFile();
        readSoldCarsFromFile();
        PersonManager.deserializeUsers();
        PersonManager.deserializeEmployees();

        Auto auto = autoCatalog.get(vinCode);
        User user = PersonManager.users.get(userId);
        Employee employee = PersonManager.employees.get(employeeId);

        if (auto != null && user != null && employee != null) {

            auto.setDate(LocalDate.now());
            soldCars.put(vinCode, auto);
            autoCatalog.remove(vinCode);
            user.addPurchase(auto);
            employee.addSale(auto);
            writeAutoCatalogToFile();
            writeSoldAutoToFile();
            PersonManager.serializeUsers();
            PersonManager.serializeEmployees();
            LOGGER.info("Car {} bought by user {} and sold by employee {}", auto, user.getName(), employee.getName());
        } else {
            if (auto == null) {
                LOGGER.error("Car with VIN-code {} not found in catalog", vinCode);
            }
            if (user == null) {
                LOGGER.error("User with ID {} not found", userId);
            }
            if (employee == null) {
                LOGGER.error("Employee with ID {} not found", employeeId);
            }
        }
    }
}
