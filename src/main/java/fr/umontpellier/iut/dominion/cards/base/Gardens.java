package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;

/**
 * Carte Jardins (Gardens)
 *
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité inférieure).
 */
public class Gardens extends Card {
    public Gardens() {
        super("Gardens", 4);
    }

    @Override
    public int getVictoryValue(Player p) {
        return p.getAllCards().size()/10;
    }
}