package Screens;

import GameObjects.Tanks.Bullet;
import GameObjects.Tanks.EnemyTank;
import GameObjects.Tanks.PlayerTank;
import GameObjects.Walls.*;
import Services.BattlegroundService;
import Services.CollisionService;
import Services.LevelService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BattleGround extends JPanel implements ActionListener {
    BattlegroundService battlegroundService = new BattlegroundService();
    CollisionService collisionService = new CollisionService();

    private PlayerTank tank;
    private ArrayList<EnemyTank> enemy = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Tiles> tiles = new ArrayList<>();
    private Window window;
    private int level;
    private Timer timer;
    private final int DELAY = 10;

    public BattleGround(Window window) {
        initializeBattleground(window);
    }

    private void initializeBattleground(Window window) {
        setBackground(Color.BLACK);
        timer = new Timer(DELAY, this);
        timer.start();
        addKeyListener(new TAdapter());
        setFocusable(true);
        loadObjects(1, 224, 575, window);
    }

    private void loadObjects(int level, int tankX, int tankY, Window window) {
        this.window = window;
        this.level = level;
        this.tiles = battlegroundService.initializeTiles(level);
        this.tank = battlegroundService.initializePlayerTank(tankX, tankY);
        collisionService.loadObjects(this.tiles);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawObjects(graphics);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update() {
        battlegroundService.updatePlayerTank(tank);
        battlegroundService.updatePlayerTankBullets(tank);
    }

    private void drawObjects(Graphics graphics) {

        for (Tiles tile : tiles) {
            graphics.drawImage(tile.getImage(), tile.getX(), tile.getY(), this);
        }

        for (Bullet bullet : tank.getBullets()) {
            graphics.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
        }

        graphics.drawImage(tank.getImage(), tank.getX(), tank.getY(), this);
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            tank.keyPressed(event);
        }

        @Override
        public void keyReleased(KeyEvent event) {
            tank.keyReleased(event);
        }
    }
}
