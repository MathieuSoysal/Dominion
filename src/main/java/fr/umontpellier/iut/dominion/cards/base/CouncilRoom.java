package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Chambre du conseil (Council Room)
 *
 * +4 Cartes. 
 * +1 Achat.
 *  Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends Action {
    public CouncilRoom() {
        super("Council Room", 5);
    }

    @Override
    public void play(Player p) {
        p.getOtherPlayers().forEach(Player::drawToHand);
        p.drawNCardsToHand(4);
        p.incrementBuys(1);
    }
}