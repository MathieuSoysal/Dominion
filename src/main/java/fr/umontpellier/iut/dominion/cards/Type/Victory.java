package fr.umontpellier.iut.dominion.cards.Type;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.Arrays;
import java.util.List;

public abstract class Victory extends Card {

    public Victory(String cardName, int cost) {
        super(cardName, cost);
    }

    @Override
    public abstract int getVictoryValue(Player p);

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.VICTORY);
    }

}
