package Screens;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JPanel window;

    public JPanel getWindow() {
        return window;
    }

    public Window() {
        initializeWindow();
    }

    private void initializeWindow() {

        setTitle("Battle City");

        window = new JPanel();
        window.setLayout(new GridLayout(1, 0));
        window.setMinimumSize(new Dimension(640, 640));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(window, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(window, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }
}
