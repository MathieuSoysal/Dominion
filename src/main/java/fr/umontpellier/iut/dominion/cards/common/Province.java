package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Treasure;
import fr.umontpellier.iut.dominion.cards.Type.Victory;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Province
 *
 * 6 VP
 */
public class Province extends Victory {
    public Province() {
        super("Province", 8);
    }

    @Override
    public int getVictoryValue(Player p) {
        return 6;
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Victory);
    }
}