package GameObjects.Walls;

public class Tiles extends Sprite {

    private int type;

    public Tiles(int x, int y) {
        super(x, y);
    }
    public void setType(int type) {this.type = type;}
    public int getType() {
        return this.type;
    }
}
