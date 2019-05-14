package fr.umontpellier.iut.dominion.cards.common;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Treasure;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Cuivre (Copper)
 *
 * 1 Pièce
 */
public class Copper extends Treasure {
    public Copper() {
        super("Copper", 0);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(1);
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Treasure);
    }
}
