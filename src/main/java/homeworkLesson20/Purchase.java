package homeworkLesson20;

/*
1. Создание списка покупок:
a. Создайте класс Purchase с полями itemName (название товара) и price (цена товара).
b. Создайте метод main для инициализации ArrayList с покупками и заполните его несколькими товарами.

2. Добавление новых покупок:
a. Напишите метод для добавления новой покупки в список. Параметры метода: название товара и его цена.
b. Проверьте работу метода, добавив несколько новых покупок в список.

3. Вывод списка покупок:
a. Напишите метод для вывода всех покупок из списка.
b. Проверьте работу метода, вызвав его после добавления нескольких покупок.

4. Поиск покупок:
a. Напишите метод для поиска покупок по названию товара.
b. Проверьте работу метода, вызвав его для поиска нескольких товаров.

5. Удаление покупок:
a. Напишите метод для удаления покупки по её названию.
b. Проверьте работу метода, удалив одну из покупок и выведя список покупок снова.
 */

import java.util.ArrayList;
import java.util.Iterator;

public class Purchase {

    private String itemName;
    private double price;

    public Purchase(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    //Добавляем новый товар в список
    public static void addPurchase(ArrayList <Purchase> purchaseArrayList, String purchaseName, double purchasePrice) {
        purchaseArrayList.add(new Purchase(purchaseName, purchasePrice));
    }

    //Выводим на экран список товаров с форматированием в виде таблицы.
    public static void printer (ArrayList <Purchase> purchaseArrayList){
        System.out.printf("%-3s %-20s %s%n", "№", "Наименование", "Цена, €");
        line();
        int counterNum = 0;
        for (Purchase purchase : purchaseArrayList) {
            counterNum++;
            System.out.printf("%-3s %-20s %.2f%n",counterNum, purchase.getItemName(), purchase.getPrice());
        }
    }

    //Метод для поиска элемента в списке по наименованию
    public static void search(ArrayList <Purchase> purchaseArrayList, String item) {
        boolean found = false;
        for (int i = 0; i < purchaseArrayList.size(); i++) {

            Purchase purchase = purchaseArrayList.get(i);

            if (purchase.getItemName().equalsIgnoreCase(item)) {
                System.out.println("Товар " + item + " находится в списке под номером " + (i + 1));
                found = true;
                //break; в списке есть повторяющийся товар для проверки такого случая
            }
        }
        if (!found) {
            System.out.println("Введенного товара нет в списке");
        }
    }

    //Метод для удаления элемента из списка по наименованию
    public static void deleteItem(ArrayList <Purchase> purchaseArrayList, String itemDel) {

        boolean foundToDel = false;
        int counter = 0;

        Iterator <Purchase> iterator = purchaseArrayList.iterator();

        while (iterator.hasNext()) {
            Purchase purchase = iterator.next();
            counter++;
            if (purchase.getItemName().equalsIgnoreCase(itemDel)) {
                iterator.remove();
                System.out.println("Товар " + itemDel + " удален из списка c позиции " + counter);
                foundToDel = true;
                // break; // Если считать, что в списке только один элемент с искомым названием
            }
        }
        if (!foundToDel) {
            System.out.println("Товара с названием " + itemDel + " нет в списке");
        }
    }


    public static void line() {
        System.out.println("----------------------------------");
    }
}
