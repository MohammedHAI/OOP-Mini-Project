public class GameCharacter extends GameObject implements Tangible {
    
    // ! UNUSED - SALVAGE METHODS AND MOVE THEM INTO PLAYER
    private String name;
    private int positionX, positionY;

    public GameCharacter(int id, String name) {
        super(id);
        this.name = name;
    };

    

    //public abstract void talk();
    //public abstract void move();



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