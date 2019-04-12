package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Sentinelle
 * +1 Action.
 * +1 Carte.
 * Regardez les 2 premières cartes de votre deck. Écartez et/ou défaussez certaines d'entre elles.
 * Placez le reste au-dessus dans n'importe quel ordre.
 */
public class Sentry extends Card {

    public Sentry(String name, int cost) {
        super(name, cost);
    }
}
