package fr.umontpellier.iut.dominion;

import fr.umontpellier.iut.dominion.cards.Card;

import java.util.*;

/**
 * Modélise un joueur de Dominion
 */
public class Player {
    /**
     * Nom du joueur
     */
    private String name;

    /**
     * Nombre d'numberOfActions disponibles
     */
    private int numberOfActions;

    /**
     * Nombre d'achats disponibles
     */
    private int numberOfBuys;

    /**
     * Nombre de pièces disponibles pour acheter des cartes
     */
    private int money;

    /**
     * La partie en cours
     */
    private Game game;

    /**
     * Liste des cartes dans la main du joueur
     */
    private ListOfCards hand;

    /**
     * Liste des cartes dans la défausse du joueur
     */
    private ListOfCards discard;

    /**
     * Liste des cartes dans la pioche du joueur
     */
    private ListOfCards draw;

    /**
     * Listes des cartes qui ont été jouées pendant le tour courant
     */
    private ListOfCards inPlay;

    /**
     * Constructeur
     *
     * Initialise les différentes piles de cartes du joueur, place 3 cartes
     * Estate et 7 cartes Copper dans la défausse du joueur puis fait piocher 5
     * cartes en main au joueur.
     *
     * @param name: le nom du joueur
     * @param game: le jeu en cours
     *
     * Indications: On peut utiliser la méthode {@code endTurn()} pour
     * préparer la main du joueur après avoir placé les cartes dans la défausse.
     */
    public Player(String name, Game game) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Getters et setters
     */
    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getNumberOfActions() {
        return numberOfActions;
    }

    public int getNumberOfBuys() {
        return numberOfBuys;
    }

    public Game getGame() {
        return game;
    }

    // Les méthodes getHand, getDiscard, getDraw et getInPlay servent uniquement pour les tests.
    // Vous ne devez pas les utiliser dans votre code.
    public ListOfCards getHand() {
        return hand;
    }

    public ListOfCards getDiscard() {
        return discard;
    }

    public ListOfCards getDraw() {
        return draw;
    }

    public ListOfCards getInPlay() {
        return inPlay;
    }

    /**
     * Renvoie une liste des cartes que le joueur a en main.
     * La liste renvoyée doit être une nouvelle {@code ListOfCards} dont les
     * éléments sont les mêmes que ceux de {@code hand}.
     */
    public ListOfCards getCardsInHand() {
        return new ListOfCards(hand);
    }

    /**
     * Renvoie une liste de toutes les cartes possédées par le joueur
     * (le deck complet c'est-à-dire toutes les cartes dans la main, la
     * défausse, la pioche et en jeu)
     */
    public ListOfCards getAllCards() {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Renvoie le nombre total de points de victoire du joueur
     *
     * Ce total est calculé en ajoutant les valeurs individuelles de toutes les
     * cartes dans le deck du joueur (en utilisant la méthode
     * {@code getVictoryValue()}) des cartes
     */
    public int getVictoryPoints() {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Renvoie une liste des autres joueurs de la partie.
     *
     * Les adversaires sont listés dans l'ordre de jeu, c'est-à-dire que le
     * premier de la liste est celui qui joue immédiatement après le joueur,
     * puis le suivant, et ainsi de suite jusqu'au joueur qui joue immédiatement
     * avant le joueur.
     *
     * Rmq: Cette méthode fait appel à la méthode {@code getOtherPlayers(Player p)}
     * de la classe {@code Game}.
     */
    public List<Player> getOtherPlayers() {
        return game.otherPlayers(this);
    }

    /**
     * Incrémente le nombre d'numberOfActions du joueur
     *
     * @param n nombre d'numberOfActions à ajouter (ce nombre peut être négatif si l'on
     *          souhaite diminuer le nombre d'numberOfActions)
     */
    public void incrementActions(int n) {
        numberOfActions += n;
    }

    /**
     * Incrémente le nombre de pièces du joueur
     *
     * @param n nombre de pièces à ajouter (ce nombre peut être négatif si l'on
     *          souhaite diminuer le nombre de pièces)
     */
    public void incrementMoney(int n) {
        money += n;
    }

    /**
     * Incrémente le nombre d'achats disponibles du joueur
     *
     * @param n nombre d'achats à ajouter (ce nombre peut être négatif si l'on
     *          souhaite diminuer le nombre d'achats)
     */
    public void incrementBuys(int n) {
        numberOfBuys += n;
    }

    /**
     * Pioche une carte dans la pioche du joueur.
     *
     * Si la pioche du joueur est vide, on commence par mélanger la défausse
     * et transférer toutes les cartes de la défausse dans la pioche.
     * On retire et renvoie ensuite la première carte de la pioche si elle n'est
     * pas vide (sinon la méthode ne fait rien et renvoie {@code null})
     *
     * @return la carte piochée, {@code null} si aucune carte disponible
     */
    public Card drawCard() {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Pioche une carte et la place directement dans la main du joueur.
     *
     * Cette méthode fait appel à la méthode {@code drawCard()} pour piocher une
     * carte et la place dans la main du joueur.
     *
     * @return la carte piochée, {@code null} si aucune carte disponible
     */
    public Card drawToHand() {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Ajoute une carte à la main du joueur.
     */
    public void addToHand(Card c) {
        hand.add(c);
    }

    /**
     * Défausse une carte.
     *
     * La carte est ajoutée à la pile de défausse du joueur. On suppose que la carte
     * a été retirée de son emplacement précédent.
     */
    public void discardCard(Card c) {
        discard.add(c);
    }

    /**
     * Renvoie une représentation de l'état du joueur sous forme d'une chaîne
     * de caractères.
     *
     * Cette représentation comporte
     * - le nom du joueur
     * - le nombre d'numberOfActions, de pièces et d'achats du joueur
     * - le nombre de cartes dans la pioche et dans la défausse du joueur
     * - la liste des cartes en jeu du joueur
     * - la liste des cartes dans la main du joueur
     *
     * On pourrait par exemple avoir l'affichage suivant:
     *
     *      -- Toto --
     * Actions: 2     Money: 4     Buys: 1     Draw: 7     Discard: 3
     * In play: Village, Copper, Silver, Copper
     * Hand: Estate, Province
     */
    @Override
    public String toString() {
        String r = String.format("     -- %s --\n", name);
        r += String.format("Actions: %d     Money: %d     Buys: %d     Draw: %d     Discard: %d\n",
                numberOfActions,
                money, numberOfBuys, draw.size(), discard.size());
        r += String.format("In play: %s\n", inPlay.toString());
        r += String.format("Hand: %s\n", hand.toString());
        return r;
    }

    /**
     * Méthode utilitaire pour l'interface graphique (qui sera ajoutée ultérieurement). À NE PAS MODIFIER.
     */
    public String toJSON() {
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add(String.format("\"name\": \"%s\"", name));
        joiner.add(String.format("\"actions\": %d", numberOfActions));
        joiner.add(String.format("\"money\": %d", money));
        joiner.add(String.format("\"buys\": %d", numberOfBuys));
        joiner.add(String.format("\"draw\": %d", draw.size()));
        joiner.add(String.format("\"discard\": %d", discard.size()));
        joiner.add(String.format("\"in_play\": %s", inPlay.toJSON()));
        joiner.add(String.format("\"hand\": %s", hand.toJSON()));
        return "{" + joiner.toString() + "}";
    }

    /**
     * Joue une carte de la main du joueur.
     *
     * @param c carte à jouer
     *
     * Cette méthode ne vérifie pas que le joueur a le droit de jouer la carte,
     * ni même que la carte se trouve effectivement dans sa main.
     * La méthode retire la carte de la main du joueur, la place dans la liste
     * {@code inPlay} et exécute la méthode {@code play(Player p)} de la carte.
     */
    private void playCard(Card c) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Joue une carte de la main du joueur.
     *
     * @param cardName nom de la carte à jouer
     *
     * S'il existe une carte dans la main du joueur dont le nom est égal au
     * paramètre, la carte est jouée à l'aide de la méthode
     * {@code playCard(Card c)}. Si aucune carte ne correspond, la méthode ne
     * fait rien.
     */
    public void playCard(String cardName) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Le joueur gagne une carte.
     *
     * @param c carte à gagner (éventuellement {@code null})
     *
     * Si la carte n'est pas {@code null}, elle est placée sur la défausse du
     * joueur. On suppose que la carte a correctement été retirée de son
     * emplacement précédent au préalable.
     */
    public void gain(Card c) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Le joueur gagne une carte de la réserve
     *
     * @param cardName nom de la carte à gagner. S'il existe une carte dans la
     *                 réserve ayant ce nom, cette carte est retirée de la réserve et placée
     *                 sur la défausse du joueur.
     * @return la carte qui a été ajoutée à la défausse du joueur, ou {@code
     * null} si aucune carte n'a été prise dans la réserve.
     */
    public Card gainFromSupply(String cardName) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Le joueur achète une carte de la réserve
     *
     * La méthode cherche une carte dans la réserve dont le nom est égal au
     * paramètre, puis vérifie que le joueur a assez de pièces pour l'acheter
     * et au moins un achat disponible.
     * Si le joueur peut acheter la carte, le coût de la carte est soustrait à
     * l'argent du joueur, le nombre d'achats disponibles est décrémenté de 1
     * et la carte est gagnée par le joueur.
     *
     * @param cardName nom de la carte à acheter
     * @return la carte qui a été gagnée ou {@code null} si l'achat n'a pas eu
     * lieu
     */
    public Card buyCard(String cardName) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Ajoute une carte sur le dessus de la pioche du joueur
     */
    public void addToDraw(Card c) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Attend une entrée de la part du joueur (au clavier) et renvoie le choix
     * du joueur.
     *
     * @param instruction message à afficher à l'écran pour indiquer au joueur
     *                    la nature du choix qui est attendu
     * @param choices     une liste de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (la liste sera convertie en ensemble
     *                    par la fonction pour éliminer les doublons, ce qui permet de compter
     *                    correctement le nombre d'options disponibles)
     * @param canPass     booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la chaîne de
     *                    caractères vide ("") qui signifie qu'il désire passer.
     * @return la méthode lit l'entrée clavier jusqu'à ce qu'un choix valide
     * soit entré par l'utilisateur (un élément de {@code choices} ou
     * éventuellement la chaîne vide si l'utilisateur est autorisé à passer).
     * Lorsqu'un choix valide est obtenu, il est renvoyé.
     *
     * Si l'ensemble {@code choices} ne comporte qu'un seul élément et que
     * {@code canPass} est faux, l'unique choix valide est automatiquement
     * renvoyé sans lire l'entrée de l'utilisateur.
     *
     * Si l'ensemble des choix est vide, la chaîne vide ("") est
     * automatiquement renvoyée par la méthode (indépendamment de la valeur de
     * {@code canPass}).
     *
     * Remarque: En général vous devriez plutôt utiliser les deux fonctions qui suivent (chooseCard(...)
     * et chooseOption(...)) qui appellent celle-ci.
     *
     */
    public String choose(String instruction, List<String> choices, boolean canPass, boolean show_choices) {
        // La liste de choix est convertie en ensemble pour éviter les doublons
        Set<String> choiceSet = new HashSet<>(choices);
        // Aucun choix disponible
        if (choiceSet.isEmpty())
            return "";
        else // Un seul choix possible (renvoyer cet unique élément)
            if (choiceSet.size() == 1 && !canPass)
                return choiceSet.iterator().next();
            else {
                String input;
                // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
                while (true) {
                    prompt(instruction, choices, show_choices);
                    input = game.readLine();
                    // si une réponse valide est obtenue, elle est renvoyée
                    if (choiceSet.contains(input) || (canPass && input.equals("")))
                        return input;
                }
            }
    }

    /**
     * Attend une entrée de la part du joueur et renvoie le choix du joueur.
     * Dans cette méthode, la liste des choix correspond à des mots clés possibles (par
     * exemple "y"/"n" pour un choix binaire). Les choix sont toujours affichés après
     * l'instruction.
     *
     * @param instruction message à afficher à l'écran pour indiquer au joueur
     *                    la nature du choix qui est attendu
     * @param choices     liste des choix possibles (cette liste sera affiché après
     *                    l'instruction)
     * @param canPass     booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la chaîne de
     *                    caractères vide ("") qui signifie qu'il désire passer.
     *
     * Exemple d'utilisation pour demander à l'utilisateur de choisir "y" ou "n"
     * {@code
     *   List<String> choices = Arrays.asList("y", "n");
     *   String input = p.chooseOption("Do you want to ...?", choices, false);
     * }
     */
    public String chooseOption(String instruction, List<String> choices, boolean canPass) {
        return choose(instruction, choices, canPass, true);
    }

    /**
     * Attend une entrée de la part du joueur et renvoie le choix du joueur.
     * Dans cette méthode, la liste des choix est donnée sous la forme d'une
     * liste de cartes et le joueur doit choisir le nom d'une de ces cartes.
     *
     * @param instruction message à afficher à l'écran pour indiquer au joueur
     *                    la nature du choix qui est attendu
     * @param choices     liste de cartes parmi lesquelles il faut en choisir une
     *                    parmi lesquelles l'utilisateur doit choisir
     * @param canPass     booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la chaîne de
     *                    caractères vide ("") qui signifie qu'il désire passer.
     *
     * La méthode commence par construire une liste de tous les noms des cartes
     * dans {@code choices} puis appelle la méthode précédente pour faire
     * choisir un nom parmi cette liste à l'utilisateur.
     *
     * Exemple d'utilisation pour faire choisir le nom d'une carte Action de sa
     * main à un joueur (dans cet exemple le joueur n'a pas le droit de passer
     * s'il a au moins une carte Action en main, mais la méthode peut quand
     * même renvoyer {@code ""} s'il n'a aucune carte Action en main) :
     * {@code
     *   ListOfCards choices = new ListOfCards();
     *   for (Card c: p.getCardsInHand()) {
     *     if (c.getTypes().contains(CardType.Action)) {
     *       choices.add(c);
     *     }
     *   }
     *   String input = p.chooseCard("Choose an Action cards.",
     *   choices, false);
     * }
     */
    public String chooseCard(String instruction, ListOfCards choices, boolean canPass) {
        // liste de noms de cartes
        List<String> stringChoices = new ArrayList<>();
        // tous les noms sont ajoutés à l'ensemble
        for (Card c : choices)
            stringChoices.add(c.getName());
        // appel de la méthode précédente en passant l'ensemble de noms
        return choose(instruction, stringChoices, canPass, false);
    }

    /**
     * Envoie l'état de la partie pour affichage aux joueurs et à l'UI avant de faire un choix
     *
     * @param instruction l'instruction qui est donnée au joueur
     * @param choices la liste des choix possibles
     * @param show_choices indique s'il faut afficher la liste des choix (true) ou non (false)
     */
    public void prompt(String instruction, List<String> choices, boolean show_choices) {
        // Prépare la version affichée à l'utilisateur
        game.println("");
        game.println(this.game.toString());
        game.println(this.toString());
        String ligneInstruction = ">>> " + instruction;
        if (show_choices) {
            StringJoiner choicesJoiner = new StringJoiner(" / ");
            for (String choice: choices) {
                choicesJoiner.add("\"" + choice + "\"");
            }
            ligneInstruction += "(" + choicesJoiner.toString() + ") <<<";
        } else {
            ligneInstruction += " <<<";
        }
        // Envoie la version affichage aux joueurs
        game.println(ligneInstruction);

        // Prépare la représentation envoyée à l'UI
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add(String.format("\"game\": %s", game.toJSON()));
        joiner.add(String.format("\"active_player\": %s", toJSON()));
        joiner.add(String.format("\"instruction\": \"%s\"", instruction));
        if (show_choices) {
            StringJoiner choicesJoiner = new StringJoiner(", ");
            for (String choice: choices) {
                choicesJoiner.add("\"" + choice + "\"");
            }
            joiner.add(String.format("\"choices\": [%s]", choicesJoiner.toString()));
        } else {
            joiner.add("\"choices\": []");
        }
        // Envoie la version pour l'UI
        game.printToUI("{" + joiner.toString() + "}\n");
    }

    /**
     * Termine le tour du joueur
     *
     * - Les compteurs d'numberOfActions, argent et achats du joueur sont remis à 0
     * - Les cartes en main et en jeu sont défaussées
     * - Le joueur pioche 5 cartes en main
     */
    public void endTurn() {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Démarre le tour du joueur
     *
     * Les compteurs de nombre d'actions, de nombre d'achats et argent sont initialisés
     */
    public void startTurn() {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Exécute le tour d'un joueur
     *
     * Cette méthode exécute successivement les 5 phases du tour d'un joueur:
     *
     * 1. (Préparation) la méthode {@code startTurn()} est appelée
     *
     * 2. (Action) Tant que le joueur a des numberOfActions disponibles, on lui demande
     * de choisir le nom d'une carte Action de sa main à jouer. Il peut passer à
     * tout moment à la phase suivante (soit de manière forcée s'il n'a plus de
     * carte Action en main soit volontairement en entrant la chaîne vide).
     * Lorsqu'il joue une carte Action, la fonction décrémente son nombre
     * d'numberOfActions puis joue la carte.
     *
     * 3. (Trésor) Le joueur joue toutes les cartes Trésor de sa main
     * automatiquement (dans le jeu de base il n'y a aucune raison de ne pas
     * jouer tous les trésors automatiquement).
     *
     * 4. (Achat) Tant que le joueur a au moins un achat disponible, on lui
     * demande de choisir le nom d'une carte de la réserve qu'il veut acheter.
     * Il ne peut acheter que des cartes dont le prix est inférieur à l'argent
     * dont il dispose. Le joueur peut passer (et terminer son tour) à tout
     * moment pendant cette phase.
     *
     * 5. (Fin) La méthode {@code endTurn()} est appelée pour terminer le tour
     * du joueur
     */
    public void playTurn() {
        // 1. (Préparation)
        startTurn();

        // 2. (Action)
        String input;
        while (numberOfActions > 0) {
            ListOfCards choices = new ListOfCards();
            for (Card c : hand)
                if (c.getTypes().contains(CardType.Action))
                    choices.add(c);
            input = chooseCard("Action phase (ENTER to pass).", choices, true);
            if (input.equals(""))
                break;
            else {
                numberOfActions -= 1;
                playCard(input);
            }
        }

        // 3. (Trésor)
        for (Card c : hand){
            if (c.getTypes().contains(CardType.Treasure))
                playCard(c);
        }

        // 4. (Achat)
        while (numberOfBuys > 0) {
            ListOfCards choices = new ListOfCards();
            for (Card c : game.availableSupplyCards())
                if (c.getCost() <= money)
                    choices.add(c);
            input = chooseCard("Buy phase (ENTER to pass).", choices, true);
            if (input.equals(""))
                break;
            else
                buyCard(input);
        }

        // 5. (Fin)
        endTurn();
    }
}
