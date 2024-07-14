package homeworkLesson50;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class Order {

    private static final long serialVersionUID = 1L;

    private List<Product> productsInOrder;
    private Person customer;
    private LocalDateTime dateOfOrder;
    private OrderStatus orderStatus;
    private DeliveryStatus deliveryStatus;
    private long orderID;

    public Order(List<Product> productsInOrder, Person customer) {
        this.productsInOrder = productsInOrder;
        this.customer = customer;
        this.dateOfOrder = LocalDateTime.now();
        this.orderStatus = OrderStatus.PENDING;
        this.deliveryStatus = DeliveryStatus.PENDING;
        this.orderID = parseLong(generateId());
    }

    // Метод для генерации ID
    private String generateId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return LocalDateTime.now().format(formatter);
    }

    public List<Product> getProductsInOrder() {
        return new ArrayList<>(productsInOrder);
    }

    public Person getCustomer() {
        return customer;
    }

    public LocalDateTime getDateOfOrder() {
        return dateOfOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderID);
    }

    @Override
    public String toString() {
        return "Order{" +
                "productsInOrder=" + productsInOrder +
                ", customer=" + customer +
                ", dateOfOrder=" + dateOfOrder +
                ", orderStatus=" + orderStatus +
                ", deliveryStatus=" + deliveryStatus +
                ", orderID=" + orderID +
                '}';
    }
}
