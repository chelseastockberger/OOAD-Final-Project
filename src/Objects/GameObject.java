package Objects;

import Main.Screen;
import TileMap.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    public int x,y;
    public BufferedImage image;
    public BufferedImage destroyedimage;
    public String name;
    public boolean collision = false;
    Screen s;
    public boolean isDestroyed = false;

    public void setPos(){
        Tile pos = s.map.getRandomPosition();
        this.x = pos.x;
        this.y = pos.y;
    }


    public void draw(Graphics2D g){

        g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);

    }

    public void setDestroyed(){

        this.isDestroyed = true;
        this.image = destroyedimage;
        this.collision = false;


    }



}
