package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Salle du trône (Throne Room)
 *
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends Card {
    public ThroneRoom() {
        super("Throne Room", 4);
    }


    @Override
    public void play(Player p) {
        ListOfCards actionInHand = new ListOfCards();
        for (Card c : p.getCardsInHand()) {
            if (c.getTypes().contains(CardType.Action)) {
                actionInHand.add(c);
            }
        }
        String chosenCardName = p.chooseCard("Choisissez une carte Action de votre main", actionInHand, true);
        if (! chosenCardName.equals("")) {
            Card chosenCard = p.getCardsInHand().getCard(chosenCardName);
            p.playCard(chosenCardName); // action de la carte + passage dans InPlay
            chosenCard.play(p); // n'utilise que l'action de la carte
        }

    }


    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}