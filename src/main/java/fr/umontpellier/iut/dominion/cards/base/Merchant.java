package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.common.Silver;

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

    private int playCount;

    private static boolean bonusApplicable = false;

    public Merchant() {
        super("Merchant", 3);
        playCount = 0;
    }

    @Override
    public void play(Player p) {
        playCount++;
        p.incrementActions(1);
        p.drawNCardsToHand(1);

        ListOfCards cardsInPlay = p.getCardsInPlay();
        if ((cardsInPlay.indexOf(cardsInPlay.getCard("Throne Room")) == cardsInPlay.indexOf(this)-1)
                && (playCount == 2)) {
            bonusApplicable = true;
        }

        //TODO: Gérer son exception
        // j'ai mit en commun son action avec la card Silver (Silver active son effet que si la carte Silver est joué)
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }

    public static boolean getBonusApplicable() {
        Boolean bonusApplicableTmp = bonusApplicable;
        bonusApplicable = false;
        return bonusApplicableTmp;
    }
}
