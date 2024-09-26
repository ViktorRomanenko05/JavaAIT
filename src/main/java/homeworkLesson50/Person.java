package homeworkLesson50;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class Person {

    private static final long serialVersionUID = 1L;

private long id;
String eMail;
private String name;
private LocalDateTime birthday;
private String phoneNumber;
private String address;
private LocalDateTime registrationDate;
private int sale;
private List<Order> ordersHistory;

    public Person(long id, String eMail, String name, LocalDateTime birthday, String phoneNumber, String address, LocalDateTime registrationDate, int sale, List<Order> ordersHistory) {
        this.id = parseLong(generateId());
        this.eMail = eMail;
        this.name = name;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = LocalDateTime.now();
        this.sale = 0;
        this.ordersHistory = ordersHistory;
    }


    // Метод для генерации ID
    private String generateId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return LocalDateTime.now().format(formatter);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String geteMail() {
        return eMail;
    }

    public List<Order> getOrdersHistory() {
        return new ArrayList<>(ordersHistory);
    }

    public void setOrdersHistory(List<Order> ordersHistory) {
        this.ordersHistory = ordersHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(eMail, person.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(eMail);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", registrationDate=" + registrationDate +
                ", sale=" + sale +
                ", ordersHistory=" + ordersHistory +
                '}';
    }

    //Добавление покупки пользователю
    public  void addPurchaseToMe (Product item){
        if (item != null)

    }
}
