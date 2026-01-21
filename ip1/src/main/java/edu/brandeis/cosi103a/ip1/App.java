package edu.brandeis.cosi103a.ip1;
import java.util.Scanner;
import java.util.Random;

public class App {
    private static final int TOTAL_TURNS = 10;
    private static final int MAX_REROLLS = 2;
    private static final int DIE_SIDES = 6;

    private final Player player1;
    private final Player player2;
    private final Scanner scanner;
    private final Random random;

    public App() {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public static void main(String[] args) {
        App game = new App();
        game.play();
    }

    /**
     * Main game loop handling the turns for both players.
     */
    public void play() {
        System.out.println("Welcome to the Dice Game!");
        System.out.println("Each player gets 10 turns. You can re-roll up to 2 times per turn.");

        for (int turn = 1; turn <= TOTAL_TURNS; turn++) {
            System.out.println("\n--- Round " + turn + " ---");
            processTurn(player1);
            processTurn(player2);
        }

        displayFinalResults();
    }

    /**
     * Handles the logic for a single player's turn.
     * Modularized to allow for easier testing of turn logic.
     */
    public void processTurn(Player player) {
        int currentRoll = rollDie();
        int reRollsUsed = 0;

        while (reRollsUsed < MAX_REROLLS) {
            System.out.printf("%s, you rolled a %d. (Re-rolls used: %d/%d)%n", 
                               player.getName(), currentRoll, reRollsUsed, MAX_REROLLS);
            System.out.print("Do you want to re-roll? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("y")) {
                currentRoll = rollDie();
                reRollsUsed++;
            } else {
                break;
            }
        }

        player.addToScore(currentRoll);
        System.out.printf("%s finishes turn with a %d. Total score: %d%n", 
                           player.getName(), currentRoll, player.getScore());
    }

    public int rollDie() {
        return random.nextInt(DIE_SIDES) + 1;
    }

    private void displayFinalResults() {
        System.out.println("\n============================");
        System.out.println("        GAME OVER          ");
        System.out.println("============================");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());

        if (player1.getScore() > player2.getScore()) {
            System.out.println("Winner: " + player1.getName() + "!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("Winner: " + player2.getName() + "!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    /**
     * Modular Player class to track scores and names.
     */
    static class Player {
        private final String name;
        private int score;

        public Player(String name) {
            this.name = name;
            this.score = 0;
        }

        public void addToScore(int points) {
            this.score += points;
        }

        public String getName() { return name; }
        public int getScore() { return score; }
    }
}