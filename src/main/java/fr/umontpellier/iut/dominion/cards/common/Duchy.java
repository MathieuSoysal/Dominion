package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Treasure;
import fr.umontpellier.iut.dominion.cards.Type.Victory;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Duché (Duchy)
 *
 * 3 VP
 */
public class Duchy extends Victory {
    public Duchy() {
        super("Duchy", 5);
    }

    @Override
    public int getVictoryValue(Player p) {
        return 3;
    }
}