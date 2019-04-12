package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Vassal
 *
 * +2 Pièces.
 * Défaussez la première carte de votre deck. Si c'est une carte Action, vous pouvez la jouer.
 */
public class Vassal extends Card {

    public Vassal(String name, int cost) {
        super(name, cost);
    }
}
