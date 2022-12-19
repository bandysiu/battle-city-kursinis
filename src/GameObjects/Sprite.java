package GameObjects;

import GameObjects.Tanks.Bullet;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.ImageIcon;

public abstract class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    protected int lastDir = 4;
    protected int dirX;
    protected int dirY;
    protected int direction;
    protected int directionTimer;
    protected int directionUpdate;
    protected int fireTimer;
    protected int fireUpdate;
    protected int speed;

    protected int health;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public abstract List<Bullet> getBullets();

    public abstract int getDirX();

    public abstract int getDirY();

    public abstract void tankAI();

    public abstract void notifyObservers();
    public abstract void addObserver(Observer observer);
}