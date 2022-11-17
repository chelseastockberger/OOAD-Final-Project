package TileMap;

import Main.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {

    public BufferedImage image;
    public boolean collision;
    int type;
    public int x,y;
    public Rectangle rect;


    public Tile(){

        rect = new Rectangle(0,0,32,32);
        type = 0;

    }


}

class Blank extends Tile {


    public Blank(){

        collision = true;

        try {

            File file = new File("resources/tiles/white.png");
            image = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }
    }



}

class Grass extends Tile {

    public Grass(){

        collision = false;

        try {

            File file = new File("resources/tiles/blank_grass.png");
            image = ImageIO.read(file);
            type = 1;

        }catch(IOException e){
            e.printStackTrace();
        }
    }


}

class Corner extends Tile {

    public Corner(char choice){

        collision = true;

        try {

            File file = new File("resources/tiles/TLouter.png");

            switch(choice){
                case 'Q':
                    file = new File("resources/tiles/TLouter.png");
                    break;
                case 'E':
                    file = new File("resources/tiles/TRouter.png");
                    break;
                case 'A':
                    file = new File("resources/tiles/BLouter.png");
                    break;
                case 'D':
                    file = new File("resources/tiles/BRouter.png");
                    break;
                case 'U':
                    file = new File("resources/tiles/TLinner.png");
                    break;
                case 'I':
                    file = new File("resources/tiles/TRinner.png");
                    break;
                case 'J':
                    file = new File("resources/tiles/BLinner.png");
                    break;
                case 'K':
                    file = new File("resources/tiles/BRinner.png");
                    break;
                case 'M':
                    file = new File("resources/tiles/midHori.png");
                    break;
                case 'V':
                    file = new File("resources/tiles/midVert.png");
                    break;
            }

            image = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

class Top extends Tile {

    public Top(){

        collision = true;

        try {

            File file = new File("resources/tiles/grass_edge-T.png");
            image = ImageIO.read(file);


        }catch(IOException e){
            e.printStackTrace();
        }
    }


}

class Bottom extends Tile {

    public Bottom(){

        collision = true;

        try {

            File file = new File("resources/tiles/grass_edge-B.png");
            image = ImageIO.read(file);


        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

class Left extends Tile {

    public Left(){
        collision = true;
        try {

            File file = new File("resources/tiles/grass_edge-L.png");
            image = ImageIO.read(file);


        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

class Right extends Tile {

    public Right(){
        collision = true;
        try {

            File file = new File("resources/tiles/grass_edge-R.png");
            image = ImageIO.read(file);


        }catch(IOException e){
            e.printStackTrace();
        }
    }

}


class Portal extends Tile {

    public Portal(){
        collision = false;
        try {

            File file = new File("resources/tiles/portal.png");
            image = ImageIO.read(file);


        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
