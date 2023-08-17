// Should be thrown when specifying Room coordinates but does not exist
public class NoSuchRoomException extends RuntimeException{
    public NoSuchRoomException(String message) {
        super(message);
    }
}
