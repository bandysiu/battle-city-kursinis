package GameObjects.Tanks;

import GameObjects.Observer;
import GameObjects.Sprite;
import Services.CollisionService;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayerTank extends Sprite {
    private List<Bullet> bullets = new ArrayList<>();

    public PlayerTank(int x, int y) {
        super(x, y);
        loadImage("src/Sprites/player_tank.png");
        getImageDimensions();
        this.health = 1;
        this.direction = 3;
        this.speed = 1;
    }

    public void damageTank(int damage) {
        this.health -= damage;
    }

    public void move() {
        if (!CollisionService.checkPlayerTankWallCollision()) {
            x += dirX;
            y += dirY;
        }
    }

    public void fire() {
        Bullet bullet;

        switch (this.direction) {
            case 1, 2 -> {
                bullet = new Bullet(true, this.direction, x, y + 12);
                bullets.add(bullet);
            }

            case 3, 4 -> {
                bullet = new Bullet(true, this.direction, x + 12, y);
                bullets.add(bullet);
            }
        }
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            dirX = 0;
            dirY = -1 * speed;
            direction = 4;
        }
        if (key == KeyEvent.VK_DOWN) {
            dirX = 0;
            dirY = 1 * speed;
            direction = 3;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dirX = 1 * speed;
            dirY = 0;
            direction = 1;
        }
        if (key == KeyEvent.VK_LEFT) {
            dirX = -1 * speed;
            dirY = 0;
            direction = 2;
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
        if (key == KeyEvent.VK_SPACE && getBullets().size() < 3) {
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

    @Override
    public void tankAI() {
    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void addObserver(Observer observer) {

    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
