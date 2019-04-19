package fr.umontpellier.iut.dominion;

import fr.umontpellier.iut.dominion.cards.Card;
import fr.umontpellier.iut.dominion.cards.FactoryListOfCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Classe représentant une partie de Dominion
 */
public class Game {
    /**
     * Tableau contenant les joueurs de la partie
     */
    private ArrayList<Player> players;

    /**
     * Index du joueur dont c'est actuellement le tour
     */
    private int currentPlayerIndex;

    /**
     * Liste des piles dans la réserve du jeu.
     *
     * On suppose ici que toutes les listes contiennent des copies de la même
     * carte.
     * Ces piles peuvent être vides en cours de partie si toutes les cartes de
     * la pile ont été achetées ou gagnées par les joueurs.
     */
    private List<ListOfCards> supplyStacks;

    /**
     * Liste des cartes qui ont été écartées (trash)
     */
    private ListOfCards trashedCards;

    /**
     * Scanner permettant de lire les entrées au clavier
     */
    private Scanner scanner;

    /**
     * Constructeur
     *
     * @param playerNames   liste des noms des joueurs qui participent à la
     *                      partie. Le constructeur doit créer les objets correspondant aux joueurs
     * @param kingdomStacks liste de piles de réserve à utiliser correspondant
     *                      aux cartes "royaume" à utiliser dans la partie, auxquelles le
     */
    public Game(String[] playerNames, String[] kingdomStacks) {
        currentPlayerIndex = 0;
        int nbPlayers = playerNames.length;
        trashedCards = new ListOfCards();
        scanner = new Scanner(System.in);

        // Création des piles de réserve
        supplyStacks = new ArrayList<>();
        for (String cardName : kingdomStacks) {
            supplyStacks.add(FactoryListOfCards.createCardList(10, cardName));
        }
        // Ajout des piles communes à la réserve
        // Le nombre d'exemplaires des cartes Victoire dépend du nombre de joueurs
        int nbVictory = nbPlayers <= 2 ? 8 : 12;

        supplyStacks.add(FactoryListOfCards.createCardList(60, "Copper"));
        supplyStacks.add(FactoryListOfCards.createCardList(40, "Silver"));
        supplyStacks.add(FactoryListOfCards.createCardList(30, "Gold"));
        supplyStacks.add(FactoryListOfCards.createCardList(nbVictory, "Estate"));
        supplyStacks.add(FactoryListOfCards.createCardList(nbVictory, "Duchy"));
        supplyStacks.add(FactoryListOfCards.createCardList(nbVictory, "Province"));
        supplyStacks.add(FactoryListOfCards.createCardList(10 * (nbPlayers - 1), "Curse"));


        // Création des joueurs
        players = new ArrayList<>(nbPlayers);
        for (String playerName : playerNames)
            players.add(new Player(playerName, this));
    }

    /**
     * Renvoie le joueur correspondant à l'indice passé en argument
     * On suppose {@code index} est un indice valide du tableau
     * {@code players}
     *
     * @param index indice dans le tableau des joueurs du joueur à renvoyer
     */
    public Player getPlayer(int index) {
        return players.get(index);
    }

    /**
     * Renvoie le nombre de joueurs participant à la partie
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Renvoie l'indice du joueur passé en argument dans le tableau des
     * joueurs, ou -1 si le joueur n'est pas dans le tableau.
     */
    private int indexOfPlayer(Player p) {
        return players.indexOf(p);
    }

    /**
     * Renvoie la liste des adversaires du joueur passé en argument, dans
     * l'ordre dans lequel ils apparaissent à partir du joueur {@code p}.
     *
     * @param p joueur dont on veut renvoyer la liste des adversaires. On
     *          suppose que {@code p} est bien dans le tableau des joueurs.
     * @return un {@code ArrayList} contenant les autres joueurs de la partie
     * en commençant par celui qui se trouve juste après {@code p} et en
     * terminant par celui qui se trouve juste avant (le tableau est considéré
     * comme cyclique c'est-à-dire qu'après le premier élément on revient au
     * premier).
     */
    public List<Player> otherPlayers(Player p) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Renvoie la liste des cartes qui sont disponibles à l'achat dans la
     * réserve.
     *
     * @return une liste de cartes contenant la première carte de chaque pile
     * non-vide de la réserve (cartes royaume et cartes communes)
     */
    public ListOfCards availableSupplyCards() {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Renvoie une représentation de l'état de la partie sous forme d'une chaîne
     * de caractères.
     *
     * Cette représentation comporte
     * - le nom du joueur dont c'est le tour
     * - la liste des piles de la réserve en indiquant pour chacune :
     * - le nom de la carte
     * - le nombre de copies disponibles
     * - le prix de la carte
     * si la pile n'est pas vide, ou "Empty stack" si la pile est vide
     *
     * On pourrait par exemple avoir l'affichage suivant:
     *
     *      -- Toto's Turn --
     * Cellar x10 (2)   [Empty stack]   Moat x10 (2)
     * Workshop x7 (3)   [Empty stack]   Bureaucrat x3 (4)
     * Gardens x6 (4)   Militia x2 (4)   Festival x8 (5)
     * Witch x2 (5)   Copper x60 (0)   Silver x30 (3)
     * Gold x20 (6)   Estate x8 (2)   Duchy x8 (5)
     * Province x2 (8)   Curse x1 (0)
     */
    @Override
    public String toString() {
        Player currentPlayer = players.get(currentPlayerIndex);
        String title = String.format("     -- %s's Turn --\n", currentPlayer.getName());
        StringJoiner joiner = new StringJoiner("   ");
        for (List<Card> stack : supplyStacks)
            if (stack.isEmpty())
                joiner.add("[Empty stack]");
            else {
                Card c = stack.get(0);
                joiner.add(String.format("%s x%d(%d)", c.getName(), stack.size(), c.getCost()));
            }
        return title + joiner.toString() + "\n";
    }

    /**
     * Méthode utilitaire pour l'interface graphique (qui sera ajoutée ultérieurement). À NE PAS MODIFIER.
     */
    public String toJSON() {
        Player currentPlayer = players.get(currentPlayerIndex);
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add("\"turn_player\": \"" + currentPlayer.getName() + "\"");
        StringJoiner kingdomJoiner = new StringJoiner(", ");
        for (List<Card> stack : supplyStacks) {
            if (stack.isEmpty()) {
                kingdomJoiner.add("{\"number\": 0}");
            } else {
                Card c = stack.get(0);
                kingdomJoiner.add(
                        String.format("{\"card\": \"%s\", \"number\": %d, \"cost\": %d}", c.getName(),
                                stack.size(), c.getCost()));
            }
        }
        joiner.add("\"kingdom\": [" + kingdomJoiner.toString() + "]");
        return "{" + joiner.toString() + "}";
    }


    /**
     * Renvoie une carte de la réserve dont le nom est passé en argument.
     *
     * @param cardName nom de la carte à trouver dans la réserve
     * @return la carte trouvée dans la réserve ou {@code null} si aucune carte
     * ne correspond
     */
    public Card getFromSupply(String cardName) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Retire et renvoie une carte de la réserve
     *
     * @param cardName nom de la carte à retirer de la réserve
     * @return la carte retirée de la réserve ou {@code null} si aucune carte
     * ne correspond au nom passé en argument
     */
    public Card removeFromSupply(String cardName) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Teste si la partie est terminée
     *
     * @return un booléen indiquant si la partie est terminée, c'est-à-dire si
     * au moins l'unedes deux conditions de fin suivantes est vraie
     * - 3 piles ou plus de la réserve sont vides
     * - la pile de Provinces de la réserve est vide
     * (on suppose que toute partie contient une pile de Provinces, et donc si
     * aucune des piles non-vides de la réserve n'est une pile de Provinces,
     * c'est que la partie est terminée)
     */
    public boolean isFinished() {
        boolean hasProvince = false;
        int nbEmptyStacks = 0;    // nombre de piles vides
        for (ListOfCards stack : supplyStacks)
            // une pile est vide
            if (stack.isEmpty())
                nbEmptyStacks += 1;
            else
                // il reste encore des Provinces
                if (stack.get(0).getName().equals("Province"))
                    hasProvince = true;
        return (nbEmptyStacks >= 3 || !hasProvince);
    }

    /**
     * Boucle d'exécution d'une partie.
     *
     * Cette méthode exécute les tours des joueurs jusqu'à ce que la partie soit
     * terminée. Lorsque la partie se termine, la méthode affiche le score
     * final et les cartes possédées par chacun des joueurs.
     */
    public void run() {
        while (!isFinished()) {
            // joue le tour du joueur courant
            players.get(currentPlayerIndex).playTurn();
            // passe au joueur suivant
            currentPlayerIndex += 1;
            if (currentPlayerIndex >= players.size())
                currentPlayerIndex = 0;
        }
        System.out.println("Game over.");
        // Affiche le score et les cartes de chaque joueur
        for (Player p : players)
            System.out.println(String.format("%s: %d Points.\n%s\n", p.getName(), p.getVictoryPoints(),
                    p.getAllCards().toString()));
    }

    /**
     * Affiche une chaîne de caractères sur la sortie standard
     *
     * @param message chaîne de caractères à afficher
     */
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Affiche une ligne sur la sortie standard
     *
     * @param message chaîne de caractères à afficher (la fonction ajoute automatiquement un retour à la ligne après)
     */
    public void println(String message) {
        print(message + "\n");
    }

    /**
     * Envoie une chaîne de caractères à l'interface graphique
     *
     * Cette méthode ne fait rien pour le moment et VOUS NE DEVEZ PAS L'UTILISER (NI LA MODIFIER).
     * Elle sera implémentée plus tard dans une sous-classe de Game qui vous sera fournie pour l'interface graphique.
     *
     * @param message chaîne de caractères à envoyer
     */
    public void printToUI(String message) {
    }

    /**
     * Lit une ligne de l'entrée standard
     *
     * C'est cette méthode qui doit être appelée à chaque fois qu'on veut lire
     * l'entrée clavier de l'utilisateur (par exemple dans Player.choose), ce
     * qui permet de n'avoir qu'un seul Scanner pour tout le programme
     *
     * @return une chaîne de caractères correspondant à la ligne suivante de
     * l'entrée standard (sans le retour à la ligne final)
     */
    public String readLine() {
        return scanner.nextLine();
    }
}