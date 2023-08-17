// Items can be obtained by the Player.
// When used, they can have a certain effect
// When discarded, they leave the player's inventory but not the game map
// In the player's inventory they should have coordinates (-1, -1)

public abstract class Item extends GameObject implements Tangible {
    private String name;
    private String description;
    private int quantity;
    private int healthPoints;
    private boolean discardable;
    private int positionX; // From Tangible
    private int positionY; // From Tangible

    // Items added to the game are not added to the player's inventory immediately
    public Item(int id, String name, String description, int quantity, int healthPoints, boolean discardable, int positionX, int positionY) {
        super(id);
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.healthPoints = healthPoints;
        this.discardable = discardable;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public abstract String details();
    public abstract int attack();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Specifically, how many of this Item are left in the Player's inventory.
    // This is not used anywhere else
    public int getQuantity() {
        return quantity;
    }

    public boolean isDiscardable() {
        return discardable;
    }

    // Deducts health when the Player attacks
    public void takeDamage(int damage) {
        if (healthPoints < 1) {
            System.out.println("It is already broken...");
        }
        else {
            healthPoints -= damage;
        }
    }

    // Checks whether the Item is damaged completely
    public boolean isBroken() {
        if (healthPoints < 1) {
            healthPoints = 0;
            return true;
        }
        else {
            return false;
        }
    }

    // Adds to the item's quantity
    public void addTo(int amount) {
        quantity += amount;
    }

    // Checks if object is equal by name only
    public boolean equals(Object obj) {
        Item temp = (Item) obj;
        return (temp.getName().equals(this.getName()));
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
