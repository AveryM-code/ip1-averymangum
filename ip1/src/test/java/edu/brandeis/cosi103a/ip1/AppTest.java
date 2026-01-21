package edu.brandeis.cosi103a.ip1;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    @DisplayName("Player should initialize with zero score")
    void testPlayerInitialization() {
        App.Player player = new App.Player("Test Player");
        assertEquals(0, player.getScore(), "Initial score should be 0");
    }

    @Test
    @DisplayName("Score should accumulate correctly after multiple turns")
    void testScoreAccumulation() {
        App.Player player = new App.Player("Scorer");
        player.addToScore(5);
        player.addToScore(3);
        player.addToScore(1);
        assertEquals(9, player.getScore(), "Score should be the sum of all rolls (5+3+1)");
    }

    @RepeatedTest(100)
    @DisplayName("Die roll should always be between 1 and 6")
    void testRollDieBounds() {
        App game = new App();
        int roll = game.rollDie();
        assertTrue(roll >= 1 && roll <= 6, "Roll " + roll + " is out of bounds (1-6)");
    }

    @Test
    @DisplayName("Player name should remain constant")
    void testPlayerNameImmutability() {
        App.Player player = new App.Player("Alice");
        assertEquals("Alice", player.getName());
    }
}
