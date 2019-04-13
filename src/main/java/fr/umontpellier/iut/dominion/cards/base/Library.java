package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Bibliothèque (Library)
 *
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté.
 * Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends Card {
    public Library() {
        super("Library", 5);
    }
}