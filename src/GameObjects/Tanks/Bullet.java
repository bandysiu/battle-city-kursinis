package GameObjects.Tanks;

import GameObjects.Sprite;

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
}
