// Weapons are used to attack items

public class Weapon extends Item {
    private int attackPoints;

    public Weapon(int id, String name, String description, int quantity, int healthPoints, boolean discardable, int attackPoints, int positionX, int positionY) {
        super(id, name, description, quantity, healthPoints, discardable, positionX, positionY);
        this.attackPoints = attackPoints;
    }

    public int attack() {
        System.out.println("You did " + attackPoints + " damage!");
        return attackPoints;
    }

    // From Item
    public String details() {
        return getName() + "\t* " + getQuantity() + " left\t* attack points: " + attackPoints + "\n" + getDescription();
    }
}
