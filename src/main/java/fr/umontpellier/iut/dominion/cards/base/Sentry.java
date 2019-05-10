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
        ListOfCards cardDrawed = new ListOfCards();
        while (cardDrawed.size() != 2)
            cardDrawed.add(p.drawCard());

        if (!cardDrawed.isEmpty()) {
            for (String chooseC = p.chooseCard("Écartez celles que vous voulez", cardDrawed, true); !chooseC
                    .equals(""); chooseC = (cardDrawed.isEmpty() ? ""
                            : p.chooseCard("Écartez la si vous voulez", cardDrawed, true)))
                p.getGame().addToTrash(cardDrawed.remove(chooseC));

            if (!cardDrawed.isEmpty()) {
                for (String chooseC = p.chooseCard("Défausser celles que vous voulez", cardDrawed, true); !chooseC
                        .equals(""); chooseC = (cardDrawed.isEmpty() ? ""
                                : p.chooseCard("Défausser la si vous voulez", cardDrawed, true)))
                    p.discardCard(cardDrawed.remove(chooseC));

                while (!cardDrawed.isEmpty())
                    p.addToDraw(cardDrawed.remove(p.chooseCard(
                            "Replacez les sur votre deck dans l'ordre de votre choix :", cardDrawed, false)));

            }
        }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
