package fr.umontpellier.iut.dominion;

import fr.umontpellier.iut.dominion.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game minimalGame;

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
    }

    @Test
    void testNbPlayers() {
        assertEquals(3, minimalGame.getNumberOfPlayers());
    }

    @Test
    void testGetPlayer() {
        Player p = minimalGame.getPlayer(1);
        assertEquals("Titi", p.getName());
    }

    @Test
    void testOtherPlayersSize() {
        Player p = minimalGame.getPlayer(1);
        assertEquals(2, minimalGame.otherPlayers(p).size());
    }

    @Test
    void testOtherPlayersNames() {
        Player p = minimalGame.getPlayer(1);
        assertEquals("Tutu", minimalGame.otherPlayers(p).get(0).getName());
        assertEquals("Toto", minimalGame.otherPlayers(p).get(1).getName());
    }

    @Test
    void testGetFromSupply() {
        assertEquals("Gold", minimalGame.getFromSupply("Gold").getName());
    }

    @Test
    void testGetNotInSupply() {
        assertNull(minimalGame.getFromSupply("Blop"));
    }

    @Test
    void testRemoveFromSupply() {
        for (int i = 0; i < 12; i++)
            assertEquals("Duchy", minimalGame.removeFromSupply("Duchy").getName());

        assertNull(minimalGame.removeFromSupply("Duchy"));
    }

    @Test
    void testRemoveNotInSupply() {
        assertNull(minimalGame.removeFromSupply("Blop"));
    }

    @Test
    void testNbAvailableSupplies() {
        assertEquals(7, minimalGame.availableSupplyCards().size());
        for (int i = 0; i < 12; i++)
            minimalGame.removeFromSupply("Estate");

        assertEquals(6, minimalGame.availableSupplyCards().size());
    }

    @Test
    void testNbCardsInSupplies() {
        for (int i = 0; i < 12; i++) {
            Card c = minimalGame.removeFromSupply("Estate");
            assertNotNull(c);
        }
        assertNull(minimalGame.removeFromSupply("Estate"));
        for (int i = 0; i < 12; i++) {
            Card c = minimalGame.removeFromSupply("Duchy");
            assertNotNull(c);
        }
        assertNull(minimalGame.removeFromSupply("Duchy"));
        for (int i = 0; i < 12; i++) {
            Card c = minimalGame.removeFromSupply("Province");
            assertNotNull(c);
        }
        assertNull(minimalGame.removeFromSupply("Province"));
        for (int i = 0; i < 20; i++) {
            Card c = minimalGame.removeFromSupply("Curse");
            assertNotNull(c);
        }
        assertNull(minimalGame.removeFromSupply("Curse"));
        for (int i = 0; i < 40; i++) {
            Card c = minimalGame.removeFromSupply("Silver");
            assertNotNull(c);
        }
        assertNull(minimalGame.removeFromSupply("Silver"));
        for (int i = 0; i < 30; i++) {
            Card c = minimalGame.removeFromSupply("Gold");
            assertNotNull(c);
        }
        assertNull(minimalGame.removeFromSupply("Gold"));
    }

    @Test
    void testEndGame3Stack() {
        assertFalse(minimalGame.isFinished());
        for (int i = 0; i < 12; i++)
            minimalGame.removeFromSupply("Estate");

        assertFalse(minimalGame.isFinished());
        for (int i = 0; i < 20; i++)
            minimalGame.removeFromSupply("Curse");

        assertFalse(minimalGame.isFinished());
        for (int i = 0; i < 30; i++)
            minimalGame.removeFromSupply("Gold");

        assertTrue(minimalGame.isFinished());
    }

    @Test
    void testEndGameProvince() {
        for (int i = 0; i < 12; i++)
            minimalGame.removeFromSupply("Province");

        assertTrue(minimalGame.isFinished());
    }
}