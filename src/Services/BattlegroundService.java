package Services;

import GameObjects.EnemyTankObserver;
import GameObjects.Sprite;
import GameObjects.Tanks.Bullet;
import GameObjects.Tanks.EnemyTank;
import GameObjects.Tanks.PlayerTank;
import GameObjects.Walls.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattlegroundService {
    public List<Sprite> initializeEnemyTank() {
        List<Sprite> enemyTanks = new ArrayList<>();
        Random random = new Random();
        while (enemyTanks.size() < 5) {
            int randomX = random.nextInt(576) + 32;
            int randomY = random.nextInt(240) + 32;

            Sprite tank = new EnemyTank(randomX, randomY);

            if (!CollisionService.checkEnemyTankWallCollision((EnemyTank) tank)) {
                enemyTanks.add(tank);
                new EnemyTankObserver(tank);
            }
        }
        return enemyTanks;
    }

    public List<Tiles> initializeTiles(int level) {
        List<Tiles> tiles = new ArrayList<>();
        List<List<Integer>> matrixList = LevelService.readLevelFile(level);

        int x = 0;
        int y = 0;

        for(List<Integer> list: matrixList){
            for(Integer tile: list){
                TileType tileType = TileType.getTypeFromValue(tile);

                switch (tileType) {
                    case WALL -> tiles.add(new Wall(x * 32, y * 32));
                    case BRICK -> tiles.add(new Brick(x * 32, y * 32));
                    case BASE -> tiles.add(new Base(x * 32, y * 32));
                }

                x++;
            }
            x = 0;
            y++;
        }

        return tiles;
    }

    public void updatePlayerTank() {
        PlayerTank tank = PlayerSingleton.getInstance();

        tank.move();

        if (tank.getHealth() < 0) {
            tank.setVisible(false);
        }
    }

    public void updatePlayerTankBullets() {
        PlayerTank tank = PlayerSingleton.getInstance();

        List<Bullet> tankBullets = tank.getBullets();

        for (Bullet bullet : tankBullets) {
            if (bullet.isVisible()) {
                bullet.move();
                CollisionService.checkBulletWallCollision(bullet);
            }
        }

        tankBullets.removeIf(bullet -> !bullet.isVisible());
    }

    public void updateEnemyTanks(List<Sprite> tanks) {

        for (Sprite tank : tanks) {
            tank.tankAI();
        }

        tanks.removeIf(tank -> !tank.isVisible());
    }

    public void updateEnemyTankBullets(List<Sprite> tanks) {
        for (Sprite tank : tanks) {
            List<Bullet> tankBullets = tank.getBullets();

            for (Bullet bullet : tankBullets) {
                if (bullet.isVisible()) {
                    bullet.move();
                    CollisionService.checkBulletWallCollision(bullet);
                }
            }

            tankBullets.removeIf(bullet -> !bullet.isVisible());
        }
    }
}
