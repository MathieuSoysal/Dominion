package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Braconnier (Poacher)
 *
 * +1 Action.
 * +1 Carte.
 * +1 Pièce.
 * Défaussez une carte de votre main par pile de réserve épuisée.
 */
public class Poacher extends Action {
    public Poacher() {
        super("Poacher", 4);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(1);
        p.incrementActions(1);
        p.drawNCardsToHand(1);

        int nbSupplyCardsEmpty = p.getGame().nbSupplyCardsEmpty(); //TODO : Vraiment trop spécifique vérifier si seul solution

        while (nbSupplyCardsEmpty-- != 0) {
            String instruction = "Choisi " + nbSupplyCardsEmpty + " carte" + ((nbSupplyCardsEmpty > 1) ? "s" : "")
                    + " à défausser :";
            p.handToDisCard(p.chooseCard(instruction, p.getCardsInHand(), false));
        }
    }
}
