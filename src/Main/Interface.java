package Main;

import java.awt.*;
import java.util.ArrayList;

public class Interface {

    Screen s;
    Graphics2D g;
    Font font, bigfont;
    public String text = "";
    public String currtext = "";
    //public String textArr[];
    public ArrayList<String> textArr;
    public int textIndex;

    public Interface(Screen s){
        this.s = s;

        font = new Font("Arial", Font.PLAIN, 20);
        bigfont = new Font("Arial", Font.BOLD,80);
    }

    public void showText(String text){
        this.text = text;
    }

    public void draw(Graphics2D g){
        this.g = g;

        g.setFont(font);
        g.setColor(Color.white);

        if(s.state == s.default_state){

        }
        if(s.state == s.pause_state){

        }
        if(s.state == s.text_state){

            textInterface();

        }

    }

    public void textInterface(){
        int y = s.screenHeight - s.tileSize*3;
        int width = s.screenWidth - (s.tileSize * 6);
        int height = s.tileSize*2;
        int x = s.screenWidth/2-(width/2);

        textWindow(x,y,width,height);

        g.setFont(g.getFont().deriveFont(Font.PLAIN));
        x+=s.tileSize;
        y+=s.tileSize;
        g.drawString(currtext,x,y);

    }

    public void textWindow(int x,int y, int width, int height){

        Color c = new Color(10, 22, 5, 157);
        g.setColor(c);
        g.fillRoundRect(x,y,width,height,40,40);

        c = new Color(225, 247, 210);
        g.setColor(c);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(x+5,y+5,width-10,height-10,30,30);

    }

    public void pauseInterface(){
        g.setFont(g.getFont().deriveFont(Font.PLAIN,80F));
        String str = "| |";
        int x = s.screenWidth/2;
        int y = s.screenHeight/2;
        g.drawString(str,x,y);
    }



}
