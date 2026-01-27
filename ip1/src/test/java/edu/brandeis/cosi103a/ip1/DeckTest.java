package edu.brandeis.cosi103a.ip1;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;   
import java.util.ArrayList; 
import java.util.Arrays; 

public class DeckTest {
    Card c1 = new Card("Card 1", 1, 1, CardType.CRYPTO);
    Card c2 = new Card("Card 2", 2, 2, CardType.AUTOMATION);
    Card c3 = new Card("Card 3", 3, 3, CardType.AUTOMATION);
    Deck d = new Deck(new ArrayList<Card>(Arrays.asList(c1, c2, c3)));

    @Test
    public void drawTest() {
        ArrayList<Card> drawn = new ArrayList<Card>();
        drawn.add(d.draw());
        drawn.add(d.draw());
        drawn.add(d.draw());
        assertTrue(drawn.get(0).name == "Card 1" 
                || drawn.get(1).name == "Card 1" 
                || drawn.get(2).name == "Card 1");
    }

    public void reshuffleTest() {
        ArrayList<Card> drawn = new ArrayList<Card>();
        drawn.add(d.draw());
        drawn.add(d.draw());
        drawn.add(d.draw());
        assertTrue(drawn.get(0).name == "Card 1" 
                || drawn.get(1).name == "Card 1" 
                || drawn.get(2).name == "Card 1");
    }
    
    @Test
    public void deckValueTest() {
        assertEquals(1, d.deckValue(CardType.CRYPTO));
        assertEquals(5, d.deckValue(CardType.AUTOMATION));
    }

    
    @Test(expected=Error.class)
    public void emptyDeckErrorTest() {
        d.draw();
        d.draw();
        d.draw();
        d.draw();
    }
}