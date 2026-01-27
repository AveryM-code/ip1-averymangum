package edu.brandeis.cosi103a.ip1;
import java.util.ArrayList;

public class Player {
    public String name; // The player's name
    public Deck deck; // The player's personal deck
    private ArrayList<Card> hand; // The player's current hand
    private ArrayList<Card> played; // The cards the player has played from their hand
    private int HAND_SIZE; // The max hand size, which is a constant
    public Game game; // The Game this Player is part of
    private PlayerStrategy strategy; // The player's strategy

    // Constructor for Player
    Player(String name, ArrayList<Card> cards, int HAND_SIZE, PlayerStrategy strategy, Game game) {
        this.name = name;
        // Create deck
        this.deck = new Deck(cards);
        // Initialize hand with HAND_SIZE cards; no cards are played yet
        this.HAND_SIZE = HAND_SIZE;
        this.hand = new ArrayList<Card>();
        this.played = new ArrayList<Card>();
        for (int i = 0; i < this.HAND_SIZE; i++) {
            this.hand.add(this.deck.draw());
        }
        this.strategy = strategy;
        this.game = game;
    }

    // Takes the player's turn according to the strategy
    public void takeTurn() {
        // Continue until buy phase is done
        boolean buyPhaseDone = false;
        while (!buyPhaseDone) {
            // Request an action from the strategy
            PlayerAction nextAction = this.strategy.nextAction(this.hand, this.played, this.game.box);
            //System.out.println(this.hand.size() + " " + nextAction.type);
            // Execute PLAY action
            if (nextAction.type == PlayerActionType.PLAY) {
                System.out.println(this.name + " plays " + this.hand.get(nextAction.index).name + "!");
                this.play(nextAction.index);
            } 
            // Execute BUY action
            else if (nextAction.type == PlayerActionType.BUY) {
                if (nextAction.index != -1) {
                    System.out.println(this.name + " buys " + this.game.box.get(nextAction.index).name + "!");
                    this.buy(nextAction.index);
                }
                buyPhaseDone = true;
            }
        }
        // Cleanup phase
        this.cleanup();
        System.out.println(this.name + " ends their turn!");
        //System.out.println("Turn done!");
    }

    // Play the card with index i in the hand
    public void play(int index) {
        // Throw error if this card does not exist
        if (index > this.hand.size()) {
            throw new Error("Requested card index " + index + " is too large for hand size " + this.hand.size() + "!");
        } else {
            Card toPlay = this.hand.get(index);
            this.played.add(toPlay);
            this.hand.remove(index);
        }
    }

    // Gets the summed value of all played cards of a certain type
    // (works for either crypto or automation cards, though this method
    // is only used with crypto cards in practice)
    public int cardValue(ArrayList<Card> cards, CardType type) {
        int toReturn = 0;
        for (Card card : cards) {
            if (card.type == type) {
                toReturn += card.value;
            }
        }
        return toReturn;
    }

    public void buy(int index) {
        // Throw error if the card does not exist
        if (index > this.game.box.size()) {
            throw new Error("Requested card index " + index + " is too large for box size " + this.game.box.size() + "!");
        } 
        else {
            // Find the card
            Card toBuy = this.game.box.get(index);
            // Compliance check - can the player buy the card?
            if (this.cardValue(this.played, CardType.CRYPTO) < toBuy.cost) {
                throw new Error("Requested card '" + toBuy.name + "' costs " + toBuy.cost + ", which is more than played value " + this.cardValue(this.played, CardType.CRYPTO) + "!");
            }
            else {
                // Immediately discard bought card
                //System.out.println(this.game.box);
                this.deck.discard(toBuy);
                this.game.box.remove(index);
            }
        }
    }

    // Executes the cleanup phase
    public void cleanup() {
        // Cleanup step 1: Discard entire current hand & played cards
        while (this.hand.size() > 0) {
            this.deck.discard(this.hand.get(0));
            this.hand.remove(0);
        }
        while (this.played.size() > 0) {
            this.deck.discard(this.played.get(0));
            this.played.remove(0);
        }
        // Cleanup step 2: Deal new hand
        for (int i = 0; i < this.HAND_SIZE; i++) {
            this.hand.add(this.deck.draw());
        }
    }

    public int playerValue(CardType type) {
        return this.deck.deckValue(type) + this.cardValue(this.hand, type) + this.cardValue(this.played, type);
    }
}
