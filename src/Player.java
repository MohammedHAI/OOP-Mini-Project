/*
    Player has the following actions:
    - Move
    - Check Inventory
    - Equip Item
    - Discard Item
    - Attack
    - Save Game
    - Quit Game
*/

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends GameObject implements Tangible {
    private String name;
    private ArrayList<Item> inventory;
    private Item equippedItem;
    private int positionX; // From Tangible
    private int positionY; // From Tangible
    private static int BASE_STRENGTH = 0;

    // Action constants
    private static final char KEY_NORTH = GameCommon.KEY_NORTH;
    private static final char KEY_WEST = GameCommon.KEY_WEST;
    private static final char KEY_SOUTH = GameCommon.KEY_SOUTH;
    private static final char KEY_EAST = GameCommon.KEY_EAST;

    private static final char KEY_INVENTORY = GameCommon.KEY_INVENTORY;
    private static final char KEY_ATTACK = GameCommon.KEY_ATTACK;
    private static final char KEY_TAKE = GameCommon.KEY_TAKE;
    private static final char KEY_SAVE = GameCommon.KEY_SAVE;

    private static final char KEY_QUIT = GameCommon.KEY_QUIT;

    public static final char ITEM_EQUIP = GameCommon.ITEM_EQUIP;
    public static final char ITEM_DETAILS = GameCommon.ITEM_DETAILS;
    public static final char ITEM_DISCARD = GameCommon.ITEM_DISCARD;


    // Player with starting inventory
    public Player(int id, String name, ArrayList<Item> inventory, Item equippedItem, int positionX, int positionY) {
        super(id);
        this.name = name;
        this.inventory = inventory;
        this.equippedItem = equippedItem;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // Blank for when loading data
    public Player(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    // Meant for checking win conditions in GameOverEvent
    public Item getEquippedItem() {
        return equippedItem;
    }

    // From Tangible
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPosition(int newX, int newY) {
        positionX = newX;
        positionY = newY;
    }

    /* --- Field Actions  --- */

    // Prints out a list of Objects the player can see in their current location
    public void canSee(Room currentRoom) {
        System.out.println(currentRoom.details());
        System.out.println("You can see: ");
        currentRoom.printItems();
        System.out.println();
    }

    // Gets user input to make the player perform an action
    public boolean makePlayerAction(GameMap currentMap) {
        Room currentRoom = currentMap.getRoom(positionX, positionY);
        this.canSee(currentRoom);
        GameCommon.printActions();
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("X - " + getPositionX() + ", Y - " + getPositionY());
        char action = GameCommon.input().charAt(0);
        return performAction(currentMap, currentRoom, action);
    }

    // Decides which action to perform based on the user input
    // Also checks if the player wants to continue playing
    public boolean performAction(GameMap currentMap, Room currentRoom, char action) {
        switch (action) {
            case KEY_NORTH:
            case KEY_WEST:
            case KEY_SOUTH:
            case KEY_EAST:
                move(currentMap, action);
                break;
            case KEY_INVENTORY:
                openInventory();
                break;
            case KEY_ATTACK:
                attack(currentRoom);
                break;
            case KEY_TAKE:
                take(currentRoom);
                break;
            case KEY_SAVE:
                save(currentMap);
                break;
            case KEY_QUIT:
                return false;
            default:
                System.out.println("Error: That is not a valid action");
                break;
        }

        // If any action taken except QUIT
        System.out.println();
        return true;
    }

    public void move(GameMap currentMap, char direction) {
        positionX = GameCommon.moveHorizontal(direction, currentMap, positionX);
        positionY = GameCommon.moveVertical(direction, currentMap, positionY);
    }

    public void openInventory() {
        boolean isInventoryEmpty = GameCommon.displayInventory(inventory);
        if (isInventoryEmpty) { return; }

        int itemID = Integer.parseInt(GameCommon.input()) - 1;
        if (itemID > -1 && itemID < inventory.size()) {
            Item target = inventory.get(itemID);
            makeItemChoice(target);
        }
        else {
            throw new NoSuchItemException(this.toString() + "\tError: Item with index " + itemID + " not found");
        }
    }

    // Gives the player some options after selecting an item
    public void makeItemChoice(Item target) {
        GameCommon.printItemActions();
        char choice = GameCommon.input().charAt(0);
        switch (choice) {
            case ITEM_EQUIP:
                equip(target);
                break;
            case ITEM_DETAILS:
                details(target);
                break;
            case ITEM_DISCARD:
                discard(target);
                break;
            default:
                System.out.println("Error: That is not a valid action");
                break;
        }
    }

    /* --- Main Actions --- */

    // Attack an Item residing in a Room
    public void attack(Room currentRoom) {
        ArrayList<Item> items = currentRoom.getItems();

        if (items.size() < 1) {
            System.out.println("There are no items here. You cannot attack anything.");
            return;
        }
        
        int targetID = GameCommon.chooseTarget(items);
        boolean validTarget = GameCommon.isTargetValid(items, targetID);

        if (validTarget) {
            Item target = GameCommon.getTarget(items, targetID);

            System.out.println("You try to attack the " + target.getName());
            target.takeDamage(BASE_STRENGTH + equippedItem.attack());
            if (target.isBroken()) {
                System.out.println("You damaged the " + target.getName() + " beyond repair...");
            }
            else {
                System.out.println("The " + target.getName() + " still stands...");
            }
        }
    }

    // Adds new item to inventory or adds to existing bundle if of same name
    public void take(Room currentRoom) {
        ArrayList<Item> items = currentRoom.getItems();

        if (items.size() < 1) {
            System.out.println("There are no items here. You cannot take anything.");
            return;
        }
        
        int targetID = GameCommon.chooseTarget(items);
        boolean validTarget = GameCommon.isTargetValid(items, targetID);

        if (validTarget) {
            Iterator<Item> it = inventory.iterator();
            Item target = GameCommon.getTarget(items, targetID);

            while (it.hasNext()) {
                Item temp = it.next();
                if (temp.equals(target)) {
                    System.out.println("You take " + target.getQuantity() + " more " + target.getName());
                    temp.addTo(target.getQuantity());
                    currentRoom.removeItem(target.getId());
                    return;
                }
            }

            System.out.println("You now have " + target.getQuantity() + " " + target.getName());
            inventory.add(target);
            currentRoom.removeItem(target.getId());
        }
    }

    public void save(GameMap currentMap) {
        Game.save(this, currentMap);
    }

    /* --- Item Actions --- */
    
    // Equip desired Item from inventory
    public void equip(Item target) {
        for (Item item : inventory) {
            if (item.equals(target)) {
                System.out.println("Equipped " + target.getName());
                equippedItem = item;
                return;
            }
        }

        throw new NoSuchItemException(this.toString() + "\tError: Item " + target.toString() + " not found in inventory");
    }

    // Prints description
    public void details(Item target) {
        System.out.println("Details: " + target.details());
    }

    // Discard item
    public void discard(Item target) {
        if (target.isDiscardable()) {
            Iterator<Item> it = inventory.iterator();

            while (it.hasNext()) {
                Item temp = it.next();
                if (temp.equals(target)) {
                    it.remove();
                }
            }
    
            throw new NoSuchItemException(this.toString() + "\tError: Item with ID" + target.getId() + " not found");    
        }
        else {
            System.out.println(target.getName() + " is not discardable");
        }
    }
}
