package homeworkLesson42;

public class Booking {
    private String customerName;
    private int roomNumber;
    private String roomType;
    private String checkInDate;
    private String checkOutDate;

    public Booking(String customerName, int roomNumber, String roomType, String checkInDate, String checkOutDate) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "Имя клиента: " + customerName +
                ", номер в отеле: " + roomNumber +
                ", тип номера: " + roomType +
                ", заезд: " + checkInDate +
                ", выезд: " + checkOutDate +
                '}';
    }
}
