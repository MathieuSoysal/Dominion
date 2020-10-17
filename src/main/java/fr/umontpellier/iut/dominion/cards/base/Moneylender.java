package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Prêteur sur gages (Moneylender)
 *
 * Écartez une carte Cuivre de votre main. Dans ce cas, +3 Pièces.
 */
public class Moneylender extends Action {
    public Moneylender() {
        super("Moneylender", 4);
    }

    @Override
    public void play(Player p) {
        ListOfCards cardsCopperInHand = new ListOfCards();
        for (Card cardInHand : p.getCardsInHand()) {
            if (cardInHand.nameIs("Copper"))
                cardsCopperInHand.add(cardInHand);
        }
        if (playerWantsPutCardCopperInTrash(p, cardsCopperInHand)) {
            p.incrementMoney(3);
            p.handToTrash("Copper");
        }
    }

    private boolean playerWantsPutCardCopperInTrash(Player p, ListOfCards cardsCopperInHand) {
        return p.chooseCard("Écartez une carte Cuivre de votre main pour gagné +3$ .", cardsCopperInHand, true)
                .equals("Copper");
    }
}