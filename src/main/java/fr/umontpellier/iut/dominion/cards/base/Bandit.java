package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Carte Bandit
 *
 * Gagnez un Or.
 * Chaque joueur révèle les deux premières cartes de son deck, écarte un trésor autre que Cuivre et défausse le reste.
 */
public class Bandit extends Card {
    public Bandit() {
        super("Bandit", 5);
    }

    public void play(Player p) {
        p.gain(p.getGame().removeFromSupply("Gold"));

        for (Player currentPlayer : p.getOtherPlayers()) {
            ListOfCards cardsTreasure = new ListOfCards();
            ListOfCards listDrawCards = new ListOfCards();

            listDrawCards.add(currentPlayer.drawToHand()); //Pioche les 2 cartes du deck
            listDrawCards.add(currentPlayer.drawToHand());

            Iterator<Card> iter = listDrawCards.iterator();

            while (iter.hasNext()) {
                Card c = iter.next();

                if (c.getTypes().contains(CardType.Treasure) && !c.getName().contains("Copper")) {
                    cardsTreasure.add(c);
                    iter.remove();
                }
            }

//            for (Card c : listDrawCards) { // Trie les cartes treasure picohées dans cardsTreasure
//                if (c.getTypes().contains(CardType.Treasure) && !c.getName().contains("Copper") && cardsTreasure.isEmpty()) {
//                    cardsTreasure.add(c);
//                }
//            }

            // Trash une carte Treasure

            if (cardsTreasure.size() == 2) {
                currentPlayer.getGame().addToTrash(currentPlayer.removeFromHand(currentPlayer.chooseCard("Choisissez le Trésor à écarter", cardsTreasure, false)));
            } else if (cardsTreasure.size() == 1){
                currentPlayer.getGame().addToTrash(currentPlayer.removeFromHand(cardsTreasure.remove(0).getName()));
            }

            // discard le reste

            for (Card c: listDrawCards) {
                currentPlayer.handToDisCard(c.getName());
            }
        }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        types.add(CardType.Attack);
        return types;
    }
}
