package homeworkLesson26;

public enum OrderStatus {
    PENDING("Order is pending approval"),
    PROCESSING("Order is processing"),
    SHIPPED("Order was shipped"),
    DELIVERED("Order was delivered"),
    CANCELLED("Order is cancelled");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean canCancel(OrderStatus status){
        if(status == OrderStatus.PENDING || status == OrderStatus.PROCESSING){
            return true;
        }
        else return false;
    }
}
