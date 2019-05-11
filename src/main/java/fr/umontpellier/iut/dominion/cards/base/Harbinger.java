package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Avant-coureur (Harbinger)
 *
 * +1 Carte.
 * +1 Action.
 * Regardez dans votre défausse, vous pouvez prendre une carte et la mettre sur votre deck.
 */
public class Harbinger extends Card {
    public Harbinger() {
        super("Harbinger", 3);
    }

    @Override
    public void play(Player p) {
        p.incrementActions(1);
        p.drawToHand();
        if (!p.getCardsInDiscard().isEmpty()) {
            p.addToDraw(p.removeFromDiscard(
                    p.chooseCard("Choisissez une carte à placer sur votre deck :",
                                            p.getCardsInDiscard(),
                                    true)));
        }

    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}
