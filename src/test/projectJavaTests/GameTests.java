package projectJavaTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import projectJava.Game;
import projectJava.GameManager;
import projectJava.GameType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTests {
GameManager gameManager;
    @BeforeEach
    public void setUp() {
        gameManager = new GameManager();
        gameManager.deserializeGames();
    }

    @Test
    @DisplayName("Addition of duplicate game")
    public void testDuplicateGame() {
        gameManager.createNewGame("Poker", GameType.CARD, 10.0, 1000.0);
        gameManager.createNewGame("Poker", GameType.CARD, 20.0, 2000.0);

        Game game = gameManager.getGames().get("Poker");
        assertEquals(20, game.getMinimalBet());
        assertEquals(2000, game.getMaximalBet());
    }

    @Test
    @DisplayName("Addition of game with invalid minimal bet")
    public void testInvalidMinimalBet() {
        int oldSize = gameManager.getGames().size();
        gameManager.createNewGame("Bad Game", GameType.KENO, -10.0, 1000.0);
        assertEquals(oldSize, gameManager.getGames().size());
    }
}
