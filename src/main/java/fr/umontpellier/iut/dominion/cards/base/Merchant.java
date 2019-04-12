package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Marchand
 * +1 Action.
 * +1 Carte.
 * Gagnez +1 pièce lorsque vous jouez la première fois un Argent durant ce tour.
 */
public class Merchant extends Card {

    public Merchant(String name, int cost) {
        super(name, cost);
    }
}
