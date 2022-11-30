package Screens;

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
    private ArrayList<EnemyTank> enemy;
    private final ArrayList<Tiles> tiles = new ArrayList<>();
    private Window window;
    private int level;

    private Timer timer;
    private final int DELAY = 10;

    public BattleGround(Window window) {
        this.window = window;

        initializeBattleground();
    }

    public void initializeBattleground() {
        setBackground(Color.BLACK);

        timer = new Timer(DELAY, this);
        timer.start();

        addKeyListener(new TAdapter());
        setFocusable(true);

        level = 1;
        tank = new PlayerTank(224, 576);

        initializeTiles();

        battlegroundService.loadObjects(tank, enemy, tiles);
        collisionService.loadObjects(tiles);
    }

    public void initializeTiles() {
        int[][] newLevel = LevelService.getLevel(level);
        int type;

        for (int i = 0; i < newLevel.length; i++) {
            for (int j = 0; j < newLevel[0].length; j++) {
                type = newLevel[i][j];
                TileType tileType = TileType.getTypeFromValue(type);

                switch (tileType) {
                    case WALL -> tiles.add(new Wall(j * 32, i * 32));
                    case BRICK -> tiles.add(new Brick(j * 32, i * 32));
                    case BASE -> tiles.add(new Base(j * 32, i * 32));
                }
            }
        }
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

    public void update() {
        battlegroundService.updatePlayerTank();
    }

    private void drawObjects(Graphics graphics) {
        for (Tiles tile : tiles) {
            graphics.drawImage(tile.getImage(), tile.getX(), tile.getY(), this);
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
