package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Faq implements Serializable {
    private static final long serialVersionUID = 1L;
    private static File file = Path.of("src","main", "resources", "faq.txt").toFile();
    private static final Logger LOGGER = LoggerFactory.getLogger(Faq.class);
    private static TreeMap<String, String> faq = new TreeMap<>();

    //Метод добавления.
    public static void addFaq(String question, String answer){
        if (question == null || answer == null){
            LOGGER.warn("Question or answer cannot be null");
        } else {
            faq.put(question, answer);
            LOGGER.info("Question and answer was added");
        }
    }

    //Метод удаления вопроса
    public static void deleteFaq(String question){
        if (question == null){
            LOGGER.warn("Question cannot be null");
        }
        else {
            parseFaq();
            String found = faq.remove(question);
            if (found != null) {
                LOGGER.info("Question and answer was deleted");
                writeFaq();
            }
            else {
                LOGGER.warn("Question not found");
            }
        }
    }

    // Метод поиска вопроса
    public static TreeMap<String, String> searchFaq(String keyword) {
        TreeMap<String, String> matches = new TreeMap<>();
        int counter = 1;
        for (Map.Entry<String, String> entry : faq.entrySet()) {
            if (entry.getKey().contains(keyword)) {
                matches.put(entry.getKey(), entry.getValue());
            }
        }
        if (!matches.isEmpty()) {
            LOGGER.info("Found {} matches", matches.size());
            System.out.println("\n" + matches.size() + " matches found");
            for (Map.Entry<String, String> match : matches.entrySet()) {
                System.out.println("\nQuestion: " + counter++);
                System.out.println(match.getKey());
                System.out.println("\nAnswer: ");
                System.out.println(match.getValue());
            }
        } else {
            LOGGER.warn("No matches found for keyword: {}", keyword);
        }
        return matches;
    }

    //Вывод всех вопросов
    public static void showAllFaq (){
        parseFaq();
        int counter = 1;
        System.out.println("\nFAQ:");
        for (Map.Entry<String, String> i : faq.entrySet()){
            System.out.println("\nQuestion: " + counter++);
            System.out.println(i.getKey());
            System.out.println("\nAnswer: ");
            System.out.println(i.getValue());
        }
    }


    //Метод для записи всего списка FAQ в файл
    private static void writeFaq (){
        parseFaq();//Синхронизируем FAQ в памяти с файлом
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Map.Entry <String, String> question : faq.entrySet()) {
                fileWriter.write(question.getKey() + "\\|" + question.getValue());
            }
            LOGGER.info("FAQ has been updated successfully");
        } catch (FileNotFoundException exception) {
            LOGGER.error("File FAQ was not found " + exception.getMessage());
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    //Сериализация
    public static void serializeFaq() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/faq"))) {
            objectOutputStream.writeObject(faq);
            LOGGER.info("FAQ have been serialized");
        } catch (IOException exception) {
            LOGGER.error("Error during faq serialization", exception.getMessage());
        }
    }

    public static void deserializeFaq() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/resources/faq"))) {
            faq = (TreeMap<String, String>) objectInputStream.readObject();
            LOGGER.info("FAQ have been deserialized");
        } catch (IOException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
        catch (ClassNotFoundException exception) {
            LOGGER.error("Error during users deserialization", exception.getMessage());
        }
    }

    //Метод для записи нового вопроса в файл и актуализации активного списка
    public static void addNewFaq(String question, String answer) {
        if(question == null || answer == null){
            LOGGER.error("Question and answer cannot be null");
        }
        else {
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(question + "|" + answer + "\n");
                LOGGER.info("Question " + question + " has been added successfully");
            } catch (FileNotFoundException exception) {
                LOGGER.error("File FAQ was not found" + exception.getMessage());
            } catch (IOException exception) {
                LOGGER.error(exception.getMessage());
            }
            parseFaq();//Актуализируем хранилище в памяти
        }
    }

    //Метод для парсинга всех FAQ из файла и сохранения в TreeMap
    public static void parseFaq (){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");
                faq.put(data[0].trim(), data[1].trim());
            }
            LOGGER.info("FAQ loaded");
        }
        catch (FileNotFoundException exception){
            LOGGER.error("File with FAQ was not found",exception.getMessage());
        }
        catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public static TreeMap<String, String> getFaq() {
        return faq;
    }
}
