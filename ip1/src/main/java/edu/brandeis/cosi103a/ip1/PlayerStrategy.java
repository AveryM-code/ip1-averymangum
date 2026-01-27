package edu.brandeis.cosi103a.ip1;
import java.util.ArrayList;

public class PlayerStrategy {
    PlayerStrategy () {
    }

    public PlayerAction nextAction(ArrayList<Card> hand, ArrayList<Card> played, ArrayList<Card> box) {
        // Do we still have crypto cards to play? Play all of them.
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).type == CardType.CRYPTO) {
                return new PlayerAction(PlayerActionType.PLAY, i);
            }
        }
        // Scan the box. Buy the highest-value crypto card.
        // If there are no crypto cards (or we can't afford any),
        // buy the highest-value automation card.
        // If there are no cards we can afford (or no cards at all), return
        // an empty buy (IE end turn without buying).
        int value = this.cardValue(played, CardType.CRYPTO);
        int toBuy = -1;
        for (int i = 0; i < box.size(); i++) {
            Card toBuyCard = toBuy == -1 ? null : box.get(toBuy);
            Card currCard = box.get(i);
            // Prefer crypto card if available and affordable
            if ((toBuyCard == null ||
              (currCard.type == CardType.CRYPTO 
              && toBuyCard.type == CardType.AUTOMATION)
              )
              && currCard.cost <= value) {
                toBuy = i;
            } 
            // Select highest-value card that is affordable
            else {
                if ((toBuyCard == null || currCard.value > toBuyCard.value)
                  && currCard.cost <= value) {
                    toBuy = i;
                }
            }
        }
        //System.out.println(value + " " + toBuy);
        return new PlayerAction(PlayerActionType.BUY, toBuy);
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
}

enum PlayerActionType {PLAY, BUY};

class PlayerAction {
    public PlayerActionType type;
    public int index;
    public PlayerAction(PlayerActionType type, int index) {
        this.type = type;
        this.index = index;
    }
}