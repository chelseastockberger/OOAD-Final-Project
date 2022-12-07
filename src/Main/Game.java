package Main;
import Entities.Boss;
import Entities.Enemy;
import Entities.EnemyFactory;
import Entities.Player;
import Objects.GameObject;
import Objects.ObjectFactory;
import TileMap.Map;
import TileMap.Tile;

import javax.swing.*;
import java.util.ArrayList;

/*
GAME
Singleton for managing starting new game,
generating enemies and objects,
and new level
 */

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
    // CHANGE THIS to change final level (like 10 or something)
    int finallevel = 1;
    public boolean lastLevel = false;

    // Make new screen
    public void newGame(){

        StartMenu startMenu = new StartMenu();
        startMenu.draw();

        //Hacky way to wait until the start window is closed
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

    public void restartGame(){



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

    // Based on the level, generate enemies
    public ArrayList<Enemy> generateEnemies(int level){

        ArrayList<Enemy> enemies = new ArrayList<>();

        EnemyFactory ef = new EnemyFactory();

        if(level <= 2){
            for(int i=1; i<3; i++) {
                Enemy e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
        }else if(level <= 4){
            for(int i=2; i<4; i++) {
                Enemy e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
        }else if(level <= 6){
            for(int i=3; i<7; i++) {
                Enemy e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
        }

        return enemies;

    }

    // Starts a new level, sets screen enemies, player pos, and has it load new map
    public void newLevel(){

        level++;

        if(level != finallevel) {
            screen.loadMap();
            screen.enemies = generateEnemies(level);
            screen.player.setPos();
            screen.objects = generateObjects(level);
        }else{
            lastLevel = true;
            finalLevel();
        }

    }

    public void finalLevel(){

        screen.boss.x = screen.screenWidth/2-(screen.tileSize*4/2);
        screen.boss.y = 100;
        screen.audio.stop();
        screen.audio = new Audio(true);
        screen.playMusic();
        screen.loadMap();
        screen.enemies = null;
        screen.objects = null;
        screen.state_ = new bossState(this.screen);
        screen.player.x = screen.screenWidth/2;
        screen.player.y = screen.screenHeight-(screen.tileSize*2);

    }

    // Generate objects
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
