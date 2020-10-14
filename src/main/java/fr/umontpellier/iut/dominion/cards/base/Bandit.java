package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Attack;

/**
 * Carte Bandit
 *
 * Gagnez un Or.
 * Chaque joueur révèle les deux premières cartes de son deck, écarte un trésor autre que Cuivre et défausse le reste.
 */
public class Bandit extends Attack {
    public Bandit() {
        super("Bandit", 5);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        p.gain(p.getGame().removeFromSupply("Gold"));
        for (Player otherP : super.getAffectedPlayers(p)) {
            ListOfCards trasureCardsDrawn = getTwocardsFromDeckThatAreTreasureDiscardRest(otherP);
            if (!trasureCardsDrawn.isEmpty()) {
                String chosenCardName = otherP.chooseCard("Ecarte un trésor", trasureCardsDrawn, false);
                otherP.getGame().addToTrash(trasureCardsDrawn.remove(chosenCardName));
                if (!trasureCardsDrawn.isEmpty())
                    otherP.discardCard(trasureCardsDrawn.get(0));
            }
        }
    }

    private ListOfCards getTwocardsFromDeckThatAreTreasureDiscardRest(Player otherP) {
        ListOfCards trasureCardsDrawn = new ListOfCards();
        for (int i = 0; i < 2; i++) {
            Card cardDrawn = otherP.drawCard();
            if (cardDrawn != null) {
                if (cardDrawn.isOfType(CardType.TREASURE) && !cardDrawn.nameIs("Copper")) {
                    trasureCardsDrawn.add(cardDrawn);
                } else {
                    otherP.discardCard(cardDrawn);
                }
            }
        }
        return trasureCardsDrawn;
    }
}
