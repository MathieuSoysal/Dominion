package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Festival
 *
 * +2 Actions.
 * +1 Achat.
 * +2 Pièces.
 */
public class Festival extends Action {
    public Festival() {
        super("Festival", 5);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(2);
        p.incrementBuys(1);
        p.incrementActions(2);
    }
}