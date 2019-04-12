package fr.umontpellier.iut.dominion;

import fr.umontpellier.iut.dominion.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestUtils {
    /**
     * Convertit une ListOfCards en liste de chaînes de caractères (les noms des cartes)
     */
    private static ArrayList<String> cardsToString(ListOfCards l) {
        ArrayList<String> result = new ArrayList<>();
        for (Card c : l)
            result.add(c.getName());

        return result;
    }

    /**
     * Teste si une ListOfCards contient exactement les cartes indiquées dans la
     * chaîne de caractères `namesString` (noms de cartes séparées par des virgules)
     */
    public static boolean isList(ListOfCards cards, String namesString) {
        String[] names = namesString.split(",\\s*");
        ArrayList<String> cardNames = cardsToString(cards);
        for (int i = 0; i < names.length; i++)
            if (!names[i].equals(cardNames.get(i)))
                return false;
        return true;
    }

    /**
     * Teste si une ListOfCards contient exactement les cartes indiquées dans la chaîne `namesString`
     * (noms de cartes séparées par des virgules). Les deux listes doivent avoir les mêmes éléments,
     * avec les mêmes multiplicités mais l'ordre n'a pas d'importance.
     */
    public static boolean hasCards(ListOfCards cards, String... names) {
        if (cards.size() != names.length) {
            return false;
        }
        Arrays.sort(names);
        ArrayList<String> cardNames = cardsToString(cards);
        Collections.sort(cardNames);

        for (int i = 0; i < names.length; i++)
            if (!names[i].equals(cardNames.get(i)))
                return false;
        return true;
    }

    /**
     * Teste si une ListOfCards contient au moins le nom indiqué dans chaîne `namesString` (un nom de carte).
     */

    public static boolean hasThisCard(ListOfCards cards, String name) {
        List<String> l = cardsToString(cards);
        return l.contains(name);
    }

    /**
     * Renvoie une ListOfCards contenant `nb_copies` exemplaires de la carte passée en argument
     *
     * @param c:         classe de carte à instancier
     * @param nb_copies: nombre d'exemplaires à mettre dans la pile
     * @return une liste de cartes
     */
    public static ListOfCards makeStack(Class<?> c, int nb_copies) {
        ListOfCards stack = new ListOfCards();
        for (int i = 0; i < nb_copies; i++)
            try {
                stack.add((Card) c.getConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        return stack;
    }
}
