package projectJava;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static java.lang.Long.parseLong;

public class Transaction implements Serializable {

    private Person client;
    private Game game;
    private double bet;
    private double win;
    private long id;
    private LocalDateTime date;

    public Transaction(Person client, Game game, double bet, double win) {
        this.client = client;
        this.game = game;
        this.bet = bet;
        this.win = win;
        this.id = parseLong(generateId());
    }

    // Метод для генерации ID
    private String generateId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return LocalDateTime.now().format(formatter);
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
