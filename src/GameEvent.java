/* Game events can include:
    - User input
    - Changing Player stats
    - Placeholder events
*/

public abstract class GameEvent extends GameObject {
    public GameEvent(int id) {
        super(id);
    }

    public abstract boolean checkConditions(Player p);
    public abstract void run(boolean gameWon);
}
