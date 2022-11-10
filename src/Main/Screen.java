package Main;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import Entities.Enemy;
import Entities.EnemyFactory;
import Entities.Player;
import TileMap.Map;

public class Screen extends JPanel implements Runnable{

    Game game;

    // Settings for the window screen

    final int singleTileSize = 32; // 32 x 32 px tile
    final int scale = 3;
    public final int tileSize = singleTileSize * scale; // 48 x 48 tiles on screen
    public final int screenCols = 16;
    public final int screenRows = 10;
    final int screenWidth = tileSize * screenCols;
    final int screenHeight = tileSize * screenRows;

    // Objects/variables

    public Map map;
    InputHandler input;
    public Collision collision;
    Thread thread;
    int FPS = 60;
    ArrayList<Enemy> enemies;
    public Player player;
    boolean portaladded = false;



    public Screen(Game g){
        this.game = g;
        map = new Map(this);
        input = new InputHandler();
        collision = new Collision(this);
        player = new Player(this, input);

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
    }

    // Update data
    public void update(){

       player.update();

       for(Enemy e: enemies){
           if(!e.isDead()){
               e.update();
           }
       }

       if(checkEnemiesDefeated()){
           if(!portaladded)
                addPortal();
       }
       if(portaladded){
           if (player.x <= map.portal.x && player.x+tileSize >= map.portal.x && player.y <= map.portal.y && player.y+tileSize >= map.portal.y) {

               portaladded = false;
               game.newLevel();

           }
       }

    }

    // Add data to screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g;

        map.draw(g2D);
        player.draw(g2D);

        for(Enemy e: enemies){
            if(!e.isDead()){
                e.draw(g2D);
            }
        }

        g2D.dispose();
    }

    // Gameplay

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




}
