package GameObjects.Tanks;

import GameObjects.Observer;
import GameObjects.Sprite;
import Services.CollisionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyTank extends Sprite {

    private static List<Observer> observers = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();

    public EnemyTank(int x, int y) {
        super(x, y);
        loadImage("src/Sprites/enemy_tank.png");
        getImageDimensions();
        this.directionTimer = 0;
        this.directionUpdate = 40;
        this.fireTimer = 0;
        this.fireUpdate = 50;
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

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    @Override
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

    @Override
    public List<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public int getDirX() {
        return dirX;
    }

    @Override
    public int getDirY() {
        return dirY;
    }
}
