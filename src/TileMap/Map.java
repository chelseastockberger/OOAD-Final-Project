package TileMap;
import Main.Screen;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;

// 1 - blank grass | 2 - top | 3 - bottom | 4 - left | 5 - right


// This version uses premade maps made in txt file

public class Map {
    Screen s;

    // Holds each tile type
    int intmap[][];
    char charmap[][];
    public ArrayList<ArrayList<Tile>> tilemap;

    public Map(Screen s){
        this.s = s;
        newMap();
    }


    // Draw the map
    public void draw(Graphics2D g){

        for (int i = 0; i < s.screenCols; i++) {

            for (int j = 0; j < s.screenRows; j++) {

                Tile t = tilemap.get(i).get(j);

                g.drawImage(t.image, t.x, t.y,s.tileSize,s.tileSize,null);

            }
        }

    }

    // Given a coordinate, generate adjacent tiles
    ArrayList<ArrayList<Integer>> getDirections(int i, int j){

        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        arr.add(new ArrayList<>());

        int count = 0;

        if(j+1 <= s.screenRows-2){ // if can move down
            arr.add(new ArrayList<>());
            arr.get(0).add(0);
            arr.get(0).add(1);
            count++;
        }
        if(j-1 >= 2){ // if can move up
            arr.add(new ArrayList<>());
            arr.get(count).add(0);
            arr.get(count).add(-1);
            count ++;
        }

        if(i-1 >= 2){ // if can move left
            arr.add(new ArrayList<>());
            arr.get(count).add(-1);
            arr.get(count).add(0);
            count ++;
        }
        if(i+1 <= s.screenCols-3){ // if can move right
            arr.add(new ArrayList<>());
            arr.get(count).add(1);
            arr.get(count).add(0);
            count ++;
        }


        return arr;

    }

    // ------------ Map Creation --------------------

    public void newMap(){

        tilemap = new ArrayList<ArrayList<Tile>>();
        intmap = new int[s.screenCols][s.screenRows];
        charmap = new char[s.screenCols][s.screenRows];

        loadMap();
        makeTileMap();


    }

    // Load map from text file, place it into intmap
    public void loadMap(){

        try{

            File file = new File("resources/maps/rand1.txt");
            Scanner readFile = new Scanner(file);

            int col=0;
            int row=0;

            while(col < s.screenCols && row < s.screenRows){

                String line = readFile.nextLine();

                while(col<s.screenCols){
                    String chars[] = line.split(" ");
                    char c = chars[col].charAt(0);

                    charmap[col][row] = c;
                    col++;
                }
                if(col == s.screenCols){
                    col = 0;
                    row++;
                }


            }
        }catch(IOException e){
            e.printStackTrace();
        }


    }


    // Make tilemap from the map of integers
    public void makeTileMap(){

        TileFactory tF = new TileFactory();

        int x = 0;
        int y=0;

        // Initialize arraylists
        for(int i=0; i<s.screenCols; i++){
            tilemap.add(new ArrayList<>());
        }

        for (int i = 0; i < s.screenCols; i++) {

            x+=s.tileSize;
            y=0;

            for (int j = 0; j < s.screenRows; j++) {

                Tile t = tF.getTile(charmap[i][j]);
                t.x = x;
                t.y = y;

                //System.out.println(x + "," + y);

                y+=s.tileSize;

                tilemap.get(i).add(t);

            }
        }

        //System.out.println("--------");

    }

    // Get a random valid position on map
    public Tile getRandomPosition(){

        ArrayList<Tile> validSpots = new ArrayList<>();
        int cnt = 0;

        // Put all valid positions into an array
        for (int i = 0; i < s.screenCols; i++) {
            for (int j = 0; j < s.screenRows; j++) {

                if (tilemap.get(i).get(j).type == 1) {

                    validSpots.add(tilemap.get(i).get(j));
                    cnt++;


                }


            }
        }

        // Get random element from that array
        Random rand = new Random();
        int randIndex = rand.nextInt(cnt);
        Tile randPos = validSpots.get(randIndex);
        return randPos;

    }

    public Tile getTileAtPos(int x, int y){

        Tile tile = null;

        for (int i = 0; i < s.screenCols; i++) {
            for (int j = 0; j < s.screenRows; j++) {
                Tile t = tilemap.get(i).get(j);
                if (t.x <= x && t.x+s.tileSize >= x && t.y <= y && t.y+s.tileSize >= y) {

                    tile = t;

                }


            }
        }

        if(tile == null){
            System.out.println("noo");
        }
       return tile;



    }

    // -----------------------------------------------------------




}