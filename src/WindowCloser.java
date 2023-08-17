import java.awt.event.*;
class WindowCloser extends WindowAdapter{
    public void windowClosing(WindowEvent evt) {
        evt.getWindow().setVisible(false);
        Game.setRunning(false);
	    System.exit(0);
    }
}
