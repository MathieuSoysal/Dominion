package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Chapelle (Chapel)
 *
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends Card {
    public Chapel() {
        super("Chapel", 2);
    }

    @Override
    public void play(Player p) {
        int size = p.getCardsInHand().size(); // je le met dnas un int pour éviter la que la condition du for change
        for (int i = 0; i < 4 - (size < 4 ? 4 - size : 0); i++) {
            // ((size < 4 ? 4 - size : 0) permet d'évite à ce que
            // le joueur doit retiré une carte alors qu'il plus de carte en main
            p.handToTrash(p.chooseCard("Écartez jusqu'à 4 carte de votre main :", p.getCardsInHand(), true)));
        }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}