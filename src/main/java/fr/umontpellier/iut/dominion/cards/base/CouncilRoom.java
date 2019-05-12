package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Chambre du conseil (Council Room)
 *
 * +4 Cartes. 
 * +1 Achat.
 *  Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends Card {
    public CouncilRoom() {
        super("Council Room", 5);
    }

    @Override
    public void play(Player p) {
        p.getOtherPlayers().forEach(otherP -> otherP.drawToHand());
        p.drawNCardsToHand(4);
        p.incrementBuys(1);
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action);
    }
}