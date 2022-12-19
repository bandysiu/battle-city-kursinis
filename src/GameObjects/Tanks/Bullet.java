package GameObjects.Tanks;

import GameObjects.Observer;
import GameObjects.Sprite;

import java.util.List;

public class Bullet extends Sprite {

    private int direction;
    private boolean playerBullet;

    public Bullet(boolean playerBullet, int direction, int x, int y) {
        super(x, y);
        this.playerBullet = playerBullet;
        this.direction = direction;
        loadImage("src/Sprites/bullet.png");
        getImageDimensions();
    }

    public void move() {
        switch (this.direction) {
            case 1 -> x += 2;
            case 2 -> x -= 2;
            case 3 -> y += 2;
            case 4 -> y -= 2;
        }
    }

    public boolean isPlayerBullet() {
        return playerBullet;
    }

    @Override
    public List<Bullet> getBullets() {
        return null;
    }

    @Override
    public int getDirX() {
        return 0;
    }

    @Override
    public int getDirY() {
        return 0;
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
}
