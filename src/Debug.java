import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

// Contains code for debugging the game
// ! DEVELOPMENT USE ONLY, DO NOT INCLUDE IN THE FINAL RELEASE

/* Code snippet
                    try {
                    Debug.write();
                }
                catch (Exception e) {
                    System.out.println("NOT WRITTEN");
                }
 */

public class Debug {
    public static void write() throws IOException {
        DataManager dm = new DataManager("../files/player.dat");

        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());

        Item i1 = new Weapon(GameMap.getNextId(), "Wooden Sword", "For attacking things", 1, 10, false, 3, -1, -1);
        Item i2 = new Weapon(GameMap.getNextId(), "Sword", "A small toy sword", 2, 3, false, 1, 1, 0);
        Item i3 = new Food(GameMap.getNextId(), "Bread", "Basic food", 1, 1, 5, -1, -1);
        Item i4 = new KeyItem(GameMap.getNextId(), "Win card", "Makes you win the game", 1, 1, 1, 2);
        Item i5 = new KeyItem(GameMap.getNextId(), "Lose card", "Makes you lose the game", 1, 1, 2, 2);
        Item i6 = new KeyItem(GameMap.getNextId(), "Laptop", "It's the latest model. Brand unknown", 1, 400, 0, 0);
        Item i7 = new Food(GameMap.getNextId(), "Pretzel", "Loved by all", 3, 0, true, 5, 0, 2);
        Item i8 = new Weapon(GameMap.getNextId(), "Sponge", "Deadlier than it looks", 2, 9, true, 99, 0, 1);

        ArrayList<Item> inventory = new ArrayList<>();
        inventory.add(i1);
        inventory.add(i3);
        Player p = new Player(GameMap.getNextId(), "Player", inventory, i1, 1, 1);
        dm.writeGameObjectToFile(p);
        System.out.println("### Wrote Player ###");

        ArrayList<Room> rooms = new ArrayList<>();
        Room r1 = new Room(GameMap.getNextId(), "Bright Room", "It is almost too bright to see in here", new ArrayList<Item>(), 0, 0);
        r1.addItem(i6);
        Room r2 = new Room(GameMap.getNextId(), "Dark Room", "The room is dimly lit by torches", new ArrayList<Item>(), 1, 0);
        r2.addItem(i2);
        Room r3 = new Room(GameMap.getNextId(), "Painting Room", "The room is empty except for a single painting on one of the walls", new ArrayList<Item>(), 2, 0);
        
        Room r4 = new Room(GameMap.getNextId(), "West Room", "The walls are made of stone", new ArrayList<Item>(), 0, 1);
        r4.addItem(i8);
        Room r5 = new Room(GameMap.getNextId(), "Starting Room", "There is a simple table here", new ArrayList<Item>(), 1, 1);
        Room r6 = new Room(GameMap.getNextId(), "East Room", "The walls are muddy", new ArrayList<Item>(), 2, 1);

        Room r7 = new Room(GameMap.getNextId(), "Comfy Room", "There's a sofa and a fireplace here", new ArrayList<Item>(), 0, 2);
        r7.addItem(i7);
        r7.addItem(i7);
        Room r8 = new Room(GameMap.getNextId(), "Win Room", "It feels like something important could be here...", new ArrayList<Item>(), 1, 2);
        r8.addItem(i4);
        Room r9 = new Room(GameMap.getNextId(), "Danger Room", "It feels like something dangerous could be here...", new ArrayList<Item>(), 2, 2);
        r9.addItem(i5);

        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);
        rooms.add(r5);
        rooms.add(r6);
        rooms.add(r7);
        rooms.add(r8);
        rooms.add(r9);

        ArrayList<GameEvent> events = new ArrayList<>();
        GameWinEvent winEvent = new GameWinEvent(GameMap.getNextId(), i4);
        GameLoseEvent loseEvent = new GameLoseEvent(GameMap.getNextId(), i5);
        events.add(winEvent);
        events.add(loseEvent);

        GameMap map = new GameMap(0, rooms, events);
        dm.setPath("../files/mainmap.dat");dm.writeGameObjectToFile(map);
        System.out.println("### Wrote Map ###");
    }
}
