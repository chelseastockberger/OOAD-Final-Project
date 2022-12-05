package Main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Entities.Enemy;
import Entities.Player;
import Objects.GameObject;
import TileMap.Map;

/*
SCREEN
The beef of the program.
Runs the main game thread, updating all entities and things.
Holds most game control and elements.
Draws all the elements
 */

public class Screen extends JPanel implements Runnable{

    public Game game;

    // Settings for the window screen

    final int singleTileSize = 32; // 32 x 32 px tile
    final int scale = 3;
    public final int tileSize = singleTileSize * scale; // 48 x 48 tiles on screen
    public final int screenCols = 16;
    public final int screenRows = 10;
    final int screenWidth = tileSize * screenCols;
    final int screenHeight = tileSize * screenRows;

    // Objects/variables

    int FPS = 60;
    InputHandler input;
    public Collision collision;
    Thread thread;
    Audio audio;
    public Dialogue ui;
    ScreenUI scUI = new ScreenUI(this);

    public Map map;
    ArrayList<Enemy> enemies;
    ArrayList<GameObject> objects;
    public Player player;

    boolean portaladded = false;

    // Game state -  STATE pattern
    public State state_;

    JFrame window;



    public Screen(Game g){

        this.game = g;
        map = new Map(this);
        input = new InputHandler();
        input.s = this;
        collision = new Collision(this);
        player = new Player(this, input);
        audio = new Audio();
        ui = new Dialogue(this);
        state_ = new defaultState(this);
        playMusic();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.setFocusable(true);

    }

    public void startThread(){

        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run(){

        double paintInterval = 1000000000/FPS; // How often to repaint our little guys
        double delta = 0;
        long prevTime = System.nanoTime();
        long currTime;

        while(thread != null){

            currTime = System.nanoTime();
            delta+=(currTime - prevTime) / paintInterval;
            prevTime = currTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
        audio.stop();
    }

    // Update data
    public void update(){

        state_.doUpdate();

    }

    // Add data to screen
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g;

        map.draw(g2D);

        for(GameObject o: objects){
            o.draw(g2D);
        }

        for(Enemy e: enemies){
            if(!e.isDead()){
                e.draw(g2D);
            }
        }

        player.draw(g2D);

        scUI.draw(g2D);
        ui.draw(g2D);


        g2D.dispose();
    }

    // Gameplay

    // Check if all enemies defeated
    public boolean checkEnemiesDefeated(){

        int cnt = 0;
        for(Enemy e: enemies){
            if(e.isDead())
                cnt++;
        }

        if(cnt == enemies.size()){
            return true;
        }else{
            return false;
        }

    }

    public void loadMap(){

        map = new Map(this);

    }

    public void addPortal(){

        map.addPortal();
        portaladded = true;

    }

    public void playMusic(){
        audio.play();
        audio.loop();
    }




}