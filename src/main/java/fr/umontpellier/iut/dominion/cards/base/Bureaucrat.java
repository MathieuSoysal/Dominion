package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.AttackCards;

import java.util.Arrays;
import java.util.List;

/**
 * Carte Bureaucrate (Bureaucrat)
 *
 * Recevez une carte Argent; placez-la sur votre deck. Tous vos adversaires
 * dévoilent une carte Victoire et la placent sur leur deck (sinon ils dévoilent
 * leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends AttackCards {
    public Bureaucrat() {
        super("Bureaucrat", 4);
    }

    @Override
    public void play(Player p) {
        super.play(p);
        p.addToDraw(p.getGame().removeFromSupply("Silver"));
        ListOfCards cardsVictoryInHand = new ListOfCards();

        for (Player otherP : super.getAffectedPlayers(p)) {

            otherP.getCardsInHand().forEach(cardInHand -> {
                if (cardInHand.getTypes().contains(CardType.Victory))
                    cardsVictoryInHand.add(cardInHand);
            });

            if (cardsVictoryInHand.isEmpty()) {
                System.out.print(otherP.getName() + " dévoile ces cartes en main :");
                otherP.getCardsInHand().forEach(cardInHand -> System.out.print(cardInHand.getName() + ", "));
                System.out.print("\n");

            } else {
                String chosenCardName = otherP.chooseCard("Choisi une carte Victoire à dévoiler : ", cardsVictoryInHand,
                        false);
                System.out.println(otherP.getName() + " dévoile une carte Victoire en main : " + chosenCardName);
                otherP.addToDraw(otherP.removeFromHand(chosenCardName));
            }
        }
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action, CardType.Attack);
    }
}