import java.io.Serializable;

// Everything in the game is modeled by a GameObject.
public class GameObject implements Serializable {
    private int id;

    public GameObject(int id) {
        this.id = id;
    }

    // Provides barebones information for the GameObject
    public String toString() {
        return "ID: " + id;
    }

    public int getId() {
        return id;
    }
}
