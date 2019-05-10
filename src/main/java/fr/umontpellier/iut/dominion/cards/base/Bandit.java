package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.AttackCards;

import java.util.ArrayList;
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

        ListOfCards cardsDrawed = new ListOfCards();
        for (int i = 0; i < 2; i++) {
            Card cardDrawed = p.drawCard();
            // p.reveals(cardDrawed);
            if (cardDrawed.getTypes().contains(CardType.Treasure) && !cardDrawed.getName().equals("Copper")) {
                cardsDrawed.add(cardDrawed);
            } else {
                p.discardCard(cardDrawed);
            }
        }

        if (!cardsDrawed.isEmpty()) {
            int indexCardChoose = 0; // variable pour l'exeption doublon
            if (cardsDrawed.size() == 2) {

                if (!cardsDrawed.get(0).getName().equals(cardsDrawed.get(1).getName())) {
                    indexCardChoose = cardsDrawed.indexOf(cardsDrawed.getCard(p.chooseCard(
                            "Ecarte un trésor autre que Cuivre et défausse le reste.", cardsDrawed, false)));
                }
                p.discardCard(cardsDrawed.get(indexCardChoose == 1 ? 0 : 1));
            }
            p.getGame().addToTrash(cardsDrawed.get(indexCardChoose));
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
