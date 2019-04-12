package fr.umontpellier.iut.dominion;


/**
 * Une extension de la class `Game` dans laquelle la méthode readLine est redéfinie pour lire les
 * instructions depuis une liste de chaînes de caractères
 */
public class BMGame extends Game {
    private int currentPlayerIndex;

    /**
     * Constructeur, qui reprend exactement le constructeur de Game
     */
    public BMGame(String[] playerNames, String[] kingdomStacks) {
        super(playerNames, kingdomStacks);
        currentPlayerIndex = 0;
    }

    /**
     * Lit et renvoie une instruction dans la liste
     */
    public String readLine() {
        String choice = "";
        Player p = getPlayer(currentPlayerIndex);
        if (p.getMoney() >= 8) {
            choice = "Province";
        } else if (p.getMoney() >= 6) {
            choice = "Gold";
        } else if (p.getMoney() >= 3) {
            choice = "Silver";
        }
        currentPlayerIndex += 1;
        currentPlayerIndex %= getNumberOfPlayers();
        return choice;
    }
}
