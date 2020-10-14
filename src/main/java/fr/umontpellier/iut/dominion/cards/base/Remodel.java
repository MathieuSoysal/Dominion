package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Rénovation (Remodel)
 *
 * Écartez une carte de votre main. Recevez une carte coûtant jusqu'à 2 Pièces
 * de plus que la carte écartée.
 */
public class Remodel extends Action {
    public Remodel() {
        super("Remodel", 4);
    }

    @Override
    public void play(Player p) {
        String sacrificedCardName = p.chooseCard("Choisissez une carte à sacrifier :", p.getCardsInHand(), false);
        if (!sacrificedCardName.equals("")) {
            String chosenCardName = playerChoosesCardBasedOnCostOfSacrificedCard(p, sacrificedCardName);
            p.gainFromSupply(chosenCardName);
        }
    }

    private String playerChoosesCardBasedOnCostOfSacrificedCard(Player p, String sacrificedCardName) {
        int costToAdd = p.handToTrash(sacrificedCardName).getCost() + 2;
        String instruction = "Choisissez une carte coutant jusqu'à " + costToAdd + " :";
        return p.chooseCard(instruction, p.listCardCostingUpTo(costToAdd), false);
    }
}