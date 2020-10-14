package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Sentinelle (Sentry)
 *
 * +1 Carte. +1 Action. Regardez les 2 premières cartes de votre deck. Écartez
 * et/ou défaussez celles que vous voulez. Replacez les autres sur votre deck
 * dans l'ordre de votre choix.
 */
public class Sentry extends Action {
    public Sentry() {
        super("Sentry", 5);
    }

    @Override
    public void play(Player p) {
        p.drawToHand();
        p.incrementActions(1);
        ListOfCards cardsDrawn = drawTwoCard(p);
        trashCardsDrawnIfPlayerWants(p, cardsDrawn);
        discardCardsDrawnIfPlayerWants(p, cardsDrawn);
        replaceInDeckCardsDrawn(p, cardsDrawn);
    }

    private void replaceInDeckCardsDrawn(Player p, ListOfCards cardsDrawn) {
        while (!cardsDrawn.isEmpty())
            p.addToDraw(cardsDrawn.remove(
                    p.chooseCard("Replacez les sur votre deck dans l'ordre de votre choix :", cardsDrawn, false)));
    }

    private ListOfCards drawTwoCard(Player p) {
        ListOfCards cardsDrawn = new ListOfCards();
        while (cardsDrawn.size() != 2)
            cardsDrawn.addNullSafe(p.drawCard());
        return cardsDrawn;
    }

    private void trashCardsDrawnIfPlayerWants(Player p, ListOfCards cardsDrawn) {
        for (String chosenCard = chooseACardInCardsDrawn(p, cardsDrawn, "Écartez"); //
                playerHasChosenCard(chosenCard); //
                chosenCard = chooseACardInCardsDrawn(p, cardsDrawn, "Écartez")) {
            p.getGame().addToTrash(cardsDrawn.remove(chosenCard));
        }
    }

    private void discardCardsDrawnIfPlayerWants(Player p, ListOfCards cardsDrawn) {
        for (String chosenCard = chooseACardInCardsDrawn(p, cardsDrawn, "Défausser"); //
                playerHasChosenCard(chosenCard); //
                chosenCard = chooseACardInCardsDrawn(p, cardsDrawn, "Défausser")) {
            p.discardCard(cardsDrawn.remove(chosenCard));
        }
    }

    private boolean playerHasChosenCard(String chosenCard) {
        return !chosenCard.equals("");
    }

    private String chooseACardInCardsDrawn(Player p, ListOfCards cardsDrawn, String action) {
        String instruction = action + " l" + (cardsDrawn.size() > 1 ? "es cartes que" : "a carte si") + " vous voulez";
        return p.chooseCard(instruction, cardsDrawn, true);
    }
}
