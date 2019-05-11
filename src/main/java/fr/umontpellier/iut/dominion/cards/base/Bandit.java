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
public class Bandit extends AttackCards {
    public Bandit() {
        super("Bandit", 5);
    }

    public void play(Player p) {
        super.play(p);
        p.gain(p.getGame().removeFromSupply("Gold"));

        for (Player otherP : p.getOtherPlayers()) {
            ListOfCards cardsDrawn = new ListOfCards();

            for (int i = 0; i < 2; i++) {
                Card cardPulled = otherP.drawCard();
                if (cardPulled.getTypes().contains(CardType.Treasure) && !cardPulled.getName().equals("Copper")) {
                    cardsDrawn.add(cardPulled);
                } else {
                    otherP.discardCard(cardPulled);
                }
            }

            if (!cardsDrawn.isEmpty()) {
                String chosenCard = otherP.chooseCard("Ecarte un trésor", cardsDrawn, false);
                otherP.getGame().addToTrash(cardsDrawn.remove(chosenCard));

                if (!cardsDrawn.isEmpty())
                    otherP.discardCard(cardsDrawn.get(0));
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
