package GameObjects.Walls;

public class Brick extends Tiles {

    public Brick(int x, int y) {
        super(x, y);
        loadImage("src/Sprites/brick.png");
        setType(2);
        getImageDimensions();
    }
}
