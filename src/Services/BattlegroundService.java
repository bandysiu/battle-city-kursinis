package Services;

import GameObjects.Tanks.Bullet;
import GameObjects.Tanks.PlayerTank;
import GameObjects.Walls.*;

import java.util.ArrayList;

public class BattlegroundService {

    public PlayerTank initializePlayerTank(int x, int y){
        return new PlayerTank(x, y);
    }

    public ArrayList<Tiles> initializeTiles(int level) {
        ArrayList<Tiles> tiles = new ArrayList<>();
        int[][] newLevel = LevelService.getLevelTiles(level);
        int temp;

        for (int i = 0; i < newLevel.length; i++) {
            for (int j = 0; j < newLevel[0].length; j++) {
                temp = newLevel[i][j];
                TileType tileType = TileType.getTypeFromValue(temp);

                switch (tileType) {
                    case WALL -> tiles.add(new Wall(j * 32, i * 32));
                    case BRICK -> tiles.add(new Brick(j * 32, i * 32));
                    case BASE -> tiles.add(new Base(j * 32, i * 32));
                }
            }
        }
        return tiles;
    }

    public void updatePlayerTank(PlayerTank tank) {
        tank.move();
    }

    public void updatePlayerTankBullets(PlayerTank tank) {
        ArrayList<Bullet> tankBullets = tank.getBullets();

        for(Bullet bullet: tankBullets){
            if(bullet.isVisible()){
                bullet.move();
                CollisionService.checkPlayerBulletWallCollision(tank);
            }
        }

        tankBullets.removeIf(bullet -> !bullet.isVisible());
    }
}
