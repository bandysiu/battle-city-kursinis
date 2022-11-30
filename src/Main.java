import Screens.StartScreen;
import Screens.Window;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Window window = new Window();
                StartScreen startScreen = new StartScreen(window);

                window.getWindow().add(startScreen);
                window.setVisible(true);
            }
        });
    }
}