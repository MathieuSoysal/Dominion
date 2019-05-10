package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Braconnier (Poacher)
 *
 * +1 Action.
 * +1 Carte.
 * +1 Pièce.
 * Défaussez une carte de votre main par pile de réserve épuisée.
 */
public class Poacher extends Card {
    public Poacher() {
        super("Poacher", 4);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(1);
        p.incrementActions(1);
        p.drawNCardsToHand(1);
        int nbSupplyCardsEmpty = p.getGame().nbSupplyCardsEmpty();
        if (nbSupplyCardsEmpty > 0) { // Si une liste de carte de la réserve est vide alors :
            while (nbSupplyCardsEmpty-- != 0)// c'est juste un while avec nbSupply nombre de boucles
                p.handToDisCard(p.chooseCard("Choisi " + nbSupplyCardsEmpty + " carte"
                        + ((nbSupplyCardsEmpty > 1) ? "s" : "") + " à défausser :", p.getCardsInHand(), false));
        }// ((nbSupplyCardsEmpty > 1) ? "s" : "") histoire d'éviter la faute d'orthographe
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
