package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Bandit
 * +1 Pièce
 * Tous les autres joueurs révélent les deux premières cartes de leur deck, écartent un Trésor révélé autre
 * que Cuivre et défaussent le reste.
 */
public class Bandit extends Card {

    public Bandit(String name, int cost) {
        super(name, cost);
    }
}
