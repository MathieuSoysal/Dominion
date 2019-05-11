package fr.umontpellier.iut.dominion.cards;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import fr.umontpellier.iut.dominion.IOGame;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.base.*;
import fr.umontpellier.iut.dominion.cards.common.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static fr.umontpellier.iut.dominion.TestUtils.hasCards;
import static org.junit.jupiter.api.Assertions.*;

class CardsTest3 {
    private IOGame game;
    private Player p0, p1, p2;

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
        String[] playerNames = new String[] { "Toto", "Titi", "Tutu" };
        game = new IOGame(playerNames, new String[0]);
        p0 = game.getPlayer(0);
        p1 = game.getPlayer(1);
        p2 = game.getPlayer(2);
    }

    @Test
    void testMoatReaction() {
        p0.getHand().add(new Moat());
        p1.getHand().add(new Witch());
        p2.getHand().add(new Moat());

        game.setInput("y", "n");
        p1.playCard("Witch");
        assertNull(p2.getDiscard().getCard("Curse"));
        assertNotNull(p0.getDiscard().getCard("Curse"));
    }

    @Test
    void testThroneRoom() {
        p1.getHand().add(new ThroneRoom());
        p1.getHand().add(new Village());

        Card c1 = p1.getDraw().get(0);
        Card c2 = p1.getDraw().get(1);

        game.setInput("Village");
        p1.playCard("Throne Room");

        assertEquals(4, p1.getNumberOfActions());
        assertTrue(p1.getHand().contains(c1));
        assertTrue(p1.getHand().contains(c2));
        assertEquals(7, p1.getHand().size());
    }

    @Test
    void testLibrary() {
        p1.getDraw().clear();
        p1.getHand().clear();
        p1.getHand().add(new Library());
        p1.getHand().add(new Duchy());
        p1.getHand().add(new Duchy());
        p1.getHand().add(new Duchy());
        p1.getHand().add(new Duchy());
        p1.getDraw().add(new Gold());
        p1.getDraw().add(new Village());
        p1.getDraw().add(new Festival());
        p1.getDraw().add(new Silver());
        p1.getDraw().add(new Silver());

        game.setInput("y", "n"); // le joueur défausse le Village et prend le Festival
        p1.playCard("Library");

        assertTrue(hasCards(p1.getHand(), "Duchy", "Duchy", "Duchy", "Duchy", "Festival", "Gold", "Silver"));
        assertTrue(hasCards(p1.getDiscard(), "Village"));
    }

    @Test
    void testBandit() {
        p1.getHand().add(new Bandit());
        Card silver1 = new Silver();
        Card silver2 = new Silver();
        Card gold = new Gold();
        Card copper = new Copper();
        p2.getDraw().add(0, silver1);
        p2.getDraw().add(0, copper);
        p0.getDraw().add(0, gold);
        p0.getDraw().add(0, silver2);

        game.setInput("Copper", "Silver");

        p1.playCard("Bandit");

        assertNotNull(p1.getDiscard().getCard("Gold")); // p1 a gagné un Gold
        assertFalse(p2.getDraw().contains(silver1)); // p2 a perdu le Silver (automatiquement)
        assertFalse(p2.getDiscard().contains(silver1));
        assertFalse(p0.getDraw().contains(silver2)); // p0 a perdu le Silver (choix de l'utilisateur)
        assertFalse(p0.getDiscard().contains(silver2));

        assertTrue(p2.getDiscard().contains(copper)); // p2 a défaussé le Copper
        assertTrue(p0.getDiscard().contains(gold)); // p0 a défaussé le Gold
    }

    @Test
    void testHarbinger() {
        p1.getHand().add(new Harbinger());
        Card gold = new Gold();
        p1.getDiscard().add(gold);
        p1.getDiscard().add(new Copper());
        p1.getDiscard().add(new Copper());
        p1.getDiscard().add(new Copper());
        Card c0 = p1.getDraw().get(0);

        game.setInput("Silver", "Gold");

        p1.playCard("Harbinger");

        assertTrue(p1.getHand().contains(c0));
        assertEquals(1, p1.getNumberOfActions());
        assertEquals(gold, p1.getDraw().get(0));
        assertFalse(p1.getDiscard().contains(gold));
    }

    @Test
    void testMerchant() {
        p1.getHand().add(new Merchant());
        p1.getHand().add(new Silver());
        p1.getHand().add(new Silver());

        p1.playCard("Merchant");

        assertEquals(0, p1.getMoney());
        assertEquals(8, p1.getHand().size());
        assertEquals(1, p1.getNumberOfActions());
        p1.playCard("Silver");
        assertEquals(3, p1.getMoney());
        p1.playCard("Silver");
        assertEquals(5, p1.getMoney());
    }

    @Test
    void testSentryTrash1Discard1() {
        p1.getHand().add(new Sentry());
        Card silver = new Silver();
        Card gold = new Gold();
        Card duchy = new Duchy();
        p1.getDraw().add(0, duchy);
        p1.getDraw().add(0, silver);
        p1.getDraw().add(0, gold);

        game.setInput("Duchy", "", "Silver"); // écarte Duchy, passe, défausse Silver

        p1.playCard("Sentry");

        assertEquals(1, p1.getNumberOfActions());
        assertTrue(p1.getHand().contains(gold));
        assertFalse(p1.getAllCards().contains(duchy));
        assertFalse(p1.getDraw().contains(silver));
        assertTrue(p1.getDiscard().contains(silver));
    }

    @Test
    void testSentryPassTwice() {
        p1.getHand().add(new Sentry());
        Card silver = new Silver();
        Card gold = new Gold();
        Card duchy = new Duchy();
        p1.getDraw().add(0, duchy);
        p1.getDraw().add(0, silver);
        p1.getDraw().add(0, gold);

        game.setInput("", "", "", "Silver"); // passe, passe, replace Silver puis Duchy (Duchy au dessus)

        p1.playCard("Sentry");

        assertEquals(1, p1.getNumberOfActions());
        assertTrue(p1.getHand().contains(gold));
        assertFalse(p1.getDiscard().contains(duchy));
        assertFalse(p1.getDiscard().contains(silver));
        assertEquals(duchy, p1.getDraw().get(0));
        assertEquals(silver, p1.getDraw().get(1));
    }

    // Tests additionnels

        //Personnels

    @Test
    void testMoatReactionBureaucrat() {
        p0.getHand().add(new Bureaucrat());
        p1.getHand().add(new Moat());
        p1.getHand().add(new Gardens());
        p2.getHand().add(new Moat());
        p2.getHand().add(new Gardens());

        game.setInput("y", "n","Gardens");
        p0.playCard("Bureaucrat");

        assertNull(p1.getDraw().getCard("Gardens"));
        assertNotNull(p2.getDraw().getCard("Gardens"));
    }

    @Test
    void testThroneRoomActionMerchant() {
        p0.getHand().add(new ThroneRoom());
        p0.getHand().add(new Merchant());
        p0.getHand().add(new Silver());

        game.setInput("Merchant");

        p0.playCard("Throne Room");
        assertNotNull(p0.getInPlay().getCard("Merchant"));
        p0.playCard("Silver");

        assertNotNull(p0.getInPlay().getCard("Silver"));
        assertEquals(2, p0.getNumberOfActions());
        assertEquals(4,p0.getMoney());
    }

    @Test
    void testThroneRoomActionHarbinger() {

        p1.getHand().add(new Harbinger());
        p1.getHand().add(new Harbinger());
        Card gold = new Gold();
        p1.getDiscard().add(gold);
        p1.getDiscard().add(new Copper());
        p1.getDiscard().add(new Copper());
        p1.getDiscard().add(new Copper());
        Card c0 = p1.getDraw().get(0);
        p1.getHand().add(new ThroneRoom());

        game.setInput("Harbinger");

        p1.playCard("Throne Room");

//        assertTrue(p1.getHand().contains(c0));
//        assertEquals(2, p1.getNumberOfActions());
//        assertEquals(gold, p1.getDraw().get(0));
//        assertFalse(p1.getDiscard().contains(gold));
    }

    @Test
    void testThroneRoomInfiniteLoop() {
        p1.getHand().add(new ThroneRoom());
        p1.getHand().add(new Workshop());

        game.setInput("Workshop","Silver","Silver");

        p1.playCard("Throne Room");

        assertEquals(2,p1.getHand().size());
    }

        //Test d'autres groupes
    @Test
    void testMoatReactionBandit() {
        p0.getHand().add(new Moat());
        p0.getDraw().add(0, new Silver());
        p1.getHand().add(new Bandit());
        p2.getHand().add(new Moat());
        p2.getDraw().add(0, new Silver());

        game.setInput("y", "n");
        p1.playCard("Bandit");
        assertNotNull(p2.getDraw().getCard("Silver"));
        assertNull(p0.getDraw().getCard("Silver"));
        assertNull(p0.getDiscard().getCard("Silver"));
    }

    @Test
    void testMoatReactionMilitia() {
        p0.getHand().clear();
        p2.getHand().clear();

        p0.getHand().add(new Moat());
        p0.getHand().add(new Silver());
        p0.getHand().add(new Gold());
        p0.getHand().add(new Witch());
        p0.getHand().add(new Bandit());

        p1.getHand().add(new Militia());

        p2.getHand().add(new Moat());
        p2.getHand().add(new Silver());
        p2.getHand().add(new Gold());
        p2.getHand().add(new Smithy());
        p2.getHand().add(new Mine());


        game.setInput("y", "n", "Silver", "Gold");
        p1.playCard("Militia");
        assertEquals(5, p2.getHand().size());
        assertEquals(3, p0.getHand().size());
    }

}
