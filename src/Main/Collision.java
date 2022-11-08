package Main;

import Entities.Entity;
import TileMap.Tile;

public class Collision {
        Screen s;
        public Collision(Screen s){
            this.s = s;
        }

        public void checkTile(Entity entity){


            if (s.map.getTileAtPos(entity.x, entity.y).collision) {
                entity.collision = true;
            }




            /*
            // Get position of entity left, right, top, and bottom
            int lX = entity.x + entity.hitbox.x;
            int rX = entity.x + entity.hitbox.x + entity.hitbox.width;
            int tY = entity.y + entity.hitbox.y;
            int bY = entity.y + entity.hitbox.y + entity.hitbox.height;

            // Get the tile at the pos left, right, top, bottom
            int leftCol = lX/s.tileSize;
            int rightCol = rX/s.tileSize;
            int upRow = tY/s.tileSize;
            int downRow = bY/s.tileSize;

            Tile tile1, tile2;

            // get the tile they will be going towards
            switch(entity.dir){
                case "up":
                    upRow = (tY - entity.speed)/s.tileSize;
                    tile1 = s.map.tilemap.get(leftCol).get(upRow);
                    tile2 = s.map.tilemap.get(rightCol).get(upRow);
                    if(tile1.collision || tile2.collision){
                        entity.collision = true;
                    }
                    break;
                case "down":
                    downRow = (bY + entity.speed)/s.tileSize;
                    tile1 = s.map.tilemap.get(leftCol).get(downRow);
                    tile2 = s.map.tilemap.get(rightCol).get(downRow);
                    if(tile1.collision || tile2.collision){
                        entity.collision = true;
                    }
                    break;
                case "left":
                    leftCol = (lX - entity.speed)/s.tileSize;
                    tile1 = s.map.tilemap.get(leftCol).get(upRow);
                    tile2 = s.map.tilemap.get(leftCol).get(downRow);
                    if(tile1.collision || tile2.collision){
                        entity.collision = true;
                    }
                    break;
                case "right":
                    rightCol = (rX + entity.speed)/s.tileSize;
                    tile1 = s.map.tilemap.get(rightCol).get(upRow);
                    tile2 = s.map.tilemap.get(rightCol).get(downRow);
                    if(tile1.collision || tile2.collision){
                        entity.collision = true;
                    }
                    break;

            }

             */

        }



}
