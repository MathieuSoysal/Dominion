package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Carte Vassal
 *
 * +2 Pièces. Défaussez la première carte de votre deck. Si c'est une carte
 * Action, vous pouvez la jouer.
 */
public class Vassal extends Card {
    public Vassal() {
        super("Vassal", 3);
    }

    @Override
    public void play(Player p) {
        p.incrementMoney(2);
        Card cardDrawn = p.drawToHand();
        String instruction = "Voulez-vous jouer la carte " + cardDrawn.getName() + " ?";
        List<String> choices = Arrays.asList("y", "n");

        if (cardDrawn.getTypes().contains(CardType.Action) && p.chooseOption(instruction, choices, false).equals("y"))
            p.playCard(cardDrawn.getName());
        else
            p.handToDisCard(cardDrawn.getName());
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
