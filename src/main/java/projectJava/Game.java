package projectJava;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private GameType type;
    double minimalBet;
    double maximalBet;

    public Game(String name, GameType type, double minimalBet, double maximalBet) {
        this.name = name;
        this.type = type;
        this.minimalBet = minimalBet;
        this.maximalBet = maximalBet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public double getMinimalBet() {
        return minimalBet;
    }

    public void setMinimalBet(double minimalBet) {
        this.minimalBet = minimalBet;
    }

    public double getMaximalBet() {
        return maximalBet;
    }

    public void setMaximalBet(double maximalBet) {
        this.maximalBet = maximalBet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
