package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Mine
 *
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant
 * jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends Card {
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

            if (!chosenCardName.equals("")) { // S'il a pas choisi de passer alors :
                int costFinal = p.handToTrash(chosenCardName).getCost() + 3; // calcul du bonus

                p.getGame().availableSupplyCards().forEach(cardInAvailable -> {
                    if (cardInAvailable.getTypes().contains(CardType.Treasure)
                            && cardInAvailable.getCost() <= costFinal)
                        treasureInsupply.add(cardInAvailable);
                });

                p.addToHand(p.getGame().removeFromSupply(p.chooseCard(
                        "Choisissez une carte Trésor coutant jusqu'à " + costFinal + "$ :", treasureInsupply, false)));
                // On lui demande de choisir parmit les cartes triés dans la résérve
                // (treasureInSupply)
        }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
