package fr.umontpellier.iut.dominion.cards;

import fr.umontpellier.iut.dominion.CardType;
import fr.umontpellier.iut.dominion.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AttackCards extends Card {

    private List<Player> protectedPlayers;

    public AttackCards(String cardName, int cost) {
        super(cardName,cost);
        protectedPlayers = new ArrayList<>();
    }

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

    public List<Player> getProtectedPlayers() {
        return protectedPlayers;
    }

    public List<Player> getAffectedPlayers(Player p) {
        List<Player> playerList = new ArrayList<>();
        playerList.addAll(p.getOtherPlayers());
        playerList.removeAll(getProtectedPlayers());
        return playerList;
    }
}
