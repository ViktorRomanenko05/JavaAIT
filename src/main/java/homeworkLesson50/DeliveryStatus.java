package homeworkLesson50;

public enum DeliveryStatus {
    PENDING("Ожидает отправки"),
    DISPATCHED("Отправлен"),
    IN_TRANSIT("В пути"),
    OUT_FOR_DELIVERY("Доставляется"),
    DELIVERED("Доставлен"),
    FAILED_ATTEMPT("Неудачная попытка доставки"),
    RETURNED("Возвращен");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
