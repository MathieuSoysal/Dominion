package fr.umontpellier.iut.dominion.cards.base;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Carte Bandit
 *
 * Gagnez un Or.
 * Chaque joueur révèle les deux premières cartes de son deck, écarte un trésor autre que Cuivre et défausse le reste.
 */
public class Bandit extends Card {
    public Bandit() {
        super("Bandit", 5);
    }

    @Override
    public List<CardType> getTypes() {
        List<CardType> types = new ArrayList<>();
        types.add(CardType.Action);
        types.add(CardType.Attack);
        return types;
    }
}
