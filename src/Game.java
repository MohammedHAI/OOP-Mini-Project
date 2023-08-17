// Text Adventure Game
// Author: Mohammed Al-Islam
// Student ID: 210259326
// Date Modified: 14/04/2022

import java.io.IOException;
import java.util.ArrayList;

// Main class where the game runs

public class Game {
    public static final String BASE_PATH = "../files";
    public static final String PLAYER_PATH = BASE_PATH + "/player.dat";
    public static final String ITEMS_PATH = BASE_PATH + "/items.dat";
    public static final String ROOMS_PATH = BASE_PATH + "/rooms.dat";
    public static final String EVENTS_PATH = BASE_PATH + "/events.dat";
    public static final String MAP_PATH = BASE_PATH + "/mainmap.dat";
    public static final int WIN_EVENT_ID = 0;
    public static final int LOSE_EVENT_ID = 1;

    private static boolean running = true;

    // Main program loop
    public static void runGame(GameMap gameMap, Player gamePlayer, GameWinEvent winEvent, GameLoseEvent loseEvent) {
        boolean playing = true;
        boolean gameWon = false;

        System.out.println(GameCommon.OBJECTIVE);
        while (playing) {
            playing = gamePlayer.makePlayerAction(gameMap);
            gameMap.runEvents(gameWon, gamePlayer);
            if (gameMap.checkIfGameWon(winEvent, gamePlayer) == true || gameMap.checkIfGameLost(loseEvent, gamePlayer) == true) {
                playing = false;
                running = false;
            }
            if (!playing) {
                System.out.println("Quitting Game...");
                running = false;
            }
        }
    }

    // Loads player data
    public static Player setupPlayer() {
        try {
            DataManager dm = new DataManager(PLAYER_PATH);
            Player temp = (Player) dm.readGameObjectFromFile();
            return temp;
        } catch (IOException e) {
            System.out.println("Error: Unable to load player data. Quitting game...");
            setRunning(false);
            return null;
        }
    }

    // Loads the necessary file for the map
    public static GameMap setupMap() {
        try {
            DataManager dm = new DataManager(MAP_PATH);
            GameMap temp = (GameMap) dm.readGameObjectFromFile();
            return temp;
        } catch (IOException e) {
            System.out.println("Error: Unable to load map data. Quitting game...");
            setRunning(false);
            return null;
        }
    }

    // Save game data
    public static void save(Player player, GameMap currentMap) {
        ArrayList<Room> rooms = currentMap.getRooms();
        ArrayList<GameEvent> events = currentMap.getEvents();
        
        try {
            DataManager dm = new DataManager(PLAYER_PATH);
            dm.writeGameObjectToFile(player);
            dm.setPath(MAP_PATH);
            dm.writeGameObjectToFile(currentMap);
            System.out.println("Successfully saved data");
        }
        catch (IOException e) {
            System.out.println("Error: Unable to save data");
            return;
        }
    }

    // Meant to be set by TitleScreen
    public static void setRunning(boolean newRunning) {
        running = newRunning;
    }

    public static void start(String[] args) {

        Player gamePlayer = setupPlayer();
        GameMap gameMap = setupMap();
        GameWinEvent winEvent = (GameWinEvent) gameMap.getEvent(WIN_EVENT_ID);
        GameLoseEvent loseEvent = (GameLoseEvent) gameMap.getEvent(LOSE_EVENT_ID);

        System.out.println("--- Adventure Game ---");
        System.out.println();

        while (running) {
            if (TitleScreen.isGameStarted()) {
                runGame(gameMap, gamePlayer, winEvent, loseEvent);
            }
        }

        System.exit(0);
    }
}
