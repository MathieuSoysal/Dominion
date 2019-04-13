package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Cave (Cellar)
 *
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends Card {
    public Cellar() {
        super("Cellar", 2);
    }
}