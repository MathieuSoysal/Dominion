package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Village
 *
 * +1 Carte.
 * +2 Actions.
 */
public class Village extends Action {
    public Village() {
        super("Village", 3);
    }

    @Override
    public void play(Player p) {
        p.incrementActions(2);
        p.drawToHand();
    }
}