package GameObjects.Walls;

public enum TileType {
    EMPTY(0),
    WALL(1),
    BRICK(2),
    BASE(3);

    private final int value;

    private TileType(int value) {
        this.value = value;
    }
    public static TileType getTypeFromValue(int value) {
        return TileType.values()[value];
    }

    public int getTileValue(){
        return value;
    }
}
