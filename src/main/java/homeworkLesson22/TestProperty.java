package homeworkLesson22;

import java.util.HashSet;
import java.util.Scanner;

import com.github.javafaker.Faker;

public class TestProperty {

    static Scanner scanner = new Scanner(System.in);
    static Faker faker = new Faker();

    public static void main(String[] args) {

        HashSet<Property> propertyHashSet = new HashSet<>();

        //Список возможных типов недвижимости (для фейкера)
        String[] propertyTypes = {"Flat", "House", "Commercial"};

        /*
        Вносим объекты недвижимости.

        Принято решение о раздельном заполнении полей адреса, так как при ручном вводе
        пользователем может возникнуть большое количество комбинаций ввода, что затруднит
        обработку и увеличит вероятность некорректной обработки данных.
         */

        for (int i = 0; i < 10; i++) {

            //Выбираем тип недвижимости из списка выше с помощью "Фейкера"
            String propertyType = propertyTypes[faker.random().nextInt(propertyTypes.length)];

            //Генерируем параметры для заполнения полей адреса
            int zipCode = faker.random().nextInt(10000, 99999); // фейкер генерирует индекс как String, поэтому такой способ
            String city = faker.address().city();
            String streetName = faker.address().streetName();
            int houseNumber = faker.random().nextInt(1, 350); // то же, что с индексом

            Property.addProperty(propertyHashSet, propertyType, zipCode, city, streetName, houseNumber);
        }

        //Выводим результат на экран
        System.out.println("\nСодержимое хранилища №1 (propertyHashSet)");
        Property.printer(propertyHashSet);

        /*
        Создадим вручную несколько объектов, 5 уникальных и постоянных, а еще 5
        получим из сгенерированного фейкером списка с помощью написанного метода getProperties
         */

        Property object1 = new Property("München", "Hauptstraße", 65, 54786, "House");
        Property object2 = new Property("Essen", "Schulstraße", 134, 85188, "House");
        Property object3 = new Property("Berlin", "Uferstraße", 37, 37951, "Commercial");
        Property object4 = new Property("Stuttgart", "Zentralstraße", 272, 59851, "Flat");
        Property object5 = new Property("Hamburg", "Waldstraße", 89, 36491, "Commercial");
        Property object6 = Property.getProperties(propertyHashSet, 0);
        Property object7 = Property.getProperties(propertyHashSet, 3);
        Property object8 = Property.getProperties(propertyHashSet, 5);
        Property object9 = Property.getProperties(propertyHashSet, 7);
        Property object10 = Property.getProperties(propertyHashSet, 9);


        //Добавляем 3 повторяющиеся и 1 уникальный объект в наш HashSet
        propertyHashSet.add(object9);
        propertyHashSet.add(object7);
        propertyHashSet.add(object6);
        propertyHashSet.add(object3);

        //Вывод списка объектов после добавления повторов
        System.out.println("\n\nДобавляем 3 повторяющихся и 1 уникальный объект");
        Property.printer(propertyHashSet);

        //ЗАДАНИЕ 2
        //Фильтрация по типу объекта

        System.out.println("\nВведите интересующий тип недвижимости:\n" +
                "House, Flat, либо Commercial");

        String propertyType = scanner.nextLine().trim();
        scanner.close();
        HashSet<Property> filteredByType;
        filteredByType = Property.filter(propertyHashSet, propertyType);

        //если метод возвращает null
        if (filteredByType.isEmpty()) {
            System.out.println("Нет объектов для отображения");
        } else {
            //Выводим результат работы метода фильтрации
            Property.printer(filteredByType);
        }

        /*ЗАДАНИЕ 3
        Сравнение содержимого двух HashSet

        Создадим второй HashSet с 10 объектами недвижимости и заполним его вручную
        созданными выше объектами.
        При этом 6 объектов будут совпадать в 1 и 2 списках
         */

        HashSet<Property> propertyHashSet1 = new HashSet<>();
        propertyHashSet1.add(object1);
        propertyHashSet1.add(object2);
        propertyHashSet1.add(object3);
        propertyHashSet1.add(object4);
        propertyHashSet1.add(object5);
        propertyHashSet1.add(object6);
        propertyHashSet1.add(object7);
        propertyHashSet1.add(object8);
        propertyHashSet1.add(object9);
        propertyHashSet1.add(object10);

        //Вызываем метод для сравнения хранилищ HashSet и выводим полученные данные на экран:
        HashSet<Property> difference = Property.compareSets(propertyHashSet, propertyHashSet1);

        //Для наглядности выведем на экран объекты из второго хранилища:
        System.out.println("\nОбъекты из хранилища №2 (propertyHashSet1)");
        Property.printer(propertyHashSet1);

        //Выводим результат работы метода на экран
        System.out.println("\nУникальные объекты, содержащиеся в первом хранилище");
        Property.printer(difference);
    }
}
