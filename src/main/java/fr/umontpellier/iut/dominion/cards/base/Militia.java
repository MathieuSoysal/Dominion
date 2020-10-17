package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Attack;

/**
 * Carte Milice (Militia)
 *
 * 2 Pièces. Tous vos adversaires défaussent leurs cartes de façon à n'avoir que
 * 3 cartes en main.
 */
public class Militia extends Attack {
    public Militia() {
        super("Militia", 4);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        p.incrementMoney(2);
        for (Player otherP : super.getAffectedPlayers(p)) {
            for (int nbDiscard = otherP.getCardsInHand().size() - 3; nbDiscard > 0; nbDiscard--) {
                String instruction = "Défausse " + nbDiscard + "carte" + (nbDiscard > 1 ? "s" : "") + " de ta main :";
                otherP.handToDisCard(otherP.chooseCard(instruction, otherP.getCardsInHand(), false));
            }
        }
    }
}