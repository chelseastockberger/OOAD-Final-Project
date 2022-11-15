package Objects;

import Main.Screen;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mushroom extends GameObject {

    public Mushroom(Screen s){

        this.s = s;
        setPos();

        name = "mushroom";

        try {

            File file = new File("resources/objects/magicshroom.png");
            image = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
