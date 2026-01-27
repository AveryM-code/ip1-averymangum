package edu.brandeis.cosi103a.ip1;
import org.junit.Test;
import static org.junit.Assert.assertEquals;   
import java.util.ArrayList; 
import java.util.Arrays; 

public class PlayerGameTest {
    Card c1 = new Card("Card 1", 1, 1, CardType.CRYPTO);
    Card c2 = new Card("Card 2", 2, 2, CardType.CRYPTO);
    Card c3 = new Card("Card 3", 3, 3, CardType.AUTOMATION);
    Game g = new Game(new ArrayList<Card>(Arrays.asList(c3)),
                      new ArrayList<String>(Arrays.asList("Claire")),
                      new ArrayList<Card>(Arrays.asList(c1,c2)), 2);
    
    @Test
    public void playerConstructorTest() {
        assertEquals("Claire", g.players.get(0).name);
        assertEquals(0, g.players.get(0).deck.deckValue(CardType.CRYPTO));
    }

    @Test
    public void gameConstructorTest() {
        assertEquals(1, g.box.size());
        assertEquals("Card 3", g.box.get(0).name);
        assertEquals(1, g.players.size());
        assertEquals(2, g.HAND_SIZE);
    }

    @Test(expected=Error.class)
    public void playInvalidIndexErrorTest() {
        g.players.get(0).play(5);
    }

    @Test(expected=Error.class)
    public void buyInvalidIndexErrorTest() {
        g.players.get(0).buy(5);
    }

    @Test
    public void playAndBuyTest() {
        g.players.get(0).play(0);
        g.players.get(0).play(0);
        g.players.get(0).buy(0);
        assertEquals(3, g.players.get(0).deck.deckValue(CardType.AUTOMATION));
        assertEquals(0, g.box.size());
    }
    @Test
    public void playerValueTest() {
        g.players.get(0).play(0);
        g.players.get(0).play(0);
        g.players.get(0).buy(0);
        assertEquals(3, g.players.get(0).playerValue(CardType.CRYPTO));
        assertEquals(3, g.players.get(0).playerValue(CardType.AUTOMATION));
    }
}