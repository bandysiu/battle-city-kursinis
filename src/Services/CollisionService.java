package Services;

import GameObjects.Tanks.Bullet;
import GameObjects.Tanks.EnemyTank;
import GameObjects.Tanks.PlayerTank;
import GameObjects.Walls.TileType;
import GameObjects.Walls.Tiles;

import java.awt.*;
import java.util.List;

public class CollisionService {
    private static List<Tiles> tiles;

    public void loadObjects(List<Tiles> tiles) {
        CollisionService.tiles = tiles;
    }

    public static boolean checkPlayerTankWallCollision(PlayerTank tank) {

        Rectangle tankRectangle = new Rectangle(tank.getX() + tank.getDirX(), tank.getY() + tank.getDirY(), 32, 32);
        for (Tiles tile : tiles) {
            if (tankRectangle.intersects(tile.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkEnemyTankWallCollision(EnemyTank enemyTank) {
        Rectangle tankRectangle = new Rectangle(enemyTank.getX() + enemyTank.getDirX(), enemyTank.getY() + enemyTank.getDirY(), 32, 32);
        for (Tiles tile : tiles) {
            if (tankRectangle.intersects(tile.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static void checkBulletWallCollision(Bullet bullet) {
        for (Tiles tile : tiles) {
            Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), 8, 8);
            if (bulletRectangle.intersects(tile.getBounds())) {
                bullet.setVisible(false);
            }
        }
    }

    public static boolean checkBulletBaseCollision(List<EnemyTank> enemyTanks){
        for(EnemyTank enemyTank: enemyTanks){
            List<Bullet> bullets = enemyTank.getBullets();
            for(Bullet bullet: bullets){
                for (Tiles tile : tiles) {
                    Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), 9, 9);
                    if (bulletRectangle.intersects(tile.getBounds())) {
                        if(tile.getType() == TileType.BASE.getTileValue()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static void checkPlayerBulletHit(List<EnemyTank> enemyTanks, PlayerTank player) {
        List<Bullet> bullets = player.getBullets();

        for (EnemyTank tank : enemyTanks) {
            Rectangle tankRectangle = new Rectangle(tank.getX() + tank.getDirX(), tank.getY() + tank.getDirY(), 32, 32);
            for (Bullet bullet : bullets) {
                if (tankRectangle.intersects(bullet.getBounds())) {
                    tank.setVisible(false);
                    bullet.setVisible(false);
                }
            }
        }
    }

    public static void checkEnemyBulletHit(List<EnemyTank> enemyTanks, PlayerTank player) {
        Rectangle tankRectangle = new Rectangle(player.getX() + player.getDirX(), player.getY() + player.getDirY(), 32, 32);

        for (EnemyTank tank : enemyTanks) {
            List<Bullet> bullets = tank.getBullets();
            {
                for (Bullet bullet : bullets) {
                    if (tankRectangle.intersects(bullet.getBounds())) {
                        player.damageTank(1);
                        bullet.setVisible(false);
                    }
                }
            }
        }
    }
}
