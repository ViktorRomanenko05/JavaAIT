package home.app;

public class MyLife2 {
        public static void main(String[] args) {
            int numberX = 10;
            int numberY = 10;
            int numberZ = 30;
            boolean isNumberYMiddle;
            if (numberY > numberX && numberY < numberZ)
            {
                isNumberYMiddle = true;
            }
            else
            {
                isNumberYMiddle = false;
            }
            System.out.println(isNumberYMiddle);

        }
    }