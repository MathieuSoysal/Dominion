package fr.umontpellier.iut.dominion.cards;

import fr.umontpellier.iut.dominion.IOGame;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.base.*;
import fr.umontpellier.iut.dominion.cards.common.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static fr.umontpellier.iut.dominion.TestUtils.hasCards;
import static org.junit.jupiter.api.Assertions.*;


class CardsTest2 {
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
        String[] playerNames = new String[]{"Toto", "Titi", "Tutu"};
        game = new IOGame(playerNames, new String[0]);
        p0 = game.getPlayer(0);
        p1 = game.getPlayer(1);
        p2 = game.getPlayer(2);
    }


    @Test
    void testCellar() {
        p2.getHand().add(new Cellar());
        p2.getHand().add(new Duchy());
        p2.getHand().add(new Duchy());

        game.setInput("Duchy", "Silver", "Duchy", "");
        p2.playCard("Cellar");

        assertEquals(1, p2.getNumberOfActions());
        assertEquals(7, p2.getHand().size());
        assertEquals(2, p2.getDiscard().size());
        assertEquals(3, p2.getDraw().size());
        assertNull(p2.getHand().getCard("Duchy"));
    }


    @Test
    void testChapel() {
        p2.getHand().add(new Chapel());
        p2.getHand().add(new Duchy());
        p2.getHand().add(new Silver());

        game.setInput("Duchy", "Gold", "Silver", "");
        p2.playCard("Chapel");
        assertEquals(5, p2.getHand().size());
        assertEquals(0, p2.getDiscard().size());
    }


    @Test
    void testWorkshop() {
        p2.getHand().add(new Workshop());
        game.setInput("Gold", "Silver");
        p2.playCard("Workshop");
        assertNull(p2.getDiscard().getCard("Gold"));
        assertNotNull(p2.getDiscard().getCard("Silver"));
    }


    @Test
    void testBureaucrat() {
        p0.getHand().clear();
        p0.getHand().add(new Estate());
        p0.getHand().add(new Duchy());

        p1.getHand().add(new Bureaucrat());

        p2.getHand().clear();
        p2.getHand().add(new Gold());
        p2.getHand().add(new Copper());
        p2.getHand().add(new Curse());

        game.setInput("Province", "Duchy", "");
        p1.playCard("Bureaucrat");

        assertNotNull(p0.getHand().getCard("Estate"));   // p0 a toujours Estate en main
        assertNull(p0.getHand().getCard("Duchy"));       // p0 n'a plus de Duchy en main
        assertEquals("Duchy", p0.getDraw().get(0).getName());    // le Duchy est sur la pioche de p0
        assertNotNull(p1.getDraw().getCard("Silver"));   // p1 a un Silver sur la pioche
        assertEquals(3, p2.getHand().size());            // p0 a toujours les mêmes cartes en main
    }


    @Test
    void testMilitia() {
        p0.getHand().clear();
        p0.getHand().add(new Silver());
        p0.getHand().add(new Silver());
        p0.getHand().add(new Duchy());
        p0.getHand().add(new Duchy());
        p0.getHand().add(new Duchy());
        p0.getHand().add(new Duchy()); // p0 a 6 cartes en main, il doit en défausser 3

        p1.getHand().add(new Militia());

        p2.getHand().clear();
        p2.getHand().add(new Silver());
        p2.getHand().add(new Silver()); // p2 a 2 cartes en main, non affecté par Militia

        game.setInput("Silver", "Estate", "Silver");
        // Estate n'est pas un choix valide. Après le 2e Silver, un Duchy devrait être choisi automatiquement
        p1.playCard("Militia");

        assertEquals(2, p1.getMoney());
        ArrayList<String> witnessList = new ArrayList<>(Arrays.asList("Silver", "Silver"));
        assertTrue(hasCards(p2.getHand(), "Silver", "Silver"));
        assertEquals(0, p2.getDiscard().size());
        assertTrue(hasCards(p0.getHand(), "Duchy", "Duchy", "Duchy"));
        assertTrue(hasCards(p0.getDiscard(), "Silver", "Silver", "Duchy"));
    }


    @Test
    void testMoneylenderWithCopper() {
        p2.getHand().clear();
        p2.getHand().add(new Moneylender());
        p2.getHand().add(new Silver());
        p2.getHand().add(new Silver());
        p2.getHand().add(new Copper());

        p2.playCard("Moneylender");

        assertEquals(3, p2.getMoney());
        assertTrue(hasCards(p2.getHand(), "Silver", "Silver"));
    }


    @Test
    void testMoneylenderNoCopper() {
        p2.getHand().clear();
        p2.getHand().add(new Moneylender());
        p2.getHand().add(new Silver());
        p2.getHand().add(new Silver());

        p2.playCard("Moneylender");

        assertEquals(0, p2.getMoney());
        assertTrue(hasCards(p2.getHand(), "Silver", "Silver"));
    }


    @Test
    void testRemodel() {
        p2.getHand().add(new Remodel());
        p2.getHand().add(new Silver());
        game.setInput("Silver", "Province", "Duchy");
        p2.playCard("Remodel");

        assertNull(p2.getDiscard().getCard("Province"));
        assertNotNull(p2.getDiscard().getCard("Duchy"));
        assertNull(p2.getHand().getCard("Silver"));
    }


    @Test
    void testCouncilRoom() {
        p1.getHand().add(new CouncilRoom());
        p1.playCard("Council Room");

        assertEquals(6, p0.getHand().size());
        assertEquals(9, p1.getHand().size());
        assertEquals(1, p1.getNumberOfBuys());
        assertEquals(6, p2.getHand().size());
    }


    @Test
    void testMine() {
        p2.getHand().clear();
        p2.getHand().add(new Mine());
        p2.getHand().add(new Estate());
        p2.getHand().add(new Copper());
        p2.getHand().add(new Silver());

        game.setInput("Estate", "Copper", "Gold", "Silver");
        p2.playCard("Mine");

        assertTrue(hasCards(p2.getHand(), "Estate", "Silver", "Silver"));
        assertEquals(0, p2.getDiscard().size());
    }


    @Test
    void testWitch() {
        p1.getHand().add(new Witch());
        p1.playCard("Witch");

        assertNotNull(p0.getDiscard().getCard("Curse"));
        assertNull(p1.getDiscard().getCard("Curse"));
        assertNotNull(p2.getDiscard().getCard("Curse"));
        assertEquals(7, p1.getHand().size());
    }


    @Test
    void testPoacher() {
        p1.getHand().add(new Poacher());
        Card duchy1 = new Duchy();
        Card duchy2 = new Duchy();
        p1.getHand().add(duchy1);
        p1.getHand().add(duchy2);
        for (int i = 0; i < 99; i++) { game.removeFromSupply("Silver"); }   // vider la pile de Silver
        for (int i = 0; i < 99; i++) { game.removeFromSupply("Gold"); }     // vider la pile de Gold

        game.setInput("Duchy", "Province", "Duchy");

        p1.playCard("Poacher");
        assertEquals(6, p1.getHand().size());
        assertEquals(1, p1.getMoney());
        assertTrue(p1.getDiscard().contains(duchy1));
        assertTrue(p1.getDiscard().contains(duchy2));
    }


    @Test
    void testArtisan() {
        Card silver = new Silver();
        p1.getHand().add(new Artisan());
        p1.getHand().add(silver);

        // - essaie d'acheter un Gold (pas autorisé) puis un Duchy (ok)
        // - repose le Silver de sa main sur son deck
        game.setInput("Gold", "Duchy", "Silver");

        p1.playCard("Artisan");

        assertNull(p1.getHand().getCard("Gold"));       // Gold n'a pas été gagné
        assertNotNull(p1.getHand().getCard("Duchy"));   // Duchy a été gagné en main
        assertFalse(p1.getHand().contains(silver));     // Silver n'est plus en main
        assertEquals(silver, p1.getDraw().get(0));      // Silver est sur le deck
    }


    @Test
    void testVassalPlayAction() {
        p1.getHand().add(new Vassal());
        Card village = new Village();
        p1.getDraw().add(0, village);

        game.setInput("y");     // joue le Village

        p1.playCard("Vassal");

        assertEquals(2, p1.getMoney());
        assertEquals(2, p1.getNumberOfActions());   // +2 actions du Village
        assertEquals(6, p1.getHand().size());       // +1 carte du Village
        assertTrue(p1.getInPlay().contains(village));
    }


    @Test
    void testVassalNotAction() {
        p1.getHand().add(new Vassal());
        Card gold = new Gold();
        p1.getDraw().add(0, gold);

        game.setInput("y");     // ne devrait pas être lu

        p1.playCard("Vassal");

        assertEquals(2, p1.getMoney());
        assertTrue(p1.getDiscard().contains(gold));
    }
}