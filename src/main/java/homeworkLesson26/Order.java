package homeworkLesson26;

public class Order {
    public static void main(String[] args) {

        OrderStatus status = OrderStatus.SHIPPED;

        boolean canCancel = status.canCancel(status);

        System.out.println("Status of your order: " + status);
        System.out.println("Order can be cancelled? - " + canCancel);
    }
}
