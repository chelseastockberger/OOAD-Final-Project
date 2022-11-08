package TileMap;

public class TileFactory {

    public Tile getTile(char type){

        switch(type){
            case '-':
                return new Blank();
            case 'G':
                return new Grass();
            case 'T':
                return new Top();
            case 'B':
                return new Bottom();
            case 'L':
                return new Left();
            case 'R':
                return new Right();
            default:
                return new Corner(type);
        }

    }

}
