package fr.umontpellier.iut.dominion.cards.Type;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;
import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.Type.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Attack extends Action {

    /**
     * Liste des joueurs protégés par Moat
     */
    private List<Player> protectedPlayers;

    public Attack(String cardName, int cost) {
        super(cardName,cost);
        protectedPlayers = new ArrayList<>();
    }

    /**
     * Vérifie si les joueurs adverses ont une carte Moat dans leur main et leur demande si ils veulent la dévoiler
     *
     * @param p joueur qui exécute l'effet de la carte
     *
     */
    public void play(Player p) {
        for (Player currentPlayer : p.getOtherPlayers()) {
            for (Card currentCard : currentPlayer.getCardsInHand()) {
                if (currentCard.getName().equals("Moat")) {
                    List<String> choices = Arrays.asList("y","n");
                    if (currentPlayer.choose("Voulez-vous dévoiler votre carte : Moat ? ",
                            choices,
                            false,
                            true).equals("y")) {
                        protectedPlayers.add(currentPlayer);
                    }
                }
            }
        }
    }

    /**
     * Renvoie la liste des joueurs protégés par Moat
     *
     */
    public List<Player> getProtectedPlayers() {
        return protectedPlayers;
    }

    /**
     * Renvoie la liste des joueurs affectés par la carte attaque
     *
     * @param p le joueur qui a joué la carte attaque
     *
     * @return la liste des joueurs qui peuvent être affectés par la carte attaque (non protégés par Moat)
     */
    public List<Player> getAffectedPlayers(Player p) {
        List<Player> playerList = new ArrayList<>();
        playerList.addAll(p.getOtherPlayers());
        playerList.removeAll(getProtectedPlayers());
        return playerList;
    }

    @Override
    public List<CardType> getTypes() {
        return Arrays.asList(CardType.Action, CardType.Attack);
    }
}
