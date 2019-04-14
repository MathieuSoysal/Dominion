package fr.umontpellier.iut.dominion;

/**
 * Classe pour l'exécution d'une partie de Dominion
 */
class AppDominion {

    public static void main(String[] args) {
        // Noms des joueurs de la partie
        // (le nombre total de joueurs correspond au nombre de noms dans le tableau)
        String[] playerNames = new String[]{"Marco", "Polo"};

        // Changer les noms des classes selon les cartes à utiliser dans la partie.
        String[] kingdomCards = {"Cellar", "Chapel", "Moat", "Village", "Workshop",
                "Militia", "Smithy", "ThroneRoom", "Laboratory", "Witch"};

        // Instancie et exécute une partie
        Game g = new Game(playerNames, kingdomCards);
        g.run();
    }
}