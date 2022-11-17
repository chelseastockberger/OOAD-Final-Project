package Main;
import Entities.Enemy;
import Entities.EnemyFactory;
import Entities.Player;
import Objects.GameObject;
import Objects.ObjectFactory;
import TileMap.Map;
import TileMap.Tile;

import javax.swing.*;
import java.util.ArrayList;

public class Game {


    private static Game instance = null;

    public static Game getGame(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    Screen screen;
    JFrame window;
    public int level = 0;

    // Make new screen
    public void newGame(){

        StartMenu startMenu = new StartMenu();
        startMenu.draw();

        // Hacky way to wait until the start window is closed
        while (startMenu.frame.isDisplayable())
        {

        }

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure");

        screen = new Screen(this);
        screen.enemies = generateEnemies(1);
        screen.objects = generateObjects(1);

        window.add(screen);
        window.pack();
        window.setVisible(true);
        screen.startThread();

    }

    public ArrayList<Enemy> generateEnemies(int level){

        ArrayList<Enemy> enemies = new ArrayList<>();

        EnemyFactory ef = new EnemyFactory();

        if(level <= 2){
            for(int i=1; i<3; i++) {
                Enemy e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
        }else if(level <= 4){
            for(int i=1; i<4; i++) {
                Enemy e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
        }else if(level <= 6){
            for(int i=1; i<5; i++) {
                Enemy e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
        }

        return enemies;

    }


    public void newLevel(){

        level++;

        screen.loadMap();
        screen.enemies = generateEnemies(level);
        screen.player.setPos();
        screen.objects = generateObjects(level);

    }

    public ArrayList<GameObject> generateObjects(int level){

        ArrayList<GameObject> objs = new ArrayList<>();

        ObjectFactory obj = new ObjectFactory();
        GameObject o1 = obj.getObject(screen,"chest");
        GameObject o2 = obj.getObject(screen,"shroom");

        objs.add(o1);
        objs.add(o2);

        return objs;

    }


}
