package GameObjects.Walls;

public class Wall extends Tiles {

    public Wall(int x, int y) {
        super(x, y);
        loadImage("src/Sprites/wall.png");
        setType(1);
        getImageDimensions();
    }
}
