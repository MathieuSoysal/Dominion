package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.Arrays;
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
        String chosenCard;
        String instruction;
        //int phase = 0;

        while (cardsDrawn.size() != 2)
            cardsDrawn.addNullSafe(p.drawCard());

        instruction = "Écartez l" + (cardsDrawn.size() > 1 ? "es cartes que" : "a carte si") + " vous voulez";
        chosenCard = p.chooseCard(instruction, cardsDrawn, true);

        while (!chosenCard.equals("")) {
            p.getGame().addToTrash(cardsDrawn.remove(chosenCard));
            chosenCard = p.chooseCard("Écartez la carte si vous voulez", cardsDrawn, true);
        }

        instruction = "Défausser l" + (cardsDrawn.size() > 1 ? "es cartes que" : "a carte si") + " vous voulez";
        chosenCard = p.chooseCard(instruction, cardsDrawn, true);

        while (!chosenCard.equals("")) {
            p.discardCard(cardsDrawn.remove(chosenCard));
            chosenCard = p.chooseCard("Défausser la si vous voulez", cardsDrawn, true);
        }

        // Pour les instructeurs :
        //Si mon code se répéte trop :
        //
        //  instruction = "Écartez l" + (cardsDrawn.size() > 1 ? "es cartes que" : "a carte si") + " vous voulez";
        //  chosenCard = p.chooseCard(instruction, cardsDrawn, true);
        //
        // while (!chosenCard.equals("") && phase++ != 0) {      autre possibilité : for (String chosenCard = p.chooseCard("Écartez l" + (cardsDrawn.size() > 1 ? "es cartes que" : "a carte si") + " vous voulez", cardsDrawn,true); !chosenCard.equals("") && phase++ != 0;chosenCard = p.chooseCard(instruction, cardsDrawn, true))
        //     Card cardRemove = cardsDrawn.remove(chosenCard);

        //     if (phase == 0) {
        //         p.getGame().addToTrash(cardRemove);
        //     } else {
        //         p.discardCard(cardsDrawn.remove(chosenCard));                
        //     }
            
        //     instruction = (phase > 0 ? "Défausser" : "Écartez") + " l"
        //             + (cardsDrawn.size() > 1 ? "es cartes que" : "a carte si") + " vous voulez";
        //     chosenCard = p.chooseCard(instruction, cardsDrawn, true);
        // }

        while (!cardsDrawn.isEmpty())
            p.addToDraw(cardsDrawn.remove(
                    p.chooseCard("Replacez les sur votre deck dans l'ordre de votre choix :", cardsDrawn, false)));
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action);
    }
}
