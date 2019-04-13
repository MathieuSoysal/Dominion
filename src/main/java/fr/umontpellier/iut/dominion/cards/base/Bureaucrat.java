package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Bureaucrate (Bureaucrat)
 *
 * Recevez une carte Argent; placez-la sur votre deck.
 * Tous vos adversaires dévoilent une carte Victoire et la placent sur leur deck (sinon ils dévoilent leur
 * main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends Card {
    public Bureaucrat() {
        super("Bureaucrat", 4);
    }
}