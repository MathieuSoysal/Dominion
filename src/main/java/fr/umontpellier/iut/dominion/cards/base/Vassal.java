package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Vassal
 *
 * +2 Pièces.
 * Défaussez la première carte de votre deck. Si c'est une carte Action, vous pouvez la jouer.
 */
public class Vassal extends Card {
    public Vassal() {
        super("Vassal", 3);
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
