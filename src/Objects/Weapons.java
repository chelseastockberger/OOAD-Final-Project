package Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Weapons {

    public String name;
    public String caption;
    public int damage;
    public BufferedImage upAttack, downAttack, leftAttack, rightAttack;



}

class BasicSword extends Weapons {

    public BasicSword(){
        name = "Basic Sword";
        caption = "Just a basic sword. Deals 5 damage.";
        damage = 5;
        try{
            // Attack images
            File file = new File("resources/weapons/sword.png");
            downAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordup.png");
            upAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordleft.png");
            leftAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordright.png");
            rightAttack = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}

class SharpSword extends Weapons {

    public SharpSword(){
        name = "Sharp Sword";
        caption = "A nice, sharp sword! Deals 7 damage.";
        damage = 7;
        try{
            // Attack images
            File file = new File("resources/weapons/sword.png");
            downAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordup.png");
            upAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordleft.png");
            leftAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordright.png");
            rightAttack = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}

class CrystalSword extends Weapons {

    public CrystalSword(){
        name = "Crystal Sword";
        caption = "A beautiful crystal sword! Deals 10 damage.";
        damage = 10;
        try{
            // Attack images
            File file = new File("resources/weapons/sword.png");
            downAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordup.png");
            upAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordleft.png");
            leftAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordright.png");
            rightAttack = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}

class MagicStaff extends Weapons {

    public MagicStaff(){
        name = "Magic Staff";
        caption = "Staff enchanted with forest magic. Deals 13 damage.";
        damage = 13;
        try{
            // Attack images
            File file = new File("resources/weapons/sword.png");
            downAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordup.png");
            upAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordleft.png");
            leftAttack = ImageIO.read(file);
            file = new File("resources/weapons/swordright.png");
            rightAttack = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }



}