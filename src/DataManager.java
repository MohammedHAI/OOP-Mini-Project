import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Has methods for reading and writing different types of files needed by the game

public class DataManager {
    public String filePath;

    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    public void setPath(String filePath) {
        this.filePath = filePath;
    }

    // Writes a GameObject to a file
    public void writeGameObjectToFile(GameObject object) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filePath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);    

        try {
            objectOut.writeObject(object);
        }
        catch (IOException e) {}
        finally {
            objectOut.close();
        }
    }

    // Writes an ArrayList of GameObjects to a file
    public <T> void writeGameObjectsToFile(ArrayList<T> objects) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filePath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);    

        try {
            objectOut.writeInt(objects.size());
            for (T gameObject : objects) {
                objectOut.writeObject(gameObject);
            }
        }
        catch (IOException e) {}
        finally {
            objectOut.close();
        }
    }

    // Reads in an object from a file and returns it
    public GameObject readGameObjectFromFile() throws IOException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        ArrayList<GameObject> objects = new ArrayList<>();

        try {
            GameObject temp = (GameObject) objectIn.readObject();
            return temp;
        }
        catch (Exception e) { return null; }
        finally {
            objectIn.close();
        }
    }

    // Reads in objects from a file and returns an ArrayList of them
    public ArrayList<GameObject> readGameObjectsFromFile() throws IOException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        ArrayList<GameObject> objects = new ArrayList<>();

        try {
            int noOfObjects = objectIn.readInt();
            for (int i = 0; i < noOfObjects; i++) {
                GameObject temp = (GameObject) objectIn.readObject();
                objects.add(temp);
            }
        }
        catch (Exception e) {}
        finally {
            objectIn.close();
        }

        return objects;
    }
}
