package Entities;

import Main.Screen;
import TileMap.Tile;

public class EnemyFactory {

    public Enemy getEnemy(Screen s) {

        return new Ghost(s);

    }


}
