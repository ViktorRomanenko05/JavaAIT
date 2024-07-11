package projectJava;

public enum GameType {
    CARD("Card game"),
    ROULETTE ("Roulette game"),
    SLOT ("Slot game"),
    KENO ("Keno game");

    private final String description;

    GameType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
