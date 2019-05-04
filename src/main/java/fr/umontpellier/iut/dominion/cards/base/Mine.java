package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
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

//    @Override
//    public void play(Player p) {
//        p.chooseCard("You may trash a Treasure from your hand. Gain a Treasure to your hand costing up to $3 more than it.", p.getCardsInHand(),false );
//    }
@Override
public List<CardType> getTypes() {
    List<CardType> types = new ArrayList<>();
    types.add(CardType.Action);
    return types;
}
}