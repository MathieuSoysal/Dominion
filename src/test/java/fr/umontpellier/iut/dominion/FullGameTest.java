package fr.umontpellier.iut.dominion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullGameTest {

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {
            }
        }));

    }


    @Test
    void testFullGameTwoPlayers() {
        String[] playerNames = new String[]{"Toto", "Titi"};
        BMGame game = new BMGame(playerNames, new String[0]);

        game.run();
        int score0 = game.getPlayer(0).getVictoryPoints();
        int score1 = game.getPlayer(1).getVictoryPoints();
        assertEquals(3, score0 % 6);
        assertEquals(3, score1 % 6);
        assertEquals(6 + 6 * 8, score0 + score1);
    }


    @Test
    void testFullGameThreePlayers() {
        String[] playerNames = new String[]{"Toto", "Titi", "Tutu"};
        BMGame game = new BMGame(playerNames, new String[0]);

        game.run();
        int score0 = game.getPlayer(0).getVictoryPoints();
        int score1 = game.getPlayer(1).getVictoryPoints();
        int score2 = game.getPlayer(2).getVictoryPoints();
        assertEquals(3, score0 % 6);
        assertEquals(3, score1 % 6);
        assertEquals(3, score2 % 6);
        assertEquals(9 + 6 * 12, score0 + score1 + score2);
    }



    @Test
    void testFullGameFourPlayers() {
        String[] playerNames = new String[]{"Toto", "Titi", "Tutu", "Lolo"};
        BMGame game = new BMGame(playerNames, new String[0]);

        game.run();
        int score0 = game.getPlayer(0).getVictoryPoints();
        int score1 = game.getPlayer(1).getVictoryPoints();
        int score2 = game.getPlayer(2).getVictoryPoints();
        int score3 = game.getPlayer(3).getVictoryPoints();
        assertEquals(3, score0 % 6);
        assertEquals(3, score1 % 6);
        assertEquals(3, score2 % 6);
        assertEquals(3, score3 % 6);
        assertEquals(12 + 6 * 12, score0 + score1 + score2 + score3);
    }
}
