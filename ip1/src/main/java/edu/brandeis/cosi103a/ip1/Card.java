package edu.brandeis.cosi103a.ip1;

public class Card {
    // Card information stored here
    final String name;
    final int cost;
    final int value;
    final CardType type;
    Card (String name, int cost, int value, CardType type) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.type = type;
    }

    // Create a new card with the same information as this one
    public Card copy() {
        return new Card(this.name, this.cost, this.value, this.type);
    }

    // toString implementation
    public String toString() {
        return this.name;
    }
}

// Enum to differentiate the two main card types of the game
enum CardType {AUTOMATION, CRYPTO};