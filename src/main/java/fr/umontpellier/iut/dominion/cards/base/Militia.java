package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.AttackCards;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Milice (Militia)
 *
 * 2 Pièces. Tous vos adversaires défaussent leurs cartes de façon à n'avoir que
 * 3 cartes en main.
 */
public class Militia extends AttackCards {
    public Militia() {
        super("Militia", 4);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        p.incrementMoney(2);
        for (Player otherP : super.getAffectedPlayers(p)) {
            for (int size = otherP.getCardsInHand().size()-3; size > 0; size--){
                otherP.handToDisCard(otherP.chooseCard(
                        "Défausse " + size + "carte" + (size > 1 ? "s" : "") + " de ta main :",
                        otherP.getCardsInHand(), false));
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