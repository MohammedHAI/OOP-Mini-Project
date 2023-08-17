// A collection of common data and methods to be used throughout the game
// This includes input and output for Player actions

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GameCommon {
    // Action constants

    public static final String OBJECTIVE = "Win the game by finding and equipping \'Win card\', but beware of finding a \'Lose card\'!";

    public static final char KEY_NORTH = 'W';
    public static final char KEY_WEST = 'A';
    public static final char KEY_SOUTH = 'S';
    public static final char KEY_EAST = 'D';

    public static final char KEY_INVENTORY = 'I';
    public static final char KEY_ATTACK = 'J';
    public static final char KEY_TAKE = 'K';
    public static final char KEY_SAVE = 'L';

    public static final char KEY_QUIT = 'Q';

    public static final char ITEM_EQUIP = 'J';
    public static final char ITEM_DETAILS = 'K';
    public static final char ITEM_DISCARD = 'L';

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    // Takes user input from the keyboard and return it
    public static String input() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    // Displays list of actions to the player
    public static void printActions() {
        print(KEY_NORTH + " - Move north\t");
        print(KEY_WEST + " - Move west\t");
        print(KEY_SOUTH + " - Move south\t");
        println(KEY_EAST + " - Move east");
        println("");
        println(KEY_INVENTORY + " - Inventory");
        println(KEY_ATTACK + " - Attack");
        println(KEY_TAKE + " - Take item");
        println(KEY_SAVE + " - Save game");
        println("");
        println(KEY_QUIT + " - Quit game");

        print("Enter an action: ");
    }

    // Moves the Object left or right if possible
    public static int moveHorizontal(char direction, GameMap currentMap, int positionX) {
        switch (direction) {
            case KEY_WEST:
                if (positionX > 0) { return --positionX; }
                break;
            case KEY_EAST:
                if (positionX < currentMap.getBoundaryX() - 1) { return ++positionX; }
                break;
        }
        return positionX;
    }

    // Moves the Object up or down if possible
    public static int moveVertical(char direction, GameMap currentMap, int positionY) {
        switch (direction) {
            case KEY_NORTH:
                if (positionY > 0) { return --positionY; }
                break;
            case KEY_SOUTH:
                if (positionY < currentMap.getBoundaryY() - 1) { ++positionY; }
                break;
        }
        return positionY;
    }

    // Displays the Player's inventory and returns whether there are any items or not
    public static boolean displayInventory(ArrayList<Item> inventory) {
        System.out.println("--- Inventory ---");

        if (inventory.size() < 1) {
            System.out.println("You currently have no items.");
            return true;
        }
        else {
            System.out.println("You currently have these items:");
            Iterator<Item> it = inventory.iterator();
            int count = 0;

            while (it.hasNext()) {
                Item i = it.next();
                println((count + 1) + ":\t" + i.getName());
                count++;
            }

            System.out.print("Enter an item number: ");
            return false;
        }
    }
    
    public static void printItemActions() {
        println(ITEM_EQUIP + " - Equip item");
        println(ITEM_DETAILS + " - Check details");
        println(ITEM_DISCARD + " - Discard item");
        println("");
        print("What would you like to do? ");
    }

    // Player picks
    public static int chooseTarget(ArrayList<Item> items) {
        print("Choose an item: ");
        int targetID = Integer.parseInt(input());
        return targetID;
    }

    // Checks if target Item is in the room
    public static boolean isTargetValid(ArrayList<Item> items, int targetID) {
        return (targetID > 0 && targetID < items.size() + 1);
    }

    public static Item getTarget(ArrayList<Item> items, int targetID) {
        return items.get(targetID - 1);
    }

    public static void printAttackOptions(ArrayList<Item> items) {
        int count = 0;

        println("Items you can attack:");
        for (Item item : items) {
            println(count + ": " + item.getName());
            item.getName();
        }
        print("Choose an item to attack: ");
    }
}
