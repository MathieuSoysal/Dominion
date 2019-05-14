package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Treasure;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Or (Gold)
 *
 * 3 Pièces
 */
public class Gold extends Treasure {
    public Gold() {
        super("Gold", 6);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(3);
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Treasure);
    }

}
