package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public boolean upPress, downPress, leftPress, rightPress, enterPress;

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        int c = e.getKeyCode();

        if(c == KeyEvent.VK_W){
            upPress = true;
        }
        if(c == KeyEvent.VK_A){
            leftPress = true;
        }
        if(c == KeyEvent.VK_S){
            downPress = true;
        }
        if(c == KeyEvent.VK_D){
            rightPress = true;
        }
        if(c == KeyEvent.VK_ENTER){
            enterPress = true;
        }

    }
    public void keyReleased(KeyEvent e){
        int c = e.getKeyCode();

        if(c == KeyEvent.VK_W){
            upPress = false;
        }
        if(c == KeyEvent.VK_A){
            leftPress = false;
        }
        if(c == KeyEvent.VK_S){
            downPress = false;
        }
        if(c == KeyEvent.VK_D){
            rightPress = false;
        }
        if(c == KeyEvent.VK_ENTER){
            enterPress = false;
        }

    }

}
