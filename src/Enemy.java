import java.util.Random;

public class Enemy extends GameCharacter {
    private boolean hostile; // Enemy will attack relentlessly if Player attacks, even if Player leaves the room
    private String talkSound; // Sound the enemy makes when attempting to talk to it

    public Enemy(int id, String name, int healthPoints, int attackPoints, int defensePoints, boolean hostile, String talkSound) {
        super(id, name);
        this.hostile = hostile;
        this.talkSound = talkSound;
    }

    // Make sound
    public void talk() {
        //System.out.println(getName() + ": " + talkSound);
    }

    // TODO: Actually make it move
    public void move() {
        Random r = new Random();
        int moveChance = r.nextInt(12);

        if (moveChance < 4) {
            // Stay where it is
        }
        else if (moveChance < 6) {
            //up
        }
        else if (moveChance < 8) {
            //down
        }
        else if (moveChance < 10) {
            //left
        }
        else if (moveChance < 12) {
            //right
        }
    }

    // Attack player
    public void attack(Player p) {
        System.out.println("The ");// + getName() + " attacks");
        /*p.takeDamage(this.getAttackPoints());*/
    }

    // Damage dealt by Player
    public void takeDamage() {

    }

    // Returns whether the Enemy should be dead
    /*public boolean checkIfDead() {
        if (getHealthPoints() < 1) {
            return true;
        }
        else {
            return false;
        }
    }*/
}
