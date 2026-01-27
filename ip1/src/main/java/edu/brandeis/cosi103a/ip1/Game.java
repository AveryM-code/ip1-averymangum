package edu.brandeis.cosi103a.ip1;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public ArrayList<Card> box;
    public ArrayList<Player> players;
    public int HAND_SIZE;
    // Initialize game with n players
    Game(ArrayList<Card> box, ArrayList<String> playerNames, ArrayList<Card> startingDeck, int HAND_SIZE) {
        this.box = box;
        this.HAND_SIZE = HAND_SIZE;
        // Initialize the players
        this.players = new ArrayList<Player>();
        for (int i = 0; i < playerNames.size(); i++) {
            ArrayList<Card> deck = new ArrayList<Card>();
            // Deep copy the starting deck to create new cards
            for (Card card: startingDeck) {
                deck.add(new Card(card.name, card.cost, card.value, card.type));
            }
            // Add player
            this.players.add(new Player(
                playerNames.get(i), deck, this.HAND_SIZE, new PlayerStrategy(), this));
        }
    }

    public void play() {
        // Start with random player
        Random r = new Random();
        int p = r.nextInt(players.size()); // Current player
        // Cycle players until box runs out of framework cards
        int frameworkCount = 0;
            for (Card card: box) { if (card.name == "Framework") { frameworkCount++; } }
        while (frameworkCount > 0) {    
            // Player p takes turn
            players.get(p).takeTurn();
            // Player p+1 is the next player
            p = (p + 1) % players.size();
            // Count remaining framework cards
            frameworkCount = 0;
            for (Card card: box) { if (card.name == "Framework") { frameworkCount++; } }
        }
        int winningScore = 0;
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).name + "'s score : " + players.get(i).playerValue(CardType.AUTOMATION));
            if (players.get(i).playerValue(CardType.AUTOMATION) > winningScore) { winningScore = players.get(i).playerValue(CardType.AUTOMATION); }
        }
        System.out.print("Winners: ");
        for (Player pl: players) {
            if (pl.playerValue(CardType.AUTOMATION) == winningScore) {
                System.out.print(pl.name + " ");
            }
        }
    }
}
