package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Artisan
 *
 * Gagnez une carte coûtant jusqu'à 5 Pièces dans votre main.
 * Mettez une carte de votre main sur votre deck.
 */
public class Artisan extends Card {
    public Artisan() {
        super("Artisan", 6);
    }

    @Override
    public void play(Player p) {
        p.addToHand(p.getGame().removeFromSupply((p.chooseCard("Choisissez une carte coutant jusqu'à 5 $ à placer sur dans votre main :",p.listCardCostingUpTo(5),false))));
        p.addToDraw(p.removeFromHand(p.chooseCard("Choisissez une carte à placer sur le dessus de votre pioche :", p.getCardsInHand(), false)));
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
