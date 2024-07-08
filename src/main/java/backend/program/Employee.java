package backend.program;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class Employee extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    private String position;
    private LocalDate dateOfHire = LocalDate.now(); // Дата инициализируется для каждого нового объекта текущей датой
    private HashMap<String, Auto> sales = new HashMap<>();

    public Employee(String name, String password, String phoneNumber, String address, String eMail, int id, String position) {
        super(name, password, phoneNumber, address, eMail, id);
        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException("Position cannot be null or empty");
        }
        this.position = position;
    }

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException("Position cannot be null or empty");
        }
        this.position = position;
    }

    public HashMap<String, Auto> getSales() {
        return new HashMap<>(sales);
    }

    // Метод для добавления продажи в список сотрудника
    public void addSale(Auto auto) {
        if (auto == null) {
            LOGGER.error("Object \"Auto\" is null");
            return;
        }
        Auto previousAuto = sales.put(auto.getVinCode(), auto);
        if (previousAuto == null) {
            LOGGER.info("Auto was added to sales of " + this.getName());
        } else {
            LOGGER.info("Auto was added earlier");
        }
    }

    @Override
    public String toString() {
        return ("Employee ID: " + this.getId() + "\n" +
                "Name:        " + this.getName() + "\n" +
                "Phone:       " + this.getPhoneNumber() + "\n" +
                "E-mail:      " + this.getEmail() + "\n" +
                "Position:    " + this.getPosition() + "\n" +
                "Hired Date:  " + this.getDateOfHire());
    }
}
