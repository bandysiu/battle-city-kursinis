package Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartScreen extends JPanel implements ActionListener, KeyListener {

    public Window window;

    public StartScreen(Window window) {
        this.setBackground(Color.LIGHT_GRAY);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);
        this.window = window;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Press ENTER to start the game", 240, 320);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            loadBattleground();
        }
    }

    public void loadBattleground() {
        BattleGround battleground = new BattleGround(window);

        window.getWindow().removeAll();
        window.getWindow().add(battleground);
        window.setVisible(true);

        battleground.requestFocusInWindow();
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }
}
