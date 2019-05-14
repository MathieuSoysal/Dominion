package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Treasure;
import fr.umontpellier.iut.dominion.cards.Type.Victory;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Domaine (Estate)
 *
 * 1 VP
 */
public class Estate extends Victory {
    public Estate() {
        super("Estate", 2);
    }

    @Override
    public int getVictoryValue(Player p) {
        return 1;
    }
}