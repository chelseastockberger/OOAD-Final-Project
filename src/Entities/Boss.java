package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Boss extends Entity {

    public BufferedImage image;
    public BufferedImage attackimage;
    public BufferedImage currimage;

    public Boss(){

        try {

            File file = new File("resources/enemies/boss.png");
            image = ImageIO.read(file);
            currimage = image;

        }catch(IOException e){
            e.printStackTrace();
        }




    }



}
