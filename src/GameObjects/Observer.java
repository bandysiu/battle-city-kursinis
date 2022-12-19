package GameObjects;

import GameObjects.Tanks.EnemyTank;

public abstract class Observer {
    protected EnemyTank enemyTank;
    public abstract void update();
}