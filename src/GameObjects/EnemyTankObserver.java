package GameObjects;

import GameObjects.Tanks.Bullet;

import java.util.List;

public class EnemyTankObserver extends Observer {

    Sprite tank;

    public EnemyTankObserver(Sprite tank) {
        this.tank = tank;
    }

    @Override
    public void update() {
        List<Bullet> bullets = this.tank.getBullets();

        for (Bullet bullet : bullets){
            bullet.setVisible(false);
        }
    }
}
