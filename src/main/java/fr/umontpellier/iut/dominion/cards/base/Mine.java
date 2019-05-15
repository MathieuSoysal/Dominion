package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Mine
 *
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant
 * jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends Action {
    public Mine() {
        super("Mine", 5);
    }

    @Override
    public void play(Player p) {
        ListOfCards treasureInHand = new ListOfCards();
        ListOfCards treasureInsupply = new ListOfCards();

        p.getCardsInHand().forEach(cardInHand -> {
            if (cardInHand.getTypes().contains(CardType.Treasure))
                treasureInHand.add(cardInHand);
        });

            String chosenCardName = p.chooseCard("Choisissez une carte Trésor à écarter :", treasureInHand, true);

            if (!chosenCardName.equals("")) {
                int costFinal = p.handToTrash(chosenCardName).getCost() + 3;

                p.getGame().availableSupplyCards().forEach(cardInAvailable -> {
                    if (cardInAvailable.getTypes().contains(CardType.Treasure)
                            && cardInAvailable.getCost() <= costFinal)
                        treasureInsupply.add(cardInAvailable);
                });

                p.addToHand(p.getGame().removeFromSupply(p.chooseCard(
                        "Choisissez une carte Trésor coutant jusqu'à " + costFinal + "$ :", treasureInsupply, false)));
        }
    }
}
