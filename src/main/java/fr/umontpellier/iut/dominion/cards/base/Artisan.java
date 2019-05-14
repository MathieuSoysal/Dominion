package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Artisan
 *
 * Gagnez une carte coûtant jusqu'à 5 Pièces dans votre main. Mettez une carte
 * de votre main sur votre deck.
 */
public class Artisan extends Action {
    public Artisan() {
        super("Artisan", 6);
    }

    @Override
    public void play(Player p) {
        String chosenCard = p.chooseCard("Choisissez une carte coutant jusqu'à 5 $:", p.listCardCostingUpTo(5), false);

        p.addToHand(p.getGame().removeFromSupply((chosenCard)));
        chosenCard = p.chooseCard("Choisissez une carte à placer dessus votre pioche :", p.getCardsInHand(), false);
        p.addToDraw(p.removeFromHand(chosenCard));
    }
}
