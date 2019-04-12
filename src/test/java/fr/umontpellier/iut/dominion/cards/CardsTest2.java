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
    private Player player;

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
        player = game.getPlayer(2);
    }

    @Disabled
    @Test
    void testCellar() {
        player.getHand().add(new Cellar());
        player.getHand().add(new Duchy());
        player.getHand().add(new Duchy());

        game.setInput("Duchy", "Silver", "Duchy", "");
        player.playCard("Cellar");

        assertEquals(1, player.getNumberOfActions());
        assertEquals(7, player.getHand().size());
        assertEquals(2, player.getDiscard().size());
        assertEquals(3, player.getDraw().size());
        assertNull(player.getHand().getCard("Duchy"));
    }

    @Disabled
    @Test
    void testChapel() {
        player.getHand().add(new Chapel());
        player.getHand().add(new Duchy());
        player.getHand().add(new Silver());

        game.setInput("Duchy", "Gold", "Silver", "");
        player.playCard("Chapel");
        assertEquals(5, player.getHand().size());
        assertEquals(0, player.getDiscard().size());
    }

    @Disabled
    @Test
    void testWorkshop() {
        player.getHand().add(new Workshop());
        game.setInput("Gold", "Silver");
        player.playCard("Workshop");
        assertNull(player.getDiscard().getCard("Gold"));
        assertNotNull(player.getDiscard().getCard("Silver"));
    }

    @Disabled
    @Test
    void testBureaucrat() {
        Player p0 = game.getPlayer(0);
        Player p1 = game.getPlayer(1);
        Player p2 = game.getPlayer(2);
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

    @Disabled
    @Test
    void testMilitia() {
        Player p0 = game.getPlayer(0);
        Player p1 = game.getPlayer(1);
        Player p2 = game.getPlayer(2);

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

    @Disabled
    @Test
    void testMoneylenderWithCopper() {
        player.getHand().clear();
        player.getHand().add(new Moneylender());
        player.getHand().add(new Silver());
        player.getHand().add(new Silver());
        player.getHand().add(new Copper());

        player.playCard("Moneylender");

        assertEquals(3, player.getMoney());
        assertTrue(hasCards(player.getHand(), "Silver", "Silver"));
    }

    @Disabled
    @Test
    void testMoneylenderNoCopper() {
        player.getHand().clear();
        player.getHand().add(new Moneylender());
        player.getHand().add(new Silver());
        player.getHand().add(new Silver());

        player.playCard("Moneylender");

        assertEquals(0, player.getMoney());
        assertTrue(hasCards(player.getHand(), "Silver", "Silver"));
    }

    @Disabled
    @Test
    void testRemodel() {
        player.getHand().add(new Remodel());
        player.getHand().add(new Silver());
        game.setInput("Silver", "Province", "Duchy");
        player.playCard("Remodel");

        assertNull(player.getDiscard().getCard("Province"));
        assertNotNull(player.getDiscard().getCard("Duchy"));
        assertNull(player.getHand().getCard("Silver"));
    }

    @Disabled
    @Test
    void testCouncilRoom() {
        Player p0 = game.getPlayer(0);
        Player p1 = game.getPlayer(1);
        Player p2 = game.getPlayer(2);

        p1.getHand().add(new CouncilRoom());
        p1.playCard("Council Room");

        assertEquals(6, p0.getHand().size());
        assertEquals(9, p1.getHand().size());
        assertEquals(1, p1.getNumberOfBuys());
        assertEquals(6, p2.getHand().size());
    }

    @Disabled
    @Test
    void testMine() {
        player.getHand().clear();
        player.getHand().add(new Mine());
        player.getHand().add(new Estate());
        player.getHand().add(new Copper());
        player.getHand().add(new Silver());

        game.setInput("Estate", "Copper", "Gold", "Silver");
        player.playCard("Mine");

        assertTrue(hasCards(player.getHand(), "Estate", "Silver", "Silver"));
        assertEquals(0, player.getDiscard().size());
    }

    @Disabled
    @Test
    void testWitch() {
        Player p0 = game.getPlayer(0);
        Player p1 = game.getPlayer(1);
        Player p2 = game.getPlayer(2);

        p1.getHand().add(new Witch());
        p1.playCard("Witch");

        assertNotNull(p0.getDiscard().getCard("Curse"));
        assertNull(p1.getDiscard().getCard("Curse"));
        assertNotNull(p2.getDiscard().getCard("Curse"));
        assertEquals(7, p1.getHand().size());
    }
}