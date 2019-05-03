package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Malédiction (Curse)
 *
 * -1 VP
 */
public class Curse extends Card {
    public Curse() {
        super("Curse", 0);
    }

    @Override
    public int getVictoryValue(Player p) {
        return -1;
    }
}