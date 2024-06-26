package homeworkLesson43;

import java.util.Objects;

public class Country implements Comparable<Country> {

    private String name;
    private String capital;
    private int population;
    private double area;

    public Country(String name, String capital, int population, double area) {
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(capital, country.capital);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(capital);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", area=" + area +
                '}';
    }

    @Override
    public int compareTo(Country country) {
        return name.compareTo(country.name);
    }
}
