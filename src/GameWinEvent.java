public class GameWinEvent extends GameEvent {
    private Item winItem;

    public GameWinEvent(int id, Item winItem) {
        super(id);
        this.winItem = winItem;
    }

    // Check if the win conditions have been met
    public boolean checkConditions(Player p) {
        if (p.getEquippedItem().equals(winItem)) {
            return true;
        }
        else {
            return false;
        }
    }

    // Prints a message indicating the game is over based on whether the Player won or not
    public void run(boolean gameWon) {
        if (gameWon) {
            System.out.println("You win the game!");
        }
    }
}
