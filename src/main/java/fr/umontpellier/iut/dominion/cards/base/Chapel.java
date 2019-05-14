package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Chapelle (Chapel)
 *
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends Action {
    public Chapel() {
        super("Chapel", 2);
    }

    @Override
    public void play(Player p) {
        if (!p.getCardsInHand().isEmpty()) {

            int nbDiscardMax = p.getCardsInHand().size() > 4 ? 4 : p.getCardsInHand().size();
            String instruction = "Écartez jusqu'à " + nbDiscardMax + " carte de votre main :";
            String chosenCardName = p.chooseCard(instruction, p.getCardsInHand(), true);

            while (!chosenCardName.equals("") && nbDiscardMax-- !=0) {
                p.handToTrash(chosenCardName);
                instruction = "Écartez jusqu'à " + nbDiscardMax + " carte de votre main :";
                chosenCardName = p.chooseCard(instruction, p.getCardsInHand(), true);
            }
        }
    }
}
