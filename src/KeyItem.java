// An item that is important for the player to hold onto.
// Cannot be discarded

public class KeyItem extends Item {

    // Key items are not discardable
    public KeyItem(int id, String name, String description, int quantity, int healthPoints, int positionX, int positionY) {
        super(id, name, description, quantity, healthPoints, false, positionX, positionY);
    }

    public int attack() {
        System.out.println("You did 1 damage!");
        return 1;
    }

    // From Item
    public String details() {
        return getName() + "\t* " + getQuantity() + " left,\t* cannot be discarded" + "\n" + getDescription();
    }
}
