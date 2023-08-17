// A Tangible GameObject has coordinates in the GameMap
// If the GameObject does not physically exist within the GameMap, then it should not implement Tangible
public interface Tangible {
    public abstract int getPositionX();
    public abstract int getPositionY();
    public abstract void setPosition(int newX, int newY);
}
