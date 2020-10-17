package fr.umontpellier.iut.dominion;

/**
 * Type énuméré des différents types de cartes possibles
 *
 * Une carte peut éventuellement avoir plusieurs types, par exemple
 * Action/Attaque ou Action/Réaction
 */
public enum CardType {
    TREASURE, ACTION, VICTORY, CURSE, REACTION, ATTACK
}