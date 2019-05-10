package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Carte Bibliothèque (Library)
 *
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté.
 * Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends Card {
    public Library() {
        super("Library", 5);
    }

    @Override
    public void play(Player p) {
        ListOfCards aside = new ListOfCards();
        List<String> choices = Arrays.asList("y", "n");

        while (p.getCardsInHand().size() != 7) {
            Card drawResult = p.drawCard();

            if (drawResult.getTypes().contains(CardType.Action) && p.chooseOption("Voulez-vous mettre de côté la carte " + drawResult.getName() + " ?", choices, false)
                    .equals("y")) {
                aside.add(drawResult);
            } else {
                p.addToHand(drawResult);
            }
        }
        for (Card c: aside) {
            p.discardCard(c);
        }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}