package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Douves (Moat)
 *
 * +2 Cartes.
 * Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler cette carte de votre main. Dans ce
 * cas, l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends Action {
    public Moat() {
        super("Moat", 2);
    }

    @Override
    public void play(Player p) {
        p.drawNCardsToHand(2);
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action, CardType.Reaction);
    }
}