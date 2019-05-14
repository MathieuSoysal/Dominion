package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.AttackCards;

import java.util.Arrays;
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

        for (Player otherP : super.getAffectedPlayers(p)) {
            ListOfCards cardDrawnList = new ListOfCards();

            for (int i = 0; i < 2; i++) {
                Card cardDrawn = otherP.drawCard();
                if (cardDrawn != null) {
                    if (cardDrawn.getTypes().contains(CardType.Treasure) && !cardDrawn.getName().equals("Copper")) {
                        cardDrawnList.add(cardDrawn);
                    } else {
                        otherP.discardCard(cardDrawn);
                    }
                }
            }

            if (!cardDrawnList.isEmpty()) {
                String chosenCardName = otherP.chooseCard("Ecarte un trésor", cardDrawnList, false);
                otherP.getGame().addToTrash(cardDrawnList.remove(chosenCardName));

                if (!cardDrawnList.isEmpty())
                    otherP.discardCard(cardDrawnList.get(0));
            }
        }
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action, CardType.Attack);
    }
}
