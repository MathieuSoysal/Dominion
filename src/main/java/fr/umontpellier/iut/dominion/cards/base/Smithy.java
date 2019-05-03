package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Forgeron (Smithy)
 *
 * +3 Cartes.
 */
public class Smithy extends Card {
    public Smithy() {
        super("Smithy", 4);
    }

    @Override
    public void play(Player p) {
        for (int i = 0; i < 3; i++) {
            p.drawToHand();
        }
    }
}