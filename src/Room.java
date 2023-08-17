// Each room is contained within a Map
// They house some objects

import java.util.ArrayList;
import java.util.Iterator;

public class Room extends GameObject implements Tangible {
    private String name;
    private String description;
    private ArrayList<Item> items;
    private int positionX, positionY; // From Tangible

    public Room(int id, String name, String description, ArrayList<Item> items, int positionX, int positionY) {
        super(id);
        this.name = name;
        this.description = description;
        this.items = items;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    // Gets all items
    public ArrayList<Item> getItems() {
        return items;
    }

    // Adds a new Item to the end of the list
    public void addItem(Item i) {
        items.add(i);
        GameMap.incrementNextID();;
    }

    public void removeItem(int id) {
        Iterator<Item> it = items.iterator();

        while (it.hasNext()) {
            Item temp = it.next();
            if (temp.getId() == id) {
                it.remove();
                return;
            }
        }

        throw new NoSuchItemException(this.toString() + "\tError: Item with ID" + id + " not found");
    }

    public String details() {
        return "Current Room: " + name + "\n" + description;
    }

    // Print the list of items in the room
    public void printItems() {
        int count = 0;
        for (Item item : items) {
            count++;
            System.out.println(count + ": " + item.getName() + "\tx" + item.getQuantity());
        }
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
}
