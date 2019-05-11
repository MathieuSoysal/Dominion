package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Sentinelle (Sentry)
 *
 * +1 Carte.
 * +1 Action.
 * Regardez les 2 premières cartes de votre deck. Écartez et/ou défaussez celles que vous voulez.
 * Replacez les autres sur votre deck dans l'ordre de votre choix.
 */
public class Sentry extends Card {
    public Sentry() {
        super("Sentry", 5);
    }

    @Override
    public void play(Player p) {
        p.drawToHand();
        p.incrementActions(1);
        ListOfCards cardsDrawn = new ListOfCards();
        while (cardsDrawn.size() != 2)
            cardsDrawn.add(p.drawCard());

        String chosenCard = p.chooseCard("Écartez celles que vous voulez", cardsDrawn, true);
        for (; !chosenCard.equals(""); chosenCard = p.chooseCard("Écartez celles que vous voulez", cardsDrawn, true)) {
            p.getGame().addToTrash(cardsDrawn.remove(chosenCard));
        }

        chosenCard = p.chooseCard("Défausser celles que vous voulez", cardsDrawn, true);
        for(;!chosenCard.equals("");chosenCard = p.chooseCard("Défausser celles que vous voulez", cardsDrawn, true)) {
            p.discardCard(cardsDrawn.remove(chosenCard));
        }

        while (!cardsDrawn.isEmpty())
            p.addToDraw(cardsDrawn.remove(
                    p.chooseCard("Replacez les sur votre deck dans l'ordre de votre choix :", cardsDrawn, false)));
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
