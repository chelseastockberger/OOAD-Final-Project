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
    public int health;
    public int maxHealth;

    public BufferedImage up1,down1,left1,right1,up2,down2,left2,right2;
    public BufferedImage upAttack, downAttack, leftAttack, rightAttack;
    public BufferedImage upBlock, block;
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

    public boolean isDead(){
        if(health <= 0){
            return true;
        }else{
            return false;
        }
    }

    public void updateHealth(int amt){
        health = health+amt;
    }


}
