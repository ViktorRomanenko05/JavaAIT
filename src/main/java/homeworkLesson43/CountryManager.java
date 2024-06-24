package homeworkLesson43;

import HomeworkLesson27.CrewSet;
import HomeworkLesson27.FilmStudioEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CountryManager {



    private static File file = Path.of("src","main", "java", "homeworkLesson43", "countries.txt").toFile();
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryManager.class);
    private static HashMap<String, Country> countries = new HashMap<>();

    public static HashMap<String, Country> getCountries() {
        return new HashMap<>(countries);
    }

    //Метод для парсинга данных из файла и сохранения в HashMap
    public static void parseCountries (){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(", ");
                Country country = new Country(data[0].trim(),data[1].trim(),Integer.parseInt(data[2].trim()),Double.parseDouble(data[3].trim()));
                countries.put(data[0].trim(), country);
            }
            LOGGER.info("Countries loaded");
        }
        catch (FileNotFoundException exception){
            LOGGER.error(exception.getMessage());
        }
        catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }

    }

    private static void writeCountries (){
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Country countryBuffer : countries.values()) {
                fileWriter.write(countryBuffer.getName() + ", " + countryBuffer.getCapital() + ", " + countryBuffer.getPopulation() + ", " + countryBuffer.getArea() + "\n");
            }
            System.out.println("Country  has been updated successfully");
            LOGGER.info("Country has been updated successfully");
        } catch (FileNotFoundException exception) {
            LOGGER.error(exception.getMessage());
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    //Метод для записи новой страны в файл
    public static void addCountry(String name, String capital, int population, double area) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(name + ", " + capital + ", "+ population+ ", " + area + "\n");
            System.out.println("Country " + name + " has been added successfully");
            LOGGER.info("Country " + name + " has been added successfully");
        }
        catch (FileNotFoundException exception) {
            LOGGER.error(exception.getMessage());
        }
        catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
        parseCountries();//Актуализируем хранилище в памяти
    }

    //Метод для удаления информации о стране
    public static void removeCountry(String name){
        parseCountries();
        Country previousCountry = countries.remove(name);
        if(previousCountry == null){
            System.out.println("Country " + name + " was not found");
            LOGGER.info("Country {} was not found", name);
        }
        else {
            System.out.println("Country " + name + " was removed");
            LOGGER.info("Country {} was removed", name);
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Country country : countries.values()) {
                fileWriter.write(country.getName() + ", " + country.getCapital() + ", " + country.getPopulation() + ", " + country.getArea() + "\n");
            }
        } catch (FileNotFoundException exception) {
            LOGGER.error(exception.getMessage());
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
        parseCountries();
    }

    //Метод для поиска страны и вывода информации о ней
    public static void searchCountry(String name) {
        parseCountries();
        Country country = countries.get(name);
        if (country != null) {
            LOGGER.info("Country found: " + country.getName());
            System.out.println("Name: " + country.getName());
            System.out.println("Capital: " + country.getCapital());
            System.out.println("Population: " + country.getPopulation());
            System.out.println("Area: " + country.getArea());
        } else {
            LOGGER.info("Country {} not found: ", name);
            System.out.println("Country" + name + " not found");
        }
    }

    //Метод обновления информации о стране
    public static void updateCountry(String name, String capital, int population, double area) {
        parseCountries();
        Country country = countries.get(name);
        if (country != null) {
            country.setCapital(capital);
            country.setPopulation(population);
            country.setArea(area);
            writeCountries();
            parseCountries();
        }
        else {
            System.out.println("Country " + name + " was not found");
        }
    }

    //Метод для изменения названия страны
    public static void updateCountryName(String name, String newName) {
        parseCountries();
        Country country = countries.get(name);
        if (country != null) {
            country.setName(newName);
            countries.put(newName, country);
            countries.remove(name);
            writeCountries();
            parseCountries();
        }
        else {
            System.out.println("Country " + name + " was not found");
        }
    }

    //Метод для изменения названия столицы
    public static void updateCountryCapital(String name, String capital) {
        parseCountries();
        Country country = countries.get(name);
        if (country != null) {
            country.setCapital(capital);
            writeCountries();
            parseCountries();
        }
        else {
            System.out.println("Country " + name + " was not found");
        }
    }

    //Метод для изменения значения численности населения страны
    public static void updateCountryPopulation(String name, int population) {
        parseCountries();
        Country country = countries.get(name);
        if (country != null) {
            country.setPopulation(population);
            writeCountries();
            parseCountries();
        }
        else {
            System.out.println("Country " + name + " was not found");
        }
    }

    //Метод для изменения значения площади страны
    public static void updateCountryArea(String name, double area) {
        parseCountries();
        Country country = countries.get(name);
        if (country != null) {
            country.setArea(area);
            writeCountries();
            parseCountries();
        }
        else {
            System.out.println("Country " + name + " was not found");
        }
    }



    //Сортировка стран по алфавиту
    public static void sortByNameAndPrint(){
        parseCountries();
        List<Country> sortedCountries = countries.values().stream()
        .sorted()
        .collect(Collectors.toList());
        System.out.println("\nСписок стран:");
        line();
        System.out.printf("%-4s %-25s %-20s %-11s %s%n", "№", "Name", "Capital", "Population", "Area (km^2)");
        line();
        int counter = 0;
        for (Country countryToPrint : sortedCountries) {
            counter++;
            System.out.printf("%-4s %-25s %-20s %-11s %s%n", counter, countryToPrint.getName(), countryToPrint.getCapital(), countryToPrint.getPopulation(), countryToPrint.getArea());
            //line();

        }
        line();
    }

    //Метод для вывода информации о всех странах
//    public static void printCountries(HashMap <String, Country> countries) {
//        System.out.println("\nСписок стран:");
//        line();
//        System.out.printf("%-4s %-25s %-20s %-11s %s%n", "№", "Name", "Capital", "Population", "Area (km^2)");
//        line();
//        int counter = 0;
//        for (Country countryToPrint : countries.values()) {
//            counter++;
//            System.out.printf("%-4s %-25s %-20s %-11s %s%n", counter, countryToPrint.getName(), countryToPrint.getCapital(), countryToPrint.getPopulation(), countryToPrint.getArea());
//            //line();
//
//        }
//        line();
//    }

    //Метод для добавления страны в список
//    private void addCountry (Country country) {
//        if (country == null) {
//            LOGGER.error("Object country is null");
//        } else {
//            Country previousCountry = countries.put(country.getName(), country);
//            if (previousCountry == null) {
//                LOGGER.info("Country " + country.getName() + " successfully added");
//            } else {
//                LOGGER.info("Country " + country.getName() + " was updated");
//            }
//        }
//    }

    private static void line(){
        System.out.println("---------------------------------------------------------------------------");
    }


}
