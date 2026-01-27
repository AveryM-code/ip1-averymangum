package edu.brandeis.cosi103a.ip1;
import java.util.ArrayList;
import java.util.Arrays;

public class AutomationTheGame {
    public static void main(String[] args) {
        Card METHOD = new Card("Method",2,1,CardType.AUTOMATION);
        Card MODULE = new Card("Module",5,3,CardType.AUTOMATION);
        Card FRAMEWORK = new Card("Framework",8,6,CardType.AUTOMATION);
        Card BITCOIN = new Card("Bitcoin",0,1,CardType.CRYPTO);
        Card ETHEREUM = new Card("Ethereum",3,2,CardType.CRYPTO);
        Card DOGECOIN = new Card("Dogecoin",6,3,CardType.CRYPTO);

        ArrayList<Card> BOX = new ArrayList<Card>();
        for (int i = 0; i < 14-3*2; i++) { BOX.add(METHOD.copy()); } 
        for (int i = 0; i < 8; i++) { BOX.add(MODULE.copy()); } 
        for (int i = 0; i < 8; i++) { BOX.add(FRAMEWORK.copy()); } 
        for (int i = 0; i < 60-7*2; i++) { BOX.add(BITCOIN.copy()); } 
        for (int i = 0; i < 40; i++) { BOX.add(ETHEREUM.copy()); } 
        for (int i = 0; i < 30; i++) { BOX.add(DOGECOIN.copy()); } 

        ArrayList<Card> STARTING_DECK = new ArrayList<Card>();
        for (int i = 0; i < 3; i++) { STARTING_DECK.add(METHOD.copy()); } 
        for (int i = 0; i < 7; i++) { STARTING_DECK.add(BITCOIN.copy()); } 

        ArrayList<String> playerList = new ArrayList<String>(Arrays.asList("Alice", "Bob"));

        Game game = new Game(BOX, playerList , STARTING_DECK, 5);
        game.play();
    }
}
