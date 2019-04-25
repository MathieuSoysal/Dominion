package fr.umontpellier.iut.dominion.gui;

import fr.umontpellier.iut.dominion.Game;

import java.util.concurrent.LinkedBlockingQueue;

public class GameGUI extends Game implements Runnable {

    private LinkedBlockingQueue<String> inputQueue;

    public GameGUI(String[] playerNames, String[] kingdomStacks) {
        super(playerNames, kingdomStacks);
        this.inputQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void printToUI(String message) {
        DominionServer.updateGameState(message);
    }

    public void addInput(String message) {
        inputQueue.add(message);
    }

    @Override
    public String readLine() {
        try {
            return inputQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
