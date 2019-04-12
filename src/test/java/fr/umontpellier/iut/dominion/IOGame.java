package fr.umontpellier.iut.dominion;


import java.util.ArrayList;
import java.util.List;

/**
 * Une extension de la class Game dans laquelle la méthode readLine est redéfinie pour lire les
 * instructions depuis
 * une liste de chaînes de caractères
 */
public class IOGame extends Game {
    /**
     * Liste contenant les instructions à lire (qui remplacent les entrées au clavier)
     */
    private List<String> instructions;

    /**
     * Constructeur, qui reprend exactement le constructeur de Game
     */
    public IOGame(String[] playerNames, String[] kingdomStacks) {
        super(playerNames, kingdomStacks);
        this.instructions = new ArrayList<>();
    }

    /**
     * Efface la liste des instructions
     */
    private void clearInstructions() {
        instructions.clear();
    }

    /**
     * Ajoute une instruction
     */
    private void addInstruction(String s) {
        instructions.add(s);
    }

    /**
     * Lit et renvoie une instruction dans la liste
     */
    public String readLine() {
        return instructions.remove(0);
    }

    /**
     * Fixe la liste d'instructions du jeu.
     *
     * @param args liste de chaînes de caractères. Chaque élément est une instruction (sans '\n' à la fin)
     */
    public void setInput(String... args) {
        clearInstructions();
        for (String s : args)
            addInstruction(s);
    }

}
