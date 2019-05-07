package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void play(Player p) {
        p.gainFromSupply("Silver");
        for (Player otherP : p.getOtherPlayers()) {
            //Nous avons besoin d'une méthode pour show une carte
        }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        types.add(CardType.Attack);
        return types;
    }
}