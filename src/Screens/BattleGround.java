package Screens;

import GameObjects.Sprite;
import GameObjects.Tanks.Bullet;
import GameObjects.Tanks.PlayerTank;
import GameObjects.Walls.*;
import Services.BattlegroundService;
import Services.CollisionService;
import Services.PlayerSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BattleGround extends JPanel implements ActionListener {
    BattlegroundService battlegroundService = new BattlegroundService();
    CollisionService collisionService = new CollisionService();
    private List<Sprite> enemy;
    private List<Tiles> tiles;
    private Window window;
    private int level;
    private Timer timer;
    private final int DELAY = 10;

    private Image gameover;

    private boolean over;

    public BattleGround(Window window) {
        initializeBattleground(window);
    }

    private void initializeBattleground(Window window) {
        setBackground(Color.BLACK);
        timer = new Timer(DELAY, this);
        timer.start();
        addKeyListener(new TAdapter());
        setFocusable(true);
        loadObjects(1, window);
    }

    private void loadObjects(int level, Window window) {
        this.window = window;
        this.level = level;

        this.tiles = battlegroundService.initializeTiles(level);
        collisionService.loadObjects(this.tiles);

        this.enemy = battlegroundService.initializeEnemyTank();

        ImageIcon icon = new ImageIcon("src/Sprites/game_over.png");
        this.gameover = icon.getImage();
        this.over = false;
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
        if (!over) {
            checkGameOver();

            battlegroundService.updatePlayerTank();
            battlegroundService.updatePlayerTankBullets();
            battlegroundService.updateEnemyTanks(enemy);
            battlegroundService.updateEnemyTankBullets(enemy);

            updateCollisions();
        }

        if (enemy.isEmpty()){
            this.over = true;
        }
    }

    private void updateCollisions() {
        CollisionService.checkPlayerBulletHit(enemy);
        CollisionService.checkEnemyBulletHit(enemy);
    }

    private void drawObjects(Graphics graphics) {

        for (Tiles tile : tiles) {
            graphics.drawImage(tile.getImage(), tile.getX(), tile.getY(), this);
        }

        PlayerTank tank = PlayerSingleton.getInstance();

        for (Bullet bullet : tank.getBullets()) {
            graphics.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
        }

        for (Sprite enemyTank : enemy) {
            graphics.drawImage(enemyTank.getImage(), enemyTank.getX(), enemyTank.getY(), this);

            for (Bullet bullet : enemyTank.getBullets()) {
                graphics.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }

        graphics.drawImage(tank.getImage(), tank.getX(), tank.getY(), this);

        if (over) {
            graphics.drawImage(gameover, 216, 240, this);
        }
    }

    private void checkGameOver() {
        PlayerTank tank = PlayerSingleton.getInstance();

        if (tank.getHealth() <= 0 || CollisionService.checkBulletBaseCollision(enemy)) {
            this.over = true;
        }
    }

    private class TAdapter extends KeyAdapter {

        PlayerTank tank = PlayerSingleton.getInstance();
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
