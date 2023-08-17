import java.util.Random;

public class NPC extends GameCharacter {
    private boolean stationary; /// Whether NPC moves rooms or not
    private int messageID; // Which message to use from the file messages.txt
    private static final int MAX_TIME_UNTIL_MOVE = 0; // Move rooms randomly after up to this many turns have passed

    public NPC(int id, String name, int healthPoints, int attackPoints, int defensePoints) {
        super(id, name);
    }

    // * Consider making it so the dialogue is picked based on message ID rather than object ID
    // NPC gives line of dialogue. Message is picked based on its ID in the messages array
    /*public void talk() {
        try {
            System.out.println(Game.getMessage(id));
        } catch (NoSuchMessageException e) {
            System.out.println(this.toString() + "\tError: No message found with that ID");
        }
    }*/

    // Randomly whether to move or not
    public void move() {
        Random randomNumber = new Random();

        int direction = randomNumber.nextInt(5);
        switch (direction) {
            case 0:
                
                break;
        
            default:
                break;
        }
    }
}
