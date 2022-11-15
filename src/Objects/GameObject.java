package Objects;

import Main.Screen;
import TileMap.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    public int x,y;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    Screen s;

    public void setPos(){
        Tile pos = s.map.getRandomPosition();
        this.x = pos.x;
        this.y = pos.y;
    }

    public void draw(Graphics2D g){

        g.drawImage(image,x,y,s.tileSize,s.tileSize,null);

    }



}
