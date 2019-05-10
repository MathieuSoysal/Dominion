package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Cave (Cellar)
 *
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends Card {
    public Cellar() {
        super("Cellar", 2);
    }

    @Override
    public void play(Player p) {
        p.incrementActions(1);
        int discarded = 0;
        // j'ai initalisé chooseCard dans le for et je l'actualise aussi dans le for et
        // vérifie
        // si le joueur décide de passé cela renvoi la chaine "" et ça intéromp la
        // boucle
        for (String cardDiscard = p.chooseCard("Défaussez 1 carte, ou passez", p.getCardsInHand(), true); !cardDiscard
                .equals(""); cardDiscard = (p.getCardsInHand().isEmpty() ? ""
                        : p.chooseCard("Défaussez 1 carte, ou passez", p.getCardsInHand(), true))) {
            p.handToDisCard(cardDiscard);
            discarded++;
        }
        p.drawNCardsToHand(discarded);
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        return types;
    }
}