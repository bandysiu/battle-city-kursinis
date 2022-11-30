package GameObjects.Tanks;

import GameObjects.Walls.Sprite;
import Services.CollisionService;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerTank extends Sprite {

    private int health = 3;

    private int dirX;
    private int dirY;

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

        Rectangle tank = new Rectangle(x + dirX, y + dirY, width, height);

//        System.out.println(tank.getBounds());

        if(!CollisionService.checkTankCollision(tank)){
            x += dirX;
            y += dirY;
        }

        if ( x < 32 ) {
            x = 32;
        }
        if ( x > 576 ) {
            x = 576;
        }
        if ( y < 32 ) {
            y = 32;
        }
        if ( y > 576 ) {
            y = 576;
        }
    }

    public void fire() {

    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            dirX = 0;
            dirY = -1;
        }
        if (key == KeyEvent.VK_DOWN) {
            dirX = 0;
            dirY = 1;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dirX = 1;
            dirY = 0;
        }
        if (key == KeyEvent.VK_LEFT) {
            dirX = -1;
            dirY = 0;
        }
    }

    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            dirY = 0;
        }
        if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            dirY = 0;
        }
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            dirX = 0;
        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            dirX = 0;
        }
    }

    public void setX(int x) {
        this.x += x;
    }

    public int getHealth() {
        return this.health;
    }
}
