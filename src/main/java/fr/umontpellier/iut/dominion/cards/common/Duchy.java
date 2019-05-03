package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Duché (Duchy)
 *
 * 3 VP
 */
public class Duchy extends Card {
    public Duchy() {
        super("Duchy", 5);
    }

    @Override
    public int getVictoryValue(Player p) {
        return 3;
    }
}