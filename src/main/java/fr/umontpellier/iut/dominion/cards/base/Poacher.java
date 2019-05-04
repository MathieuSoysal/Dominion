package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Braconnier (Poacher)
 *
 * +1 Action.
 * +1 Carte.
 * +1 Pièce.
 * Défaussez une carte de votre main par pile de réserve épuisée.
 */
public class Poacher extends Card {
    public Poacher() {
        super("Poacher", 4);
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
