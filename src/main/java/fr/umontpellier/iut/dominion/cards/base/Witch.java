package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.AttackCards;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Sorcière (Witch)
 *
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCards {
    public Witch() {
        super("Witch", 5);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        super.getAffectedPlayers(p).forEach(otherP -> otherP.gainFromSupply("Curse"));
        p.drawNCardsToHand(2);
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action, CardType.Attack);
    }
}