package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Prêteur sur gages (Moneylender)
 *
 * Écartez une carte Cuivre de votre main.
 * Dans ce cas, +3 Pièces.
 */
public class Moneylender extends Card {
    public Moneylender() {
        super("Moneylender", 4);
    }

    @Override
    public void play(Player p) {
        if (p.chooseCard("Écartez une carte Cuivre de votre main pour gagné +3 Pièces.", p.getHand(), true)
        // Attention il est possible qu'il est le droit de choisir une carte qui ne corréspond pas à copper (à testé dans dominion server)
                .equals("Copper")) {
            p.incrementMoney(3);
            p.removeFromHand("Copper");
         }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}