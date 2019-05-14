package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Marché (Market)
 *
 * +1 Carte.
 * +1 Action.
 * +1 Achat.
 * +1 Pièce.
 */
public class Market extends Action {
    public Market() {
        super("Market", 5);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(1);
        p.incrementActions(1);
        p.incrementBuys(1);
        p.drawToHand();
    }
}