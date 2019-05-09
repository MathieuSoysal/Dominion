package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.Player;
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
public class Bureaucrat extends Card {
    public Bureaucrat() {
        super("Bureaucrat", 4);
    }

    // il y a surrement des erreurs dans System.out.print
    @Override
    public void play(Player p) {
        p.addToDraw(p.getGame().removeFromSupply("Silver"));
        ListOfCards cardsVictory = new ListOfCards();
        for (Player otherP : p.getOtherPlayers()) {
            for (Card c : otherP.getCardsInHand()) { // Trie les cartes victoires de sa main dans cardsVictory
                if (c.getTypes().contains(CardType.Victory))
                    cardsVictory.add(c);
            }
            if (cardsVictory.isEmpty()) { // Si cardsVictory est vide alors :
                System.out.print(otherP.getName() + "dévoile ces cartes en main :");
                otherP.getCardsInHand().forEach(x -> System.out.print(x.getName() + ", "));
                System.out.print("\n");
            } else { // si cardsVictory n'est pas vide alors :
                String carteReveal = otherP.chooseCard("Choisi une carte Victoire  à dévoiler : ", cardsVictory, false);
                // j'ai du utiliser une variable String car je devait à la fois afficher la
                // carte et la déplacer dans la pioche
                System.out.println(otherP.getName() + " dévoile une carte Victoire en main : " + carteReveal);
                otherP.addToDraw(otherP.removeFromHand(carteReveal));
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