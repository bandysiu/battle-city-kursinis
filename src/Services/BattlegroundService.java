package Services;

import GameObjects.Tanks.EnemyTank;
import GameObjects.Tanks.PlayerTank;
import GameObjects.Walls.Tiles;

import java.util.ArrayList;

public class BattlegroundService {

    private PlayerTank tank;
    private ArrayList<EnemyTank> enemy = new ArrayList<>();
    private ArrayList<Tiles> tiles = new ArrayList<>();

    private CollisionService collisionService = new CollisionService();

    public void loadObjects(PlayerTank tank, ArrayList<EnemyTank> enemy, ArrayList<Tiles> tiles) {
        this.tank = tank;
        this.enemy = enemy;
        this.tiles = tiles;
    }

    public void updatePlayerTank() {
        tank.move();
    }

    public void updateBoardTiles() {

    }

    public void updateCollisions() {

    }
}
