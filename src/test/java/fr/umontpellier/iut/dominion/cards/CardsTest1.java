package fr.umontpellier.iut.dominion.cards;

import fr.umontpellier.iut.dominion.Game;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.base.*;
import fr.umontpellier.iut.dominion.cards.common.Silver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardsTest1 {
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
        Game minimalGame = new Game(playerNames, new String[0]);
        player = minimalGame.getPlayer(1);
    }


    @Test
    void testInitialState() {
        assertEquals(5, player.getHand().size());
        assertEquals(5, player.getDraw().size());
        assertEquals(0, player.getDiscard().size());
        assertEquals(0, player.getNumberOfActions());
        assertEquals(0, player.getNumberOfBuys());
        assertEquals(0, player.getMoney());
    }

    @Disabled
    @Test
    void testMoatAction() {
        player.getHand().add(new Moat());
        Card c1 = player.getDraw().get(0);
        Card c2 = player.getDraw().get(1);
        player.playCard("Moat");
        assertTrue(player.getHand().contains(c1));
        assertTrue(player.getHand().contains(c2));
        assertEquals(3, player.getDraw().size());
    }


    @Test
    void testVillage() {
        player.getHand().add(new Village());
        Card c1 = player.getDraw().get(0);
        player.playCard("Village");
        assertEquals(2, player.getNumberOfActions());
        assertTrue(player.getHand().contains(c1));
        assertEquals(4, player.getDraw().size());
    }


    @Test
    void testGardens() {
        assertEquals(3, player.getVictoryPoints());  // le joueur a initialement 10 cartes
        player.getHand().add(new Gardens());
        assertEquals(4, player.getVictoryPoints());  // le joueur a 11 cartes (1 Gardens)
        player.getDiscard().add(new Silver());
        player.getDiscard().add(new Silver());
        player.getDiscard().add(new Silver());
        player.getDiscard().add(new Silver());
        player.getDiscard().add(new Silver());
        player.getDiscard().add(new Silver());
        player.getDiscard().add(new Silver());
        player.getDiscard().add(new Silver());
        assertEquals(4, player.getVictoryPoints());  // le joueur a 19 cartes (1 Gardens)
        player.getDiscard().add(new Silver());
        assertEquals(5, player.getVictoryPoints());  // le joueur a 20 cartes (1 Gardens)
        player.getDraw().add(new Gardens());
        assertEquals(7, player.getVictoryPoints());  // le joueur a 21 cartes (2 Gardens)
    }


    @Test
    void testSmithy() {
        player.getHand().add(new Smithy());
        player.playCard("Smithy");
        assertEquals(8, player.getHand().size());
        assertEquals(2, player.getDraw().size());
    }


    @Test
    void testFestival() {
        player.getHand().add(new Festival());
        player.playCard("Festival");
        assertEquals(1, player.getNumberOfBuys());
        assertEquals(2, player.getMoney());
        assertEquals(2, player.getNumberOfActions());
        assertEquals(5, player.getHand().size());
    }


    @Test
    void testLaboratory() {
        player.getHand().add(new Laboratory());
        player.playCard("Laboratory");
        assertEquals(7, player.getHand().size());
        assertEquals(1, player.getNumberOfActions());
    }


    @Test
    void testMarket() {
        player.getHand().add(new Market());
        Card c1 = player.getDraw().get(0);
        player.playCard("Market");
        assertTrue(player.getHand().contains(c1));
        assertEquals(1, player.getNumberOfActions());
        assertEquals(1, player.getNumberOfBuys());
        assertEquals(1, player.getMoney());
    }
}