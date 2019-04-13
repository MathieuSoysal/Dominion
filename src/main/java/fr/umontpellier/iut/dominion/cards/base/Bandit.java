package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Bandit
 *
 * Gagnez un Or.
 * Chaque joueur révèle les deux premières cartes de son deck, écarte un trésor autre que Cuivre et défausse le reste.
 */
public class Bandit extends Card {
    public Bandit() {
        super("Bandit", 5);
    }
}
