package homeworkLesson50;

public enum OrderStatus {
    PENDING("Ожидает обработки"),
    PROCESSING("В обработке"),
    SHIPPED("Отправлен"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменен"),
    RETURNED("Возвращен");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}