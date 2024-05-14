package homeworkLesson32;

public class MethodCompare {

    public int max(int a, int b) {
        if (a == b) {
            System.out.println("a = b");
            // Возвращаем любое из значений, так как они равны
            return a;
        } else if (a > b) {
            return a;
        } else {
            return b;
        }
    }


}
