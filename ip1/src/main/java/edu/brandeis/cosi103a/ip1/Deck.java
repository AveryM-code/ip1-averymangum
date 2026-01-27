package edu.brandeis.cosi103a.ip1;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    // A deck contains a draw pile and a discard pile
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;
    // Constructor
    Deck(ArrayList<Card> cards) {
        this.drawPile = cards;
        this.drawPile = this.shuffle(this.drawPile);
        //System.out.println("d " + this.drawPile.size());
        this.discardPile = new ArrayList<Card>();
    }

    // Returns a shuffled version of the input card list
    private ArrayList<Card> shuffle(ArrayList<Card> cards) {
        ArrayList<Card> toReturn = new ArrayList<Card>();
        Random r = new Random();
        //System.out.println("a " + cards.size() + ", " + toReturn.size());
        while (cards.size() > 0) {
            int i = r.nextInt(cards.size());
            toReturn.add(cards.get(i));
            cards.remove(i);
        }
        //System.out.println("b " + cards.size() + ", " + toReturn.size());
        return toReturn;
    }

    // Sends a card to the discard pile
    public void discard(Card card) {
        this.discardPile.add(card);
    }

    // Returns the next card in the draw pile, and removes it from the draw pile
    // If draw pile empty, discard pile is shuffled into the draw pile and set as empty
    public Card draw() {
        if (this.drawPile.size() == 0) {
            this.drawPile = this.shuffle(this.discardPile);
            this.discardPile = new ArrayList<Card>();
        }
        if (this.drawPile.size() == 0) {
            throw new Error("No cards left in deck!");
        }
        Card toReturn = this.drawPile.get(0);
        this.drawPile.remove(0);
        return toReturn;
    }

    // Counts the value of all cards in the deck of a specified type
    public int deckValue(CardType type) {
        int toReturn = 0;
        for (Card card : this.drawPile) {
            if (card.type == type) {
                toReturn += card.value;
            }
        }
        for (Card card : this.discardPile) {
            if (card.type == type) {
                toReturn += card.value;
            }
        }
        return toReturn;
    }
}