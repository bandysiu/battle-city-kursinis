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
        graphics.drawString("Press SPACE to start the game", 240, 320);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
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
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
