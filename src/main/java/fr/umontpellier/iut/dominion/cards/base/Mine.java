package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Carte Mine
 *
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus ;
 * ajoutez cette carte à votre main.
 */
public class Mine extends Card {
    public Mine() {
        super("Mine", 5);
    }

    @Override
    public void play(Player p) {
        ListOfCards HandTreasure = p.getCardsInHand();

        Iterator<Card> iter = HandTreasure.iterator();

        while (iter.hasNext()) {
            Card c = iter.next();
            if (!c.getTypes().contains(CardType.Treasure))
                iter.remove();
        }

        int costToAdd = p.handToTrash(p.chooseCard("Choisissez une carte Trésor à écarter :", HandTreasure, false))
                .getCost() +3;
        ListOfCards listTreasure = p.listCardCostingUpToByType(costToAdd, CardType.Treasure);

        p.gainFromSupplyToHand(p.chooseCard("Choisissez une carte Trésor coutant jusqu'à " + (costToAdd + 3) + " :",
                listTreasure, false));
    }
@Override
public List<CardType> getTypes() {
    List<CardType> types = new ArrayList<>();
    types.add(CardType.Action);
    return types;
}
}