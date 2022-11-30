package Services;

import GameObjects.Walls.TileType;
import GameObjects.Walls.Tiles;

import java.awt.*;
import java.util.ArrayList;

public class CollisionService {
    private static ArrayList<Tiles> tiles;

    public void loadObjects(ArrayList<Tiles> tiles) {
        CollisionService.tiles = tiles;
    }

    public static boolean checkTankCollision(Rectangle tank) {
        for (Tiles tile : tiles) {

            if (tank.intersects(tile.getBounds())) {
                return true;
            }
        }
        return false;
    }
}
