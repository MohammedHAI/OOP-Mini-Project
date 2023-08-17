import java.awt.*;
import java.awt.event.*;

public class TitleScreen extends Frame {
    private static boolean gameStarted = false;

    public TitleScreen(){
        super("Adventure Game");
        this.setLayout(new FlowLayout());

        Label gameTitle = new Label("Adventure Game");
        this.add(gameTitle);
        Button startGameButton = new Button("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TitleScreen.getWindows()[0].setVisible(false);
                gameStarted = true;
                Game.start(null);
            }
        });
        this.add(startGameButton);

        WindowCloser wc = new WindowCloser();
	    this.addWindowListener(wc);

        this.setSize(180, 120);
        setLocation(500, 400);
        this.setVisible(true);
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void main(String[] args) throws Exception {
        TitleScreen title = new TitleScreen();
    }
}
