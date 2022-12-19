package GameObjects.Walls;

import GameObjects.Observer;
import GameObjects.Sprite;
import GameObjects.Tanks.Bullet;

import java.util.List;

public class Tiles extends Sprite {

    private int type;

    public Tiles(int x, int y) {
        super(x, y);
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

    public void setType(int type) {this.type = type;}
    public int getType() {
        return this.type;
    }
}
