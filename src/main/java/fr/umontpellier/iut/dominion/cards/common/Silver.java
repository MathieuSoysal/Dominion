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
        int bonus = (2+((p.searchInPlay("Silver")==1)?1*p.searchInPlay("Merchant"):0));
        if (Merchant.getBonusApplicable()) { bonus += 1; }
        p.incrementMoney(bonus);
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Treasure);
    }
}
