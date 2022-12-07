package TileMap;

import java.awt.*;

public class Tile {

    public int x,y;
    public Rectangle rect;
    public TileType type;

    public Tile(TileType type){
        this.type = type;
        rect = new Rectangle(0,0,32,32);
    }


}
