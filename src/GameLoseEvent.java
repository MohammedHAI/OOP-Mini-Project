public class GameLoseEvent extends GameEvent {
    private Item loseItem;

    public GameLoseEvent(int id, Item loseItem) {
        super(id);
        this.loseItem = loseItem;
    }

    public boolean checkConditions(Player p) {
        if (p.getEquippedItem().equals(loseItem)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void run(boolean gameWon) {
        if (!gameWon) {
            System.out.println("You lost the game...");
        }
    }
}