package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Bibliothèque (Library)
 *
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté.
 * Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends Action {
    public Library() {
        super("Library", 5);
    }

    @Override
    public void play(Player p) {
        if (p.getCardsInHand().size() < 7) {
            ListOfCards cardsAsideList = new ListOfCards();
            for (Card cardDrawn = p.drawCard(); playerCanDrawCard(p, cardDrawn); cardDrawn = p.drawCard()) {
                if (isActionCard(cardDrawn) && playerWantsPutCardAside(p, cardDrawn))
                    cardsAsideList.add(cardDrawn);
                else
                    p.addToHand(cardDrawn);
            }
            cardsAsideList.forEach(p::discardCard);
        }
    }

    private boolean playerCanDrawCard(Player p, Card cardDrawn) {
        return p.getCardsInHand().size() != 7 && cardDrawn != null;
    }

    private boolean playerWantsPutCardAside(Player p, Card cardDrawn) {
        String instruction = "Voulez-vous mettre de côté la carte " + cardDrawn.getName() + " ?";
        List<String> choices = Arrays.asList("y", "n");
        return p.chooseOption(instruction, choices, false).equals("y");
    }

    private boolean isActionCard(Card cardDrawn) {
        return cardDrawn.getTypes().contains(CardType.ACTION);
    }
}