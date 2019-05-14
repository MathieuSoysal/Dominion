package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Type.Action;

/**
 * Carte Atelier (Workshop)
 *
 * Recevez une carte coûtant jusqu'à 4 Pièces.
 */
public class Workshop extends Action {
    public Workshop() {
        super("Workshop", 3);
    }

    @Override
    public void play(Player p) {
        p.gainFromSupply(p.chooseCard("Choisissez une carte coutant jusqu'à 4 $ :",p.listCardCostingUpTo(4),false));
    }
}