package fr.umontpellier.iut.dominion.cards;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Représentation des cartes du jeu Dominion
 */
public abstract class Card {
    /**
     * Le nom de la carte
     */
    private String name;

    /**
     * Le coût de la carte à l'achat
     */
    private int cost;

    /**
     * Constructeur simple
     *
     * @param name le nom de la carte
     * @param cost le coût de la carte
     */
    public Card(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Getters et setters
     */
    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    /**
     * Retourne {@code true} si {@value carName} correspond au nom de cette carte.
     * 
     * @param cardName qui doit correspondre
     * 
     * @return {@code boolean} true si le nom correspond 
     * 
     * @see Card#name
     */
    public boolean nameIs(String cardName){
        return name.equals(cardName);
    }

    /**
     * Renvoie une liste des types de la carte (éléments de {@code CardType})
     *
     * Le type d'une carte dépend de la sous-classe de {@code Card} à laquelle
     * la carte appartient. Ici, la méthode se contente donc de renvoyer un
     * {@code ArrayList} vide, auquel les sous-classes ajouteront les types.
     */
    public List<CardType> getTypes() {
        return new ArrayList<>();
    }

    /**
     * Retourne vrai si this est de même type que {@value cardType}
     * 
     * @param cardType le {@code CardType} qui doit être vérifié
     * 
     * @return {@code boolean} vrai si du même type
     * 
     * @see Card#getTypes()
     */
    public boolean isOfType(CardType cardType){
        return getTypes().contains(cardType);
    }

    /**
     * Renvoie une représentation de la carte sous forme de chaîne de caractères
     * (ici la fonction renvoie le nom de la carte)
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Exécute l'effet de la carte, jouée par le joueur {@code p}
     *
     * @param p joueur qui exécute l'effet de la carte
     *
     * L'action de cette méthode dépend de la classe de la carte.
     */
    public void play(Player p) {}

    /**
     * Renvoie la valeur de la carte en points de victoire (c'est cette méthode
     * qui est appelée sur toutes les cartes du deck d'un joueur pour
     * déterminer le score du joueur en fin de partie)
     *
     * @param p joueur qui possède la carte (la valeur d'une carte peut dépendre du joueur qui la possède,
     *          c'est le cas des cartes Gardens)
     *
     * Toutes les cartes qui ne sont pas de type Victoire ont une valeur de 0
     * (la méthode devra donc être redéfinie pour les cartes ayant une valeur non nulle).
     */
    public int getVictoryValue(Player p) {
        return 0;
    }
}