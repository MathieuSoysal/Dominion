package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Rénovation (Remodel)
 *
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
 */
public class Remodel extends Card {
    public Remodel() {
        super("Remodel", 4);
    }

    @Override
    public void play(Player p) {
        Card removed = p.removeFromHand(p.chooseCard("Choisissez une carte à placer sur le dessus de votre pioche :", p.getCardsInHand(), false));
        int costToAdd = removed.getCost();
        p.gainFromSupply(p.chooseCard("Choisissez une carte coutant jusqu'à 4 :",p.listCardCostingUpTo(costToAdd + 2),false));
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}