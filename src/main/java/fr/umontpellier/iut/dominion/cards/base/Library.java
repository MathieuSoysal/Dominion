package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

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
        ListOfCards CardsAsideList = new ListOfCards();
        List<String> choices = Arrays.asList("y", "n");

        while (p.getCardsInHand().size() != 7) {

            Card cardDrawn = p.drawCard();
            String instruction = "Voulez-vous mettre de côté la carte " + cardDrawn.getName() + " ?";
            boolean cardIsAction = cardDrawn.getTypes().contains(CardType.Action);

            if (cardIsAction && p.chooseOption(instruction, choices, false).equals("y")) {
                CardsAsideList.add(cardDrawn);
            } else {
                p.addToHand(cardDrawn);
            }
        }
        CardsAsideList.forEach(cardAside -> p.discardCard(cardAside));
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action);
    }
}