package homeworkLesson22;

import java.util.HashSet;
import java.util.Objects;

public class Property {

    private String city;
    private String street;
    private int houseNumber;
    private int zipCode;
    private String type;

    public Property(String city, String street, int houseNumber, int zipCode, String type) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return houseNumber == property.houseNumber && zipCode == property.zipCode && Objects.equals(street, property.street) && Objects.equals(city, property.city) && Objects.equals(type, property.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, houseNumber, zipCode, type);
    }

    //Метод для добавления недвижимости
    public static void addProperty(HashSet<Property> propertyHashSet, String type, int zipCode, String city, String street, int houseNumber) {
        propertyHashSet.add(new Property(city, street, houseNumber, zipCode, type));
    }

    //Метод для фильтрации недвижимости по типу
    public static HashSet <Property> filter (HashSet<Property>propertyHashSet, String objectType){
        HashSet <Property> filtered = new HashSet<>();

        boolean found = false;
        for (Property property : propertyHashSet){
            if (property.getType().equalsIgnoreCase(objectType)){
                filtered.add(property);
                found = true;
            }
        }
        if (!found){
            System.out.println("Требуемого типа недвижимости нет всписке");
        }
        return filtered;
    }

    //Метод для определения уникальных элементов в первом массиве
    public static HashSet <Property> compareSets (HashSet<Property> propertyHashSet1, HashSet<Property> propertyHashSet2){
        HashSet<Property> difference = new HashSet<>(propertyHashSet1);
        difference.removeAll(propertyHashSet2);
        return difference;
    }

    //Метод для вывода данных на экран
    public static void printer(HashSet<Property> propertyHashSet) {

            line();
            System.out.printf("%-3s %-12s %-7s %-22s %-20s %s%n", "№", "Тип", "Индекс", "Город", "Улица", "№ дома");
            line();
            int counterNum = 0;
            for (Property property : propertyHashSet) {
                counterNum++;
                System.out.printf("%-3s %-12s %-7s %-22s %-20s %s%n", counterNum, property.getType(), property.getZipCode(), property.getCity(), property.getStreet(), property.getHouseNumber());
            }
            line();
    }

    //Метод для копирования из первого hashSet объектов (т. к. он заполняется фейкером)
    public static Property getProperties(HashSet<Property>propertyHashSet, int numberInList){

        int position =0;
        boolean found = false;
        for (Property object : propertyHashSet){
            if (position == numberInList){
                return  object;
            }
            position++;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Property{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", zipCode=" + zipCode +
                ", type='" + type + '\'' +
                '}';
    }

    //Линия для метода "printer"
    public static void line() {
        System.out.println("---------------------------------------------------------------------------");
    }
}
