package fr.umontpellier.iut.dominion;

import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Liste de cartes
 */
public class ListOfCards extends ArrayList<Card> {


    /**
     * Constructeur vide
     */
    public ListOfCards() {
        super();
    }

    /**
     * Constructeur à partir d'une liste de cartes
     */
    public ListOfCards(List<Card> l) {
        super(l);
    }

    /**
     * Mélange la liste
     */
    public void shuffle() {
        Collections.shuffle(this);
    }

    /**
     * Retire une carte de la liste dont le nom est égal à l'argument passé
     *
     * @param cardName le nom de la carte à retirer
     * @return la carte retirée, {@code null} sinon
     */
    public Card remove(String cardName) {
        Card cardToRemove = this.getCard(cardName);
        this.remove(cardToRemove);
        return cardToRemove;
    }

    /**
     * Renvoie une carte de la liste dont le nom est égal à l'argument
     *
     * @param cardName le nom de la carte à chercher
     * @return une carte de la liste ayant le nom cherché si elle existe,
     * {@code null} sinon
     */
    public Card getCard(String cardName) {
        for (Card currentCard : this) {
            if (cardName.equals(currentCard.getName())) {
                return currentCard;
            }
        }
        return null;
    }

    /**
     * Représentation de la liste sous forme d'une chaîne de caractères
     *
     * Cette fonction renvoie une chaîne de caractères constituée des noms des
     * cartes de la liste séparées par ", ".
     * Par exemple, si la liste contient une carte Village et une carte Copper,
     * la fonction renvoie la chaîne "Village, Copper"
     */
    @Override
    public String toString() {
        return this.stream()
                .map(card -> card.toString())
                .collect(Collectors.joining(", "));
    }

    /**
     * Méthode utilitaire pour l'interface graphique (qui sera ajoutée ultérieurement). À NE PAS MODIFIER.
     */
    public String toJSON() {
        StringJoiner joiner = new StringJoiner(", ");
        for (Card c : this) {
            joiner.add("\"" + c.toString() + "\"");
        }
        return "[" + joiner.toString() + "]";
    }
}
