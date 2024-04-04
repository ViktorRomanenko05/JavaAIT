package homeworkLesson23;

import java.util.HashSet;
import java.util.Iterator;

public class ClothingManager {

    private static HashSet<ClothingItem> clothesCatalog = new HashSet<>();

    public static HashSet<ClothingItem> getClothesCatalog() {
        return clothesCatalog;
    }

    //Метод для добавления одежды в каталог
    public static void addClothes(ClothingItem clothes) {
        if (clothes == null) {
            System.out.println("Item is null");
        } else {
            boolean addItem = clothesCatalog.add(clothes);
            if (addItem) {
                System.out.println("Item " + clothes.getName() + " successfully added ");
            } else {
                System.out.println("Item " + clothes.getName() + " was already added in catalog");
            }
        }
    }

    //Метод для удаления объекта из каталога
    public static void removeClothes(ClothingItem clothes) {
        if (clothes == null) {
            System.out.println("Item is null");
            System.out.println("Item " + clothes.getName() + " successfully removed from catalog");
        } else {
            boolean removeItem = clothesCatalog.remove(clothes);
            if (removeItem) {
                System.out.println("Item " + clothes.getName() + " successfully removed from catalog");
            } else {
                System.out.println("Item " + clothes.getName() + " was not removed. It was not found in catalog");
            }

        }
    }

    //Метод для удаления объекта из каталога по параметрам
    public static void deleteItem(HashSet<ClothingItem> clothes, String nameDel, String sizeDel, String colorDel, String categoryDel) {
        Iterator<ClothingItem> iterator = clothes.iterator();
        while (iterator.hasNext()) {
            ClothingItem clothingItem = iterator.next();
            if (clothingItem.getCategory().equalsIgnoreCase(categoryDel) && clothingItem.getName().equalsIgnoreCase(nameDel)&&
            clothingItem.getSize().equalsIgnoreCase(sizeDel)&& clothingItem.getColor().equalsIgnoreCase(colorDel)){
                iterator.remove();
                System.out.println("Item " + clothingItem.getCategory() + " " + clothingItem.getName() + " removed" );
            }
        }
    }

    //Фильтр одежды по категории
    public static HashSet<ClothingItem> filterByCategory(String category) {
        HashSet<ClothingItem> filteredByCategory = new HashSet<>();

        if (clothesCatalog.isEmpty()) {
            System.out.println("Clothes catalog is empty");
        } else {
            for (ClothingItem clothes : clothesCatalog) {
                if (clothes.getCategory().equalsIgnoreCase(category)) {
                    filteredByCategory.add(clothes);
                }
            }
            if (filteredByCategory.isEmpty()) {
                System.out.println("Items with category " + category + " was not found");
            }
        }
        return filteredByCategory;
    }

    //Фильтр одежды по названию
    public static HashSet<ClothingItem> filterByName(String name) {
        HashSet<ClothingItem> filteredByName = new HashSet<>();

        if (clothesCatalog.isEmpty()) {
            System.out.println("Clothes catalog is empty");
        } else {
            for (ClothingItem clothes : clothesCatalog) {
                if (clothes.getName().equalsIgnoreCase(name)) {
                    filteredByName.add(clothes);
                }
            }
            if (filteredByName.isEmpty()) {
                System.out.println("Items with name " + name + " was not found");
            }
        }
        return filteredByName;
    }

    //Фильтр одежды по размеру
    public static HashSet<ClothingItem> filterBySize(String size) {

        HashSet<ClothingItem> filteredBySize = new HashSet<>();

        if (clothesCatalog.isEmpty()) {
            System.out.println("Clothes catalog is empty");
        } else {
            for (ClothingItem clothes : clothesCatalog) {
                if (clothes.getSize().equalsIgnoreCase(size)) {
                    filteredBySize.add(clothes);
                }
            }
            if (filteredBySize.isEmpty()) {
                System.out.println("Items with size " + size + " was not found");
            }
        }
        return filteredBySize;
    }

    //Фильтр одежды по цвету
    public static HashSet<ClothingItem> filterByColor(String color) {
        HashSet<ClothingItem> filteredByColor = new HashSet<>();

        if (clothesCatalog.isEmpty()) {
            System.out.println("Clothes catalog is empty");
        } else {
            for (ClothingItem clothes : clothesCatalog) {
                if (clothes.getColor().equalsIgnoreCase(color)) {
                    filteredByColor.add(clothes);
                }
            }
            if (filteredByColor.isEmpty()) {
                System.out.println("Items with color " + color + " was not found");
            }
        }
        return filteredByColor;
    }

    //Метод для вывода данных на экран
    public static void printer(HashSet<ClothingItem> clothesCatalog) {

        line();
        System.out.printf("%-3s %-15s %-10s %-8s %s%n", "№", "Name", "Category", "Size", "Color");
        line();
        int counterNum = 0;
        for (ClothingItem item : clothesCatalog) {
            counterNum++;
            System.out.printf("%-3s %-15s %-10s %-8s %s%n", counterNum, item.getName(), item.getCategory(), item.getSize(), item.getColor());
        }
        line();
    }

    //Линия для метода "printer"
    public static void line() {
        System.out.println("---------------------------------------------");
    }
}