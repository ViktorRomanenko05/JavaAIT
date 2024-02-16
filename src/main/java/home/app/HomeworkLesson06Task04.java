package home.app;
/*
Комбинированный налог: Создайте программу для расчёта налога, который зависит
от категории товара и региона покупателя. Налог на электронику 10%, на одежду 5%.
Если покупатель из региона A, применяется дополнительный налог 2%.
 */

import java.util.Scanner;

public class HomeworkLesson06Task04 {

    public static void main(String[] args) {


        System.out.println("Расчёт налога, начисляемого при покупке" +
                "\n \nВВЕДИТЕ ДАННЫЕ О ПОКУПКЕ\n");

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите код вашего региона:");
        String region = scan.next();

        System.out.println("Общая стоимость товаров из категории \"Электроника\" без учета налога:");
        float electro = scan.nextFloat();

        System.out.println("Общая стоимость товаров из категории \"Одежда\" без учета налога:");
        float clths = scan.nextFloat();

        scan.close();

        /*
        В данной задаче добавление регионального налога к общей сумме с уже учтёнными налогами
        по категориям товаров может привести к двойному налогообложению, поэтому корректным будет
        сначала вычислить налоговую базу и потом применить к ней необходимые налоговые ставки отдельно.
         */

        boolean check = region.equals("A") || region.equals("a") || region.equals("А") || region.equals("а");
        // Латиница || кириллица

        float taxBase = electro + clths; // Расчёт налоговой базы
        float sum;                       // Общая сумма покупки с учётом налогов
        float sumAddTax = 0f;            // Сумма дополнительного регионального налога
        float sumTax;                    // Общая сумма всех налогов
        byte addTax = 0;                // Ставка регионального налога

        float electroTax = electro * 0.1f;  // Сумма налога на покупки в категории "Электроника"
        float clthsTax = clths * 0.05f;     // Сумма налога на покупки в категории "Одежда"

        float electroWithTax = electro + electroTax; // Общая сумма с учетом налога в категории "Электроника"
        float clthsWithTax = clths + clthsTax;       // Общая сумма с учетом налога в категории "Одежда"

        if (check == false) {
            sum = electroWithTax + clthsWithTax;
        } else {
            sum = taxBase * 0.02f + electroWithTax + clthsWithTax;
            addTax = 2;
            sumAddTax = taxBase * 0.02f;
        }

        sumTax = sumAddTax + electroTax + clthsTax;

        System.out.println("\nОбщая сумма покупки: " + sum + " EUR");
        System.out.println("из них налог: " + sumTax + " EUR");
        System.out.println("_________________________________________________\n");
        System.out.println("Сумма в категории \"Электроника\": " + electroWithTax + " EUR");
        System.out.println("из них налог: " + electroTax + " EUR  (10%)\n");
        System.out.println("Сумма в категории \"Одежда\": " + clthsWithTax + " EUR");
        System.out.println("из них налог: " + clthsTax + " EUR   (5%)\n");
        System.out.println("Ставка регионального налога: " + addTax + "%");
        System.out.println("Сумма регионального налога: " + sumAddTax + " EUR");

    }
}
