package edu.brandeis.cosi103a.ip1;
import org.junit.Test;
import static org.junit.Assert.assertEquals;    

public class CardTest {
    Card c = new Card("Punch card", 12, 1, CardType.AUTOMATION);

    @Test
    public void constructorTest() {
        assertEquals("Punch card", c.name);
        assertEquals(12, c.cost);
        assertEquals(1, c.value);
        assertEquals(CardType.AUTOMATION, c.type);
    }

    @Test
    public void toStringTest() {
        assertEquals("Punch card", c.toString());
    }

    @Test
    public void copyTest() {
        Card c2 = c.copy();
        assertEquals("Punch card", c2.name);
        assertEquals(12, c2.cost);
        assertEquals(1, c2.value);
        assertEquals(CardType.AUTOMATION, c2.type);
    }
}