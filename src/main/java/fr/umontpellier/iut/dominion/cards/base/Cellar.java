package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Cave (Cellar)
 *
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends Card {
    public Cellar() {
        super("Cellar", 2);
    }

    @Override
    public void play(Player p) {
        p.incrementActions(1);
        boolean wantsDiscard = true;
        int discarded = 0;
        int size = p.getCardsInHand().size();
        while (wantsDiscard) {
            p.discardCard(p.removeFromHand(p.chooseCard("Défaussez 1 carte, ou passez", p.getCardsInHand(), true)));
            if (size == p.getCardsInHand().size()) {
                wantsDiscard = false;
            } else {
                discarded++;
                size--;
            }
        }
        p.drawNCardsToHand(discarded);
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}