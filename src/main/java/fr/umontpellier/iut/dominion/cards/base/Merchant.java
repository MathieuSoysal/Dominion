package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Marchand (Merchant)
         *
         * +1 Carte.
         * +1 Action.
         * La première fois que vous jouez un argent pendant ce tour, +1 Pièce.
         */
public class Merchant extends Card {
    public Merchant() {
        super("Merchant", 3);
    }

    @Override
    public void play(Player p) {
        p.incrementActions(1);
        p.drawNCardsToHand(1);
        // j'ai mit en commun son action avec la card Silver (Silver active son effet qui s'il la carte siler est joué)
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
