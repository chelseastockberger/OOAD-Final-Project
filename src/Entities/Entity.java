package Entities;
import Main.Screen;
import TileMap.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {

    public int x,y;
    int oX, oY;
    public int speed;
    Screen s;

    public BufferedImage up1,down1,left1,right1,up2,down2,left2,right2;
    public String dir;
    public Rectangle hitbox;
    public int animCount = 0;
    public int animStep = 1;
    public boolean collision = false;

    public void setPos(){
        Tile pos = s.map.getRandomPosition();
        this.x = pos.x;
        this.y = pos.y;
    }


}
