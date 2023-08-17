// The main game world
// Houses different objects in the game including characters and items

import java.util.ArrayList;

public class GameMap extends GameObject {
    private ArrayList<Room> rooms;  // A subset of objects
    private ArrayList<GameEvent> events;
    private static int nextID; // AKA objectCounter
    private static final int MAP_WIDTH = 3;
    private static final int MAP_HEIGHT = 3;
    
    // Create Map with a list of Rooms and Objects already created
    public GameMap(int id, ArrayList<Room> rooms, ArrayList<GameEvent> events) {
        super(id);
        this.rooms = rooms;
        this.events = events;

        // The Map itself is technically Object 0. Since this is taken, the next object must be 1
        nextID = 1;
    }

    // Blank for when loading data
    public GameMap(int id) {
        super(id);
    }

    // Gets the ID for the next Object to be added
    public static int getNextId() {
        return nextID;
    }

    public static void incrementNextID() {
        nextID++;
    }

    public int getBoundaryX() {
        return MAP_WIDTH;
    }

    public int getBoundaryY() {
        return MAP_HEIGHT;
    }

    // Gets a Room at the specified location
    public Room getRoom(int targetX, int targetY) throws NoSuchRoomException {
        for (Room r : rooms) {
            if (r.getPositionX() == targetX && r.getPositionY() == targetY) {
                return r; // Must return if successful
            }
        }

        throw new NoSuchRoomException(this.toString() + "\tError: No such room exists at the coordinates " + "(" + targetX + ", " + targetY + ")");
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    // Adds a new Room at the specified location
    public void addRoom(Room r, int targetX, int targetY) {
        // If coordinates are within the bounds of the map and the room is not already taken
        if ((targetX < MAP_WIDTH && targetX >= 0) && (targetY < MAP_HEIGHT && targetY >= 0)) {
            rooms.add(r);
            nextID++;
        }
        else {
            throw new IndexOutOfBoundsException(this.toString() + "\tError: Room coordinates ( " + targetX + ", " + targetY + ") are out of the bounds of the map");
        }
    }

    public void addEvent(GameEvent ge) {
        events.add(ge);
        nextID++;
    }

    public GameEvent getEvent(int id) {
        return events.get(id);
    }

    public ArrayList<GameEvent> getEvents() {
        return events;
    }

    // Move item from one room to another, in the specified direction
    public void moveItem(Item i, char direction) {
        int positionX = i.getPositionX();
        int positionY = i.getPositionY();

        getRoom(positionX, positionY).removeItem(i.getId());
        positionX = GameCommon.moveHorizontal(direction, this, positionX);
        positionY = GameCommon.moveVertical(direction, this, positionY);
        getRoom(positionX, positionY).addItem(i);
    }

    // Runs all GameEvents that the map contains
    public void runEvents(boolean gameWon, Player p) {
        for (GameEvent ge : events) {
            if (ge.checkConditions(p) == true) {
                ge.run(gameWon);
            }
        }
    }

    // Checks if the game is won based on an event condition
    public boolean checkIfGameWon(GameWinEvent ge, Player p) {
        return ge.checkConditions(p);
    }

    public boolean checkIfGameLost(GameLoseEvent ge, Player p) {
        return ge.checkConditions(p);
    }
}