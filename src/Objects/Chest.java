package Objects;

import Main.Screen;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Chest extends GameObject {

    public Chest(Screen s){

        name = "chest";
        this.s = s;
        setPos();

        try {

            File file = new File("resources/objects/chest.png");
            image = ImageIO.read(file);
            file = new File("resources/objects/chest-open.png");
            destroyedimage = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
