package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import java.util.Arrays;
import java.util.List;

/**
 * Carte Marchand (Merchant)
         *
         * +1 Carte.
         * +1 Action.
         * La première fois que vous jouez un argent pendant ce tour, +1 Pièce.
         */
public class Merchant extends Card {

    private static int bonusApplicable = 0;

    public Merchant() {
        super("Merchant", 3);
    }

    @Override
    public void play(Player p) {
        p.incrementActions(1);
        p.drawNCardsToHand(1);
        bonusApplicable++;
        // j'ai mit en commun son action avec la card Silver (Silver active son effet
        // que si la carte Silver est joué)
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action);
    }

    /**
     * Renvoie un boolean indiquant si le bonus de Merchant est applicable une
     * deuxième fois Permet de gérer le cas où Merchant est joué avec Throne Room
     *
     * @return {@code true} si le bonus doit être appliqué une deuxième fois,
     *         {@code false} sinon
     */
    public static int getBonusApplicable() {
        return bonusApplicable;
    }

    /**
     * Setter bonusApplicable
     * 
     * @param n
     */
    public static void resetBonusApplicable() {
        bonusApplicable = 0;
    }
}
