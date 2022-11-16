package Objects;

import java.awt.image.BufferedImage;

public class Items {

    public BufferedImage image;
    public String name = "";
    public String caption = "";
    int timeHeld;


}

// Heal 40 health
class Potion extends Items {

    public Potion(){
        name = "Potion";
        caption = "You gained 40 health!";
    }




}

// Damage received reduced by 2
class Hat extends Items {

    public Hat(){
        name = "Cool Hat";
        caption = "You put on a cool hat. Damage received reduced by 2 pts!";
    }


}

// Take 20 damage
class Poison extends Items {

    public Poison(){
        name = "Poison";
        caption = "Yuck! You took 20 damage.";
    }


}

// Raises damage given by 5
class UltraStrength extends Items {

    public UltraStrength(){
        name = "Ultra Strength";
        caption = "Feeling pumped! You now deal 5 extra damage to enemies.";
    }



}

