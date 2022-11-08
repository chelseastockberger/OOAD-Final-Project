package Main;
import Entities.Enemy;
import Entities.EnemyFactory;
import Entities.Player;
import TileMap.Map;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    Screen screen;
    JFrame window;
    int level = 0;

    // Make new screen
    public void newGame(){

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure");

        screen = new Screen();
        window.add(screen);
        window.pack();
        window.setVisible(true);
        screen.startThread();

    }

    public void newLevel(int level){




    }

}
