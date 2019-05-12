package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Forgeron (Smithy)
 *
 * +3 Cartes.
 */
public class Smithy extends Card {
    public Smithy() {
        super("Smithy", 4);
    }

    @Override
    public void play(Player p) {
        p.drawNCardsToHand(3);
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action);
    }
}