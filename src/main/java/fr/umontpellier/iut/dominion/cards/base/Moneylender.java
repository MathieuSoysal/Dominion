package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Prêteur sur gages (Moneylender)
 *
 * Écartez une carte Cuivre de votre main. Dans ce cas, +3 Pièces.
 */
public class Moneylender extends Card {
    public Moneylender() {
        super("Moneylender", 4);
    }

    @Override
    public void play(Player p) {
        ListOfCards cardsCopperInHand = new ListOfCards();

        p.getCardsInHand().forEach(cardInHand -> {
            if (cardInHand.getName().equals("Copper"))
                cardsCopperInHand.add(cardInHand);
        });

        if (p.chooseCard("Écartez une carte Cuivre de votre main pour gagné +3$ .", cardsCopperInHand, true)
                .equals("Copper")) {
            p.incrementMoney(3);
            p.handToTrash("Copper");
        }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}