package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Douves (Moat)
 *
 * +2 Cartes.
 * Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler cette carte de votre main. Dans ce
 * cas, l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends Card {
    public Moat() {
        super("Moat", 2);
    }

}