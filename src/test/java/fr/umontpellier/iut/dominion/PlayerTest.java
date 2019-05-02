package fr.umontpellier.iut.dominion;

import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.common.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Game minimalGame;
    private Player p;

    @BeforeEach
    void disableConsole() {

        System.setOut(new PrintStream(new OutputStream() {

            @Override
            public void write(int arg0) {

            }
        }));

    }

    @BeforeEach
    void setUp() {
        String[] playerNames = new String[]{"Toto", "Titi", "Tutu"};
        minimalGame = new Game(playerNames, new String[0]);
        p = minimalGame.getPlayer(0);

    }


    @Test
    void testPlayerConstructorHand() {
        assertEquals(5, p.getHand().size());
    }


    @Test
    void testPlayerConstructorDraw() {
        assertEquals(5, p.getDraw().size());
    }


    @Test
    void testIncrementActions() {
        p.incrementActions(2);
        assertEquals(2, p.getNumberOfActions());
        p.incrementActions(-1);
        assertEquals(1, p.getNumberOfActions());
    }


    @Test
    void testIncrementMoney() {
        p.incrementMoney(2);
        assertEquals(2, p.getMoney());
        p.incrementMoney(-1);
        assertEquals(1, p.getMoney());
    }


    @Test
    void testIncrementBuys() {
        p.incrementBuys(2);
        assertEquals(2, p.getNumberOfBuys());
        p.incrementBuys(-1);
        assertEquals(1, p.getNumberOfBuys());
    }


    @Test
    void testDrawCardSimple() {
        p.addToDraw(new Copper());
        p.addToDraw(new Copper());

        int drawSize = p.getDraw().size();
        assertEquals(p.drawCard().getName(), "Copper");
        assertEquals(drawSize - 1, p.getDraw().size());
    }


    @Test
    void testDrawCardEmptyDraw() {
        p.getDiscard().clear();
        p.getDraw().clear();
        p.getDiscard().add(new Copper());
        p.getDiscard().add(new Copper());
        p.getDiscard().add(new Copper());

        assertEquals("Copper", p.drawCard().getName());
        assertEquals(0, p.getDiscard().size());
        assertEquals(2, p.getDraw().size());
    }


    @Test
    void testDrawCardEmptyDrawEmptyDiscard() {
        p.getDiscard().clear();
        p.getDraw().clear();

        assertNull(p.drawCard());
    }


    @Test
    void testCardsInHand() {
        //test que le getCardsInHand renvoie bien une liste indépendante
        p.getHand().clear();
        p.addToHand(new Copper());
        p.addToHand(new Copper());
        ListOfCards l = new ListOfCards(p.getHand());
        assertNotSame(p.getHand(), l);
        assertEquals(p.getHand(), l);
    }


    @Test
    void testTotalCards() {
        assertEquals(10, p.getAllCards().size());
        p.getHand().add(new Copper());
        p.getDraw().add(new Curse());
        p.getInPlay().add(new Copper());
        p.getDiscard().add(new Estate());
        assertEquals(14, p.getAllCards().size());
    }


    @Test
    void testVictoryPoints() {
        assertEquals(3, p.getVictoryPoints());

        p.getHand().add(new Copper());
        p.getDraw().add(new Copper());
        p.getInPlay().add(new Copper());
        p.getDiscard().add(new Copper());
        assertEquals(3, p.getVictoryPoints());

        p.getHand().add(new Estate());
        p.getDraw().add(new Duchy());
        p.getInPlay().add(new Province());
        p.getDiscard().add(new Curse());
        assertEquals(12, p.getVictoryPoints());
    }


    @Test
    void testPlayCardValid() {

        p.getHand().add(new Copper());
        int hand_size = p.getHand().size();
        p.playCard("Copper");
        assertEquals(hand_size - 1, p.getHand().size());
        assertEquals(1, p.getInPlay().size());
    }


    @Test
    void testPlayCardInvalid() {

        p.getHand().add(new Copper());
        int hand_size = p.getHand().size();
        p.playCard("Blop");
        assertEquals(hand_size, p.getHand().size());
        assertEquals(0, p.getInPlay().size());
    }


    @Test
    void testGain() {
        int discard_size = p.getDiscard().size();
        p.gainFromSupply("Province");
        assertEquals(discard_size + 1, p.getDiscard().size());
        assertEquals("Province", p.getDiscard().get(0).getName());
        for (int i = 0; i < 11; i++) {
            assertNotNull(minimalGame.removeFromSupply("Province"));
        }
        assertNull(minimalGame.removeFromSupply("Province"));
    }


    @Test
    void testBuyCard() {
        p.incrementMoney(8);
        p.incrementBuys(1);

        Card c = p.buyCard("Province");
        assertEquals("Province", c.getName());
        assertNotNull(p.getDiscard().getCard("Province"));
        assertEquals(0, p.getNumberOfBuys());
        assertEquals(0, p.getMoney());
    }


    @Test
    void testBuyCardTooExpensive() {
        p.incrementMoney(7);
        p.incrementBuys(1);

        Card c = p.buyCard("Province");
        assertNull(c);
        assertNull(p.getDiscard().getCard("Province"));
        assertEquals(1, p.getNumberOfBuys());
        assertEquals(7, p.getMoney());
    }


    @Test
    void testBuyCardNoBuys() {
        p.incrementMoney(8);
        p.incrementBuys(0);

        Card c = p.buyCard("Province");
        assertNull(c);
        assertNull(p.getDiscard().getCard("Province"));
        assertEquals(0, p.getNumberOfBuys());
        assertEquals(8, p.getMoney());
    }
}