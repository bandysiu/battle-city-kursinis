package Services;

import GameObjects.Tanks.Bullet;
import GameObjects.Tanks.PlayerTank;
import GameObjects.Walls.Tiles;

import java.awt.*;
import java.util.ArrayList;

public class CollisionService {
    private static ArrayList<Tiles> tiles;

    public void loadObjects(ArrayList<Tiles> tiles) {
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

    public static void checkPlayerBulletWallCollision(PlayerTank tank) {
        for (Tiles tile : tiles) {
            for(Bullet bullet: tank.getBullets()){
                Rectangle bulletRectangle = new Rectangle(bullet.getX(), bullet.getY(), 8, 8);
                if (bulletRectangle.intersects(tile.getBounds())) {
                    bullet.setVisible(false);
                }
            }
        }
    }
}
