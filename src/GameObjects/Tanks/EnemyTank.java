package GameObjects.Tanks;

import GameObjects.Sprite;
import Services.CollisionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyTank extends Sprite {
    private int lastDir = 4;
    private int dirX;
    private int dirY;
    private int direction;
    private int directionTimer;
    private int directionUpdate;
    private int fireTimer;
    private int fireUpdate;
    private int speed;
    private List<Bullet> bullets = new ArrayList<>();

    public EnemyTank(int x, int y) {
        super(x, y);
        loadImage("src/Sprites/enemy_tank.png");
        getImageDimensions();
        this.directionTimer = 0;
        this.directionUpdate = 40;
        this.fireTimer = 0;
        this.fireUpdate = 80;
        this.direction = 4;
        this.speed = 2;
    }

    private void move(){
        if (!CollisionService.checkEnemyTankWallCollision(this)){
            x += dirX;
            y += dirY;
        }
    }

    private void chooseRandomDirection(){
        Random random = new Random();
        this.direction = random.nextInt(4) + 1;
        turnToDirection();
    }

    private void turnToDirection(){
        switch (this.direction){
            case 1 -> {
                this.dirX = this.speed;
                this.dirY = 0;
            }
            case 2 -> {
                this.dirX = -this.speed;
                this.dirY = 0;
            }

            case 3 -> {
                this.dirY = -this.speed;
                this.dirX = 0;
            }

            case 4 -> {
                this.dirY = this.speed;
                this.dirX = 0;
            }

        }
    }

    public void tankAI(){
        if (this.directionUpdate <= this.directionTimer){
            chooseRandomDirection();
            this.directionTimer = 0;
        } else {
            this.directionTimer++;
        }

        if (this.fireUpdate <= this.fireTimer){
            fire();
            this.fireTimer = 0;
        } else {
            this.fireTimer++;
        }

        move();
    }

    private void fire(){
        Bullet bullet;
        switch (this.direction) {
            case 1, 2 -> {
                bullet = new Bullet(false, this.direction, x, y + 12);
                bullets.add(bullet);
            }
            case 3, 4 -> {
                bullet = new Bullet(false, this.direction, x + 12, y);
                bullets.add(bullet);
            }
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }
}
