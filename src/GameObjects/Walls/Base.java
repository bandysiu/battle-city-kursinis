package GameObjects.Walls;

public class Base extends Tiles {

    public Base(int x, int y) {
        super(x, y);
        loadImage("src/Sprites/base.png");
        setType(3);
        getImageDimensions();
    }
}
