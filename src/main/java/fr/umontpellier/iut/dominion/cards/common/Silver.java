package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.base.Merchant;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Argent (Silver)
 *
 * 2 Pièces
 */
public class Silver extends Card {

    public Silver() {
        super("Silver", 3);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(2+ Merchant.getBonusApplicable());
        Merchant.resetBonusApplicable();
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Treasure);
    }
}
