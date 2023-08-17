// Food Items can be used to heal Characters
// NOTE: Healing functionality not currently implemented

public class Food extends Item {
    private int healingAmount;

    // By default, food is discardable unless specified otherwise (see below)
    public Food(int id, String name, String description, int quantity, int healthPoints, boolean discardable, int healingAmount, int positionX, int positionY) {
        super(id, name, description, quantity, healthPoints, discardable, positionX, positionY);
        this.healingAmount = healingAmount;
    }

    // Shorthand for discardable food
    public Food(int id, String name, String description, int quantity, int healthPoints, int healingAmount, int positionX, int positionY) {
        super(id, name, description, quantity, healthPoints, true, positionX, positionY);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public int attack() {
        System.out.println("You did no damage!");
        return 0;
    }

    // From Item
    public String details() {
        return getName() + "\t* " + getQuantity() + " left,\t* heals " + healingAmount + "\n" + getDescription();
    }
}
