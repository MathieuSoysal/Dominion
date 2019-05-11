package fr.umontpellier.iut.dominion.gui;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class DominionServer {
    private static ArrayList<Session> clients = new ArrayList<>();
    private static String gameState;
    private static GameGUI game;

    public static void main(String[] args) {
        // Liste de toutes les cartes disponibles
        ArrayList<String> allKingdomCards = new ArrayList<>(Arrays.asList(
                "Artisan",
                "Bandit",
                "Bureaucrat",
                "Cellar",
                "Chapel",
                "CouncilRoom",
                "Festival",
                "Gardens",
                "Harbinger",
                "Laboratory",
                "Library",
                "Market",
                "Merchant",
                "Militia",
                "Mine",
                "Moat",
                "Moneylender",
                "Poacher",
                "Remodel",
                "Sentry",
                "Smithy",
                "ThroneRoom",
                "Vassal",
                "Village",
                "Witch",
                "Workshop"));
        Collections.shuffle(allKingdomCards);

        // Noms des joueurs
        String[] playerNames = new String[]{"Marco", "Polo"};
        // Cartes royaume à utiliser
        // Option 1. Aucune carte royaume (uniquement les cartes communes)
        // String[] kingdomCards = new String[0];

        // Option 2. Liste explicite de cartes royaume à utiliser (le nombre de cartes peut être quelconque)
        String[] kingdomCards = new String[]{"CouncilRoom", "Harbinger", "Laboratory", "Smithy", "Village"};

        // Option 3. Choix aléatoire de 10 cartes parmi la liste complète allKingdomCards définie précédemment
        // String[] kingdomCards = allKingdomCards.subList(0, 10).toArray(new String[0]);

        // Lancement de la partie
        game = new GameGUI(playerNames, kingdomCards);

        // Prépare le serveur websocket
        Server server = new Server("localhost", 3232, "/", WebSocketClient.class);
        try {
            server.start(); // lance le serveur
            new Thread(game).start();   // démarre le jeu

            Scanner scanner = new Scanner(System.in);
            while(true) {
                game.addInput(scanner.nextLine());
                scanner.close();
            }
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }

    protected static void addInput(String message) {
        game.addInput(message);
    }

    protected static void updateGameState(String message) {
        gameState = message;
        // Envoie l'état de la partie à tous les clients
        try {
            for (Session session: clients) {
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void addClient(Session session) {
        DominionServer.clients.add(session);
        // Envoie l'état actuel de la partie au nouveau client
        try {
            session.getBasicRemote().sendText(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void removeClient(Session session) {
        DominionServer.clients.remove(session);
    }
}
