package Entities;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import Main.Screen;
import Main.InputHandler;
import Objects.GameObject;
import Objects.ItemWeaponFactory;
import Objects.Items;
import Objects.Weapons;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Player extends Entity{

    InputHandler k;
    BufferedImage levelimg;
    int attackCount = 0;
    int blockCount = 0;
    boolean attacking;
    boolean blocking;
    public int damage;
    public int defense;
    ArrayList<Items> items;
    public Weapons weapon;

    public Player(Screen s, InputHandler k){

        health = 100;
        maxHealth = 100;
        defense = 0;
        this.s = s;
        this.k = k;

        ItemWeaponFactory w = new ItemWeaponFactory();
        weapon = w.getWeapon(4);
        this.damage = weapon.damage;
        this.items= new ArrayList<>();

        setPos();

        speed=4;
        dir = "down";

        hitbox = new Rectangle(9*3,15*3,13*3,17*3);

        getImage();
    }

    public void setWeapon(Weapons w){
        this.weapon = w;
    }
    public void addItem(Items i){
        items.add(i);
    }

    public void update(){

        if(attacking == true){
            doAttack();
        }
        if(blocking == true){
            doBlock();
        }

        getMove();

        setAttacking();
        setBlocking();

    }

    // If movement keys entered, set direction, move player if not colliding.
    public void getMove(){

        if(k.upPress == true || k.downPress == true || k.leftPress == true || k.rightPress == true) {


            collision = false;
            s.collision.checkTile(this);
            s.collision.checkObjects(this);

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

            }else{

                // Bounce back!
                x = oX;
                y=oY;
            }

        }
    }

    // Draw sprite based on directions going/action
    public void draw(Graphics2D g){
        BufferedImage img = null;

        // Draw player
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

        // Draw weapon on player
        if(attacking == true){
            switch(dir){
                case "down":
                    g.drawImage(downAttack,x+(22),y+(36),s.tileSize,s.tileSize,null);
                    break;
                case "up":
                    g.drawImage(upAttack,x+(22),y+(36),s.tileSize,s.tileSize,null);
                    break;
                case "left":
                    g.drawImage(leftAttack,x-(53),y+(9),s.tileSize,s.tileSize,null);
                    break;
                case "right":
                    g.drawImage(rightAttack,x+(49),y+15,s.tileSize,s.tileSize,null);
                    break;
            }
        }

    }

    // Get images
    public void getImage(){

        try{

            // Moving images
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

            // Attack images
            file = new File("resources/weapons/sword.png");
            downAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordup.png");
            upAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordleft.png");
            leftAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordright.png");
            rightAttack = ImageIO.read(file);

            // Level img
            file = new File("resources/icons/level.png");
            levelimg = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    // Set attacking if enter key pressed
    public void setAttacking(){
        if(k.enterPress == true){
            attacking = true;
        }
    }
    // Set blocking if shift key pressed
    public void setBlocking(){
        if(k.shiftPress == true){
            blocking = true;
        }
    }


    // Take damage
    public void getAttack(int dmg){
        // if not blocking, take damage
        if(!blocking) {
            health -= (dmg - defense);
        }
    }

    // 25 frames of attack, runs when attacking is true, only does attack once 25 ms passed
    public void doAttack(){

        attackCount++;

        if(attackCount > 25){

            // Check if hit monster
            if (s.collision.getCollidingMonster(this) != null) {
                Entity e = s.collision.getCollidingMonster(this);
                e.updateHealth(-damage);
            }

            // Check if hit object
            if (s.collision.checkObjects(this) != null) {
                interactObject();
            }

            attackCount = 0;
            attacking = false;
        }
    }
    // Do 25 ms of blocking, then set to false
    public void doBlock(){
        blockCount++;
        if(blockCount > 25){
            blockCount = 0;
            blocking = false;
        }
    }

    void interactObject(){
        GameObject o = s.collision.checkObjects(this);
        o.giveItemToPlayer(this);
        s.state = s.text_state;
        o.write();
        o.setDestroyed();

    }

}
