package Entities;

import Main.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy extends Entity{

    public BufferedImage image;
    int health;
    int damage;

    public void update(){

        collision = false;
        s.collision.checkTile(this);
        String dirs[] = {"up","down","left","right"};

        // Change direction every so often
        animCount++;
        if (animCount > 100) {
            Random rand = new Random();
            int r = rand.nextInt(4);
            dir = dirs[r];
            animCount = 0;
        }

        // Move
        if (collision == false) {
            oX = x;
            oY = y;
            switch (dir) {
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;

            }
        }else{
            x = oX;
            y = oY;
        }



    }
    // Draw sprite based on directions going
    public void draw(Graphics2D g){

        g.drawImage(image,x,y,s.tileSize,s.tileSize,null);

    }

}

class Ghost extends Enemy {

    public Ghost(Screen s){

        this.s = s;
        health = 5;
        damage = 2;
        setPos();

        speed=1;
        dir = "-";
        hitbox = new Rectangle(9,15,13,17);

        try {

            File file = new File("resources/enemies/ghost_front.png");
            image = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }


    }

}

class Blob extends Enemy{


}

class Isopod extends Enemy{


}

class Skull extends Enemy{


}

class Head extends Enemy{


}