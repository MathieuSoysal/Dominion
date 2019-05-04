package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Festival
 *
 * +2 Actions.
 * +1 Achat.
 * +2 Pièces.
 */
public class Festival extends Card {
    public Festival() {
        super("Festival", 5);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(2);
        p.incrementBuys(1);
        p.incrementActions(2);
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}