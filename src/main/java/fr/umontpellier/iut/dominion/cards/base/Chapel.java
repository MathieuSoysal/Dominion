package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Chapelle (Chapel)
 *
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends Card {
    public Chapel() {
        super("Chapel", 2);
    }

    @Override
    public void play(Player p) {
        if (!p.getCardsInHand().isEmpty()) {
            int i = 4;
            for (String chooseC = p.chooseCard(
                    "Écartez jusqu'à " + (p.getCardsInHand().size() > 4 ? 4 : 4) + " carte"
                            + (p.getCardsInHand().size() > 1 ? "s" : "") + " de votre main :",
                    p.getCardsInHand(), true); i-- > 0
                            && !chooseC.equals(""); chooseC = (p.getCardsInHand().isEmpty()) ? ""
                                    : p.chooseCard(
                                            "Écartez jusqu'à " + i + " carte" + (i > 1 ? "s" : "") + " de votre main :",
                                            p.getCardsInHand(), true))
                p.handToTrash(chooseC);
        }
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}