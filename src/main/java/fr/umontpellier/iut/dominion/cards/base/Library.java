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
        int size = p.getCardsInHand().size();
        
        if (size < 7) {
            // j'ai mit la condition ici pour pouvoir mettre le drawcard avant la boucle et
            // ainsi vérifier s'il est null en même temps que la boucle while et ce qui me
            // permet aussi d'éviter de faire le getType d'une card null.
            ListOfCards CardsAsideList = new ListOfCards();
            List<String> choices = Arrays.asList("y", "n");
            Card cardDrawn = p.drawCard();

            while (size++ != 7 && cardDrawn != null) {

                String instruction = "Voulez-vous mettre de côté la carte " + cardDrawn.getName() + " ?";
                boolean cardIsAction = cardDrawn.getTypes().contains(CardType.Action);

                if (cardIsAction && p.chooseOption(instruction, choices, false).equals("y")) {
                    CardsAsideList.add(cardDrawn);
                } else {
                    p.addToHand(cardDrawn);
                }
                cardDrawn = (size == 7) ? null : p.drawCard(); /* évité de carrote une carte de la pioche à la fin de la boucle */
            }
            CardsAsideList.forEach(cardAside -> p.discardCard(cardAside));
        }
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action);
    }
}