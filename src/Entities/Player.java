package Entities;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import Main.Screen;
import Main.InputHandler;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Player extends Entity{

    InputHandler k;
    int health = 10;

    public Player(Screen s, InputHandler k){
        this.s = s;
        this.k = k;

        setPos();

        speed=4;
        dir = "down";

        hitbox = new Rectangle(9,15,13,17);

        getImage();
    }

    public void update(){

        if(k.upPress == true || k.downPress == true || k.leftPress == true || k.rightPress == true) {


            collision = false;
            s.collision.checkTile(this);


            // Handle keyboard input
            if (k.upPress == true) {
                dir = "up";
            } else if (k.downPress == true) {
                dir = "down";
            } else if (k.leftPress == true) {
                dir = "left";
            } else if (k.rightPress == true) {
                dir = "right";
            }


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

                // Bounce back!

                x = oX;
                y=oY;
            }

            // Do sprite animation
            animCount++;
            if (animCount > 15) {
                if (animStep == 1) {
                    animStep = 2;
                } else {
                    animStep = 1;
                }
                animCount = 0;
            }

        }


    }
    // Draw sprite based on directions going
    public void draw(Graphics2D g){
        BufferedImage img = null;
        switch(dir){
            case "up":
                if(animStep == 1){
                    img = up1;
                }else{
                    img = up2;
                }
                break;
            case "down":
                if(animStep == 1){
                    img = down1;
                }else{
                    img = down2;
                }
                break;
            case "left":
                if(animStep == 1){
                    img = left1;
                }else{
                    img = left2;
                }
                break;
            case "right":
                if(animStep == 1){
                    img = right1;
                }else{
                    img = right2;
                }
                break;
        }
        g.drawImage(img,x,y,s.tileSize,s.tileSize,null);

    }
    public void getImage(){

        try{
            File file = new File("resources/player/frog_back1.png");
            up1 = ImageIO.read(file);
            file = new File("resources/player/frog_front1.png");
            down1 = ImageIO.read(file);
            file = new File("resources/player/frog_left1.png");
            left1 = ImageIO.read(file);
            file = new File("resources/player/frog_right1.png");
            right1 = ImageIO.read(file);

            file = new File("resources/player/frog_back2.png");
            up2 = ImageIO.read(file);
            file = new File("resources/player/frog_front2.png");
            down2 = ImageIO.read(file);
            file = new File("resources/player/frog_left2.png");
            left2 = ImageIO.read(file);
            file = new File("resources/player/frog_right2.png");
            right2 = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
