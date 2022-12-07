package Entities;

import Main.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public abstract class Enemy extends Entity{

    public BufferedImage image, attackimage, hurtimage;
    public boolean isHit = false;
    int damage;
    int attackSpeed;
    int attackCount = 0;
    int hurtCount = 0;

    public void update(){

        collision = false;
        s.collision.checkTile(this);
        String dirs[] = {"up","down","left","right"};

        animCount++;

        // If got hit, display hit image and pause
        if(isHit){
            hurtCount++;
            if(hurtCount>=20){
                hurtCount = 0;
                isHit=false;
                currimage = image;
            }

        }else {

            // Move if not attacking
            if (!checkDoAttack(s.player)) {

                if (animCount > 100) {
                    Random rand = new Random();
                    int r = rand.nextInt(4);
                    dir = dirs[r];
                    animCount = 0;
                }
                randMove();

            } else {

                // Add to attack count, attack if attacking
                attackCount++;
                doAttack(s.player);
            }
        }

    }

    // Draw sprite based on directions going
    public void draw(Graphics2D g){

        g.drawImage(currimage,x,y,s.tileSize,s.tileSize,null);

        // Draw health bar if player is colliding
        if(s.collision.getIsPlayerColliding(this)) {

            // Draw HP bars
            double scale = (double) 73 / maxHealth;
            double hpBar = scale * health;

            // Outline
            g.setColor(new Color(21, 35, 10));
            g.fillRect(x + 12, y - 15, 73, 10);

            // Fill red
            g.setColor(new Color(144, 50, 40));
            g.fillRect(x + 12, y - 15, (int) hpBar, 10);

        }

    }

    // Check if close enough to player to attack
    public boolean checkDoAttack(Player p){
        // If certain dist away from player (size of 3 tile), move towards
        int dist = s.tileSize;
        double currDist = sqrt(pow((p.x - x),2) + pow((p.y - y),2));

        if(currDist <= dist){
            return true;
        }else{
            return false;
        }
    }

    // Attack player
    public void doAttack(Player p){

        // Display attack animation and then get rid of it
        if(attackCount <= 10){
            currimage = attackimage;
        }else{
            currimage = image;
        }
        // Do attack every 75
        if(attackCount >= 75){
            currimage = attackimage;
            attackCount = 0;
            p.getAttack(damage);
        }

    }

    // Move randomly
    void randMove(){

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

    // Move towards player
    void moveToPlayer(Player p){

            float xS = (p.x - x);
            float yS = (p.y - y);
            double factor = speed / Math.sqrt(xS * xS + yS * yS);
            xS *= factor;
            yS *= factor;

            x += xS;
            y += yS;

    }

    // Set if hit
    public void getHit(){
         isHit = true;
         currimage = hurtimage;
         doKnockback();
    }

    void doKnockback(){

        switch(s.player.dir){
            case "up":
                y = y-(s.tileSize/3);
                break;
            case "down":
                y = y+(s.tileSize/3);
                break;
            case "left":
                x = x-(s.tileSize/3);
                break;
            case "right":
                x = x+(s.tileSize/3);
                break;

        }

        // Check if the knockback will result in going out of bounds
        s.collision.checkTile(this);
        if(collision){
            x = oX;
            y = oY;
        }

        oX = x;
        oY = y;


    }



}

class Ghost extends Enemy {

    public Ghost(Screen s){

        this.s = s;
        health = 20;
        maxHealth = 20;
        damage = 10;
        setPos();

        speed=1;
        attackSpeed = 1;
        dir = "-";
        hitbox = new Rectangle(9,15,13,17);

        try {

            File file = new File("resources/enemies/ghost_front.png");
            File file2 = new File("resources/enemies/ghost-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
            file = new File("resources/enemies/ghost-hurt.png");
            hurtimage = ImageIO.read(file);
            currimage = image;


        }catch(IOException e){
            e.printStackTrace();
        }


    }

}

class Blob extends Enemy{

    public Blob(Screen s){

        this.s = s;
        health = 20;
        maxHealth = 20;
        damage = 10;
        setPos();

        speed=1;
        attackSpeed = 1;
        dir = "-";
        hitbox = new Rectangle(9,15,13,17);

        try {

            File file = new File("resources/enemies/blob.png");
            File file2 = new File("resources/enemies/blob-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
            file = new File("resources/enemies/blob-hurt.png");
            hurtimage = ImageIO.read(file);
            currimage = image;

        }catch(IOException e){
            e.printStackTrace();
        }


    }

}

class Isopod extends Enemy{

    public Isopod(Screen s){

        this.s = s;
        health = 20;
        maxHealth = 20;
        damage = 10;
        setPos();

        speed=1;
        attackSpeed = 2;
        dir = "-";
        hitbox = new Rectangle(9,15,13,17);

        try {

            File file = new File("resources/enemies/pillbug.png");
            File file2 = new File("resources/enemies/pullbug-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
            file = new File("resources/enemies/pillbug-hurt.png");
            hurtimage = ImageIO.read(file);
            currimage = image;

        }catch(IOException e){
            e.printStackTrace();
        }


    }



}

class Skull extends Enemy{

    public Skull(Screen s){

        this.s = s;
        health = 60;
        maxHealth = 60;
        damage = 25;
        setPos();

        speed=1;
        attackSpeed = 2;
        dir = "-";
        hitbox = new Rectangle(9,15,13,17);

        try {

            File file = new File("resources/enemies/skullkid.png");
            File file2 = new File("resources/enemies/skullkid-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
            file = new File("resources/enemies/skullkid-hurt.png");
            hurtimage = ImageIO.read(file);
            currimage = image;

        }catch(IOException e){
            e.printStackTrace();
        }


    }



}

class Head extends Enemy{

    public Head(Screen s){

        this.s = s;
        health = 100;
        maxHealth = 100;
        damage = 30;
        setPos();

        speed=1;
        attackSpeed = 2;
        dir = "-";
        hitbox = new Rectangle(9,15,13,17);

        try {

            File file = new File("resources/enemies/head.png");
            File file2 = new File("resources/enemies/head-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
            file = new File("resources/enemies/head-hurt.png");
            hurtimage = ImageIO.read(file);
            currimage = image;

        }catch(IOException e){
            e.printStackTrace();
        }


    }



}