package edu.brandeis.cosi103a.ip1;
import org.junit.Test;
import static org.junit.Assert.assertEquals;   
import java.util.ArrayList; 
import java.util.Arrays; 

public class PlayerStrategyTest {
    Card c1 = new Card("Card 1", 1, 1, CardType.CRYPTO);
    Card c2 = new Card("Card 2", 2, 2, CardType.CRYPTO);
    Card c3 = new Card("Card 3", 3, 3, CardType.AUTOMATION);
    PlayerStrategy ps = new PlayerStrategy();
    
    @Test
    public void playCardTest() {
        PlayerAction pa = ps.nextAction(
            new ArrayList<Card>(Arrays.asList(c1,c2)),
            new ArrayList<Card>(),
            new ArrayList<Card>(Arrays.asList(c3))
        );
        assertEquals(PlayerActionType.PLAY, pa.type);
        assertEquals(0, pa.index);
    }

    @Test
    public void buyCardTest() {
        PlayerAction pa = ps.nextAction(
            new ArrayList<Card>(),
            new ArrayList<Card>(Arrays.asList(c1,c2)),
            new ArrayList<Card>(Arrays.asList(c3))
        );
        assertEquals(PlayerActionType.BUY, pa.type);
        assertEquals(0, pa.index);
    }

    @Test
    public void buyNothingTest() {
        PlayerAction pa = ps.nextAction(
            new ArrayList<Card>(),
            new ArrayList<Card>(Arrays.asList(c1)),
            new ArrayList<Card>(Arrays.asList(c3))
        );
        assertEquals(PlayerActionType.BUY, pa.type);
        assertEquals(-1, pa.index);
    }
}