package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Marchand (Merchant)
         *
         * +1 Carte.
         * +1 Action.
         * La première fois que vous jouez un argent pendant ce tour, +1 Pièce.
         */
public class Merchant extends Card {
    public Merchant() {
        super("Merchant", 3);
    }
}
