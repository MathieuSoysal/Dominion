package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Domaine (Estate)
 *
 * 1 VP
 */
public class Estate extends Card {
    public Estate() {
        super("Estate", 2);
    }

    @Override
    public int getVictoryValue(Player p) {
        return 1;
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Victory);
        return types;
    }

}