package Main;

import Entities.Enemy;
import Entities.Entity;
import Objects.GameObject;
import TileMap.Tile;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Collision {
        Screen s;
        public Collision(Screen s){
            this.s = s;
        }

        public void checkTile(Entity entity){

            if (s.map.getTileAtPos(entity.x, entity.y).collision) {
                entity.collision = true;
            }

        }

        public Entity getCollidingMonster(Entity player){

            int dist = s.tileSize;

            for(Enemy e: s.enemies){
                double currDist = sqrt(pow((player.x - e.x),2) + pow((player.y - e.y),2));
                if(currDist <= dist){
                    return e;
                }
            }

            return null;

        }

    public boolean getIsPlayerColliding(Entity e){

        int dist = s.tileSize;
        double currDist = sqrt(pow((s.player.x - e.x),2) + pow((s.player.y - e.y),2));
        if(currDist <= dist){
            return true;
        }else{
            return false;
        }

    }

        public GameObject checkObjects(Entity player){

            int dist = s.tileSize;

            for(GameObject o: s.objects){
                double currDist = sqrt(pow((player.x - o.x),2) + pow((player.y - o.y),2));
                if(currDist <= dist && o.collision){
                    return o;
                }
            }

            return null;

        }



}
