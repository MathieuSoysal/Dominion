package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Cave (Cellar)
 *
 * +1 Action. Défaussez autant de cartes que vous voulez. +1 Carte par carte
 * défaussée.
 */
public class Cellar extends Action {
    public Cellar() {
        super("Cellar", 2);
    }

    @Override
    public void play(Player p) {
        p.incrementActions(1);

        int discarded = 0;
        String instruction = "Choisi les cartes à défausser";
        String chosenCardName = p.chooseCard(instruction, p.getCardsInHand(), true);

        while (!chosenCardName.equals("")) {
            p.handToDisCard(chosenCardName);
            instruction = "Choisi les cartes à défausser ( jusqu'à présent " + discarded++ + " cartes défausser.)";
            chosenCardName = p.chooseCard(instruction, p.getCardsInHand(), true);
        }
        p.drawNCardsToHand(discarded);
    }
}