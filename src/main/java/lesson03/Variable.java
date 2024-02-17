package lesson03;

public class Variable {
    //-128 до 127 байт
    static byte age = 27;

    //-32768 до 32767
    static short preis = 10000;

    //-2147483648 до 2147483647
    static int number = 200000;

    //-9223372036854775809 до 9223372036854775807
    static long bigNumber = 98006884877L;

    //дробные числа с плавающей тчкой маленькие
    static float euroPrice = 0.85f;

    //дробные числа много знаков после запятой
    static double dollarPreis = 0.85;

    //символы
    static char charLetter = 'A';

    // boolean ... = true или false;
    //логические
    static boolean result;

    static String text = "Hallo";

    //текст несколько символов
    public static void main(String[] args) {
        System.out.println("Возраст " + age);
        System.out.println("Курс EUR/USD: " + euroPrice);
        System.out.println(charLetter);
        System.out.println(text);
        System.out.println(result);
    }

}
