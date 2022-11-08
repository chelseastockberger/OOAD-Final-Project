package Main;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import Entities.Enemy;
import Entities.EnemyFactory;
import Entities.Player;
import TileMap.Map;

public class Screen extends JPanel implements Runnable{

    // Settings for the window screen

    final int singleTileSize = 32; // 32 x 32 px tile
    final int scale = 3;
    public final int tileSize = singleTileSize * scale; // 48 x 48 tiles on screen
    public final int screenCols = 16;
    public final int screenRows = 10;
    final int screenWidth = tileSize * screenCols;
    final int screenHeight = tileSize * screenRows;

    // Objects/variables

    public Map map = new Map(this);
    InputHandler input = new InputHandler();
    Thread thread;
    int FPS = 60;
    public Collision collision = new Collision(this);
    ArrayList<Enemy> enemies;
    Player player = new Player(this, input);
    EnemyFactory ef = new EnemyFactory();
    Enemy e = ef.getEnemy(this);


    public Screen(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.setFocusable(true);
    }

    public void setEnemies(ArrayList<Enemy> enemies){

        this.enemies = enemies;

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
       e.update();


    }

    // Add data to screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g;

        map.draw(g2D);
        player.draw(g2D);
        e.draw(g2D);

        g2D.dispose();
    }

}
