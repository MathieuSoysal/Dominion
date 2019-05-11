package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.AttackCards;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
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

    // il y a surrement des erreurs dans System.out.print
    @Override
    public void play(Player p) {
        super.play(p);
        p.addToDraw(p.getGame().removeFromSupply("Silver"));
        ListOfCards cardsVictory = new ListOfCards();

        for (Player otherP : super.getAffectedPlayers(p)) {
            for (Card c : otherP.getCardsInHand()) {
                if (c.getTypes().contains(CardType.Victory))
                    cardsVictory.add(c);
            }
            if (cardsVictory.isEmpty()) { // S'il n'a pas de carte Victory alors :
                System.out.print(otherP.getName() + "dévoile ces cartes en main :");
                otherP.getCardsInHand().forEach(x -> System.out.print(x.getName() + ", "));
                System.out.print("\n");

            } else { // S'il a des cartes victory en main :
                String chosenCardName = otherP.chooseCard("Choisi une carte Victoire à dévoiler : ", cardsVictory,
                        false);
                System.out.println(otherP.getName() + " dévoile une carte Victoire en main : " + chosenCardName);
                otherP.addToDraw(otherP.removeFromHand(chosenCardName));
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