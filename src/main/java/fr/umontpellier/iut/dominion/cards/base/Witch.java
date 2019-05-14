package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Attack;

/**
 * Carte Sorcière (Witch)
 *
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends Attack {
    public Witch() {
        super("Witch", 5);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        super.getAffectedPlayers(p).forEach(otherP -> otherP.gainFromSupply("Curse"));
        p.drawNCardsToHand(2);
    }
}