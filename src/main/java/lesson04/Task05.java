package lesson04;

public class Task05 {
    public static void main(String[] args) {
        int song01 = 500;
        int song02 = 550;
        int song03 = 600;
        int totalTime = song01 + song02 + song03;
        int minutes = totalTime / 60;
        int secunds = totalTime % 60;
        System.out.println("Общее время: " + minutes + " минут " + secunds + " секунд");
    }
}
