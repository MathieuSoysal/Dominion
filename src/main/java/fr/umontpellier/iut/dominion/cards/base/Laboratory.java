package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Laboratoire (Laboratory)
 *
 * +2 Cartes.
 * +1 Action.
 */
public class Laboratory extends Action {
    public Laboratory() {
        super("Laboratory", 5);
    }

    @Override
    public void play(Player p) {
        p.drawNCardsToHand(2);
        p.incrementActions(1);
    }
}