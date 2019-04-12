package fr.umontpellier.iut.dominion.cards;

import fr.umontpellier.iut.dominion.IOGame;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.base.*;
import fr.umontpellier.iut.dominion.cards.common.Duchy;
import fr.umontpellier.iut.dominion.cards.common.Gold;
import fr.umontpellier.iut.dominion.cards.common.Silver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static fr.umontpellier.iut.dominion.TestUtils.hasCards;
import static org.junit.jupiter.api.Assertions.*;


class CardsTest3 {
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
        player = game.getPlayer(1);
    }

    @Disabled
    @Test
    void testMoatReaction() {
        Player p0 = game.getPlayer(0);
        Player p1 = game.getPlayer(1);
        Player p2 = game.getPlayer(2);

        p0.getHand().add(new Moat());
        p1.getHand().add(new Witch());
        p2.getHand().add(new Moat());

        game.setInput("y", "n");
        p1.playCard("Witch");
        assertNull(p2.getDiscard().getCard("Curse"));
        assertNotNull(p0.getDiscard().getCard("Curse"));
    }


    @Disabled
    @Test
    void testThroneRoom() {
        player.getHand().add(new ThroneRoom());
        player.getHand().add(new Village());

        Card c1 = player.getDraw().get(0);
        Card c2 = player.getDraw().get(1);

        game.setInput("Village");
        player.playCard("Throne Room");

        assertEquals(4, player.getNumberOfActions());
        assertTrue(player.getHand().contains(c1));
        assertTrue(player.getHand().contains(c2));
        assertEquals(7, player.getHand().size());
    }

    @Disabled
    @Test
    void testLibrary() {
        player.getDraw().clear();
        player.getHand().clear();
        player.getHand().add(new Library());
        player.getHand().add(new Duchy());
        player.getHand().add(new Duchy());
        player.getHand().add(new Duchy());
        player.getHand().add(new Duchy());
        player.getDraw().add(new Gold());
        player.getDraw().add(new Village());
        player.getDraw().add(new Festival());
        player.getDraw().add(new Silver());
        player.getDraw().add(new Silver());

        game.setInput("y", "n");    // le joueur défausse le Village et prend le Festival
        player.playCard("Library");

        assertTrue(
                hasCards(player.getHand(), "Duchy", "Duchy", "Duchy", "Duchy", "Festival", "Gold", "Silver"));
        assertTrue(hasCards(player.getDiscard(), "Village"));
    }

}
