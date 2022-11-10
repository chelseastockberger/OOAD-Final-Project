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

    public BufferedImage image;
    public BufferedImage attackimage;
    public BufferedImage currimage;
    int damage;
    int attackSpeed;
    int attackCount = 0;

    public void update(){

        collision = false;
        s.collision.checkTile(this);
        String dirs[] = {"up","down","left","right"};

        animCount++;

        // Move if not attacking
        if(!checkDoAttack(s.player)) {

            //currimage = image;
            if (animCount > 100) {
                Random rand = new Random();
                int r = rand.nextInt(4);
                dir = dirs[r];
                animCount = 0;
            }
            randMove();

        }else{
            attackCount++;
            doAttack(s.player);
        }



    }
    // Draw sprite based on directions going
    public void draw(Graphics2D g){

        g.drawImage(currimage,x,y,s.tileSize,s.tileSize,null);


        // Draw HP bars
        double scale = (double)s.tileSize/maxHealth;
        double hpBar = scale*health;

        // Outline
        g.setColor(new Color(139, 186, 101));
        g.fillRect(x-1, y-16, s.tileSize+2, 12);

        // Fill red
        g.setColor(new Color(255,30,0));
        g.fillRect(x, y-15, (int)hpBar, 12);

    }

    // Check if close to player
    public boolean checkRangetoPlayer(Player p){
        // If certain dist away from player (size of 3 tile), move towards
        int dist = s.tileSize*3;
        double currDist = sqrt(pow((p.x - x),2) + pow((p.y - y),2));

        if(currDist <= dist){
            return true;
        }else{
            return false;
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
        // Do attack every 50
        if(attackCount >= 50){
            currimage = attackimage;
            attackCount = 0;
            System.out.println("attacking");
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



}

class Ghost extends Enemy {

    public Ghost(Screen s){

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

            File file = new File("resources/enemies/ghost_front.png");
            File file2 = new File("resources/enemies/ghost-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
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
        attackSpeed = 2;
        dir = "-";
        hitbox = new Rectangle(9,15,13,17);

        try {

            File file = new File("resources/enemies/blob.png");
            File file2 = new File("resources/enemies/ghost-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
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
            File file2 = new File("resources/enemies/ghost-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
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
            File file2 = new File("resources/enemies/ghost-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
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
            File file2 = new File("resources/enemies/ghost-attack.png");
            image = ImageIO.read(file);
            attackimage = ImageIO.read(file2);
            currimage = image;

        }catch(IOException e){
            e.printStackTrace();
        }


    }



}