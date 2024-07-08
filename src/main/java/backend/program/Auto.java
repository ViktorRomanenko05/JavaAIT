package backend.program;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Auto implements Serializable {

    private String vinCode; // уникальный ID - код VIN
    private String brand; // марка машины
    private String model; // модель машины
    private int price; // цена в центах, чтобы избежать погрешности
    private int yearOfProduction; // год изготовления
    private String shortCharacteristics; // краткая инфа по машине
    private String fullCharacteristics; // полная инфа по машине
    private String color; // цвет машины
    private LocalDate date; // изначально по умолчанию дата продажи равна null


    // НАДО ДОБАВИТЬ ФОТО МАШИНЫ


    public Auto(String vinCode, String brand, String model, int price, int yearOfProduction,
                String shortCharacteristics, String fullCharacteristics, String color) {
        this.vinCode = vinCode;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.shortCharacteristics = shortCharacteristics;
        this.fullCharacteristics = fullCharacteristics;
        this.color = color;
        this.date = null;
    }

    public String getVinCode() {
        return vinCode;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    } // изначально цена в евроцентах. делим на 100, чтобы избежать погрешности

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public String getShortCharacteristics() {
        return shortCharacteristics;
    }

    public String getFullCharacteristics() {
        return fullCharacteristics;
    }

    public String getColor() {
        return color;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public void setShortCharacteristics(String shortCharacteristics) {
        this.shortCharacteristics = shortCharacteristics;
    }

    public void setFullCharacteristics(String fullCharacteristics) {
        this.fullCharacteristics = fullCharacteristics;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n-----------------------------------------\n");
        sb.append("               Auto Details              \n");
        sb.append("-----------------------------------------\n");
        sb.append("Brand:                ").append(brand).append("\n");
        sb.append("Model:                ").append(model).append("\n");
        sb.append("Color:                ").append(color).append("\n");
        sb.append("Year of Production:   ").append(yearOfProduction).append("\n");
        sb.append("Price:                $").append(price).append("\n");
        sb.append("VIN Code:             ").append(vinCode).append("\n");
        sb.append("Short Characteristics:\n").append(shortCharacteristics).append("\n");
        sb.append("Full Characteristics:\n").append(fullCharacteristics).append("\n");
        sb.append("Date:                 ").append(date).append("\n");
        sb.append("-----------------------------------------\n");
        return sb.toString();
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Auto auto = (Auto) object;
        return Objects.equals(vinCode, auto.vinCode);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), vinCode);
    }
}



