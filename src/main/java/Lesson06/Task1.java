package Lesson06;

public class Task1 {
    public static void main(String[] args) {

        int amount = 999;
        int experience = 5;
        int sale;
        int salePlus;
        int totalSale;

        boolean byerSale;

        if (amount >= 1000) {
            sale = 10;
        } else sale = 0;

        if (experience >= 5) {
            salePlus = 5;
        } else {
            salePlus = 0;
        }
        totalSale = sale + salePlus;
        System.out.println("Общая скидка= " + totalSale);
    }
}