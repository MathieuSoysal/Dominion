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
            // j'ai mit la condition ici pour pouvoir mettre le drawcard avant la boucle et
            // ainsi vérifier s'il est null en même temps que la boucle while et ce qui me
            // permet aussi d'éviter de faire le getType d'une card null.
            ListOfCards cardsAsideList = new ListOfCards();
            List<String> choices = Arrays.asList("y", "n");
            Card cardDrawn = p.drawCard();

            while (p.getCardsInHand().size() != 7 && cardDrawn != null) {

                String instruction = "Voulez-vous mettre de côté la carte " + cardDrawn.getName() + " ?";
                boolean cardIsAction = cardDrawn.getTypes().contains(CardType.ACTION);

                if (cardIsAction && p.chooseOption(instruction, choices, false).equals("y"))
                    cardsAsideList.add(cardDrawn);
                else
                    p.addToHand(cardDrawn);
                cardDrawn = p.drawCard();
            }
            cardsAsideList.forEach(p::discardCard);
        }
    }
}