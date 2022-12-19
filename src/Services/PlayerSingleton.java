package Services;

import GameObjects.Tanks.PlayerTank;

public class PlayerSingleton {
    private static PlayerTank instance;

    public static PlayerTank getInstance(){
        if(instance == null){
            instance = new PlayerTank(224, 575);
        }

        return instance;
    }
}
