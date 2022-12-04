package Main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
STARTMENU
What appears when you load up the game.
 */


public class EndMenu{

    JFrame frame = new JFrame();
    JButton button1;
    JButton button2;
    JLabel label;
    BufferedImage img;
    Image btnimg;

    public EndMenu(){
//        try {
//            File file = new File("resources/icons/start_art.png");
//            img = ImageIO.read(file);
//            file = new File("resources/icons/button.png");
//            BufferedImage btnimgB = ImageIO.read(file);
//
//            btnimg = btnimgB.getScaledInstance(btnimgB.getWidth()*5, btnimgB.getHeight()*5, Image.SCALE_DEFAULT);
        button1 = new JButton("Quit");
        button2 = new JButton("Restart");
        label = new JLabel("Game Over");
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }

    public void draw()
    {

        // Making the frame
        //Image img_ = img.getScaledInstance(img.getWidth()*5, img.getHeight()*5, Image.SCALE_DEFAULT);
        //JLabel label = new JLabel(new ImageIcon(img_));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.add(label);
        //frame.pack();
        frame.setSize(1920,1080);

        // Add button
        button1.setBounds(55*5, 116*5, 450, 215);
        button2.setBounds(55*5 + 800,116*5,450,215);
        label.setBounds(55*5 + 600, 400,700,215);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                Game game = Game.getGame();

                game.restartGame();
            }
        });

        frame.add(button1);
        frame.getContentPane().add(button1);
        frame.add(button2);
        frame.getContentPane().add(button2);
        frame.add(label);
        frame.setLayout(null);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}