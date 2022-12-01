package GameObjects.Tanks;

import GameObjects.Sprite;
import Services.CollisionService;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayerTank extends Sprite {

    private int health = 3;

    private int lastDir = 3;

    private int speed = 10;
    private int dirX;
    private int dirY;

    private ArrayList<Bullet> bullets = new ArrayList<>();

    public PlayerTank(int x, int y) {
        super(x, y);
        loadImage("src/Sprites/player_tank.png");
        getImageDimensions();
    }

    public void damageTank(int damage) {
        if (!(this.health - damage <= 0)) {
            this.health -= damage;
        }
    }

    public void move() {

        if (x < 32) {
            x = 32;
        }
        if (x > 576) {
            x = 576;
        }
        if (y < 32) {
            y = 32;
        }
        if (y > 576) {
            y = 576;
        }

        if (!CollisionService.checkPlayerTankWallCollision(this)) {
            x += dirX;
            y += dirY;
        }
    }

    public void fire() {
        Bullet bullet;
        switch (this.lastDir) {
            case 1, 2 -> {
                bullet = new Bullet(true, this.lastDir, x, y + 12);
                bullets.add(bullet);
            }

            case 3, 4 -> {
                bullet = new Bullet(true, this.lastDir, x + 12, y);
                bullets.add(bullet);
            }
        }
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            dirX = 0;
            dirY = -1;
            lastDir = 4;
        }
        if (key == KeyEvent.VK_DOWN) {
            dirX = 0;
            dirY = 1;
            lastDir = 3;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dirX = 1;
            dirY = 0;
            lastDir = 1;
        }
        if (key == KeyEvent.VK_LEFT) {
            dirX = -1;
            dirY = 0;
            lastDir = 2;
        }
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            dirY = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dirY = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dirX = 0;
        }
        if (key == KeyEvent.VK_LEFT) {
            dirX = 0;
        }
        if (key == KeyEvent.VK_SPACE && getBullets().size() < 3){
            fire();
        }
    }

    public int getHealth() {
        return this.health;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
